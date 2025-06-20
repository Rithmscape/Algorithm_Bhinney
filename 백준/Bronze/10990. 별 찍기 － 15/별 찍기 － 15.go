package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var res = strings.Repeat(" ", n-1) + "*\n"
	for i := 2; i <= n; i++ { // 2 * n - 1
		res += strings.Repeat(" ", n-i) + "*" + strings.Repeat(" ", 2*(i-1)-1) + "*\n"
	}

	fmt.Print(res)
}