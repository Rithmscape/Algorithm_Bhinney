import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 테스트 개수

		for (int i = 0; i < n; i++) {
			TreeMap<Integer, Integer> tree = new TreeMap<>();
			int t = Integer.parseInt(br.readLine());

			for (int j = 0; j < t; j++) {
				String[] arr = br.readLine().split(" ");
				int num = Integer.parseInt(arr[1]);

				if (arr[0].equals("I"))
					tree.put(num, tree.getOrDefault(num, 0) + 1);
				else {
                    if (tree.isEmpty()) continue;
					int m = num == 1 ? tree.lastKey() : tree.firstKey();
					if (tree.put(m, tree.get(m) - 1) == 1)
						tree.remove(m);
				}
			}

			if (tree.isEmpty())
				System.out.println("EMPTY");
			else
				System.out.println(tree.lastKey() + " " + tree.firstKey());
		}
	}
}