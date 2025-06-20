import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int k = input();
		String ans = solution(k);
		output(ans);
	}

	private static String solution(int k) {
		StringBuilder sb = new StringBuilder();
		sb.append((1 << k) - 1).append("\n");

		dfs(k, 1, 3, 2, sb);

		return sb.toString();
	}

	private static void dfs(int n, int from, int to, int sub, StringBuilder sb) {
		if (n == 1) {
			sb.append(from).append(" ").append(to).append("\n");
			return;
		}

		dfs(n - 1, from, sub, to , sb);

		sb.append(from).append(" ").append(to).append("\n");

		dfs(n - 1, sub, to, from, sb);
	}

	private static int input() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	private static void output(String ans) {
		System.out.println(ans);
	}
}