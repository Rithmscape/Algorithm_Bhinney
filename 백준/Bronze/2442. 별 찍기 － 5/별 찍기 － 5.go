package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	for i := 1; i <= n; i++ {
		fmt.Println(strings.Repeat(" ", n-i) + strings.Repeat("*", 2*i-1))
	}
}