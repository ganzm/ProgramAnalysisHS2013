package ch.ethz.pa;

import java.util.List;
import java.util.logging.Logger;

import soot.Unit;
import soot.Value;
import soot.jimple.BinopExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.GeExpr;
import soot.jimple.GotoStmt;
import soot.jimple.GtExpr;
import soot.jimple.IfStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LeExpr;
import soot.jimple.LtExpr;
import soot.jimple.NeExpr;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;
import ch.ethz.pa.pairs.PairEqual;
import ch.ethz.pa.pairs.PairGreaterEqual;
import ch.ethz.pa.pairs.PairGreaterThan;
import ch.ethz.pa.pairs.PairLowerEqual;
import ch.ethz.pa.pairs.PairLowerThan;
import ch.ethz.pa.pairs.PairNotEqual;

/**
 * Implement your numerical analysis here.
 */
public class Analysis extends ForwardBranchedFlowAnalysis<IntervalPerVar> {

	private final Logger logger = Logger.getLogger(Analysis.class.getSimpleName());

	private final ProblemReport problemReport;
	private final DefinitionStmtAnalyzer definitionStmtAnalyzer;
	private final IntervalPerVarHistory<Unit> intervalPerVarHistory;

	public Analysis(UnitGraph g) {
		super(g);

		problemReport = new ProblemReport();
		definitionStmtAnalyzer = new DefinitionStmtAnalyzer(problemReport);
		intervalPerVarHistory = new IntervalPerVarHistory<Unit>();

		logger.info("UnitGraph: " + g.toString());
	}

	void run() {
		doAnalysis();
	}

	static void unhandled(String what) {
		System.err.println("Can't handle " + what);
		System.exit(1);
	}

	@Override
	protected void flowThrough(IntervalPerVar current, Unit op, List<IntervalPerVar> fallOut, List<IntervalPerVar> branchOuts) {

		// entrance check: do nothing on unreachable code
		// (unreachable is where any intervals are empty)
		if (current.hasEmptyIntervals()) {
			copyToMany(current, fallOut);
			copyToMany(current, branchOuts);
			return;
		}

		// This could be optimized.
		logger.info("Operation: " + op + "   - " + op.getClass().getName() + "\n      state: " + current);

		Stmt s = (Stmt) op;
		IntervalPerVar fallState = new IntervalPerVar();
		fallState.copyFrom(current);
		IntervalPerVar branchState = new IntervalPerVar();
		branchState.copyFrom(current);

		if (s instanceof DefinitionStmt) {
			definitionStmtAnalyzer.analyze(current, (DefinitionStmt) s, fallState);
		}

		else if (s instanceof InvokeStmt) {
			flowThroughJInvokeStmt(current, (InvokeStmt) s);
		}

		else if (s instanceof IfStmt) {

			IfStmt is = (IfStmt) s;
			Value condition = is.getCondition();

			if (condition instanceof BinopExpr) {

				BinopExpr binopExpr = (BinopExpr) condition;

				Value a1 = binopExpr.getOp1();
				Value a2 = binopExpr.getOp2();

				if (condition instanceof GeExpr) {
					new PairGreaterEqual(a1, a2, current).restrict(branchState);
					new PairLowerThan(a1, a2, current).restrict(fallState);
				}

				else if (condition instanceof GtExpr) {
					new PairGreaterThan(a1, a2, current).restrict(branchState);
					new PairLowerEqual(a1, a2, current).restrict(fallState);
				}

				else if (condition instanceof LtExpr) {
					new PairLowerThan(a1, a2, current).restrict(branchState);
					new PairGreaterEqual(a1, a2, current).restrict(fallState);
				}

				else if (condition instanceof NeExpr) {
					new PairNotEqual(a1, a2, current).restrict(branchState);
					new PairEqual(a1, a2, current).restrict(fallState);
				}

				else if (condition instanceof LeExpr) {
					new PairLowerEqual(a1, a2, current).restrict(branchState);
					new PairGreaterThan(a1, a2, current).restrict(fallState);
				}

				else
					throw new RuntimeException("unhandled binop condition: " + condition);
			}

			else
				throw new RuntimeException("unhandled condition: " + op);
		}

		else if (s instanceof ReturnVoidStmt) {
			logger.warning("ignoring return void statement: " + op);
		}

		else if (s instanceof GotoStmt) {
			logger.warning("ignoring goto statement: " + op);
		}

		else {
			throw new RuntimeException("unhandled statement: " + op);
		}

		copyToMany(fallState, fallOut);
		copyToMany(branchState, branchOuts);
	}

	/**
	 * Transfers the {@link source} store to each of the {@link targets}.
	 * 
	 * @param source
	 * @param targets
	 */
	private void copyToMany(IntervalPerVar source, List<IntervalPerVar> targets) {
		for (IntervalPerVar fnext : targets) {
			if (source != null) {
				fnext.copyFrom(source);
			}
		}
	}

	private void flowThroughJInvokeStmt(IntervalPerVar current, InvokeStmt s) {
		// A method is called. e.g. AircraftControl.adjustValue

		// You need to check the parameters here.
		InvokeExpr expr = s.getInvokeExpr();
		if (expr.getMethod().getName().equals("adjustValue")) {
			// Check that is the method from the AircraftControl class.
			if (expr.getMethod().getDeclaringClass().getName().equals("AircraftControl")) {
				problemReport.checkInterval(expr.getArg(0), Config.legalSensorInterval, current, s);
				problemReport.checkInterval(expr.getArg(1), Config.legalValueInterval, current, s);
			}
		}
	}

	@Override
	protected void copy(IntervalPerVar source, IntervalPerVar dest) {
		dest.copyFrom(source);
	}

	/**
	 * Creates an empty store of type {@link IntervalPerVar}.
	 * <p/>
	 * 
	 * Our convention is that an {@link Interval} is only considered <b>Bottom</b> if it equals the
	 * {@link Interval#EMPTY_INTERVAL}.
	 */
	@Override
	protected IntervalPerVar entryInitialFlow() {
		return new IntervalPerVar();
	}

	@Override
	protected void merge(IntervalPerVar src1, IntervalPerVar src2, IntervalPerVar trg) {

		trg.copyFrom(src1);
		trg.mergeWith(src2);

		logger.info(String.format("Merge:\n    %s\n    %s\n    ============\n    %s\n", src1.toString(), src2.toString(), trg.toString()));
	}

	@Override
	protected void merge(Unit op, IntervalPerVar src1, IntervalPerVar src2, IntervalPerVar trg) {
		merge(src1, src2, trg);
		intervalPerVarHistory.recordAndConsiderWidening(op, trg);
	}

	@Override
	protected IntervalPerVar newInitialFlow() {
		return new IntervalPerVar();
	}

	/**
	 * Returns a list with problems found during analysis.
	 * 
	 * @return
	 */
	public List<String> getProblems() {
		return problemReport.getProblems();
	}
}
