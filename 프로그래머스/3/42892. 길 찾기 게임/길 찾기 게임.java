import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public static int[][] solution(int[][] info) {
		Node[] nodes = new Node[info.length];
		for (int i = 0; i < info.length; i++)
			nodes[i] = new Node(info[i][0], info[i][1], i + 1, null, null);

		Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.y == o2.y) return o1.x - o2.x;
				else return o2.y - o1.y;
			}
		});

		Node root = nodes[0];

		for (int i = 1; i < nodes.length; i++)
			insert(root, nodes[i]);

		int[][] answer = new int[2][nodes.length];
		Tree tree = new Tree();
		tree.pre(root, answer);
		tree.post(root, answer);

		return answer;
	}


	// 노드에 트리 정보 저장
	private static void insert(Node parent, Node child) {
		if (parent.x > child.x) {
			if (parent.left == null) parent.left = child;
			else insert(parent.left, child);
		}
		else {
			if (parent.right == null) parent.right = child;
			else insert(parent.right, child);
		}
	}

	private static class Tree {
		private int preIdx = 0;
		private int postIdx = 0;

		public void pre(Node root, int[][] answer) {
			if (root != null) {
				answer[0][preIdx++] = root.value;
				pre(root.left, answer);
				pre(root.right, answer);
			}
		}
		public void post(Node root, int[][] answer) {
			if (root != null) {
				post(root.left, answer);
				post(root.right, answer);
				answer[1][postIdx++] = root.value;
			}
		}
	}

	private static class Node {
		int x;
		int y;
		int value;
		Node left;
		Node right;

		public Node(int x, int y, int value, Node left, Node right) {
			this.x = x;
			this.y = y;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}