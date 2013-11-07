package ch.ethz.pa.logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class LoggerUtil {

	/**
	 * Debug Log configuration properties (key=value)
	 */
	//@formatter:off
	private static final String debugLloggingProperties = ""
			+ "handlers= java.util.logging.ConsoleHandler\n" 
			+ ".level= ALL\n";
	//@formatter:on

	/**
	 * Debug Log configuration properties (key=value)
	 */
	//@formatter:off
	private static final String silentLoggingProperties = ""
			+ "handlers=java.util.logging.ConsoleHandler\n" 
			+ ".level=SEVERE\n";
	//@formatter:on

	public static void iniDebug() {
		iniFromConfigString(debugLloggingProperties);
		initStdOut();
	}

	public static void iniSilent() {
		iniFromConfigString(silentLoggingProperties);

	}

	public static void iniFromConfigString(String configString) {
		ByteArrayInputStream ins = null;
		try {

			ins = new ByteArrayInputStream(configString.getBytes());
			LogManager mgr = LogManager.getLogManager();
			mgr.readConfiguration(ins);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Replaces ConsoleLogger with stdout logger
	 */
	public static void initStdOut() {

		Logger rootLogger = Logger.getGlobal().getParent();
		Handler[] handlers = rootLogger.getHandlers();

		for (Handler handler : handlers) {
			if (handler instanceof ConsoleHandler) {
				ConsoleHandler cHandler = (ConsoleHandler) handler;

				// create new handler
				StreamHandler stdOutHandler = new StreamHandler(System.out, cHandler.getFormatter()) {
					@Override
					public synchronized void publish(LogRecord record) {
						super.publish(record);
						flush();
					}
				};
				stdOutHandler.setLevel(rootLogger.getLevel());

				// replace handler
				rootLogger.addHandler(stdOutHandler);
				rootLogger.removeHandler(cHandler);

				return;
			}
		}
	}
}
