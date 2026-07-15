class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long left = y, right = y, top = x, bottom = x;

		for (int i = queries.length - 1; i >= 0; i--) {
			int direction = queries[i][0];
			int dist = queries[i][1];

			switch (direction) {
				case 0 : { // left
					if (left != 0) left += dist;
					right = Math.min(m - 1, right + dist);
					break;
				}
				case 1 : { // right
					if (right != m - 1) right -= dist;
					left = Math.max(0, left - dist);
					break;
				}
				case 2 : { // up
					if (top != 0) top += dist;
					bottom = Math.min(n - 1, bottom + dist);
					break;
				}
				case 3 : { // down
					if (bottom != n - 1) bottom -= dist;
					top = Math.max(0, top - dist);
					break;
				}
			}

			if (left >= m || right < 0 || top >= n || bottom < 0) return 0;
		}

		return (right - left + 1) * (bottom - top + 1);
    }
}