import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData data = input();
		int answer = solution(data.gate, data.plane, data.arrives);
		System.out.println(answer);
	}

	private static int solution(int gate, int plane, int[] arrives) {
		int answer = 0;

		int[] parents = new int[gate + 1];
		for (int i = 0; i <= gate; i++)
			parents[i] = i;

		for (int arrive : arrives) {
			int available = find(parents, arrive);
			if (available == 0) break;

			answer++;
			union(parents, available, available - 1);
		}

		return answer;
	}

	private static void union(int[] parents, int a, int b) {
		a = find(parents, a);
		b = find(parents, b);
		parents[a] = b;
	}

	private static int find(int[] parents, int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents, parents[x]);
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int gate = Integer.parseInt(br.readLine());
		int plane = Integer.parseInt(br.readLine());
		int[] arrives = new int[plane];

		for (int i = 0; i < plane; i++)
			arrives[i] = Integer.parseInt(br.readLine());

		return new InputData(gate, plane, arrives);
	}

	private static class InputData{
		int gate;
		int plane;
		int[] arrives;

		public InputData(int gate, int plane, int[] arrives) {
			this.gate = gate;
			this.plane = plane;
			this.arrives = arrives;
		}
	}
}
