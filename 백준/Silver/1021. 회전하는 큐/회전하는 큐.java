import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException{
		new Solution().solve();
	}

	private static class Solution {
		private int n; // 큐의 크기
		private int m; // 뽑아내려는 수의 개수
		private int[] locations; // 뽑아내려고 하는 수의 위치
		private int answer = 0; // 카운트 할 변수

		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {

			// 1 ~ n 까지 큐에 담기
			Deque<Integer> deque = new ArrayDeque<>();
			IntStream.rangeClosed(1, n).forEach(deque::offer);

			if (deque.isEmpty()) return; // 그럴 일은 없겠지만, 혹시나 큐가 비어있으면 그냥 바로 return

			for (int target : locations) {
				int l= position(target, deque);
				int r = deque.size() - l;

				if (l <= r) {
					answer += l;
					left(l, deque);
				} else {
					answer += r;
					right(r, deque);
				}

				deque.pollFirst();
			}
		}

		// 왼쪽부터 확인한 타겟 위치
		private int position(int target, Deque<Integer> deque) {
			int position = 0;

			for (Integer n : deque) {
				if (n == target) return position;
				else position++;
			}

			return -1;
		}

		// 왼쪽으로 회전
		private void left(int moves, Deque<Integer> deque) {
			for (int i = 0; i < moves; i++)
				deque.offerLast(deque.pollFirst());
		}

		// 오른쪽으로 회전
		private void right(int moves, Deque<Integer> deque) {
			for (int i = 0; i < moves; i++)
				deque.offerFirst(deque.pollLast());
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String[] arr = br.readLine().split(" ");
				n = Integer.parseInt(arr[0]);
				m = Integer.parseInt(arr[1]);

				locations = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(String.valueOf(answer));
				bw.flush();
			}
		}
	}
}