import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new Solution().solve();
	}

	private static class Solution {
		private static final String BEFORE = "____";
		private static final String QUESTION = "\"재귀함수가 뭔가요?\"";
		private static final String INTRO1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
		private static final String INTRO2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
		private static final String INTRO3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
		private static final String ANSWER = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
		private static final String FINISH = "라고 답변하였지.";
		private static final String BR = "\n";

		private int n;
		private StringBuilder answer;

		public void solve() {
			input();
			solution();
			output();
		}

		private void solution() {
			answer = new StringBuilder();
			answer
				.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

			recursion(0);
		}

		private void recursion(int cnt) {
			String before = BEFORE.repeat(cnt);

			if (cnt == n) {
				answer
					.append(before).append(QUESTION).append(BR)
					.append(before).append(ANSWER).append(BR)
					.append(before).append(FINISH).append(BR);

				return;
			}

			String intro = before + INTRO1 + before + INTRO2 + before + INTRO3;

			answer
				.append(before).append(QUESTION).append(BR)
				.append(intro).append(BR);

			recursion(cnt + 1);

			answer.append(before).append(FINISH).append(BR);
		}

		private void input() {
			try (Scanner sc = new Scanner(System.in)) {
				n = sc.nextInt();
			}
		}

		private void output() {
			System.out.println(answer);
		}
	}
}