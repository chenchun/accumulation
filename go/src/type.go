package main

import (
	"time"
	"fmt"
	"strconv"
	"reflect"
	"github.com/docker/docker/daemon/graphdriver"
)

type Struct struct {
	t time.Time
	x int
	y int
}

type Struct1 struct {
	a int
	Struct
}

type Driver struct {
	h int
}

func (d *Driver) String() string {
	return "dm"
}

func (d *Driver) Status() [][2]string {
	return nil
}

func (d *Driver) Create(id, parent string) error {return nil}
// Remove attempts to remove the filesystem layer with this id.
func (d *Driver) Remove(id string) error {return nil}
// Get returns the mountpoint for the layered filesystem referred
// to by this id. You can optionally specify a mountLabel or "".
// Returns the absolute path to the mounted layered filesystem.
func (d *Driver) Get(id, mountLabel string) (dir string, err error) {return "", nil}
// Put releases the system resources for the specified id,
// e.g, unmounting layered filesystem.
func (d *Driver) Put(id string) {return}
// Exists returns whether a filesystem layer with the specified
// ID exists on this driver.
func (d *Driver) Exists(id string) bool {return false}

func (d *Driver) Cleanup() error {return nil}

func (d *Driver) M() string {return "M method"}

func main() {
	s := Struct{
		x: 1,
	}
	if s.t.IsZero() {
		fmt.Println("s.t is nil, %v %v", s.t, s)
	}
	fmt.Println("%v", s.t)

	var used, total uint64 = 9, 18

	fmt.Println(float64(used)/float64(total) > 0.4)

	var f float64 = 12.45
	fmtFloat := strconv.FormatFloat(f, 'f', 3, 64)
	fmt.Println(fmtFloat)
	fmt.Println(strconv.ParseFloat(fmtFloat, 64))

	if "xx" == "x" {
		fmt.Println("xx==x")
	}
	if "xx" == "xx" {
		fmt.Println("xx==xx")
	}
	s1 := Struct1{
		a : 1,
		Struct: Struct{
			x : 3,
			y : 5,
		},
	}
	fmt.Println(reflect.TypeOf(s1.Struct).String())
	fmt.Println(s1.Struct.x)
	d := &Driver{
		h : 4,
	}
	fmt.Println(d.M())
	driver := graphdriver.NaiveDiffDriver(d)
	fmt.Println(driver)
	fmt.Println(reflect.TypeOf(driver.(graphdriver.ProtoDriver)).String())
	fmt.Println(reflect.TypeOf(driver).String())
	fmt.Println(reflect.ValueOf(driver).Elem().FieldByName("ProtoDriver").Elem())
	fmt.Println(reflect.ValueOf(driver).Elem().FieldByName("ProtoDriver").Elem().Elem())
	fmt.Println(reflect.ValueOf(driver).Elem().FieldByName("ProtoDriver").Elem().Elem().FieldByName("h").Int())
	fmt.Println(reflect.ValueOf(driver).Elem().FieldByName("ProtoDriver").Elem().Interface().(*Driver).M())
//	fmt.Println(reflect.ValueOf(driver).Elem().FieldByName("ProtoDriver").Elem().Elem().Interface().(Driver).M())
//	fmt.Println(driver.(graphdriver.ProtoDriver).(*Driver))
//	fmt.Println(driver.(graphdriver.ProtoDriver).(*Driver).h)
	fmt.Printf("%#v", driver.String())
	fmt.Println()
//	fmt.Printf("%#v", driver.(graphdriver.ProtoDriver).(*Driver))
}
