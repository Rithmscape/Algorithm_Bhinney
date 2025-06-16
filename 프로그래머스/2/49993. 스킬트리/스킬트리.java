import java.util.Arrays;


class Solution {
    public int solution(String skill, String[] skill_trees) {
		String[] filter
			= Arrays.stream(skill_trees)
			.map(it -> it.replaceAll("[^" + skill + "]", ""))
			.toArray(String[]::new);

		char[] skills = skill.toCharArray(); // 인덱스로 편하게 비교하기 위함
		int answer = 0;
		for (String st : filter) {
			if (st.equals(skill)) { // 두 문자가 같으면 그냥 바로 넘기기
				answer++;
				continue;
			}

			char[] arr = st.toCharArray();
			boolean check = true;
			for (int i = 0; i < arr.length; i++) {
				if (skills[i] != arr[i]) {
					check = false;
					break;
				}
			}

			if (check) answer++;
		}

		return answer;
	}
}