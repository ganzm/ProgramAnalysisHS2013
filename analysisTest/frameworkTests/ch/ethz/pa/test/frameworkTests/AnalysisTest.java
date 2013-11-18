package ch.ethz.pa.test.frameworkTests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import soot.Modifier;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.VoidType;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.toolkits.graph.BriefUnitGraph;
import ch.ethz.pa.Analysis;
import ch.ethz.pa.StateContainer;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

public class AnalysisTest {

	protected final JimpleBody simpleBody = createSimpleBody();

	/**
	 * Provides a body that consists of only a return statement.
	 * 
	 * @return
	 */
	private static JimpleBody createSimpleBody() {
		// setup global object
		Scene.v().loadClassAndSupport("java.lang.Object");

		// setup class
		SootClass sClass = new SootClass("MyClass", Modifier.PUBLIC);
		sClass.setSuperclass(Scene.v().getSootClass("java.lang.Object"));
		Scene.v().addClass(sClass);

		// setup method
		SootMethod method = new SootMethod("myMethod", Arrays.asList(new Type[] {}), VoidType.v(), Modifier.PUBLIC | Modifier.STATIC);
		sClass.addMethod(method);

		// setup body
		JimpleBody body = Jimple.v().newBody(method);
		method.setActiveBody(body);

		// add return stmt
		body.getUnits().add(Jimple.v().newReturnVoidStmt());
		return body;
	}

	Analysis analysis;

	@Before
	public void setup() {
		analysis = new Analysis(new BriefUnitGraph(simpleBody));

	}

	@Test
	public void testInitialErrorFeedback() {
		List<String> problems = analysis.getProblems();
		Assert.assertNotNull("expected a list", problems);
		Assert.assertEquals("expected an empty list", 0, problems.size());
	}

	private class AnalysisWithPublicMerge extends Analysis {
		public AnalysisWithPublicMerge() {
			super(new BriefUnitGraph(simpleBody));
		}

		@Override
		public void merge(StateContainer src1, StateContainer src2, StateContainer trg) {
			super.merge(src1, src2, trg);
		}

	}

	@Test
	public void testMergeDisjunct() {
		StateContainer st1 = new StateContainer();
		StateContainer st2 = new StateContainer();

		AnalysisWithPublicMerge analysis = new AnalysisWithPublicMerge();

		st1.getIntervalPerVar().putIntervalForVar("A", new Interval(1));
		st2.getIntervalPerVar().putIntervalForVar("A", new Interval(3));

		StateContainer trgContainer = new StateContainer();

		analysis.merge(st1, st2, trgContainer);

		IntervalPerVar trg = trgContainer.getIntervalPerVar();
		Assert.assertTrue(trg.getIntervalForVar("A").covers(new Interval(1)));
		Assert.assertTrue(trg.getIntervalForVar("A").covers(new Interval(3)));
	}

}
