package main

import "fmt"

func main() {
	var a int
	fmt.Scan(&a)

	if a == 1 {
		fmt.Print(4)
		return
	}

	if a == 2 {
		fmt.Print(8)
		return
	}

	prev1 := 4
	prev2 := 8
	var cur int

	for i := 3; i <= a; i++ {
		cur = prev1 + prev2

		prev2 = prev1
		prev2 = cur
	}

	fmt.Print(cur)
}
