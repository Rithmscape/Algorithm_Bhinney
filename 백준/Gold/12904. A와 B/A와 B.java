import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private String S, T;

		void solution() throws IOException {
			input();
			boolean answer = solve();
			output(answer);
		}

		private boolean solve() {
			String cur = T;

			while (cur.length() > S.length()) {
				char last = cur.charAt(cur.length() - 1);

				cur = last == 'A' ?
					cur.substring(0, cur.length() - 1) :
					new StringBuilder(cur.substring(0, cur.length() - 1)).reverse().toString();
			}

			return cur.equals(S);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			S = br.readLine();
			T = br.readLine();
			br.close();
		}

		private void output(boolean answer) {
			if (answer)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
}
