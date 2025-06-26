import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		List<Map<String, Integer>> input = input();

		for (Map<String, Integer> clothes : input)
			output(solution(clothes));
	}

	private static int solution(Map<String, Integer> clothes) {
		int result = 1;

		for (int value : clothes.values())
			result *= value + 1;

		return result - 1;
	}

	private static List<Map<String, Integer>> input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Map<String, Integer>> result = new ArrayList<>();
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		for (int i = 0; i < tc; i++) {
			int n = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();

			for (int j = 0; j < n; j++){
				String[] arr = br.readLine().split(" ");
				map.put(arr[1], map.getOrDefault(arr[1], 0) + 1);
			}

			result.add(map);
		}

		return result;
	}

	private static void output(int n) {
		System.out.println(n);
	}
}