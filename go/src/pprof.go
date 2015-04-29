package main

import (
	_ "net/http/pprof"
	"log"
	"net/http"
)

func main() {
	log.Println(http.ListenAndServe("localhost:6060", nil))
}
