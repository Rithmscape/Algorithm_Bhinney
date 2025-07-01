package main

import "fmt"

func main() {
	var t int
	fmt.Scan(&t)

	for i := 0; i < t; i++ {
		var a, b int
		fmt.Scan(&a, &b)

		var answer = getLastDigit(a, b)
		fmt.Println(answer)
	}
}

func getLastDigit(a, b int) int {
	a = a % 10

	if b == 0 {
		return 1
	}

	patterns := map[int][]int{
		0: {10},
		1: {1},
		2: {2, 4, 8, 6},
		3: {3, 9, 7, 1},
		4: {4, 6},
		5: {5},
		6: {6},
		7: {7, 9, 3, 1},
		8: {8, 4, 2, 6},
		9: {9, 1},
	}

	pattern := patterns[a]
	return pattern[(b-1)%len(pattern)]
}
