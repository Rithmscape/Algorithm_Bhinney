package main

import "fmt"

func main() {
	var n , f int
	fmt.Scan(&n, &f)
	base := (n / 100) * 100

	for i := 0; i < 100; i++ {
		num := base + i
		if num % f == 0 {
			fmt.Printf("%02d", i)
			break
		}
	}
}