package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var pattern = "* "
	var res = strings.Repeat(" ", n-1) + "*\n"
	for i := 2; i <= n; i++ {
		res += strings.Repeat(" ", n-i) + strings.Repeat(pattern, i-1) + "*\n"
	}

	fmt.Print(res)
}
