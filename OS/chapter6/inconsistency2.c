#include <stdio.h>
#include <pthread.h>

int sum = 0;
void *run1(void *param)
{
    int i;
    for (i = 0; i < 10000; i++)
        sum++;
    pthread_exit(0);
}
void *run2(void *param)
{
    int i;
    for (i = 0; i < 10000; i++)
        sum--;
    pthread_exit(0);
}

int main()
{
    pthread_t tid1, tid2;
    pthread_create(&tid1, NULL, run1, NULL);
    pthread_create(&tid2, NULL, run2, NULL);
    pthread_join(tid1, NULL);
    pthread_join(tid2, NULL);
    printf("%d\n", sum); // 10000더하고 10000빼니깐 0이 돼야하지 않을까?
}

// 그러나 0이 나올때도 있고, 아닐때도 있고, 자기 맘대로 나옴