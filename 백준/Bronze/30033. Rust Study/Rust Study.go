package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	n, _ := strconv.Atoi(scanner.Text())

	scanner.Scan()
	targetStr := strings.Fields(scanner.Text())
	targets := make([]int, n)
	for i, s := range targetStr {
		targets[i], _ = strconv.Atoi(s)
	}

	scanner.Scan()
	achieveStr := strings.Fields(scanner.Text())
	achieves := make([]int, n)
	for i, s := range achieveStr {
		achieves[i], _ = strconv.Atoi(s)
	}

	res := 0

	for i := 0; i < n; i++ {
		if targets[i] <= achieves[i] {
			res++
		}
	}

	fmt.Print(res)
}
