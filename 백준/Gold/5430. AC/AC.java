import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		List<InputData> input = input();
		String[] result = solution(input);
		output(result);
	}

	private final static String ERROR = "error";
	private static String[] solution(List<InputData> input) {
		String[] result = new String[input.size()];

		for (int i = 0; i < result.length; i++) {
			InputData data = input.get(i);
			result[i] = function(data.p, data.n, data.arr);
		}

		return result;
	}

	private static String function(String p, int n, String str) {
		if (p.chars().filter(it -> it == 'D').count() > n)
			return ERROR;

		Deque<String> dq = new ArrayDeque<>();
		if (n > 0 && !str.isEmpty()) {
			String[] arr = str.split(",");
			for (String s : arr) {
				dq.offerLast(s.trim());
			}
		}

		boolean isReverse = false;
		for (char command : p.toCharArray()) {
			switch (command) {
				case 'R' : {
					isReverse = !isReverse;
					break;
				}
				case 'D' : {
					if (isReverse)
						dq.pollLast();
					else dq.pollFirst();
				}
			}
		}

		if (dq.isEmpty()) return "[]";

		StringBuilder result = new StringBuilder();
		result.append("[");

		while (dq.size() != 1){
			if (isReverse)
				result.append(dq.pollLast()).append(",");
			else
				result.append(dq.pollFirst()).append(",");
		}

		result.append(dq.poll()).append("]");

		return result.toString();
	}

	private static List<InputData> input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<InputData> result = new ArrayList<>();

		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arr = br.readLine()
					.replaceAll("\\[", "")
					.replaceAll("]", "");

			result.add(new InputData(p, n, arr));
		}

		return result;
	}

	private static void output(String[] result) {
		System.out.println(String.join("\n", result));
	}

	private static class InputData{
		String p;
		int n;
		String arr;

		public InputData(String p, int n, String arr) {
			this.p = p;
			this.n = n;
			this.arr = arr;
		}
	}
}
