#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
int sum;

void *runner(void *param);

int main(int argc, char *argv[])
{
    pthread_t tid; // pthread의 타입 tid
    pthread_attr_t attr;

    pthread_attr_init(&attr);
    pthread_create(&tid, &attr, runner, argv[1]);
    // 첫번째 argument vector를 runner의 thread로 creation
    // 자바의 new Thread와 같다 , runner = public void run
    pthread_join(tid, NULL); // runner 다 돌때까지 기다림

    printf("sum = %d\n", sum);
}

void *runner(void *param)
{
    int i, upper = atoi(param);
    sum = 0;
    for (i = 1; i <= upper; i++)
        sum += i; // 1~i까지의 합 구하고 exit
    pthread_exit(0);
}