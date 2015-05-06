## The /proc File System

Each time, everytime the file /proc/helloworld is read, the function procfs_read is called. 
Two parameters of this function are very important: the buffer (the first parameter) and the offset (the third one). 
The content of the buffer will be returned to the application which read it (for example the cat command). The offset is the current position in the file. 
If the return value of the function isn't null, then this function is called again. So be careful with this function, ***if it never returns zero, the read function is called endlessly.***
 
 ## create_proc_entry and proc_create
 
 `create_proc_entry` is deleted. So I modified the code to use `proc_create` instead. Only procfs-4.c works on ubuntu 3.16.0-23