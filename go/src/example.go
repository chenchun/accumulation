package main

import (
	"fmt"
	"time"
	"math"
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

	//22 If
	title("22 If")
	fmt.Println(sqrt(2), sqrt(-4))

	//23-24 If with a short statement, If and else
	title("23-24 If with a short statement, If and else")
	fmt.Println(
		pow(3, 2, 10),
		pow(3, 3, 20),
	)

	//25 Loops and Functions
	title("25 Loops and Functions")
	fmt.Println(Sqrt(3))

	//26 Structs
	title("26 Structs")
	type Vertex struct {
		X int
		Y int
	}
	fmt.Println(Vertex {1, 2})

	//27 Struct Fields
	title("27 Struct Fields")
	v := Vertex{1, 2}
	v.X = 4
	fmt.Println(v.X)

	//28 Pointers
	title("28 Pointers")
	p := Vertex{1, 2}
	q := &p
	q.X = 1e9
	fmt.Println(p)

	//29 Struct Literals
	title("29 Struct Literals")
	var (
		p29 = Vertex{1, 2}  // has type Vertex
		q29 = &Vertex{1, 2} // has type *Vertex
		r = Vertex{X: 1}  // Y:0 is implicit
		s = Vertex{}      // X:0 and Y:0
	)
	fmt.Println(p29, q29, r, s)

	//30 The new function
	title("30 The new function")
	v30 := new(Vertex)
	fmt.Println(v30)
	v30.X, v30.Y = 11, 9
	fmt.Println(v30)

	//31 Arrays
	title("31 Arrays")
	var a31 [2]string
	a31[0] = "Hello"
	a31[1] = "World"
	fmt.Println(a31[0], a31[1])
	fmt.Println(a31)

	//32 Slices
	title("32 Slices")
	p32 := []int{2, 3, 5, 7, 11, 13}
	fmt.Printf("p ==", p32)
	for i:= 0; i < len(p32); i++ {
		fmt.Printf("p[%d] == %d\n", i, p32[i])
	}

	//33 Slicing slices
	title("33 Slicing slices")
	p33 := []int{2, 3, 5, 7, 11, 13}
	fmt.Println("p ==", p33)
	fmt.Println("p[1:4] ==", p33[1:4])
	fmt.Println("p[:3] ==", p33[:3])
	fmt.Println("p[4:] ==", p32[4:])

	//34 Making slices
	title("34 Making slices")
	a34 := make([]int, 5)
	printSlice("a", a34)
	b34 := make([]int, 0, 5)
	printSlice("b", b34)
	c34 := b34[:2]
	printSlice("c", c34)
	d34 := c34[2:5]
	printSlice("d", d34)

	//35 Nil slices
	title("35 Nil slices")
	var z35 []int
	fmt.Println(z35, len(z35), cap(z35))
	if z35 == nil {
		fmt.Println("nil!")
	}

	//36 Range
	title("36 Range")
	var pow36 = []int{1, 2, 4, 8, 16, 32, 64, 128}
	for i, v := range pow36 {
		fmt.Printf("2**%d = %d\n", i, v)
	}

	//37 Range continued
	title("Range continued")
	pow := make([]int, 10)
	for i:= range pow {
		pow[i] = 1 << uint(i)
	}
	for _, value := range pow {
		fmt.Printf("%d\n", value)
	}

	//38 TODO

	type Position struct {
		Lat, Long float64
	}
	//39 Maps
	title("Maps")
	var m39 map[string]Position
	m39 = make(map [string]Position)
	m39["Bell Labs"] = Position {
		40.68433, -74.39967,
	}
	fmt.Println(m39["Bell Labs"])

	//40 Map literals
	title("40 Map literals")
	var m40 = map[string]Position {
		"Bell Labs" : Position {
			40.68433, -74.39967,
		},
		"Google" : Position {
			37.42202, -122.08408,
		},
	}
	fmt.Println(m40)

	//41 Map literals continued
	title("41 Map literals continued")
	var m41 = map[string]Position {
		"Bell Labs" : {40.68433, -74.39967},
		"Google" : {37.42202, -122.08408},
	}
	fmt.Println(m41)

	//42 Mutating Maps
	m42 := make(map[string]int)
	m42["Answer"] = 42
	fmt.Println(m42["Answer"])
	m42["Answer"] = 48
	fmt.Println(m42["Answer"])
	delete(m42, "Answer")
	fmt.Println(m42["Answer"])
	v42, ok := m42["Answer"]
	fmt.Println(v42, " Present?", ok)

	//44 Function values
	title("44 Function values")
	hypot := func(x, y float64) float64 {
		return math.Sqrt(x*x + y*y)
	}
	fmt.Println(hypot(3, 4))

	//45 Function closures
	title("45 Function closures")
	pos, neg := adder(), adder()
	for i:=0; i<10; i++ {
		fmt.Println(pos(i), neg(-2*i))
	}
}

func adder() func(int) int {
	sum := 0
	return func(x int) int {
		sum += x
		return sum
	}
}

func printSlice(s string, x []int) {
	fmt.Printf("%s len=%d cap=%d %v\n",
		s, len(x), cap(x), x)
}

func Sqrt(x float64) float64 {
	var z float64 = 2;  // arbitrary except 0
	for i := 0; i < 10; i++ {
		z = z - (z*z - x)/2/z
	}
	return z
}

func pow(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, lim)
	}
	// can't use v here, though
	return lim
}

func sqrt(x float64) string {
	if x < 0 {
		return sqrt(-x) + "i"
	}
	return fmt.Sprint(math.Sqrt(x))
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
	fmt.Println("//"+title)
}
