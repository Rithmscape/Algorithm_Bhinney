import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private Node root;
		private StringBuilder answer;
		void solution() throws IOException {
			input();
			solve(root);
			output();
		}

		private void solve(Node cur) {
			if (cur == null) return;

			solve(cur.left);
			solve(cur.right);

			answer.append(cur.value).append("\n");
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			answer = new StringBuilder();
			root = new Node(Integer.parseInt(br.readLine()));

			while (true) {
				String input = br.readLine();

				if (input == null || input.equals("")) break;

				root.insert(Integer.parseInt(input));
			}
		}

		private void output() {
			System.out.println(answer);
		}
	}

	private static class Node {
		int value;
		Node left, right;

		public Node(int value) {
			this.value = value;
		}

		void insert(int n) {
			if (n < this.value) {
				if (this.left == null) this.left = new Node(n);
				else this.left.insert(n);
			} else {
				if (this.right == null) this.right = new Node(n);
				else this.right.insert(n);
			}
		}
	}
}
