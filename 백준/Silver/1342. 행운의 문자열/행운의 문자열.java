import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int ans = 0;
	static int[] alpha = new int[26];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		br.close();

		for (int i = 0; i < str.length(); i++)
			alpha[str.charAt(i) - 'a']++;

		dfs(0, str.length(), ' ');
		System.out.println(ans);
	}

	public static void dfs(int idx, int len, char ch) {
		if (idx == len) {
			ans++;
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (alpha[i] == 0) continue;

			if (ch != (char) i + 'a') {
				alpha[i]--;
				dfs(idx + 1, len, (char)(i + 'a'));
				alpha[i]++;
			}
		}
	}
}