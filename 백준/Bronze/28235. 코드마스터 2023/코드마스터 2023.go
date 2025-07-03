package main

import "fmt"

func main() {
	var code string
	fmt.Scan(&code)

	var answer string

	switch code {
	case "SONGDO":
		answer = "HIGHSCHOOL"
	case "CODE":
		answer = "MASTER"
	case "2023":
		answer = "0611"
	case "ALGORITHM":
		answer = "CONTEST"
	}

	fmt.Println(answer)
}
