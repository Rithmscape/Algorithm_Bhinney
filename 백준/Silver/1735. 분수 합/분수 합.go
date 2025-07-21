package main

import "fmt"

func main() {
	var a, b int
	var c, d int
	fmt.Scan(&a, &b, &c, &d)

	numerator := (a * d) + (c * b)
	denominator := b * d

	gcd := gcd(numerator, denominator)
	numerator /= gcd
	denominator /= gcd

	fmt.Printf("%d %d", numerator, denominator)
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	}

	return gcd(b, a%b)
}
