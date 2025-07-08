class Solution {
   public int[] solution(long[] numbers) {
		int[] answer = new int[numbers.length];

		for (int i = 0; i < answer.length; i++) {
			String binary = binary(numbers[i]);
			answer[i] = valid(binary, 0, binary.length() - 1) ? 1 : 0;
		}

		return answer;
	}

	private String binary(long number) {
		String binary = Long.toBinaryString(number);
		int size = getTreeSize(binary.length());
		return "0".repeat(Math.max(0, size - binary.length())) + binary;
	}

	private boolean valid(String binary, int start, int end) {
		if (start >= end) return true;

		int mid = (start + end) / 2;
		char root = binary.charAt(mid);

		if (root == '0')
			return zero(binary, start, mid - 1) && zero(binary, mid + 1, end);

		return valid(binary, start, mid - 1) && valid(binary, mid + 1, end);
	}

	private boolean zero(String binary, int start, int end) {
		for (int i = start; i <= end; i++)
			if (binary.charAt(i) == '1') return false;

		return true;
	}

	private int getTreeSize(int length) {
		int size = 1;

		while (size < length)
			size = size * 2 + 1;

		return size;
	}
}