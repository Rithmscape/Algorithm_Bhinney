import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		add();
		solution();
	}

	private static int t, n, m;
	private static int[] a, b;
	private static List<Integer> al;
	private static List<Integer> bl;

	private static void solution() {
		// 각 리스트 포인터 생성
		int ap = al.size(), bp = bl.size();
		long cnt = 0;
		int left = 0, right = bp - 1;

		while (left < ap && right >= 0) {
			int a = al.get(left), b = bl.get(right);
			int sum = a + b;
			if (sum == t) {
				long ac = 0, bc = 0;

				// 같은 수가 여러 개 나열 되어 있을 수 있기 때문에 카운팅 해주기
				while (left < ap && al.get(left) == a) {
					left++;
					ac++;
				}

				while (right >= 0 && bl.get(right) == b) {
					right--;
					bc++;
				}

				cnt += ac * bc;
			}

			if (sum > t) right--;
			else if (sum < t) left++;
		}

		System.out.println(cnt);
	}

	// 누적 합을 더해서 정렬하는 메서드
	private static void add() {
		al = new ArrayList<>();
		bl = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += a[j];
				al.add(sum);
			}
		}

		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				sum += b[j];
				bl.add(sum);
			}
		}

		Collections.sort(al);
		Collections.sort(bl);
	}

	// 입력을 받는 메서드
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		m = Integer.parseInt(br.readLine());
		b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		br.close();
	}
}