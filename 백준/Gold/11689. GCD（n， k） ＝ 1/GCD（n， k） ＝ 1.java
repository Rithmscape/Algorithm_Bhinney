import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new GCD().solve();
	}

	private static class GCD {
		private long n; // 1 <= n <= 10^12
		private long answer = n;

		public void solve() {
			input(); // 입력
			solution(); // 풀이
			output(); // 출력
		}

		private void solution() {
			answer = n;
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0)
					answer = answer / i * (i - 1);

				while (n % i == 0)
					n /= i;
			}

			if (n > 1) {
				answer = answer / n * (n - 1);
			}
		}

		private void input() {
			try (Scanner sc = new Scanner(System.in);) {
				n = sc.nextLong();
			}
		}

		private void output() {
			System.out.println(answer);
		}
	}
}