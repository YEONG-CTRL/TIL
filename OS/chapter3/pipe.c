#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>

#define BUFFER_SIZE 25
#define READ_END 0
#define WRITE_END 1

int main()
{
    char write_msg[BUFFER_SIZE] = "Greetings";
    char read_msg[BUFFER_SIZE];
    int fd[2]; // read end 하나, write end 하나
    pid_t pid;

    pipe(fd); // 파이프 생성

    pid = fork(); // fork a new process -> 부모,자식 나눔
    if (pid > 0)
    {                        // parent process
        close(fd[READ_END]); // read할 필요 없음
        /* write to the pipe */
        write(fd[WRITE_END], write_msg, strlen(write_msg) + 1); // 쓰고
        close(fd[WRITE_END]);                                   // 닫음
    }
    else if (pid == 0)
    {                         // child process
        close(fd[WRITE_END]); // 쓸거 아니니까 닫음
        /* read to the pipe */
        read(fd[READ_END], read_msg, BUFFER_SIZE); // read_msg에 저장
        printf("read %s\n", read_msg);
        close(fd[READ_END]);
    }
    return 0;
}
