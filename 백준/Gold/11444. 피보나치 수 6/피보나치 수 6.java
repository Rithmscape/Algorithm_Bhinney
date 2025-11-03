import java.util.Scanner;

public class Main {
	final static long MOD = 1_000_000_007;
	public static void main(String[] args) {
		long n = input();
		long[][] result = solution(new long[][]{{1, 1}, {1, 0}}, n);
		System.out.println(result[0][1]);
	}

	private static long[][] solution(long[][] matrix, long exp) {
		if (exp == 1) return matrix;

		long[][] half = solution(matrix, exp / 2);
		long[][] result = multiply(half, half);

		if (exp % 2 == 1)
			result = multiply(result, matrix);

		return result;
	}

	private static long[][] multiply(long[][] a, long[][] b) {
		long[][] result = new long[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;

		return result;
	}

	private static long input() {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		sc.close();

		return n;
	}
}