import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();

		solution(a, b);
	}

	private static void solution(int a, int b) {
		int ans = 0;
		for (int i = a; i <= b; i++) {
			int factors = factors(i).size();
			if (isPrime(factors)) ans++;
		}

		System.out.println(ans);
	}

	// 소인수 목록을 반환하는 메서드
	private static List<Integer> factors(int n) {
		List<Integer> result = new ArrayList<>();

		while (n % 2 == 0) {
			result.add(2);
			n /= 2;
		}

		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			while (n % i == 0) {
				result.add(i);
				n /= i;
			}
		}

		if (n > 1) result.add(n);

		return result;
	}

	// 소수 판별
	private static boolean isPrime(int n) {
		if (n < 2) return false; // 0과 1은 소수 x

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}

		return true;
	}
}