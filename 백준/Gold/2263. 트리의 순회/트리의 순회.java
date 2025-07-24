import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		Tree data = input();
		int[] answer = solution(data);
		output(answer);
	}

	private static int[] solution(Tree data) {
		dfs(0, data.in.length - 1, 0, data.in.length - 1, 0, data);
		return data.pre;
	}

	private static int dfs(int is, int ie, int ps, int pe, int idx, Tree data) {
		if (is > ie || ps > pe) return idx;

		data.pre[idx] = data.post[pe];
		int pos = is;

		for (int i = is; i <= ie; i++) {
			if (data.in[i] == data.post[pe]) {
				pos = i;
				break;
			}
		}

		idx = dfs(is, pos - 1, ps, ps + pos - is - 1, idx + 1, data);
		idx = dfs(pos + 1, ie, ps + pos - is, pe - 1, idx, data);

		return idx;
	}

	private static Tree input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] post = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		br.close();

		return new Tree(in, post);
	}

	private static void output(int[] answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(IntStream.of(answer).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		bw.flush();
		bw.close();
	}

	private static class Tree {
		int[] in;
		int[] post;
		int[] pre;

		public Tree(int[] in, int[] post) {
			this.in = in;
			this.post = post;
			this.pre = new int[in.length];
		}
	}
}