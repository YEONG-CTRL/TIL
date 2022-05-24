
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main()
{
    pid_t pid, pid1;
    pid = fork();

    if (pid == 0) { //child process
        pid1 = getpid(); // 자신의 pid 알아내는 시스템 콜
        printf("child:pid = %d\n", pid); // 0 
        printf("child:pid1 = %d\n", pid1); // 자기 pid
    } else if (pid >0) { //parent process
        wait(NULL);
        pid1 = getpid();
        printf("parent:pid = %d\n", pid); // child의 pid
        printf("parent:pid1 = %d\n", pid1); // 부모의 pid
    }

    return 0;
}
