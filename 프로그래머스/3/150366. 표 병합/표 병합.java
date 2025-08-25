import java.util.*;

class Solution {
    public static String[] solution(String[] commands) {
		UnionFind uf = new UnionFind(50 * 50);
		List<String> result = new ArrayList<>();

		for (String command : commands) {
			String[] arr = command.split(" ");
			execute(uf, arr, result);
		}

		return result.toArray(String[]::new);
	}

	private static void execute(UnionFind uf, String[] arr, List<String> result) {
		switch (arr[0]) {
			case "UPDATE" : {
				if (arr.length == 4) { // UPDATE r c value
					int r = Integer.parseInt(arr[1]) - 1;
					int c = Integer.parseInt(arr[2]) - 1;
					String value = arr[3];
					int pos = r * 50 + c;
					uf.set(pos, value);
				} else if (arr.length == 3) { // UPDATE value1 value2
					String v1 = arr[1];
					String v2 = arr[2];

					for (int i = 0; i < 50 * 50; i++) {
						if (v1.equals(uf.get(i)))
							uf.set(i, v2);
					}
				}

				break;
			}

			case "MERGE" : { // MERGE r1 c1 r2 c2
				int r1 = Integer.parseInt(arr[1]) - 1;
				int c1 = Integer.parseInt(arr[2]) - 1;
				int r2 = Integer.parseInt(arr[3]) - 1;
				int c2 = Integer.parseInt(arr[4]) - 1;

				int pos1 = r1 * 50 + c1;
				int pos2 = r2 * 50 + c2;

				if (pos1 == pos2) break;

				uf.union(pos1, pos2);
				break;
			}

			case "UNMERGE" : { // UNMERGE r c
				int r = Integer.parseInt(arr[1]) - 1;
				int c = Integer.parseInt(arr[2]) - 1;
				int pos = r * 50 + c;
				uf.unmerge(pos);

				break;
			}

			case "PRINT" : { // PRINT r c
				int r = Integer.parseInt(arr[1]) - 1;
				int c = Integer.parseInt(arr[2]) - 1;
				int pos = r * 50 + c;
				String value = uf.get(pos);
				result.add(value == null ? "EMPTY" : value);

				break;
			}
		}
	}

	private static class UnionFind {
		int[] parent;
		String[] values;

		private UnionFind(int size) {
			parent = new int[size];
			values = new String[size];
			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}

		private int find(int num) {
			if (parent[num] != num) {
				parent[num] = find(parent[num]);
			}

			return parent[num];
		}

		private void union(int x, int y) {
			int rx = find(x);
			int ry = find(y);

			if (rx == ry) return;

			String v1 = values[rx];
			String v2 = values[ry];
			String value = null;

			if (v1 != null && v2 != null)
				value = v1;
			else if (v1 != null)
				value = v1;
			else if (v2 != null)
				value = v2;

			parent[ry] = rx;
			values[rx] = value;
			values[ry] = null;
		}

		private void set(int x, String value) {
			int root = find(x);
			values[root] = value;
		}

		private String get(int x) {
			int root = find(x);
			return values[root];
		}

		private void unmerge(int x) {
			int root = find(x);
			String value = values[root];

			for (int i = 0; i < parent.length; i++)
				find(i);

			for (int i = 0; i < parent.length; i++) {
				if (find(i) == root) {
					parent[i] = i;
					if (i == x)
						values[i] = value;
					else
						values[i] = null;
				}
			}

			values[x] = value;
		}
	}
}