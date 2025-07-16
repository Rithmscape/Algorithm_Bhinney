func solution(n int) int {
	var ans int
	for n > 10 {
		ans += n % 10
		n /= 10
	}

	return ans + n
}