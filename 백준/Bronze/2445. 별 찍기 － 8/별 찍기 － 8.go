package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var res string
	for i := 1; i <= n; i++ {
		res += strings.Repeat("*", i) + strings.Repeat(" ", 2*(n-i)) + strings.Repeat("*", i) + "\n"
	}

	for i := n - 1; i > 0; i-- {
		res += strings.Repeat("*", i) + strings.Repeat(" ", 2*(n-i)) + strings.Repeat("*", i) + "\n"
	}

	fmt.Print(res)
}
