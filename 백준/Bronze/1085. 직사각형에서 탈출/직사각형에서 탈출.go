package main

import (
	"fmt"
)

func main() {
	var x, y, w, h int
	fmt.Scan(&x, &y, &w, &h)

	toZero := min(x, y)
	toWOrH := min(w-x, h-y)
	fmt.Println(min(toZero, toWOrH))
}
