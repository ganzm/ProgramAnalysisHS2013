package ch.ethz.pa;

public class BinaryUtil {

	public static String toBinString(int intVal) {
		StringBuilder sb = new StringBuilder();

		int mask = 1 << 31;
		for (int i = 0; i < 32; i++) {
			if ((intVal & mask) != 0) {
				sb.append("1");
			} else {
				sb.append("0");
			}

			// shift mask
			mask = mask >>> 1;
		}
		return sb.toString();
	}

	public static int fromBinString(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result = result << 1;

			char c = s.charAt(i);
			if (c == '1') {
				result = result | 1;
			}

		}

		return result;
	}
}
