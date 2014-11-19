package main

import (
	"fmt"
	"net/http"
)

type Hello struct{}

func (h Hello) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "Hello!")
}

func main() {
	http.Handle("/string", String("I'm a frayed knot."))
	http.Handle("/struct", &Struct{"Hello", ":", "Gophers!"})
	http.ListenAndServe("localhost:4000", nil)

//	var h Hello
//	http.ListenAndServe("localhost:4000", h)
}

//60 Exercise: HTTP Handlers
type String string

func (str String) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "String: %s", str)
}

type Struct struct {
	Greeting string
	Punct    string
	Who      string
}

func (s Struct) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "%v", s)
}
