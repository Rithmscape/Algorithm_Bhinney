import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static final int LEN = 1_000_001;

	public static void main(String[] args) {
		long[] fx = new long[LEN];
		long[] gx = new long[LEN];

		Arrays.fill(fx, 1);

		for (int i = 2; i < LEN; i++)
			for (int j = 1; i * j < LEN; j++)
				fx[i * j] += i;

		for (int i = 1; i < LEN; i++)
			gx[i] = gx[i - 1] + fx[i];

		Scanner sc = new Scanner(System.in);
		StringBuilder answer = new StringBuilder();

		int tc = sc.nextInt();
		
		while (tc-- > 0)
			answer.append(gx[sc.nextInt()]).append("\n");

		System.out.print(answer.toString());
	}
}