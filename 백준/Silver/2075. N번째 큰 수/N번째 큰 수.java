import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData input = input();
		int answer = solution(input.n, input.pq);
		output(answer);
	}

	private static int solution(int n, PriorityQueue<Integer> pq) {
		for (int i = 0; i < n - 1; i++)
			pq.poll();

		return pq.poll();
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++)
			pq.addAll(Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList()));

		return new InputData(n, pq);
	}

	private static void output(int answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	private static class InputData {
		int n;
		PriorityQueue<Integer> pq;
		
		public InputData(int n, PriorityQueue<Integer> pq) {
			this.n = n;
			this.pq = pq;
		}
	}
}