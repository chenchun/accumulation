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
	title("9 Multi results")
	a, b := swap("hello", "world")
	fmt.Println(a, b)

	//10 Named results
	title("10 Named results")
	fmt.Println(split(17))

	//11 Variables
	title("11 Variables")
	fmt.Println(x, c, python, java)

	//12 Variables with initializers
	title("12 Variables with initializers")
	var x, j int = 1, 2
	var c, python, java = true, false, "no!"
	fmt.Println(x, j, c, python, java)

	//13 Short variable declarations
	title("13 Short variable declarations")
	var m int = 1
	n := 3
	fmt.Println(m, n)

	//14 Basic types
	title("14 Basic types")
	var (
		ToBe bool = false
		MaxInt uint64 = 1<<64 - 1
		z complex128 = cmplx.Sqrt(-5 + 12i)
	)
	fmt.Println(ToBe, MaxInt, z)

	//15 Type conversions
	title("15 Type conversions")
	var i int = 42
	var f float64 = float64(i)
	var u uint = uint(f)
	// or simply
	i1 := 42
	f1 := float64(i1)
	u1 := uint(f1)
	fmt.Println(i, f, u, i1, f1, u1)

	//16 Constants
	title("16 Constants")
	const Pi = 3.14
	const World = "世界"
	fmt.Println("hello", World)
	fmt.Println("Happy", Pi, "Day")

	//17 Numeric Constants
	title("17 Numeric Constants")
	const (
		Big = 1 << 100
		Small = Big >> 99
	)
	fmt.Println(needInt(Small))
	fmt.Println(needFloat(Small))
	fmt.Println(needFloat(Big))

	//18 For
	title("18 For")
	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println(sum)
	sum = 1
	for ; sum < 1000; {
		sum += sum
	}
	fmt.Println(sum)

	//20 For is Go's "while"
	title("20 For is Go's \"while\"")
	sum = 1
	for sum < 1000 {
		sum += sum
	}
	fmt.Println(sum)

	//21 Forever
	//for {}


}

func needInt(x int) int { return x * 10 + 1}
func needFloat(x float64) float64 { return x * 0.1 }

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

func title(title string) {
	fmt.Println()
	fmt.Println(title)
}
