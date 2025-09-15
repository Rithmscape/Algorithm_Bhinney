import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int[] king; // 킹의 위치
		private int[] rock; // 돌의 위치
		private int n; // 움직이는 횟수
		private String[] commands;

		private void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			for (String command : commands) {
				int[] pos = move(command);
				int[] newKingPos = new int[] {king[0] + pos[0], king[1] + pos[1]};

				if (outRange(newKingPos)) continue;

				if (newKingPos[0] == rock[0] && newKingPos[1] == rock[1]) {
					int[] newRockPos = new int[] {rock[0] + pos[0], rock[1] + pos[1]};

					if (outRange(newRockPos)) continue;

					rock[0] = newRockPos[0];
					rock[1] = newRockPos[1];
				}

				king[0] = newKingPos[0];
				king[1] = newKingPos[1];
			}
		}

		private boolean outRange(int[] pos) {
			return pos[0] < 0 || pos[1] < 0 || pos[0] >= 8 || pos[1] >= 8;
		}

		private int[] move(String command) {
			switch (command) {
				case "R" : {
					return new int[] {0, 1};
				}
				case "L" : {
					return new int[] {0, -1};
				}
				case "B" : {
					return new int[] {-1, 0};
				}
				case "T" : {
					return new int[] {1, 0};
				}
				case "RT" : {
					return new int[] {1, 1};
				}
				case "LT" : {
					return new int[] {1, -1};
				}
				case "RB" : {
					return new int[] {-1, 1};
				}
				case "LB" : {
					return new int[] {-1, -1};
				}
				default : {
					return new int[] {0, 0};
				}
			}
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String[] arr = br.readLine().split(" ");
				king = parse(arr[0]);
				rock = parse(arr[1]);
				n = Integer.parseInt(arr[2]);

				commands = new String[n];
				for (int i = 0; i < n; i++)
					commands[i] = br.readLine();
			}
		}

		private int[] parse(String s) {
			char[] arr = s.toCharArray();
			return new int[]{arr[1] - '1', arr[0] - 'A'};
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				String kingPos = "" + (char)(king[1] + 'A') + (char)(king[0] + '1');
				String rockPos = "" + (char)(rock[1] + 'A') + (char)(rock[0] + '1');

				bw.write(kingPos + "\n" + rockPos);
				bw.flush();
			}
		}
	}
}