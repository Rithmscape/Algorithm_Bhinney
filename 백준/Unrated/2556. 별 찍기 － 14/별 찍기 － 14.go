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
		res += strings.Repeat("*", n) + "\n"
	}

	fmt.Print(res)
}
