import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		long[] input = input();
		long answer = solution(input[0], input[1]);
		output(answer);
	}

	private static long solution(long min, long max) {
		int len = (int) (max - min + 1);
		boolean[] prime = prime(min, max, len);

		return IntStream.range(0, len)
			.mapToObj(index -> prime[index])
			.filter(it -> !it)
			.count();
	}

	private static boolean[] prime(long min, long max, int len) {
		boolean[] prime = new boolean[len];

		for (long i = 2; i <= Math.sqrt(max); i++) {
			long num = i * i;
			long start = min % num == 0 ? min / num : (min / num) + 1;

			for (long j = start; j * num <= max; j++) {
				prime[(int) (j * num - min)] = true;
			}
		}

		return prime;
	}

	private static long[] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
	}

	private static void output(long answer) {
		System.out.println(answer);
	}
}