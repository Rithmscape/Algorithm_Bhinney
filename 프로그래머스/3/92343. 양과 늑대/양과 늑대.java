import java.util.*;

class Solution {
    private ArrayList<ArrayList<Integer>> tree;
	private int ans;
	public int solution(int[] info, int[][] edges) {
		/*
		 <info>
		 * 노드 정보
		 * 0 : 양
		 * 1 : 늑대
		 * root인 info[0] = 0

* 		 <edges>
		 * 노드의 연결 관계
		 * edges.length = info.length - 1
		 * [부모, 자식]
		 */

		// 전역 변수 초기화
		ans = 0;
		tree = new ArrayList<>();
		for (int i = 0; i < info.length; i++)
			tree.add(new ArrayList<>());

		// 연결 정보 저장
		for (int[] e : edges)
			tree.get(e[0]).add(e[1]);

		HashSet<Integer> visit = new HashSet<>(tree.get(0));
		dfs(0, visit, 0, 0, info);

		return ans;
	}

	// 재귀로 돌면서 확인
	private void dfs(int idx, HashSet<Integer> visit, int sheep, int wolfs, int[] info) {
		if (info[idx] == 0) sheep++;
		else wolfs++;

		if (wolfs >= sheep) return;

		ans = Math.max(ans, sheep);

		for (int next : visit) {
			HashSet<Integer> set = new HashSet<>(visit);
			set.remove(next);

			for (int child : tree.get(next))
				set.add(child);

			dfs(next, set, sheep, wolfs, info);
		}
	}
}