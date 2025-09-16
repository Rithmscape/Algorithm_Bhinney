import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < park.length; i++)
			for (int j = 0; j < park[0].length; j++)
				if (park[i][j].equals("-1"))
					max = Math.max(max, getMaxSize(i, j, park));

		Arrays.sort(mats);

		for (int i = mats.length - 1; i >= 0; i--)
			if (mats[i] <= max)
				return mats[i];
		return -1;
	}

	private int getMaxSize(int x, int y, String[][] park) {
		int max = 1;

		for (int size = 1; x + size <= park.length && y + size <= park[0].length; size++) {
			boolean place = true;

			for (int i = x; i < x + size && place; i++)
				for (int j = y; j < y + size && place; j++)
					if (!park[i][j].equals("-1"))
						place = false;

			if (place) max = size;
			else break;
		}

		return max;
	}
}