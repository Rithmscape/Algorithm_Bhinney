package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	var pattern1 = "*" + strings.Repeat(" *", n-1) + "\n"
	var pattern2 = strings.Repeat(" *", n) + "\n"

	var res string
	for i := 1; i <= n; i++ {
		if i%2 != 0 { // 홀수
			res += pattern1
		} else {
			res += pattern2
		}
	}

	fmt.Print(res)
}
