import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Map<String, String> map = new HashMap<>();

		for (int i = 0; i < arr[0]; i++) {
			String[] str = br.readLine().split(" ");
			map.put(str[0], str[1]);
		}

		for (int i = 0; i < arr[1]; i++) {
			System.out.println(map.get(br.readLine()));
		}

		br.close();
	}
}