#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <pthread.h>
int value = 0;
void *runner(void *param);
int main(int argc, char *argv[])
{
    pid_t pid;
    pthread_t tid;
    pthread_attr_t attr;

    pid = fork();
    if (pid == 0)
    { // child process p1
        pthread_attr_init(&attr);
        pthread_create(&tid, &attr, runner, NULL); // p1안에서 두개의쓰레드가 생김
        pthread_join(tid, NULL);
        printf("CHILD: value = %d\n", value); // LINE C // runner 실행되어 value 5 먼저 출력
    }
    else if (pid > 0)
    { // parent process p0
        wait(NULL);
        printf("PARENT: value = %d\n", value); // LINE P // 이후, 복제하지 않은 value, 0
    }
}
void *runner(void *param)
{
    value = 5;
    pthread_exit(0);
}