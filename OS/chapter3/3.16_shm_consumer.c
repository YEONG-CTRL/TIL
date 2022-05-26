
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>

int main()
{
    const int SIZE = 4096; 
    const char *name = "OS"; 

    int shm_fd; 
    char *ptr; 

    shm_fd = shm_open(name, O_RDONLY, 0666);

    ptr = (char *)mmap(0,SIZE,PROT_READ, MAP_SHARED, shm_fd, 0);
    // write 때와 똑같은 shared memory 공간이 리턴됨 

    printf("%s", (char *)ptr);
    // ptr이 가르키는 것은 아까 proucer가 써놓은 영역,
    // 그렇기에 "Hello, Shared Memory!" 그대로 출력됨

    shm_unlink(name);
    // shared memory 영역 삭제해줌

    return 0;
}

// gcc 3.16_shm_consumer.c -lrt -> 컴파일 명령어

