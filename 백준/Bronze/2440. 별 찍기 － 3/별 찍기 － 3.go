package main

import (
	"fmt"
	"strings"
)

func main() {
	var n int
	fmt.Scan(&n)

	for i := n; i > 0; i-- {
		fmt.Println(strings.Repeat("*", i))
	}
}
