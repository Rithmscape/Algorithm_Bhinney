import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public static int solution(String word, String[] pages) {
		word = word.toLowerCase(); // 편하게 처리하기 위해 다 소문자로 치환

		String[] urls = new String[pages.length];
		int[] basics = new int[pages.length];
		List<List<String>> links = new ArrayList<>();
		Map<String, Integer> urlToIndex = new HashMap<>();

		parsing(pages, word, urls, basics,links, urlToIndex);

		double[] scores = new double[pages.length];
		score(basics, links, urlToIndex, scores);

		return find(basics, scores);
	}

	// 가장 높은 점수를 가진 인덱스 찾기
	private static int find(int[] basics, double[] scores) {
		int idx = 0;
		double max = basics[0] + scores[0];

		for (int i = 0; i < basics.length; i++) {
			double sum = basics[i] + scores[i];

			if (sum <= max) continue;

			idx = i;
			max = sum;
		}

		return idx;
	}

	// 링크 점수 계산
	private static void score(int[] basics, List<List<String>> links, Map<String, Integer> urlToIndex, double[] scores) {
		for (int i = 0; i < scores.length; i++) {
			for (String link : links.get(i)) {
				Integer target = urlToIndex.get(link);

				if (target == null) continue;

				scores[target] += (double) basics[i] / links.get(i).size();
			}
		}
	}

	// 페이지 파싱
	private static void parsing(String[] pages, String word,
		String[] urls, int[] basics, List<List<String>> links, Map<String, Integer> urlToIndex) {
		for (int i = 0; i < pages.length; i++) {
			String page = pages[i];

			urls[i] = url(page);
			urlToIndex.put(urls[i], i);

			basics[i] = basic(page, word);

			links.add(external(page));
		}
	}

	// 계산
	private static int basic(String page, String word) {
		Pattern bodyPattern = Pattern.compile("<body>(.*?)</body>", Pattern.DOTALL);
		Matcher bodyMatcher = bodyPattern.matcher(page);

		if (!bodyMatcher.find()) return 0;

		String body = bodyMatcher.group(1);
		String content = body.replaceAll("<[^>]*>", " ").toLowerCase();

		Pattern wordPattern = Pattern.compile("(?<![a-zA-Z])" + Pattern.quote(word) + "(?![a-zA-Z])");
		Matcher wordMatcher = wordPattern.matcher(content);

		int count = 0;
		while (wordMatcher.find()) {
			count++;
		}

		return count;
	}

	// url 추출
	private static String url(String page) {
		Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"([^\"]+)\"");
		Matcher matcher = pattern.matcher(page);
		return matcher.find() ? matcher.group(1) : "";
	}

	// 외부 링크 추출
	private static List<String> external(String page) {
		List<String> links = new ArrayList<>();
		Pattern pattern = Pattern.compile("<a href=\"([^\"]+)\"");
		Matcher matcher = pattern.matcher(page);

		while (matcher.find()) {
			links.add(matcher.group(1));
		}

		return links;
	}
}