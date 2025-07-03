package main

import "fmt"

func main() {
	names := [3]string{"S", "N", "U"}
	var price [3]int
	var weight [3]int
	var bestIdx int = 0
	var bestRatio float64 = 0

	for i := 0; i < 3; i++ {
		fmt.Scan(&price[i], &weight[i])

		// 스캔하고 바로 10봉지 가격과 무게로 바꿔주기
		price[i] *= 10
		weight[i] *= 10

		if price[i] >= 5000 { // 할인 쿠폰 적용 여부
			price[i] -= 500
		}

		var ratio = float64(weight[i]) / float64(price[i])
		if ratio > bestRatio {
			bestRatio = ratio
			bestIdx = i
		}
	}

	fmt.Println(names[bestIdx])
}
