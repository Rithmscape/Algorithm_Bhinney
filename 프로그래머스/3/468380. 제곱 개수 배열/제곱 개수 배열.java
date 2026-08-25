import java.util.*;

class Solution {
    public static long[] solution(int[] arr, long l, long r) {
		long[] cums = new long[arr.length + 1]; // 누적 길이 합
		long[] squares = new long[arr.length + 1]; // 누적 제곱 합

		for (int i = 1; i <= arr.length; i++) {
			cums[i] = cums[i - 1] + arr[i - 1];
			squares[i] = squares[i - 1] + ((long)arr[i - 1] * arr[i - 1]);
		}

		long k = prefix(r, arr, cums, squares) - prefix(l - 1, arr, cums, squares);
		long len = r - l + 1;
		long max = cums[arr.length] - len + 1;

		TreeSet<Long> set = new TreeSet<>();
		set.add(1L);
		set.add(max + 1);

		for (int i = 0; i < arr.length; i++) {
			long s = cums[i] + 1;
			if (s >= 1 && s <= max)
				set.add(s);
		}

		for (int j = 0; j < arr.length; j++) {
			long s = cums[j] + 1 - len;
			if (s >= 1 && s <= max)
				set.add(s);
		}

		Long[] pts = set.toArray(new Long[0]);
		long c = 0;

		for (int i = 0; i + 1 < pts.length; i++) {
			long low = pts[i];
			long high = pts[i + 1] - 1;

			if (high < low || low > max) continue;
			if (high > max ) high = max;

			long f = window(low, len, arr, cums, squares);

			if (high == low) {
				if (f == k) c++;
				continue;
			}

			long d = arr[find(low + len, cums, arr.length) - 1] - arr[find(low, cums, arr.length) - 1];
			if (d == 0) {
				if (f == k) c += (high - low + 1);
			} else {
				long dif = k - f;
				if (dif % d == 0) {
					long t = dif / d;
					if (t >= 0 && t <= (high - low)) c++;
				}
			}
		}

		return new long[] {k, c};
	}

	private static long prefix(long num, int[] arr, long[] cums, long[] squares) { // 이전의 합
		if (num <= 0) return 0;
		int l = block(num, arr.length, cums);

		return squares[l - 1] + (num - cums[l - 1]) * arr[l - 1];
	}

	private static int block(long num, int len, long[] cums) {
		int l = 1, h = len;
		while (l < h) {
			int mid = (l + h) >>> 1;
			if (cums[mid] >= num) h = mid;
			else l = mid + 1;
		}

		return l;
	}

	private static long window(long num, long len, int[] arr, long[] cums, long[] squares) {
		return prefix(num + len - 1, arr, cums, squares)
			- prefix(num - 1, arr, cums, squares);
	}

	private static int find(long p, long[] cums, int len) {
		int lo = 1, hi = len;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (cums[mid] >= p) hi = mid;
			else lo = mid + 1;
		}
		return lo;
	}
}