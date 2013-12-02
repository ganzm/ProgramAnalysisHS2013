package ch.ethz.pa.intervals;

public class BitVariant implements Comparable<BitVariant> {

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

	@Override
	public int compareTo(BitVariant o) {
		if (o == null)
			throw new NullPointerException();
		if (o == this)
			return 0;
		int maskCompare = Integer.compare(this.mask, mask);
		if (maskCompare != 0)
			return maskCompare;
		int bitsCompare = Integer.compare(this.bits, bits);
		return bitsCompare;
	}
}
