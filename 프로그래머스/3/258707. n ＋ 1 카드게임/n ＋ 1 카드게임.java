import java.util.HashSet;
import java.util.Set;

class Solution {
    /*
	 1. 처음 시작하는 카드 뭉치 안에서 n + 1 만들기
	 2. 추가로 뽑은 카드에서 n + 1 만들기 (동전 1개)
	 3. 추가로 뽑은 카드 두 장으로 n + 1 만들기 (동전 2개)
	 4. 여기까지 오면 더 이상 게임 진행 불가
	 */
	public int solution(int coin, int[] cards) {
		int answer = 0; // return할 답을 담을 변수
		int length = cards.length; // 카드의 길이(n)
		int index = length / 3; // 들고 있는 카드 개수

		Set<Integer> origin = init(cards, index); // 들고 있는 카드
		Set<Integer> addict = new HashSet<>(); // 추가 카드

		int target = length + 1;

		while (true) {
			answer++;

			if (index >= length) break;

			addict.add(cards[index]);
			addict.add(cards[index + 1]);
			index += 2;

			boolean flag = false;

			if (step1(origin, target)) {
				flag = true;
			} else if (coin >= 1 && step2(origin, addict, target)) {
				coin--;
				flag = true;
			} else if (coin >= 2 && step3(addict, target)) {
				coin -= 2;
				flag = true;
			}

			if (!flag) break;


		}

		return answer;
	}

	// step 1. 처음 시작하는 카드 뭉치 안에서 n + 1 만들기
	private boolean step1(Set<Integer> origin, int target) {
		for (int num : origin) {
			if (!origin.contains(target - num)) continue;

			origin.remove(num);
			origin.remove(target - num);
			return true;
		}

		return false;
	}


	// step 2. 추가로 뽑은 카드에서 n + 1 만들기 (동전 1개)
	private boolean step2(Set<Integer> origin, Set<Integer> addict, int target) {
		for (int num : origin) {
			if (!addict.contains(target - num)) continue;

			origin.remove(num);
			addict.remove(target - num);

			return true;
		}

		return false;
	}

	// step 3. 추가로 뽑은 카드 두 장으로 n + 1 만들기 (동전 2개)
	private boolean step3(Set<Integer> addict, int target) {
		for (int num : addict) {
			if (!addict.contains(target - num)) continue;

			addict.remove(num);
			addict.remove(target - num);

			return true;
		}

		return false;
	}

	private Set<Integer> init(int[] cards, int size) {
		Set<Integer> origin = new HashSet<>();

		for (int i = 0; i < size; i++)
			origin.add(cards[i]);

		return origin;
	}
}