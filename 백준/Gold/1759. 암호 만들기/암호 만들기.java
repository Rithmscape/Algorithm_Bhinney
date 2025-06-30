import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData input = input();
		List<String> result = solution(input.l, input.c, input.alphabet);
		output(result);
	}

	private static List<String> solution(int l, int c, String[] alphabet) {
		Arrays.sort(alphabet); // 사전 순으로 정렬하기
		String[] arr = new String[l];
		List<String> result = new ArrayList<>();
		dfs(alphabet, 0, 0, result, arr);

		return result;
	}

	private static void dfs(String[] alphabet, int start, int depth, List<String> result, String[] arr) {
		if (depth == arr.length) {
			String password = String.join("", arr);
			if (isValidPassword(password))
				result.add(password);

			return;
		}

		for (int i = start; i < alphabet.length; i++) {
			arr[depth] = String.valueOf(alphabet[i]);
			dfs(alphabet, i + 1, depth + 1, result, arr);
		}
	}

	private static boolean isValidPassword(String password) {
		long vowel = password.chars()
			.filter(it -> it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u')
			.count();
		long consonant = password.length() - vowel;

		return vowel >= 1 && consonant >= 2;
	}

	private static InputData input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] lc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		String[] arr = br.readLine().split(" ");

		return new InputData(lc[0], lc[1], arr);
	}

	private static void output(List<String> result) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.join("\n", result));
		bw.close();
	}

	private static class InputData{
		int l;
		int c;
		String[] alphabet;

		public InputData(int l, int c, String[] alphabet) {
			this.l = l;
			this.c = c;
			this.alphabet = alphabet;
		}
	}
}
