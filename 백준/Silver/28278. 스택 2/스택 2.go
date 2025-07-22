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
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	scanner.Scan()
	n, _ := strconv.Atoi(scanner.Text())

	stack := make([]int, 0, n)

	for i := 0; i < n; i++ {
		scanner.Scan()
		command := strings.Fields(scanner.Text())

		switch command[0] {
		case "1": // push
			x, _ := strconv.Atoi(command[1])
			stack = append(stack, x)
		case "2": // 맨 위 정수 빼고 출력, 없으면 -1
			if len(stack) == 0 {
				fmt.Fprintln(writer, -1)
			} else {
				fmt.Fprintln(writer, stack[len(stack)-1])
				stack = stack[:len(stack)-1]
			}
		case "3": // size
			fmt.Fprintln(writer, len(stack))
		case "4": // isEmpty?
			if len(stack) == 0 {
				fmt.Fprintln(writer, 1)
			} else {
				fmt.Fprintln(writer, 0)
			}
		case "5": // peek
			if len(stack) == 0 {
				fmt.Fprintln(writer, -1)
			} else {
				fmt.Fprintln(writer, stack[len(stack)-1])
			}
		}
	}
}
