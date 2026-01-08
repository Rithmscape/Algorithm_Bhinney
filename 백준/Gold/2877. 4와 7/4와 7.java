import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int k = sc.nextInt() + 1;
		StringBuilder result = new StringBuilder();

		while (k != 0) {
			int num = k % 2;
			result.append(num);
			k /= 2;
		}

		String answer = new StringBuilder(result.substring(0, result.length() - 1)).reverse().toString()
				.replaceAll("0", "4").replaceAll("1", "7");

		System.out.println(answer);
	}
}