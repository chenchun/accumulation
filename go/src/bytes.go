package main

import (
	"fmt"
)

func main() {
	var a byte
	a = 0xff
	fmt.Println(a)
	fmt.Println(a^0x01)
	fmt.Println(a&0x01)
	fmt.Println(a&^0x01)
	a &^= 0x1
	fmt.Println(a)
}

