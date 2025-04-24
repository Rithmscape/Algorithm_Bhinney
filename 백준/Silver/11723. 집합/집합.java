import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < m; i++) {
			String[] arr = br.readLine().split(" ");
			int num = (arr.length > 1) ? Integer.parseInt(arr[1]) : 0;

			switch (arr[0]) {
				case "add" : {
					set.add(num);
					break;
				}
				case "remove" : {
					set.remove(num);
					break;
				}
				case "check" : {
					if (set.contains(num))
						res.append(1).append("\n");
					else
						res.append(0).append("\n");

					break;
				}
				case "toggle" : {
					if (set.contains(num))
						set.remove(num);
					else set.add(num);

					break;
				}
				case "all" : {
					set = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toSet());
					break;
				}
				case "empty" : {
					set.clear();
					break;
				}
			}
		}

		System.out.println(res.toString());
	}
}