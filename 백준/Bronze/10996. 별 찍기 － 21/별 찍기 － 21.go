package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)
	if n == 1 {
		fmt.Print("*")
		return
	}

	var pattern = makePattern(n)
	var res = strings.Repeat(pattern, n)
	fmt.Print(res)
}

func makePattern(n int) string {
	var p1 = n / 2
	var p2 = n - p1

	var res = "*" + strings.Repeat(" *", p2-1) + "\n"
	res += strings.Repeat(" *", p1) + "\n"
	return res
}
