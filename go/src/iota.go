package main
import "fmt"

type ContainerState int

const (
	Created ContainerState = iota

	Running
	Paused
	Restarting

	removalInProgress

	Empty
	Dead
	Exited
)

const (
	i = 1 << iota
	j
	k
)

func main() {
	fmt.Println(Created)
	fmt.Println(Running)
	fmt.Println(Paused)
	fmt.Println(Restarting)
	fmt.Println(removalInProgress)
	fmt.Println(Empty)
	fmt.Println(Dead)
	fmt.Println(Exited)

	fmt.Println(i)
	fmt.Println(j)
	fmt.Println(k)
}
