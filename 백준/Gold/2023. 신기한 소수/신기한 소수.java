import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dfs(0, n);
	}

	private static void dfs(int num, int depth) {
		if (depth == 0)
			System.out.println(num);

		for (int i = 1; i < 10; i++) {
			int t = 10 * num + i;

			if (isPrime(t))
				dfs(t, depth - 1);
		}
	}

	private static boolean isPrime(int num) {
		if (num < 2) return false;

		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}

		return true;
	}
}