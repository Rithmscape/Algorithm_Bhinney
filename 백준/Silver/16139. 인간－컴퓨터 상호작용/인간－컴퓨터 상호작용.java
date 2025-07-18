import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData input = input();
		int[] answer = solution(input.s, input.questions);
		output(answer);
	}

	private static int[] solution(String s, String[][] questions) {
		int[][] counts = calculate(s);
		int[] answer = new int[questions.length];
		for (int i = 0; i < answer.length; i++)
			answer[i] = find(counts, questions[i]);


		return answer;
	}

	private static int[][] calculate(String s) {
		int[][] counts = new int[s.length() + 1][26];
		char[] arr = s.toCharArray();

		for (int i = 1; i <= arr.length; i++) {
			int alpha = arr[i - 1] - 'a';

			for (int j = 0; j < 26; j++) {
				int value = counts[i - 1][j];
				counts[i][j] = (j == alpha) ? value + 1: value;
			}
		}

		return counts;
	}

	private static int find(int[][] counts, String[] question) {
		int target = question[0].charAt(0) - 'a';
		int start = Integer.parseInt(question[1]);
		int end = Integer.parseInt(question[2]) + 1;

		return counts[end][target] - counts[start][target];
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int n = Integer.parseInt(br.readLine());
		String[][] questions = new String[n][3];
		for (int i = 0; i < n; i++)
			questions[i] = br.readLine().split(" ");
		br.close();

		return new InputData(s, questions);
	}

	private static void output(int[] answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int ans : answer)
			bw.write(ans + "\n");

		bw.flush();
		bw.close();
	}

	private static class InputData {
		String s;
		String[][] questions;

		public InputData(String s, String[][] questions) {
			this.s = s;
			this.questions = questions;
		}
	}
}