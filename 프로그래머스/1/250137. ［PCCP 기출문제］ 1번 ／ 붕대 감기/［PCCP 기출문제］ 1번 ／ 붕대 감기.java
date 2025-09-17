class Solution {
    public static int solution(int[] bandage, int health, int[][] attacks) {
		/*
		 * bandage = [시전 시간, 초당 회복량, 추가 회복량]
		 * health : 최대 체력
		 * attacks[i] = [공격 시간, 피해량]
		 */

		int[] allocate = allocate(attacks);
		int sequence = 0;
		int current = health;
		
		for (int al : allocate) {
			if (al != 0) {
				current -= al;
				sequence = 0;
				
				if (current <= 0) return -1;

				continue;
			}

			sequence++;
			current = Math.min(current + bandage[1], health);
			if (sequence == bandage[0]) {
				current = Math.min(current + bandage[2], health);
				sequence = 0;
			}
		}

		return current;
	}

	private static int[] allocate(int[][] attacks) {
		int max = -1;
		for (int[] attack : attacks)
			max = Math.max(max, attack[0]);

		int[] result = new int[max + 1];
		for (int[] attack : attacks)
			result[attack[0]] = attack[1];

		return result;
	}
}