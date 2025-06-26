package main

import (
	"fmt"
	"math"
)

func main() {
	var n int
	fmt.Scan(&n)

	for i := 0; i < n; i++ {
		var m int
		fmt.Scan(&m)

		result := isSquareNum(m)
		fmt.Println(result)
	}
}

func isSquareNum(m int) int {
	if m < 0 {
		return 0
	}

	if m == 1 {
		return 1
	}

	root := int(math.Sqrt(float64(m)))
	if root*root == m {
		return 1
	} else {
		return 0
	}
}
