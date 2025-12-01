import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		long[] arr = new long[1_000_001];
		dp(arr);

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while (tc-- > 0) {
			int n = sc.nextInt();
			System.out.println(arr[n]);
		}
	}

	private static void dp(long[] arr){
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;

		for (int i = 4; i < 1_000_001; i++)
			arr[i] = (arr[i - 3] + arr[i - 2] + arr[i - 1]) % 1_000_000_009;
	}
}