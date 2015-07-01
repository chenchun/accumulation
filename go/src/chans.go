package main
import (
	"fmt"
	"time"
)

func main() {
	waitChan := make(chan int)
	go func() {
		fmt.Println("start wait")
		<- waitChan
		fmt.Println("wait stoped")
	}()
	time.Sleep(1*time.Second)
	close(waitChan)
	time.Sleep(1*time.Second)

}
