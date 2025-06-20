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
		res += strings.Repeat(" ", n-i) + strings.Repeat("*", i) + "\n"
	}

	for i := 1; i <= n; i++ {
		res += strings.Repeat(" ", i) + strings.Repeat("*", n-i) + "\n"
	}

	fmt.Print(res)
}
