package main

import (
	"fmt"
	"math"
	"os"
)

type Vertex struct {
	X, Y float64
}

func (v *Vertex) Abs() float64 {
	return math.Sqrt(v.X * v.X + v.Y * v.Y)
}


type MyFloat float64

func (f MyFloat) Abs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

func (v *Vertex) ScaleP(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func (v Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

type Abser interface {
	Abs() float64
}

type Reader interface {
	Read(b []byte) (n int, err error)
}

type Writer interface {
	Write(b []byte) (n int, err error)
}

type ReadWriter interface {
	Reader
	Writer
}
//50
//Go does not have classes. However, you can define methods on struct types.
//The method receiver appears in its own argument list between the func
//keyword and the method name.

//51
//In fact, you can define a method on any type you define in your package, not
//just structs.
//You cannot define a method on a type from another package, or on a basic type.
func main() {
	v := &Vertex{3, 4}
	fmt.Println(v.Abs())

	f := MyFloat(-math.Sqrt2)
	fmt.Println(f.Abs())


	//52
	//There are two reasons to use a pointer receiver.
	//First, to avoid copying the value on each method call (more efficient if the value type is a large struct).
	//Second, so that the method can modify the value that its receiver points to.

	//54 Methods with pointer receivers
	v1, v2 := Vertex{3, 4}, Vertex{3, 4}
	pv1, pv2 := &Vertex{3, 4}, &Vertex{3, 4} //pointer
	v1.Scale(2)
	v2.ScaleP(2)
	pv1.Scale(2)
	pv2.ScaleP(2)
	fmt.Println(v1, v2, pv1, pv2)

	//56 Interfaces are satisfied implicitly
	var w Writer
	// os.Stdout implements Writer
	w = os.Stdout
	fmt.Fprintf(w, "hello, writer\n")

	//55 interfaces
	var a Abser
	f3 := MyFloat(-math.Sqrt2)
	v3 := Vertex{3, 4}

	a = f3  // a MyFloat implements Abser
	a = &v3 // a *Vertex implements Abser

	// In the following line, v is a Vertex (not *Vertex)
	// and does NOT implement Abser.
//	a = v3
	fmt.Println(a.Abs())
	//# command-line-arguments
	//src/methods.go:77: cannot use v3 (type Vertex) as type Abser in
	// assignment:
	//	Vertex does not implement Abser (Abs method has pointer receiver)

}


