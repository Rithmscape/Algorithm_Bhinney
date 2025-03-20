import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];

		for (int i = 0; i < n; i++)
			arr[i] = br.readLine();

		br.close();
		solution(arr);
	}

	private static void solution(String[] arr) {

		// 길이를 비교하여 긴 순으로 정렬
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o2.length(), o1.length());
			}
		});

		Set<String> set = new HashSet<>();
		for (String s1 : arr) {
			if (set.isEmpty()) {
				set.add(s1);
				continue;
			}

			boolean check = true;
			for (String s2 : set) {
				if (s2.indexOf(s1) == 0) {
					check = false;
					break;
				}
			}

			if (check) set.add(s1);
		}

		System.out.println(set.size());
	}
}