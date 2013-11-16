package ch.ethz.pa;

import ch.ethz.pa.intervals.Interval;

/**
 * Keep any kind of global configuration.
 */
public class Config {

	public static final Interval legalSensorInterval = new Interval(0, 15);
	public static final Interval legalValueInterval = new Interval(-999,999);

}
