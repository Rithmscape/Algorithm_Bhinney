import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
		output();
	}
	private static int N, M;
	private static int[] arr;
	private static List<String> list;
	private static void solution() {
		list = new ArrayList<>();
		int[] res = new int[M];
		comb(res, 0, 0);
	}
	private static void comb(int[] res, int idx, int depth) {
		if (depth == M) {
			list.add(IntStream.of(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
			return;
		}

		for (int i = idx; i < N; i++) {
			res[depth] = arr[i];
			comb(res, i, depth + 1);
		}
	}
	private static void output() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.join("\n", list));
		bw.flush();
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		br.close();
		N = nm[0];
		M = nm[1];
	}
}