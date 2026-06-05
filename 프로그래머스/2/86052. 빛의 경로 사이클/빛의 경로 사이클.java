import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        /*
		 * S : 직진
		 * L : 좌회전
		 * R : 우회전
		 * 빛이 격자의 크기를 벗어날 경우, 반대쪽 끝으로 다시 돌아옴
		 */

		/* [상, 하, 좌, 우] */
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
		List<Integer> answer = new ArrayList<>();

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length(); c++) {
				for (int d = 0; d < 4; d++) {
					if (visited[r][c][d]) continue;

					/* 사이클 탐색 */
					int cycle = 0;
					int cr = r, cc = c, cd = d;

					while (!visited[cr][cc][cd]) {
						visited[cr][cc][cd] = true;
						cycle++;

						/* 방향 전환 */
						char cell = grid[cr].charAt(cc);
						if (cell == 'L') cd = (cd + 3) % 4;
						else if (cell == 'R') cd = (cd + 1) % 4;

						/* 다음 칸 이동 */
						cr = (cr + dr[cd] + grid.length) % grid.length;
						cc = (cc + dc[cd] + grid[0].length()) % grid[0].length();
					}

					answer.add(cycle);
				}
			}
		}

		return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}