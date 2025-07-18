import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> pq = input();
		solution(pq);
	}

    private static void solution(PriorityQueue<Integer> pq) {
		int answer = 0;
		while (pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();

			answer += (a + b);
			pq.offer(a + b);
		}

		System.out.println(answer);
	}

	private static PriorityQueue<Integer> input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
		for (int i = 0; i < n; i++)
			pq.offer(Integer.parseInt(br.readLine()));

		return pq;
	}
}