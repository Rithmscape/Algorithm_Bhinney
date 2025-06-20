package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var res string
	for i := 0; i < n; i++ {
		res += strings.Repeat(" ", i) + strings.Repeat("*", 2*(n-i)-1) + "\n"
	}

	for i := 2; i <= n; i++ {
		res += strings.Repeat(" ", n-i) + strings.Repeat("*", 2*i-1) + "\n"
	}

	fmt.Print(res)
}
