import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int K, N;
		private long[] primes;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			PriorityQueue<Long> pq = new PriorityQueue<>();

			for (long prime : primes)
				pq.offer(prime);

			long cur = 0;

			while (N-- > 0 && !pq.isEmpty()) {
				cur = pq.poll();

				for (int i = 0; i < K; i++) {
					long value = cur * primes[i];

					if (value > (long) Math.pow(2, 31)) break;

					pq.offer(value);

					if (cur % primes[i] == 0) break;
				}
			}

			System.out.println(cur);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] a = br.readLine().split(" ");
			K = Integer.parseInt(a[0]); N = Integer.parseInt(a[1]);
			primes = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

			br.close();
		}
	}
}