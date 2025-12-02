import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		sc.close();

		ArrayList<String>[] arr = new ArrayList[n + 3];
		init(arr, n);
		tracking(arr, n);

		Collections.sort(arr[n]);
		String result = arr[n].size() < k ? "-1" : arr[n].get(k - 1);
		System.out.println(result);
	}

	private static void init(ArrayList<String>[] arr, int n) {
		for (int i = 0; i < n + 3; i++)
			arr[i] = new ArrayList<>();

		arr[1].add("1");
		arr[2].add("1+1");
		arr[2].add("2");
		arr[3].add("1+2");
		arr[3].add("1+1+1");
		arr[3].add("2+1");
		arr[3].add("3");
	}

	private static void tracking(ArrayList<String>[] arr, int n) {
		for (int i = 4; i <= n; i++) {
			for (int j = 1; j <= 3; j++) {
				for (String s : arr[i - j])
					arr[i].add(s + "+" + j);
			}
		}
	}
}
