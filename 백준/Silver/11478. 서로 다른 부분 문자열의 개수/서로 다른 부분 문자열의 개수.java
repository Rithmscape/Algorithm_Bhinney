import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		new Solution().solve();
	}

	private static class Solution {
		private String target;
		private Set<String> set;

		public void solve() {
			input();
			solution();
			output();
		}

		private void solution () {
			set = new HashSet<>();

			for (int i = 0; i < target.length(); i++) {
				for (int j = i + 1; j < target.length() + 1; j++) {
					set.add(target.substring(i, j));
				}
			}
		}

		private void input() {
			Scanner sc = new Scanner(System.in);
			target = sc.next();
			sc.close();
		}

		private void output() {
			System.out.println(set.size());
		}
	}
}