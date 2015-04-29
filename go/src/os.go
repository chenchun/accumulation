package main

import (
	"os"
	"fmt"
	"syscall"
	"io/ioutil"
	"os/user"
)

func main() {
	dir := "/tmp/l3"
	oldMask := syscall.Umask(0)
	os.Mkdir(dir, os.ModePerm)
	defer syscall.Umask(oldMask)
	info,_ := os.Stat(dir)
	fmt.Printf("%v", info.Mode())
	fmt.Println()

	file := "/tmp/f5"
	ioutil.WriteFile(file, []byte("xxx"), 0660)
	finfo,_ := os.Stat(file)
	fmt.Println(finfo.Sys().(*syscall.Stat_t).Gid)
	fmt.Printf("%v", finfo.Mode())
	fmt.Println()

	fmt.Printf("oldMask %v ", oldMask)

//	files, _ := ioutil.ReadDir("/tmp")
//	for _, f := range files {
//		fmt.Println(f.Name())
//		fmt.Println(os.Stat("/tmp/"+f.Name()))
//	}


	fmt.Println()
	u, _ := user.Lookup("chenchun")
	fmt.Printf("gid of chenchun %s", u.Gid)

	var gid int = 1
	fmt.Println(gid)

	var filePerm = os.FileMode(0664)
	fmt.Println(filePerm)
	var perm os.FileMode = filePerm
	fmt.Println(perm)
//	panic(55)
}
