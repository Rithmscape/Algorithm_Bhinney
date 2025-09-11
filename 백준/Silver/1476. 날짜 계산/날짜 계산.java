import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();

		int year = S;

		while (true) {
			int e = (year - 1) % 15 + 1;
			int m = (year - 1) % 19 + 1;

			if (E == e && M == m)
				break;

			year += 28;
		}

		System.out.println(year);
	}
}