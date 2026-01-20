import java.util.Scanner;

public class Main {
	private static final int MOD = 1_000_000_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		sc.close();

		long sum1 = fibonacci(b + 2);
		long sum2 = fibonacci(a + 1);

		long answer = (sum1 - sum2 + MOD) % MOD;

		System.out.println(answer);
	}

	private static Matrix power(Matrix base, long e) {
		if (e == 0) return Matrix.identity();
		if (e == 1) return base;

		Matrix half = power(base, e / 2);
		Matrix result = multiply(half, half);

		if (e % 2 == 1)
			result = multiply(result, base);

		return result;
	}

	private static long fibonacci(long n) {
		if (n == 0) return 0;
		if (n == 1 || n == 2) return 1;

		Matrix base = Matrix.fibonacci();
		Matrix result = power(base, n - 1);

		return result.matrix[0][0];
	}

	static Matrix multiply(Matrix a, Matrix b) {
		Matrix result = new Matrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					long temp = (a.matrix[i][k] % MOD) * (b.matrix[k][j] % MOD) % MOD;
					result.matrix[i][j] = (result.matrix[i][j] + temp) % MOD;
				}
			}
		}

		return result;
	}

	// 2 * 2 행렬
	private static class Matrix {
		private long[][] matrix;

		Matrix() {
			matrix = new long[2][2];
			matrix[0][0] = matrix[0][1] = matrix[1][0] = matrix[1][1] = 0;
		}

		static Matrix identity() {
			Matrix m = new Matrix();
			m.matrix[0][0] = m.matrix[1][1] = 1;

			return m;
		}

		static Matrix fibonacci() {
			Matrix m = new Matrix();
			m.matrix[0][0] = m.matrix[0][1] = m.matrix[1][0] = 1;
			m.matrix[1][1] = 0;

			return m;
		}
	}
}