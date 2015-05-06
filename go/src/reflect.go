package main

import (
	"reflect"
	"fmt"
)

type Model struct {
	eng string
	Graph int
}

func main() {
	model := Model{}
	fmt.Println(model)
	reflect.ValueOf(&model).Elem().FieldByName("Graph").SetInt(7)
	fmt.Println(model)
	//can't set unexported field using reflection
//	reflect.ValueOf(&model).Elem().FieldByName("eng").SetString("x")
	fmt.Println(reflect.TypeOf(model).Kind())
	fmt.Println(reflect.TypeOf(model).Kind() == reflect.Struct)
	fmt.Println(reflect.TypeOf(model).String() == "main.Model")
}
