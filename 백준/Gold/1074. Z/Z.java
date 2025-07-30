import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Z().solve();
	}

	private static class Z {
		private int N, R, C;
		private int answer = 0;

		public void solve() throws IOException {
			input();
			recursive(0, 0, (int) Math.pow(2, N));
			output();
		}

		private void recursive(int r, int c, int s) {
			if (s == 1) return;

			int size = s / 2;
			if (R < r + size && C < c + size)
				recursive(r, c, size);

			if (R < r + size && c + size <= C) {
				answer += (s * s) / 4;
				recursive(r, c + size, size);
			}

			if (r + size <= R && C < c + size) {
				answer += ((s * s) / 4) * 2;
				recursive(r + size, c, size);
			}

			if (r + size <= R && c + size <= C) {
				answer += ((s * s) / 4) * 3;
				recursive(r + size, c + size, size);
			}
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String[] arr = br.readLine().split(" ");
				N = Integer.parseInt(arr[0]);
				R = Integer.parseInt(arr[1]);
				C = Integer.parseInt(arr[2]);
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