package main
import "fmt"

type food interface {
	Eat()
}

type fruit struct {
	name string
}

func (f fruit) Eat() {
	fmt.Println(f.name)
}

type vegetables struct {
	name string
}

func (v vegetables) Eat() {
	fmt.Println(v.name)
}

type people struct {
	food
}

func main() {
	v := vegetables{name: "vegetables"}
	f := fruit{name: "fruit"}
	v.Eat()
	f.Eat()
	p := people{food: &v}
	p.Eat()
}


