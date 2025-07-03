package main

import "fmt"

func main() {
	var a, b int
	fmt.Scan(&a, &b)

	if 2*a >= b {
		fmt.Print("E")
	} else {
		fmt.Print("H")
	}
}