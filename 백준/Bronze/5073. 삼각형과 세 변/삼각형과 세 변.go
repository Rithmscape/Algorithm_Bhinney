package main

import (
	"fmt"
	"math"
)

func main() {
	for {
		var a, b, c float64
		fmt.Scan(&a, &b, &c)

		if a == 0 && b == 0 && c == 0 {
			break
		}

		max := math.Max(a, math.Max(b, c))
		sum := a + b + c

		if max >= sum-max {
			fmt.Println("Invalid")
		} else if a == b && b == c {
			fmt.Println("Equilateral")
		} else if a == b || b == c || c == a {
			fmt.Println("Isosceles")
		} else {
			fmt.Println("Scalene")
		}
	}
}
