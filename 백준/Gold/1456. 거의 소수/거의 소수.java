import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m = sc.nextLong();
		sc.close();

		int limit = (int) Math.sqrt(m) + 1;
		boolean[] prime = new boolean[limit];
		prime(prime);

		int cnt = 0;
		for (int i = 2; i < limit; i++) {
			if (!prime[i]) continue;

			long num = (long) i * i;

			while (num <= m) {
				if (num >= n) cnt++;

				if (num > m / i) break;

				num *= i;
			}
		}

		System.out.println(cnt);
	}

	private static void prime (boolean[] prime) {
		Arrays.fill(prime, true);

		// false 가 소수
		for (int i = 2; i * i < prime.length; i++) {
			if (prime[i]) {
				for (int j = i * i; j < prime.length; j += i) {
					prime[j] = false;
				}
			}
		}
	}
}