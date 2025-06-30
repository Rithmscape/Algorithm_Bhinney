package main

import (
	"fmt"
	"sort"
)

func main() {
	var numbers [3]int
	fmt.Scan(&numbers[0], &numbers[1], &numbers[2])

	slice := numbers[:]
	sort.Ints(slice)

	fmt.Printf("%d %d %d", slice[0], slice[1], slice[2])
}
