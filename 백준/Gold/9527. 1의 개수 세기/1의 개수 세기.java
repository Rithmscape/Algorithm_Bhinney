import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		long[] input = input();
		long answer = solution(input[0], input[1]);
        output(answer);
	}

	/*
	 * 1000 -> 1001 -> 1010 -> 1011 -> 1100 -> 1101 -> 1110 -> 1111
	 * 1개 2개 2개 3개 2개 3개 3개 4개
	 * 10000 -> 10001 -> 10010 -> 10011
	   -> 10100 -> 10101 -> 10110 -> 10111
	   -> 11000 -> 11001 ->11010 -> 11011
	   -> 11100 -> 11101 -> 11110 -> 11111
	 * 1 2 2 3 /  2 3 3 4 /  2 3 3 4 / 3 4 4 5
	 */

	private static long solution(long a, long b) {
		long[] bit = new long[55];
		bit[0] = 0;
		for (int i = 1; i < 55; i++)
			bit[i] = 2 * bit[i - 1] + (1L << (i - 1));

		long cnt1 = getBitCount(a - 1, bit);
		long cnt2 = getBitCount(b, bit);

		return cnt2 - cnt1;
	}

	private static long getBitCount(long n, long[] bit) {
        if (n <= 0) return 0;

        long answer = n & 1;

		for (int i = 54; i > 0; i--) {
			if ((n & (1L << i)) > 0L) {
				answer += bit[i] + (n - (1L << i) + 1);
				n -= (1L << i);
			}
		}

		return answer;
	}

	private static long[] input() {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();

		return new long[]{a, b};
	}
	
	private static void output(long answer) {
		System.out.println(answer);
	}
}
