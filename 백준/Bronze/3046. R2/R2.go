package main

import "fmt"

func main() {
	var r, s int
	fmt.Scan(&r, &s)

	r2 := s*2 - r
	fmt.Print(r2)
}
