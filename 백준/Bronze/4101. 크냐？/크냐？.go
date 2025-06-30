package main

import "fmt"

func main() {
	for {
		var a, b int
		fmt.Scan(&a, &b)

		if a == 0 && b == 0 {
			break
		}

		if a > b {
			fmt.Println("Yes")
		} else {
			fmt.Println("No")
		}
	}
}
