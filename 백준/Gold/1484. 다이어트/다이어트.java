import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int g = sc.nextInt();
		sc.close();

		int start = 1;
		int end = 2; // <= 100_000
		boolean flag = false;

		//g = x^2-y^2 
		while (end <= 100_000) {
			int s = start * start;
			int e = end * end;

			if (e - s == g) {
				System.out.println(end);
				flag = true;
			}

			if (e - s >= g) start++;
			else end++;
		}

		if (!flag)
			System.out.println(-1);
	}
}