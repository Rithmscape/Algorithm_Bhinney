import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // test case 개수

		StringBuilder sb = new StringBuilder();

		while (tc-- > 0){
			List<Integer> list = new ArrayList<>();

			int len = Integer.parseInt(br.readLine());
			int range = len % 10 == 0 ? len / 10 : len / 10 + 1; // 한 줄에 10개씩 입력 가능
			int count = 0; // 한 줄에 10개씩 출력 가능

			sb.append((len + 1) / 2).append("\n"); // 중앙값 먼저 출력

			for (int i = 0; i < range; i++) {
				String[] arr = br.readLine().split(" ");

				for (String s : arr) {
					list.add(Integer.parseInt(s));

					if (list.size() % 2 == 0)
						continue;

					Collections.sort(list);
					sb.append(list.get(list.size() / 2)).append(" ");
					count++;

					if (count == 10) {
						sb.append("\n");
						count = 0;
					}
				}
			}

			sb.append("\n");
		}

		br.close();

		System.out.println(sb.toString());
	}
}