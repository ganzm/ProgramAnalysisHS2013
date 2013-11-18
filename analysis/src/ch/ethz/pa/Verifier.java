package ch.ethz.pa;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.toolkits.graph.BriefUnitGraph;
import ch.ethz.pa.logging.LoggerUtil;

public class Verifier {

	private static final Logger logger = Logger.getLogger(Verifier.class.getSimpleName());

	public static final String PROGRAM_IS_SAFE = "Program is SAFE";
	public static final String PROGRAM_IS_UNSAFE = "Program is UNSAFE";

	public static void main(String[] args) {
		LoggerUtil.iniSilent();

		if (args.length < 1) {
			System.err.println("Usage: java -classpath soot-2.5.0.jar:./bin ch.ethz.pa.Verifier <class to test>");
			System.exit(-1);
		}

		List<String> result = intMain(args);

		System.out.print(result.size() == 0 ? PROGRAM_IS_SAFE : PROGRAM_IS_UNSAFE);
		System.exit(0);
	}

	public static List<String> intMain(String[] args) {

		String analyzedClass = args[0];
		SootClass c = loadClass(analyzedClass);

		logger.info("Analyzing Class " + c);

		List<String> problemsFound = new LinkedList<String>();

		/* Use the following to iterate over the class methods. */
		for (SootMethod method : c.getMethods()) {
			Analysis analysis = new Analysis(new BriefUnitGraph(method.retrieveActiveBody()));
			analysis.run();
			problemsFound.addAll(analysis.getProblems());
		}

		return problemsFound;
	}

	private static SootClass loadClass(String name) {
		SootClass c = Scene.v().loadClassAndSupport(name);
		c.setApplicationClass();
		return c;
	}
}
