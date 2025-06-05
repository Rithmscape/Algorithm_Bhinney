import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
   private static int ans;
	public static int solution(String[] storage, String[] requests) {
		char[][] arr = Arrays.stream(storage)
			.map(String::toCharArray)
			.toArray(char[][]::new);

		ans = arr.length * arr[0].length;

		List<Character> crane = new ArrayList<>(); // 크레인으로 제거한 거는 다시 제거 안해도 되기 때문에 저장해놓고 continue 처리

		for (String request : requests) {
			char target = request.charAt(0);
			if (crane.contains(target)) continue;

			switch (request.length()) { // 1 -> 지게차, 2 -> 크레인
				case 1 -> {
					removeByLift(arr, target);
					break;
				}
				case 2 -> {
					removeByCrane(arr, target);
					crane.add(target);
					break;
				}
				default -> {break;}
			}
		}

		return ans;
	}

	private static void removeByLift(char[][] arr, char target) {
		boolean[][] visited = new boolean[arr.length][arr[0].length];
		List<Node> remove = new ArrayList<>(); // 한번에 지우기

		// 가장 자리부터 시작
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[0].length; y++) {

				// 가장 자리가 아니거나 방문했다면 pass
				if (x != 0 && y != 0 && x != arr.length - 1 && y != arr[0].length - 1) continue;
				if (visited[x][y]) continue;

				visited[x][y] = true;
				if (arr[x][y] == target)
					remove.add(new Node(x, y));
				else if (arr[x][y] == '_') // 빈 공간이면 bfs
					bfs(x, y, target, arr,remove, visited);
			}
		}

		for (Node node : remove) {
			ans--;
			arr[node.x][node.y] = '_';
		}
	}

	private static void bfs(int x, int y, char target, char[][] arr, List<Node> remove, boolean[][] visited) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx  = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr[0].length || visited[nx][ny]) continue;

				visited[nx][ny] = true;
				if (arr[nx][ny] == '_') q.offer(new Node(nx, ny));
				else if (arr[nx][ny] == target) remove.add(new Node(nx, ny));
			}
		}
	}

	private static void removeByCrane(char[][] arr, char target){
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (arr[i][j] == target) {
					ans--;
					arr[i][j] = '_';
				}
	}

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}