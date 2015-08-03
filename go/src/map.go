package main
import "fmt"

func main() {
	a := make(map[string]int)
	a["1"]=2
	a["2"]=3
	fmt.Println(len(a))
	for k := range a {
		fmt.Println(k)
		delete(a, k)
	}
	fmt.Println(len(a))
}