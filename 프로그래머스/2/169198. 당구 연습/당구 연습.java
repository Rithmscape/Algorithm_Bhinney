class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < answer.length; i++) {
			Point point = mirror(m, n, balls[i], startX, startY); // 거울에 대칭한다고 가정

			if (point.x == 0)
				answer[i] = point.y > 0 ?
					Math.min(Math.min(point.l, point.r), point.u) :
					Math.min(Math.min(point.l, point.r), point.d);

			else if (point.y == 0)
				answer[i] = point.x > 0 ?
					Math.min(Math.min(point.d, point.u), point.r) :
					Math.min(Math.min(point.d, point.u), point.l);

			else
				answer[i] = Math.min(Math.min(point.l, point.r), Math.min(point.d, point.u));
		}

		return answer;
	}

	private Point mirror (int m, int n, int[] ball, int startX, int startY) {
		double x = startX - ball[0];
		double y = startY - ball[1];

		double left = Math.pow(startX + ball[0], 2) + Math.pow(y, 2); // 시작점 -> 왼쪽
		double right = Math.pow((m - startX) + (m - ball[0]), 2) + Math.pow(y , 2); // 시작점 -> 오른쪽
		double down = Math.pow(startY + ball[1], 2) + Math.pow(x, 2); // 시작점 -> 아래쪽
		double up = Math.pow((n - startY) + (n - ball[1]), 2) + Math.pow(x, 2); // 시작점-> 위쪽

		return new Point(x, y, left, right, down, up);
	}

	private class Point{
		int x;
		int y;
		int l;
		int r;
		int d;
		int u;

		public Point(double x, double y, double l, double r, double d, double u) {
			this.x = (int) x;
			this.y = (int) y;
			this.l = (int) l;
			this.r = (int) r;
			this.d = (int) d;
			this.u = (int) u;
		}
	}
}