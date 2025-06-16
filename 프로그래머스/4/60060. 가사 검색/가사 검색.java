import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] words, String[] queries) {
		Map<Integer, TrieNode> forward = new HashMap<>();
		Map<Integer, TrieNode> backward = new HashMap<>();
		for(String word : words) {
			int len = word.length();
			forward.putIfAbsent(len, new TrieNode());
			forward.get(len).insert(word);

			backward.putIfAbsent(len, new TrieNode());
			backward.get(len).insert(new StringBuilder(word).reverse().toString());
		}

		int[] ans = new int[queries.length];
		for(int i = 0; i < queries.length; i++) {
			String query = queries[i];
			int len = query.length();

			if (query.startsWith("?")) {
				TrieNode node = backward.get(len);

				if (node != null) {
					String reverse = new StringBuilder(query).reverse().toString();
					ans[i] = node.search(reverse, 0);
				}
			} else {
				TrieNode node = forward.get(len);

				if (node != null)
					ans[i] = node.search(query, 0);
			}
		}

		return ans;
	}

	private class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		Map<Integer, Integer> count = new HashMap<>();

		public void insert(String word) {
			TrieNode cur = this;
            cur.count.merge(word.length(), 1, Integer::sum);

			for (char ch : word.toCharArray()) {
				cur.children.putIfAbsent(ch, new TrieNode());
				cur = cur.children.get(ch);
				cur.count.merge(word.length(), 1, Integer::sum);
			}
		}

		public int search(String query, int idx) {
			if (idx == query.length()) return count.getOrDefault(query.length(), 0);

			char ch = query.charAt(idx);
			if (ch == '?') return count.getOrDefault(query.length(), 0);

			TrieNode child = children.get(ch);
			if (child == null) return 0;

			return child.search(query, idx + 1);
		}
	}
}