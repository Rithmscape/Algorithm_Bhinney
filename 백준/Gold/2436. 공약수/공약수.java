import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gcd = sc.nextInt();
		int lcm = sc.nextInt();
		sc.close();

		int t = lcm/ gcd;

		long a = 0, b = 0;

		for (int i = 1; i * i <= t; i++) {
			if (t % i != 0) continue;

			long aa = i;
			long bb = t / i;

			if (GCD(aa, bb) == 1) {
				a = aa * gcd;
				b = bb * gcd;
			}
		}

		System.out.println(a + " " + b);
	}

	private static long GCD(long a, long b) {
		return a % b == 0 ? b : GCD(b, a % b);
	}
}