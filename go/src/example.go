package main

import (
	"fmt"
	"time"
	"math/rand"
	"math/cmplx"
)

var x int
var c, python, java bool

func main() {
	fmt.Printf("Welcome to the playgroud! ")
	fmt.Println("The time is", time.Now())
	fmt.Println("My favorite number is ", rand.Intn(10))
	fmt.Println(add(12, 13))
	fmt.Println(parametersShareTypeAdd(12, 13))

	//9 Multi results
	a, b := swap("hello", "world")
	fmt.Println(a, b)

	//10 Named results
	fmt.Println(split(17))

	//11 Variables
	fmt.Println(x, c, python, java)

	//12 Variables with initializers
	var x, j int = 1, 2
	var c, python, java = true, false, "no!"
	fmt.Println(x, j, c, python, java)

	//13 Short variable declarations
	var m int = 1
	n := 3
	fmt.Println(m, n)

	//14 Basic types
	var (
		ToBe bool = false
		MaxInt uint64 = 1<<64 - 1
		z complex128 = cmplx.Sqrt(-5 + 12i)
	)
	fmt.Println(ToBe, MaxInt, z)

}

func add(x int, y int) int {
	return x + y
}

func parametersShareTypeAdd(x, y int) int {
	return x + y
}

func swap(x, y string) (string, string) {
	return y, x
}

func split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}
