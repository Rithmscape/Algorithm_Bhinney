import java.util.*;

class Solution {
    private static int answer;
	private static final int[] arr = new int[5];
	private static ArrayList<ArrayList<Integer>> list;
	public static int solution(int n, int[][] q, int[] ans) {
		/*
		 * 1 ~ n까지 서로 다른 정수 5개가 오름차순인 비밀 코드를 맞춰야 함
		 * q[i] 조합으로 시도 -> ans[i]개 일치
		 * 가능한 점수 조합 개수 return
		 */

		list = new ArrayList<>();
		for (int i = 0; i < q.length; i++) {
			ArrayList<Integer> l = new ArrayList<>();
			for (int num : q[i])
				l.add(num);
			list.add(l);
		}

		answer = 0;
		boolean[] visited = new boolean[n + 1];
		visited[0] = true; // 안되겠지만 혹시나 0이 들어올 경우를 대비

		combination(0, 1, n, q, ans, visited);
		return answer;
	}

	//가능한 모든 조합 확인 -> 5개면 확인 메서드로 조건 확인 -> 맞으면 개수++
	private static void combination(int depth, int cur, int n, int[][] q, int[] ans, boolean[] visited) {
		if (depth == 5) {
			if (checkArr(q, ans)) answer++;
			return;
		}

		for (int i = cur; i <= n; i++) {
			if (visited[i]) continue;

			arr[depth] = i;
			visited[i] = true;
			combination(depth + 1, i + 1, n, q, ans, visited);
			visited[i] = false;
		}
	}

	private static boolean checkArr(int[][] q, int[] ans) {
		for (int i = 0; i < q.length; i++) {
			int cnt = 0;
			for (int n : arr)
				if (list.get(i).contains(n)) cnt++;

			if (ans[i] != cnt) return false;
		}

		return true;
	}
}