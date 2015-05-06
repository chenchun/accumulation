## create a new char device

    //create a new char device named `coffee' with major/minor number 12 and 2
    mknod /dev/coffee c 12 2

## remove a char device

    rm /dev/coffee
    
## assigning to elements of a structure

    //gcc extension way
    struct file_operations fops = {
    	read: device_read,
    	write: device_write,
    	open: device_open,
    	release: device_release
    };
    
    //C99 way
    struct file_operations fops = {
    	.read = device_read,
    	.write = device_write,
    	.open = device_open,
    	.release = device_release
    };
    
## register and unregister character device driver
    
    //syscall 
    int register_chrdev(unsigned int major, const char *name, struct file_operations *fops);
    void unregister_chrdev(Major, DEVICE_NAME);
    
## module use count
    
    try_module_get(THIS_MODULE): Increment the use count.
    module_put(THIS_MODULE): Decrement the use count.