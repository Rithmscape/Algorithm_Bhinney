package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var res = strings.Repeat(" ", n-1) + "*\n"

	for i := 2; i <= n; i++ {
		if i != n {
			res += strings.Repeat(" ", n-i) + "*" + strings.Repeat(" ", 2*(i-1)-1) + "*\n"
		} else {
			res += strings.Repeat("*", 2*i-1)
		}
	}

	fmt.Print(res)
}
