import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] input = input();
		int answer = solution(input);
		output(answer);
	}

	private static int solution(int[] numbers) {
		List<boolean[]> check = new ArrayList<>();
		boolean[] arr = new boolean[10];
		check.add(arr);

		for (int num : numbers) {
			assign(num, check);
		}

		return check.size();
	}

	private static void assign(int num, List<boolean[]> check) {
		for (boolean[] booleans : check) {
			if (!booleans[num]) { // 해당 숫자에 배정이 안되어 있으면 배정하고 return
				booleans[num] = true;
				return;
			}

			if (num == 6 || num == 9) { // 만약에 6이나 9일 경우, 다른 숫자 확인
				int other = num == 6 ? 9 : 6;
				if (!booleans[other]) {
					booleans[other] = true;
					return;
				}
			}
		}

		// 여기까지 왔다는 것은 배정이 안되었다는 것
		boolean[] arr = new boolean[10];
		arr[num] = true;
		check.add(arr);
	}

	private static int[] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
	}

	private static void output(int answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}