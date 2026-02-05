import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private String[] words;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			long[] w = new long[10]; // 가중치 저장, A(0) ~ J(9)
			boolean[] z = new boolean[10]; // 0이 가능한지 여부 확인
			Arrays.fill(z, true);

			// 가중치 계산 및 각 첫 글자가 0이 될 수 없다는 것을 체크
			for (String word : words) {
				z[word.charAt(0) - 'A'] = false; // 시작은 0이 될 수 없음

				for (int i = 0; i < word.length(); i++) {
					int idx = word.charAt(i) - 'A';
					long pos = (long) Math.pow(10, word.length() - i - 1);
					w[idx] += pos;
				}
			}

			Letter[] letters = new Letter[10];
			int cnt = 0;

			for (int i = 0; i < 10; i++) {
				if (w[i] <= 0) continue;

				letters[cnt++] = new Letter(i, w[i], z[i]);
			}

			letters = Arrays.copyOf(letters, cnt);
			Arrays.sort(letters);

			// 숫자 부여
			int[] assigned = new int[10];

			if (cnt < 10) {
				int num = 9;
				for (Letter letter : letters) 
					assigned[letter.i] = num--;
			} else { // 10개 이상 사용되면, 0이 들어가야함.
				int zero = -1;
				for (int i = letters.length - 1; i >= 0; i--) {
					if (letters[i].z) {
						zero = i;
						break;
					}
				}

				int num = 9;
				for (int i = 0; i < letters.length; i++) {
					if (i == zero) 
						assigned[letters[i].i] = 0;
					else 
						assigned[letters[i].i] = num--;
				}
			}

			long sum = 0;
			for (String word : words) {
				long value = 0;
				for (char c : word.toCharArray()) {
					value = value * 10 + assigned[c - 'A'];
				}
				sum += value;
			}

			System.out.println(sum);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			words = new String[N];
			for (int i = 0; i < N; i++)
				words[i] = br.readLine();
			br.close();
		}
	}

	private static class Letter implements Comparable<Letter> {
		private int i; // 인덱스
		private long w; // 가중치
		private boolean z; // 0이 가능한지 여부

		public Letter(int i, long w, boolean z) {
			this.i = i;
			this.w = w;
			this.z = z;
		}

		@Override
		public int compareTo(Letter o) {

			return Long.compare(o.w, this.w);
		}
	}
}