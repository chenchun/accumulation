package main

import (
	"time"
	"fmt"
)

func main() {
//	time,_ := time.Parse(time.RFC3339Nano, "2014-11-25T08:27:51.615866342Z")
//	fmt.Println(time)
//	time1,_ := time.Parse(time.UnixDate, "Tue Nov 25 10:36:52 UTC 2014")
//	fmt.Println(time1)
	time2 := time.Time {}
	fmt.Println(time2.IsZero())
}
