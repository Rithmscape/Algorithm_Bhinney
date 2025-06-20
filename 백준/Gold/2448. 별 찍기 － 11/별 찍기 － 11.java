import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		int n = input();
		char[][] stars = solution(n);
		output(stars);
	}

	private static char[][] solution(int n) {
		char[][] stars = new char[n][n * 2 - 1];
		Arrays.stream(stars).forEach(it -> Arrays.fill(it, ' '));

		fillStars(stars, 0, n - 1, n);

		return stars;
	}


	private static void fillStars(char[][] stars, int r, int c, int size) {
		if (size == 3) {
			stars[r][c] = '*';
			stars[r+ 1][c - 1] = '*';
			stars[r + 1][c + 1] = '*';

			for (int i = -2; i <= 2; i++) {
				stars[r + 2][c + i] = '*';
			}

			return;
		}

		int half = size / 2;

		// 위
		fillStars(stars, r, c, half);

		// 좌쪽 아래
		fillStars(stars, r + half, c - half, half);

		// 우측 아래
		fillStars(stars, r + half, c + half, half);
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