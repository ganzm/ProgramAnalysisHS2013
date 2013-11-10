package ch.ethz.pa;

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
		System.exit(intMain(args));
	}

	public static int intMain(String[] args) {
		initLogging(args);
		logger.info("Logger initialized");

		if (args.length < 1) {
			System.err.println("Usage: java -classpath soot-2.5.0.jar:./bin ch.ethz.pa.Verifier <class to test>");
			return -1;
		}

		String analyzedClass = args[0];
		SootClass c = loadClass(analyzedClass);

		/* Use the following to iterate over the class methods. */
		for (SootMethod method : c.getMethods()) {
			Analysis analysis = new Analysis(new BriefUnitGraph(method.retrieveActiveBody()));
			analysis.run();
			// ....
		}

		
		// TODO this will eventually be replaced by more sensible output
		System.out.print(PROGRAM_IS_SAFE);

		return 0;
	}

	private static void initLogging(String[] args) {
		boolean isDebug = false;
		for (String arg : args) {
			if ("-d".equals(arg) || "-dbg".equals(arg)) {
				isDebug = true;
			}
		}

		if (isDebug) {
			LoggerUtil.iniDebug();
		} else {
			LoggerUtil.iniSilent();
		}
	}

	private static SootClass loadClass(String name) {
		SootClass c = Scene.v().loadClassAndSupport(name);
		c.setApplicationClass();
		return c;
	}
}
