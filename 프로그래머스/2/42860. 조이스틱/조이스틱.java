class Solution {
    public int solution(String name) {
        /*
		 아스키 코드 문자표
	 	 * A : 65
	 	 * Z : 90

		 움직이는 방법
		 * 위 : 다음 알파벳 name[i] - 65
		 * 아래 : 이전 알파벳 (A 이면 Z) 90 - name[i]
		 * 왼쪽 : 왼쪽으로 이동 (첫 문자에서 왼쪽이면 마지막 문자)
	 	 * 오른쪽 : 오른쪽으로 이동 (마지막 문자에서 오른쪽이면 첫 문자)
		 */
		int answer = 0;
		int move = name.length() - 1; // 최소 조작 횟수
		for (int i = 0; i < name.length(); i++) {
			int up = name.charAt(i) - 65;
			int down = 90 - name.charAt(i) + 1;

			answer += Math.min(up, down);

			int next = i + 1;
			while (next < name.length() && name.charAt(next) == 'A') next++;

			// 좌, 우 중에서 어디로 이동하는게 더 적게 이동하는가
			move = Math.min(move, (i * 2) + name.length() - next);
			move = Math.min(move, (name.length() - next) * 2 + i);
		}

		answer += move;

		return answer; 
    }
}