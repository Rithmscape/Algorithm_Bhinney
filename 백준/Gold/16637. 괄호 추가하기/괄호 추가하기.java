import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		br.close();

		solution(n, input);
	}

	private static int answer;
	private static List<Character> operators;
	private static List<Integer> numbers;

	private static void solution(int n, String input) {
		answer = Integer.MIN_VALUE;
		operators = new ArrayList<>();
		numbers = new ArrayList<>();

		for (char ch : input.toCharArray()) {
			if (ch == '+' || ch == '-' || ch == '*')
				operators.add(ch);

			else
				numbers.add(Character.getNumericValue(ch));
		}

		dfs(numbers.get(0), 0);
		System.out.println(answer);
	}

	private static void dfs(int res, int idx) {
		if (idx >= operators.size()) {
			answer = Math.max(res, answer);
			return;
		}


		// 괄호 무
		int res1 = calculate(res, numbers.get(idx + 1), operators.get(idx));
		dfs(res1, idx + 1);

		// 괄호 유
		if (idx + 1 < operators.size()) {
			int res2 = calculate(numbers.get(idx + 1), numbers.get(idx + 2), operators.get(idx + 1));
			dfs(calculate(res, res2, operators.get(idx)), idx + 2);
		}
	}

	private static int calculate(int a, int b, char op) {
		switch (op) {
			case '+' : {
				return a + b;
			}
			case '-' : {
				return a - b;
			}
			case '*' : {
				return a * b;
			}
			default : {
				return -1;
			}
		}
	}
}
