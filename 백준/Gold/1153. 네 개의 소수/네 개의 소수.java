import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		if (n < 8) {
			System.out.println(-1);
			return;
		}

		boolean[] primes = findPrime(n + 1);

		if (n % 2 == 0) {
			int target = n - 4;
			for (int p = 2; p <= target / 2; p++) {
				if (primes[p] && primes[target - p]) {
					System.out.println(2 + " " + 2 + " " + p + " " + (target - p));
					return;
				}
			}
		} else {
			if (n - 6 >= 2 && primes[n - 6]) {
				System.out.println("2 2 2 " + (n - 6));
				return;
			}

			int target = n - 5;  // 짝수
			for (int q = 2; q <= target / 2; q++) {
				if (primes[q] && primes[target - q]) {
					System.out.println("2 3 " + q + " " + (target - q));
					return;
				}
			}
		}

		System.out.println(-1);
	}

	private static boolean[] findPrime(int max) {
		boolean[] primes = new boolean[max];
		Arrays.fill(primes, true);

		primes[0] = primes[1] = false;

		for (int i = 2; i * i < max; i++)
			if (primes[i])
				for (int j = i * i; j < max; j += i)
					primes[j] = false;

		return primes;
	}
}