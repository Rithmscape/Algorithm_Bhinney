import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int[][] sudoku = new int[9][9];
		public void solution() throws IOException {
			input();
			solve(0, 0);
			output();
		}

		private boolean solve(int r, int c) {
			if (r == 9) return true;

			if (c == 9) return solve(r + 1, 0);

			if (sudoku[r][c] != 0) return solve(r, c + 1);

			for (int i = 1; i <= 9; i++) {
				// 조건문
				if(possible(r, c, i)) {
					sudoku[r][c] = i;

					if(solve(r, c + 1))
						return true;

					sudoku[r][c] = 0;
				}
			}

			return false;
		}

		private boolean possible(int r, int c, int v) {
			for (int i = 0; i < 9; i++)
				if (sudoku[r][i] == v)
					return false;

			for (int i = 0; i < 9; i++)
				if (sudoku[i][c] == v)
					return false;

			int row = (r / 3) * 3;
			int col = (c / 3) * 3;

			for (int i = row; i < row + 3; i++)
				for (int j = col; j < col + 3; j++)
					if (sudoku[i][j] == v)
						return false;

			return true;
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			for (int i = 0; i < 9; i++)
				sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			br.close();
		}

		private void output() throws IOException {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			for (int i = 0; i < 9; i++)
				bw.write(IntStream.of(sudoku[i]).mapToObj(String::valueOf).collect(Collectors.joining(" ")) + "\n");

			bw.flush();
			bw.close();
		}
	}
}