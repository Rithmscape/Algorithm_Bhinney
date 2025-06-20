import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int n = input();
		char[][] stars = solution(n);
		output(stars);
	}

	private static char[][] solution(int n) {
		char[][] stars = new char[n][n];
		Arrays.stream(stars)
			.forEach(it -> Arrays.fill(it, '*'));

		blank(stars, 0, 0, n);

		return stars;
	}

	private static void blank(char[][] stars, int r, int c, int size) {
		if (size == 1) return;

		int unit = size / 3;
		for (int i = r + unit; i < r + 2 * unit; i++)
			for (int j = c + unit; j < c + 2 * unit; j++)
				stars[i][j] = ' ';

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;

				blank(stars, r + i * unit, c + j * unit, unit);
			}
		}
	}

	private static int input() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	private static void output(char[][] stars) {
		Arrays.stream(stars)
			.map(String::new)
			.forEach(System.out::println);
	}
}
