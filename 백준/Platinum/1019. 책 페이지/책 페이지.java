import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private void solve() throws IOException {
			int n = input();
			int[] answer = solution(n);
			output(answer);
		}

		private int[] solution(int end) {
			int start = 1;
			int[] answer = new int[10];
			int count = 1;

			while (start <= end) {
				while (end % 10 != 9 && start <= end) {
					calculate(end, count, answer);
					end--;
				}

				while (start % 10 != 0 && start <= end) {
					calculate(start, count, answer);
					start++;
				}

				if (start > end) break;

				start /= 10;
				end /= 10;

				for (int i = 0; i < 10; i++)
					answer[i] += (end - start + 1) * count;

				count *= 10;
			}

			return answer;
		}

		private void calculate (int cur, int count, int[] answer) {
			while (0 < cur) {
				answer[cur % 10] += count;
				cur /= 10;
			}
		}

		private int input() throws IOException {
			try (Scanner sc = new Scanner(System.in)) {
				return sc.nextInt();
			}
		}

		private void output(int[] answer) throws IOException {
			System.out.println(IntStream.of(answer).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		}
	}
}