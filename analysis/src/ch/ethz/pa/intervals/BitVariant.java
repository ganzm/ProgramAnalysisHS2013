package ch.ethz.pa.intervals;

final public class BitVariant implements Comparable<BitVariant> {

	/**
	 * The mask for the bits that this variant knows about. Any bit set in the mask is fully
	 * determined by this variant.
	 */
	public final int mask;

	/**
	 * The actual bits set by this variant.
	 */
	public final int bits;

	public BitVariant(int mask, int bits) {
		if ((bits & ~mask) != 0)
			throw new RuntimeException("excessive bits");
		this.mask = mask;
		this.bits = bits;
	}

	public Interval toInterval() {
		if (mask == 0)
			return Interval.TOP_INTERVAL;
		return new Interval(bits & mask, bits | ~mask);
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

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof BitVariant))
			return false;
		BitVariant obv = (BitVariant) o;
		return mask == obv.mask && bits == obv.bits;
	}

	@Override
	public String toString() {
		return "[mask: " + Integer.toBinaryString(mask) + ", bits: " + Integer.toBinaryString(bits) + "]";
	}
}
