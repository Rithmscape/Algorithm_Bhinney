import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		TreeTraversal traversal = new TreeTraversal();
		traversal.solve();
	}

	private static class TreeTraversal {
		private List<Integer> inorder; // 중위 순회
		private List<Integer> postorder; // 후위 순회
		private List<Integer> preorder; // 전위 순회
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
				this.inorder = toIntegerList(br.readLine());
				this.postorder = toIntegerList(br.readLine());
				this.preorder = new ArrayList<>(Collections.nCopies(n, 0));
				this.inorderIdxMap = buildIdxMap(inorder);
				this.preorderIdx = 0;
			}
		}

		private void build() {
			recursive(
				new Range(0, inorder.size() - 1),
				new Range(0, postorder.size() - 1)
			);
		}

		private void recursive(Range inRange, Range postRange) {
			if (!inRange.isValid() || !postRange.isValid()) return;

			// 후위순위 마지막 원소가 노드의 root
			int root = postorder.get(postRange.end);
			preorder.set(preorderIdx++, root);

			// 중위 순회에서 root 찾기
			int rootIdx = findRootIndex(root);
			int leftSize = rootIdx - inRange.start;

			// left
			recursive(
				new Range(inRange.start, rootIdx - 1),
				new Range(postRange.start, postRange.start + leftSize - 1)
			);

			// right
			recursive(
				new Range(rootIdx + 1, inRange.end),
				new Range(postRange.start + leftSize, postRange.end - 1)
			);
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				String result = preorder.stream().map(String::valueOf).collect(Collectors.joining(" "));
				bw.write(result);
				bw.flush();
			}
		}

// ---------------------------------------- input helper method ----------------------------------------
		private List<Integer> toIntegerList(String line) {
			return Arrays.stream(line.split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		}

		private Map<Integer, Integer> buildIdxMap(List<Integer> array) {
			return IntStream.range(0, array.size())
				.boxed()
				.collect(Collectors.toMap(array::get, Function.identity()));
		}

// ---------------------------------------- build helper method ----------------------------------------
		private int findRootIndex(int rootValue) {
			return Optional.ofNullable(inorderIdxMap.get(rootValue))
				.orElseThrow(() -> new IllegalStateException("root를 찾을 수 없습니다. \nvalue: " + rootValue));
		}
	}

// -------------------------------------------- helper class --------------------------------------------
	private static class Range {
		final int start;
		final int end;

		Range(int start, int end) {
			this.start = start;
			this.end = end;
		}

		boolean isValid() {
			return start <= end;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", start, end);
		}
	}
}