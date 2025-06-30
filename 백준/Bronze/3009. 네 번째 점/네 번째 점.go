package main

import "fmt"

func main() {
	var x1, y1, x2, y2, x3, y3 int
	fmt.Scan(&x1, &y1)
	fmt.Scan(&x2, &y2)
	fmt.Scan(&x3, &y3)

	x := x1 ^ x2 ^ x3
	y := y1 ^ y2 ^ y3

	fmt.Printf("%d %d", x, y)
}
