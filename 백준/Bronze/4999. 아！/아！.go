package main

import "fmt"

func main() {
	var me string
	var doctor string

	fmt.Scan(&me, &doctor)

	var result string
	if len(me) < len(doctor) {
		result = "no"
	} else {
		result = "go"
	}

	fmt.Println(result)
}
