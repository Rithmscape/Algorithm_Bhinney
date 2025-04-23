import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new PriorityQueue<>((o1, o2) -> {
			int tmp = Integer.compare(Math.abs(o1), Math.abs(o2));
			if (tmp == 0)
				return Integer.compare(o1, o2);
			return tmp;
		});

		for (int i = 0; i < n; i++) {
			int m = Integer.parseInt(br.readLine());

			if (m == 0 && q.isEmpty())
				System.out.println(0);
			else if (m == 0)
				System.out.println(q.remove());
			else q.offer(m);
		}
	}
}