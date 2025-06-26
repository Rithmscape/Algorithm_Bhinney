import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] input = input();
		String result = solution(input[0], input[1]);
		output(result);
	}

	private static String solution(int n, int k) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			queue.offer(i);

		StringBuilder sb = new StringBuilder();
		sb.append("<");

		while (queue.size() != 1){
			for (int i = 0; i < k - 1; i++)
				queue.offer(queue.poll());
			sb.append(queue.poll()).append(", ");
		}

		sb.append(queue.poll()).append(">");
		return sb.toString();
	}

	private static int[] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}

	private static void output(String result) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result);
		bw.close();
	}
}
