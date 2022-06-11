#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define true 1
#define NUM_PHILS 5
enum
{
    THINKING,
    HUNGRY,
    EATING
} state[NUM_PHILS];                  // 배열
pthread_mutex_t mutex_lock;          // 뮤텍스
pthread_cond_t cond_vars[NUM_PHILS]; // 컨디션 변수

int main()
{
    int i;
    pthread_t tid;
    init();
    for (i = 0; i < NUM_PHILS; i++)
        pthread_create(&tid, NULL, philosopher, (void *)&i); // 철학자 쓰레드 5개
    for (i = 0; i < NUM_PHILS; i++)
        pthread_join(tid, NULL); // join을 해서 기다린다
    return 0;
}

void init() // 초기화
{
    int i;
    for (i = 0; i < NUM_PHILS; i++)
    {
        state[i] = THINKING;
        pthread_cond_init(&cond_vars[i], NULL); // 각 컨디션변수 초기화
    }
    pthread_mutex_init(&mutex_lock, NULL); // 뮤텍스락 초기화
    srand(time(0));
}
int leftOf(int i)
{
    return (i + NUM_PHILS - 1) % NUM_PHILS; // i의 왼쪽
}
int rightOf(int i)
{
    return (i + 1) % NUM_PHILS; // i의 오른쪽
}

void *philosopher(void *param)
{
    int id = *((int *)param);
    while (true)
    { // 철학자는 생각하고 밥먹는 과정 무한반복
        think(id);
        pickup(id);
        eat(id);
        putdown(id);
    }
}

void think(int id)
{
    printf("%d: Now, I'm thiking...\n", id);
    usleep((1 + rand() % 50) * 10000); // 0.1~0.5 초 정도 생각
}
void eat(int id)
{
    printf("%d: Now, I'm eating...\n", id);
    usleep((1 + rand() % 50) * 10000); // 마찬가지로 밥을 먹는다
}

void test(int i)
{
    // If I'm hungry and my neighbors are not eating,
    // then let me eat.
    if (state[i] == HUNGRY &&
        state[leftOf(i)] != EATING && state[rightOf(i)] != EATING)
    {
        state[i] = EATING;
        pthread_cond_signal(&cond_vars[i]);
    }
}

void pickup(int i)
{
    pthread_mutex_lock(&mutex_lock);
    state[i] = HUNGRY;
    test(i);
    while (state[i] != EATING)
    {
        pthread_cond_wait(&cond_vars[i], &mutex_lock);
    }
    pthread_mutex_unlock(&mutex_lock);
}
void putdown(int i)
{
    pthread_mutex_lock(&mutex_lock);
    state[i] = THINKING; // eating끝나고 thinking으로 간다.
    test(leftOf(i));     // state 확인하여 condition signal 걸어줌
    test(rightOf(i));
    pthread_mutex_unlock(&mutex_lock);
}