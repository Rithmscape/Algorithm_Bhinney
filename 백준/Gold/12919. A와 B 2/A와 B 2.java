import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String target = br.readLine();
		br.close();

		boolean result = dfs(str, target);
		System.out.println(result ? 1 : 0);
	}

	private static boolean dfs(String str, String target) {
		boolean check = false;

		if (str.length() >= target.length()) {
			return str.equals(target);
		}

		if (target.charAt(target.length() - 1) == 'A')
			check |= dfs(str, target.substring(0, target.length() - 1));

		if (target.charAt(0) == 'B')
			check |= dfs(str, new StringBuilder().append(target).reverse().substring(0, target.length() - 1));

		return check;
	}
}