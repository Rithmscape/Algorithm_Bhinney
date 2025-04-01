import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	static int n, len;
	static char[] arr;

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		len = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		br.close();
	}

	private static void solution() {
		int cnt = 0, ans = 0;
		for (int i = 0; i < len - 2; i++) {
			if (arr[i] == 'I' && arr[i + 1] == 'O' && arr[i + 2] == 'I') {
				cnt++;
				i++;

				if (cnt == n) {
					cnt--;
					ans++;
				}
			} else cnt = 0;
		}

		System.out.println(ans);
	}
}