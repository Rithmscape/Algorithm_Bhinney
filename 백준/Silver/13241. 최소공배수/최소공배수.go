package main

import "fmt"

func main() {
	var a, b int
	fmt.Scan(&a, &b)
	gcd := gcd(a, b)
	lcm := lcm(a, b, gcd)
	fmt.Print(lcm)
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a int, b int, gcd int) int {
	return a * b / gcd
}
