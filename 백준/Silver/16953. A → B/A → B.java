import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();

		solution(a, b);
	}

	private static void solution(int a, int b) {
		int ans = 1;

		while (b != a) {
			if (b < a) {
				ans = -1;
				break;
			}
			String s = String.valueOf(b);
			if (b % 2 == 0) b /= 2;
			else if (s.charAt(s.length() - 1) == '1') b = Integer.parseInt(s.substring(0, s.length() - 1));
			else {
				System.out.println(-1);
				return;
			}

			ans++;
		}

		System.out.println(ans);
	}
}