import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private String str;
		private int answer;
		private Stack<Character> stack;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			stack = new Stack<>();
			int value = 1;
			answer = 0;
			char before = '*';

			for (char ch : str.toCharArray()) {
				switch (ch) {
					case '(' : {
						stack.push(ch);
						value *= 2;
						break;
					}
					case '[' : {
						stack.push(ch);
						value *= 3;
						break;
					}
					case ')' : {
						if (stack.isEmpty() || stack.peek() != '(') {
							answer = 0;
							return;
						}
						else if (before == '(')
							answer += value;

						stack.pop();
						value /= 2;
						break;
					}
					case ']' : {
						if (stack.isEmpty() || stack.peek() != '[') {
							answer = 0;
							return;
						}
						else if (before == '[')
							answer += value;

						stack.pop();
						value /= 3;
						break;
					}
				}

				before = ch;
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
			br.close();
		}

		private void output() {
			if (stack.isEmpty())
				System.out.println(answer);
			else
				System.out.println(0);
		}
	}
}
