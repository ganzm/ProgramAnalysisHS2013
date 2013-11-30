package ch.ethz.pa.intervals;

public class BitVariant {

	/**
	 * The mask for the bits that this variant knows about. Any bit set in the mask is fully
	 * determined by this variant.
	 */
	public final int mask;

	/**
	 * The actual bits set by this variant.
	 */
	public final int bits;

	protected BitVariant(int mask, int bits) {
		this.mask = mask;
		this.bits = bits;
	}
}
