#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <pthread.h>

void *runner(void *param);

int main(int argc, char *argv[])
{
    pid_t pid;
    pthread_t tid;

    printf("A = %d\n", getpid()); // 현재 pid 출력
    pid = fork();
    if (pid > 0)
    {
        wait(NULL);
        printf("B = %d\n", pid);
    }
    if (pid == 0)
    { // child
        pid = fork();
        if (pid > 0)
        {
            wait(NULL);
            printf("C = %d\n", pid);
        }
        pthread_create(&tid, NULL, runner, NULL);
    }
    pid = fork();
    if (pid > 0)
    {
        wait(NULL);
        printf("D = %d\n", pid);
    }
}

void *runner(void *param)
{
    printf("I'm a thread\n");
    pthread_exit(0);
}
