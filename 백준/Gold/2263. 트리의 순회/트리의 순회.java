import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		TreeTraversal traversal = new TreeTraversal();
		traversal.solve();
	}

	private static class TreeTraversal {
		private int[] inorder; // 중위 순회
		private int[] postorder; // 후위 순회
		private int[] preorder; // 전위 순회
		private Map<Integer, Integer> inorderIdxMap; // 중위 순회 인덱스 map
		private int preorderIdx; // 전위 순회 idx

		public void solve() throws IOException { // 사실상 문제를 푸는 곳
			input();
			build();
			output();
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				int n = Integer.parseInt(br.readLine());
				this.inorder = toIntArray(br.readLine());
				this.postorder = toIntArray(br.readLine());
				this.preorder = new int[n];
				this.inorderIdxMap = buildIdxMap(inorder);
				this.preorderIdx = 0;
			}
		}

		private void build() {
			recursive(0, inorder.length - 1, 0, postorder.length - 1);
		}

		private void recursive(int is, int ie, int ps, int pe) {
			if (is > ie || ps > pe)
				return;

			// 후위순위 마지막 원소가 노드의 root
			int root = postorder[pe];
			preorder[preorderIdx++] = root;

			// 중위 순회에서 root 찾기
			int rootIdx = inorderIdxMap.get(root);
			int leftSize = rootIdx - is;

			// left
			recursive(is, rootIdx - 1, ps, ps + leftSize - 1);

			// right
			recursive(rootIdx + 1, ie, ps + leftSize, pe - 1);
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				StringBuilder result = new StringBuilder();
				for (int node : preorder)
					result.append(node).append(" ");

				bw.write(result.toString().trim());
				bw.flush();
			}
		}

		// ---------------------------------------- input helper method ----------------------------------------
		private int[] toIntArray(String line) {
			String[] arr = line.split(" ");
			int[] result = new int[arr.length];
			for (int i = 0; i < result.length; i++)
				result[i] = Integer.parseInt(arr[i]);

			return result;
		}

		private Map<Integer, Integer> buildIdxMap(int[] array) {
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 0; i < array.length; i++)
				map.put(array[i], i);

			return map;
		}
	}
}