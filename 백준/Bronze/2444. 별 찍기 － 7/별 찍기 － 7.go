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
		res += strings.Repeat(" ", n-i) + strings.Repeat("*", 2*i-1) + "\n"
	}

	for i := n - 1; i > 0; i-- {
		res += strings.Repeat(" ", n-i) + strings.Repeat("*", 2*i-1) + "\n"
	}

	fmt.Print(res)
}
