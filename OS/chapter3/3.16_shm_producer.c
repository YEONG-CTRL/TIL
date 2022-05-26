
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>

int main()
{
    const int SIZE = 4096; // shared memory의 크기
    const char *name = "OS"; // 이름 
    const char *message_0 = "Hello"; // 두개의 메시지
    const char *message_1 = "Shared Memory!\n";

    int shm_fd; // shared memory의 file descriptor interger로 선언
    // file descriptor란 프로세스가 특정 파일에 접근할 때 사용하는 추상적인 값 
    char *ptr; // shared memory로의 pointer

    // shared memory 객체 특정
    shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);

    // shared memory size 특정
    ftruncate(shm_fd, SIZE);

    ptr = (char *)mmap(0,SIZE,PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    // shared memory영역을 shm_fd가 잡음

    sprintf(ptr, "%s", message_0); 
    ptr += strlen(message_0); // message_0 쓰고 포인터 옮기고
    sprintf(ptr, "%s", message_1);
    ptr += strlen(message_1); // 마찬가지로 message_1 쓰고 포인터 옮김(Hello, Shared Memory! 맨 끝에 포인터 위치 )
}

// gcc 3.16_shm_consumer.c -lrt -> 컴파일 명령어

