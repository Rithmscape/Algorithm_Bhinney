import "strings"

func solution(my_string string) string {
	arr := []string{"a", "e", "i", "o", "u"}

	for _, a := range arr {
		my_string = strings.ReplaceAll(my_string, a, "")
	}

	return my_string
}