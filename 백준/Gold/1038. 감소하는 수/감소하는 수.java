import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		if (n <= 10)
			System.out.println(n);
		else if (n > 1022)
			System.out.println(-1);
		else {
			List<Long> list = new ArrayList<>();
			for (int i = 0; i < 10; i++)
				dfs(i, 1, list);

			Collections.sort(list);

			System.out.println(list.get(n));
		}
	}

	private static void dfs(long num, int idx, List<Long> list) {
		if (idx > 10) return;

		list.add(num);

		for (int i = 0; i < num % 10; i++)
			dfs(num * 10 + i, idx + 1, list);
	}
}
