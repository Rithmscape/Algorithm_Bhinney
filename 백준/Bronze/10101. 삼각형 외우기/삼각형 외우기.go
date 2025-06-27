package main

import "fmt"

func main() {
	var a, b, c int
	fmt.Scan(&a, &b, &c)

	if a+b+c != 180 {
		fmt.Print("Error")
		return
	}

	switch {
	case a == 60 && b == 60 && c == 60:
		fmt.Print("Equilateral")
	case a == b || b == c || c == a:
		fmt.Print("Isosceles")
	default:
		fmt.Print("Scalene")
	}
}
