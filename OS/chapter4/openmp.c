#include <stdio.h>
#include <omp.h>
int main(int argc, char *argv[])
{
    omp_set_num_threads(4); // 쓰레드 개수 지정

#pragma omp parallel // compiler directive
    {
        printf("OpenM thread: %d\n", omp_get_thread_num()); // 쓰레딩돼서 여러번 실행됨
        // 출력순서는 보장되지 않는다
    }
    return 0;
}
// 컴파일 옵션으로 -fopenmp 주어야 함
