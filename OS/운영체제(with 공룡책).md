# 목차
- [운영체제란? & O/S Structure](#운영체제란-무엇인가?)
- [Process](#process)
- [Thread](#thread와-concurrency)

<br></br>    

# 운영체제란 무엇인가?

## **Operating System** = Software that operates a computer system.

## what is computer?

- 정보를 처리하는 기계
    - 정보란? I(x) = -log2P(x)
    - EX)동전: x = {-1,1}, P(x) = 1/2, I = -log2^-1 = log2 = 1
    > 정보의 단위가 1이라면, 즉 확률 p(x)가 1/2 이 되는 사건의 단위일때, 이를 **bit(binary digit)** 이라 부른다
    - #### 정보는 어떤 불확실성을 측정하여, 이를 quantitative 하게 표현한 것
    - 정보의 최소단위 __bit__
        - 8bit = 2^8  = __1byte__
    - 이 정보를 처리하려면(0->1 OR 1->0) : _Boolean Algebra_ 사용
    - 논리 게이트: NOT, AND, OR , XOR, NAND, NOR

## 컴퓨터에겐 Universality(범용성) 이 있다.
1. NOT, AND , OR 게이트로 BOOL대수로 하는 모든 계산을 다 할 수 있다
2. NAND 게이트 만으로 모든 계산을 다 할 수 있다
> __범용 컴퓨터__ : 컴퓨터는 정보를 처리할 뿐, 어떤 목적으로 사용할지는 소프트웨어에 달려있다.

### Computability(계산 가능성)
-> Turing - computable  
-> 정지문제(Halting Proble) : 튜링 머신으로는 풀 수 없는 문제

## 튜링 머신
<img width="448" alt="image" src="https://user-images.githubusercontent.com/79896709/169282923-cc834f06-ae97-425f-884e-ae56b6d5c16b.png">
  

## 내장형 프로그램 
메모리에 프로그램을 저장하는 것, 폰 노이만이 처음 도입  

<img width="410" alt="image" src="https://user-images.githubusercontent.com/79896709/169283475-2ae524fc-404f-4c0f-8367-e3f1343c3fa1.png">

instruction regestiger가 있어서, 메모리에서 명령을 가져와서 저장함.

- 이때, 램에 저장되는 프로그램의 정의는? :  
__Program is set of instructions__ -> 특정 task 실행시키는 명령어들의 집합.
- 코드 -> 컴파일 -> 기계어

## 운영체제도 프로그램인가?
- Program __running at all times__ on the computer
- Application Program에게 __System Service__ 제공
- 하드웨어를 OS가 감싸고, 관련 요청을 받아들임
<img width="394" alt="image" src="https://user-images.githubusercontent.com/79896709/169283941-2b5993b4-f3e3-41ab-b237-2427f2345f01.png">

- __Process를 관리__ 하고, 리소스, 유저 인터페이스등을 관리   

# 운영체제의 개념과 구조(Chapter 1~2)

## 컴퓨터 시스템의 4가지 Components
- Hardware
- Operating System
- Application Program
- User

## 운영체제의 정의
- OS에 대한 Universally accepted 정의는 없다
- 가장 일반적인 정의는, __컴퓨터에서 항상 돌아가고 있는 프로그램__
- 일반적으로 **커널(kernel)** 이라 불린다.
- 이 커널에서 시스템 프로그램과 애플리케이션 프로그램에 대한 인터페이스를 제공해준다. 커널이 OS의 핵심.

## Modern Computer system
- 하나 혹은 그 이상의 CPUs
- 여러개의 device controller를 __bus__ 를 통해서 연결  

<img width="454" alt="image" src="https://user-images.githubusercontent.com/79896709/169456475-5f0ee971-4415-437c-a599-b19aefcb0457.png">



### __Bootstrap__
1. 전원을 켰을때, CPU는 메모리에서 instruction을 fetch하여 execute해야 함
2. 근데 이때 메모리는 휘발성 메모리이기에, 전원을 인가했을때 아무것도 없음
3. 그러면 CPU는 제일 처음에 로딩해야 될 명령어는 ROM등에 저장돼 있고, 이를 불러와야 함.
=> 이것을 bootstrap, 즉 부팅용 프로그램이라 부름.
> Bootstrap: the first program to run on computer power on 
: __메모리에다가 운영체제를 로딩하는 일을 해줘야 함(특히 커널)__
4. 이 부트스트래핑이 메모리에 로딩되면, 그 다음부터는 OS가 돌면서, 나머지 응용프로그램들을 메모리에 로드했다가 삭제했다가 하는 과정을 알아서 해줌.

### __Interrupts__ 
ex) 키보드의 a버튼을 사용자가 누르면, 키보드의 컨트롤러가 이 사실을 CPU한테 알려주는 것.
__하드웨어가 언제라도 interrupt를 trigger 시킬 수 있고, 이 trigger시, System bus를 통해서  CPU에 시그널이 간다.

<img width="472" alt="image" src="https://user-images.githubusercontent.com/79896709/169456763-7680b28b-1850-489e-aa6d-aa5752c25e6a.png">


### __Storage System__ 
- 메모리(RAM)은 휘발성 메모리이기에, 비 휘발성 저장장치가 필요하다
- 이때 비휘발성 저장장치를 storage system이라 하고, 이는 여러개의 hierarchy로 이뤄진다
    - 용량 storage capacity
    - access time 속도
        1. 제일 빠른건 cpu안의 registers
        2. 다음으로 cache 메모리
        3. main memory , 즉 __RAM__ => 여기까지 휘발성 저장장치
        4. SSD(solid state disk)
        5. HDD(하드디스크)  
        저장용량은 이 순서의 역순

### __I/O structure__  
OS code 구현의 대부분은 I/O를 managing하는 과정이다.
<img width="358" alt="image" src="https://user-images.githubusercontent.com/79896709/169457285-708809a8-50f7-42d1-a353-b5fbb2641ada.png">

### __Computer System Components의 정의__ 
- CPU : Instruction을 executre하는 하드웨어
- Processor : 한개 혹은 이상의 CPU를 탑재한 칩
- Core : CPU의 back computation unit
- Multicore : CPU에 여러개의 computing core 포함
- MultiProcessor : 프로세서를 여러개 포함

CPU하나에 메모리 하나 있는 구조는 더이상 사용하지 않음
이제는 __Symmetric multiprocesser__ 를 탑재함  
: CPU 또는 processor가 한개가 아니다.  
=> _메모리 하나에 여러개의 CPU를 탑재._   

__Multi-core design__ : CPU여러개를 다는 것은 비용이 많이 드니깐, CPU안에 register와 cache를 가지고 있는 CPU core를 여러개 다는 것

### __MultiProgramming__  
Program = set of instructions. 예전에는 프로그램을 메모리에 하나만 로딩해서 사용했었음.  
그런데, __여러개의 프로그램을 동시에 메모리 올려놓고, 이들을 동시에 실행시켜주자!__ 라는 개념이 Multiprogramming  

메모리에 Process가 동시에 여러개 올라가 있으면, CPU 사용효율을 높일 수 있다. => __Multitasking__ 이 가능해짐.(Concurrency)  
- Multiprogramming의 논리적 연장선
- 하나의 CPU에 Timesharing 적용
- CPU는 작업을 매우 빈번히 바꿔서, 유저는 각 작업을 동시에 여러개 돌릴 수 있음  

__CPU Scheduling__  
: RAM에 여러개의 프로세스가 동시에 존재, CPU는 한개, 하나의 작업이 CPU에서 빠져나가면, 다음으로 어떤 작업을 선택할 것인가. 에 대한 문제  
- 목표는 CPU효율을 가장 좋게 만드는 것

> Process를 메모리에 동시에 여러개 올리는 것이 multiprogramming, 이 multiprogramming 된 프로세스들을 CPU가 timesharing하면서 concurrent하게 실행하는 것을 multitasking이라 함.

### __두가지의 Operation mode__ 
1. User mode
2. Kernel mode  
잘못된 프로그램을 동작했을때, 시스템이 고장나지 않도록 막아주는 역할을 운영체제가 해야 함.   

- __system call__ 은 운영체제에게 어떤 서비스를 요청하는 것
- 그때, 커널 모드로 바꿔서 system call을 처리하고 다시 user mode로 되돌아감 
- 이때 커널모드에서만 할 수 있는 것이 있다. 커널모드에서만 하드웨어 제어 가능.
- 따라서 user process는 시스템에 중대한 지장을 끼칠 수 없게 됨.

## 현대적 컴퓨터 시스템

### Virtualizataion(가상화)
> CPU에 여러개의 프로세스를 돌릴 수 있으면, 하드웨어 자원에 운영체제를 여러개 돌릴 수 있지 않을까?에서 시작된 개념  
: OS와 H/W사이에 VMM을 끼우면 됨(Virtual Machine Manager)(WSL,VMware 등등..)  

VMM을 CPU 스케줄링 하듯이 스케줄링하여, 동시에 여러 OS를 돌림
<img width="336" alt="image" src="https://user-images.githubusercontent.com/79896709/169453788-04e1e185-e90c-4b6f-893a-add319ee9def.png">

## System call
OS에 유저가 interface 하기 위한 방법
1. CLI를 통한다 : shells(zsh,bash,csh....) - 명령어 기반
2. GUI(graphical user interface)(윈도우, Aqua macOS..)
3. Touch screen Interface  

이것은 사용자가 interface하는 방법이고, _실제로 컴퓨터 응용프로그램은 어떻게 OS와 interface하는가?_  : __System call__ 사용!  
<img width="459" alt="image" src="https://user-images.githubusercontent.com/79896709/169455429-91254dae-bcde-4cb1-b909-ff83b3e710c9.png">

system call을 호출하는 것을 __(OS) API__ 라 부른다.

- 일일히 system call을 하기 번거로우니, 여러 라이브러리들을 제공해줌.   
ex) standard C library : printf("Hello")
    - 프로세스를 제어하는 대표적인 system call 인 __fork(),wait() 등__

# Process
> Process is a program in execution(=실행중인 프로그램)

<img width="343" alt="image" src="https://user-images.githubusercontent.com/79896709/169934511-dc7b98d0-a697-4c0d-bc51-a39a929ab437.png">


- 운영체제 입장에서는 작업의 단위는 process
- process가 실행되기 위해필요한 자원들 
    - CPU
    - Memory
    - file
    - I/O 장치

- process의 memory layout은 여러 section으로 나누어진다
    1. Text Section : executable code
    2. Data Section : 전역변수들
    3. Heap Section : 동적 메모리 할당을 했을 때 Heap영역에 메모리 확보
    4. Stack Section: 함수 호출을 하면 매개변수, 리턴 주소, 지역 변수 등의 함수 스택이 쌓인다(일시적).  
<img width="259" alt="image" src="https://user-images.githubusercontent.com/79896709/169923488-e2e0400a-1fca-4d09-b6f2-57bf6ea60e6e.png">

- 프로세스의 5가지 생명주기 
    1. New : 막 프로세스가 생성된 상태
    2. Running : Cpu를 프로세스가 점유해서, CPU가 이를 실행하는 상태
    3. Waiting : 다른 프로세스가 CPU를 점유해서 쓰고 있을 때, 기다리고 있는 상태(ex: IO가 끝날때까지 대기하는 상태)
    4. Ready : waiting이 끝나고 차례가 와도 바로 CPU점유 불가, 레디 큐에 가서 대기하고 있는 상태.
    5. Terminated : 모든 것을 끝내고 종료한 상태

__process state diagram__   
<img width="406" alt="image" src="https://user-images.githubusercontent.com/79896709/169934564-439d9fb3-7710-44aa-b9c5-82b9bc6b41df.png">


- 프로세스 관리 방법  : PCB or TCB(process/task control block)  
    - PCB가 가지고 있는 정보들
    1. Process State
    2. Program Counter : 프로그램이 있는 번지수
    3. CPU register(Instruction, Data register정보들)
    2와 3은 문맥(context)
    4. CPU-scheduling 정보
    5. 메모리 관리 정보
    6. 계정 정보
    7. I/O 상태 정보
<img width="487" alt="image" src="https://user-images.githubusercontent.com/79896709/169927217-b25c148d-545a-4273-8784-64808256f5f5.png">
PCB가 여러개

__프로세스는__  
1. 싱글 쓰레드 execution을 실행하는 프로그램 
2. 싱글 쓰레드는 한번에 한 task만 처리 가능  
<img width="177" alt="image" src="https://user-images.githubusercontent.com/79896709/169934605-2a740d70-5b74-4810-bbc8-d70d9b5f59e4.png">

3. 현대적 운영체제는 process의 개념을 multiple thread로 확장
4. 그리하여 한번에 하나 이상의 일을 수행

__쓰레드는(바로 위의 프로세스의 쓰레드 개념과 다름)__
: lightweight process
- os가 pcb를 이용해 여러개 프로세스를 timesharing 하듯이, process도 여러개로 쪼갤 수 있지 않을까?
- 프로세스를 여러개 하는 것보다, 쓰레드를 여러개 하는 것의 장점이 더 큼 
    - 멀티 쓰레딩이 멀티 프로세싱 보다 대세.

## __Process Scheduling__
- __multiprogramming(multiprocessing)__ 의 목적은: CPU사용을 최대화하기 위해, 동시에 여러 프로세스를 실행시키는 것.
- __time sharing__ 의 목적은 : CPU코어 혹은 프로세스를 프로세스간에 아주 자주 스위치해서, 사용자 입장에서는 각 프로그램이 동시에 돌고 있는 것처럼 보이게 하는 것.

- Scheduling Queues:  
    - Process가 system에 들어온 후, 대기열에서 대기를 함(즉 __ready queue__ 에서 대기중) 그러고 있다가 cpu가 가능해지면 그때 cpu를 획득
    - 특정 이벤트가 일어나길 기다리고 있는 프로세스는 __wait queue__ 에 위치
    - 이 queue들은 PCB의 LinkedList로 구현됨  

__Queuing diagram__
<img width="334" alt="image" src="https://user-images.githubusercontent.com/79896709/169929556-bef1ed1a-d522-4508-a994-fb93e47681df.png">

- Context Switch(문맥 교환)
: process의 context는 PCB에 나타난다
    - interrupt가 일어났을때, system은 running process의 current context를 저장,
    - 그리하여 다시 CPU를 획득했을 때, context를 restore 가능
    - context switch는 
        1. cpu core를 다른 프로세스에게 넘겨주는 것
        2. 현재프로세스의 _state를 저장_ (문맥 저장)
        3. 새로 CPU를 획득할 프로세스의 _state를 restore함_ 
## __Opertaions on Process__
운영체제는 process creation, process termination에 대한 메커니즘을 제공해야 한다.  
- 프로세스는 새로운 프로세스를 만들 수 있다(fork).
    - 만드는 프로세스 : Parent process
    - 만들어진 포로세스 : child process  
    => 이를 통해 process tree 성립

- execution의 두가지 가능성
    1. parent가 children과 함께 concurrently하게 실행됨
    2. parent는 child가 terminated될때까지 wait한다

<img width="469" alt="image" src="https://user-images.githubusercontent.com/79896709/169934747-1e1b0f84-12d2-4c4b-82a8-b2ed23c338cb.png">


- address-space의 두가지 가능성
    1. child process는 parent process의 복제품
    2. child process는 자신에게 적재될 새로운 프로그램을 가지고 있다



- 프로세스 종료 : 마지막 문장을 execute할때 종료되거나, 
중간에 강제로 끝내고 싶으면 eixt() 시스템 콜 호출.  
OS는 메모리,파일 등을 회수하고 끝냄 

- zombie와 orphan
__zombie process__ : 종료됐지만, 그것의 parent가 wait()하지 않은 프로세스. 부모가 있는데 신경을 안쓰는 상태. 
-> daemon등의 background process에 사용
__orphan process__ : 부모 프로세스가 wait()를 호출하는 대신 종료된 프로세스. 부모가 죽은 상태.

## __프로세스의 생성__
- UNIX-like OS에서 새로운 프로세스는 __fork()__ 시스템 콜로 생성됨
- child process는 부모 process의 _address space의 복사본_ 을 가지고 있다
- 이 두개의 프로세스들은 fork() 시스템 콜 이후로 execution을 진행한다.
- 차이점은:
    1. child process의 fork()에 대한 __return code 0__
    2. __nonzero pid__ 를 리턴하면 parent process  

```C
#include <stdio.h>
#include <unistd.h>

int main()
{
    pid_t pid;
    pid = fork(); // 분기
    printf("Hello, Process! %d\n", pid);

    return 0;
}
```
<img width="380" alt="image" src="https://user-images.githubusercontent.com/79896709/170009269-9cb62843-3480-4d4b-972d-590def3a95f4.png">



- fork() 시스템 콜, 이후 일어나는 일
    1. parent process의 address space를 그대로 복제
    2. parent process는 자기 할일 계속 함 
    3. 만약 child가 도는 동안 부모가 할 일 없다면, child기다리기 위해 wait() 시스템 콜 가능 
    4. wait()큐 안에서 child 프로세스가 terminate 돼 interrupt 걸어주기를 기다림


- 밑의 경우에는 0보다 큰 pid가 나중에 출력됨..wait()콜로 인해
```C
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main()
{
    pid_t pid;
    pid = fork(); // 분기 - p0과 p1로로 
    if (pid >0)  // po에선 pid가 0보다 크기에 wait호출, p1에서는 0이기에 wait 호출되지 않음 
        wait(NULL);
    printf("Hello, Process! %d\n", pid);

    return 0;
}
```
<img width="442" alt="image" src="https://user-images.githubusercontent.com/79896709/170009528-3308fd6d-cfef-4077-a79c-f07ad190c282.png">
    

p.154 출력되는 value의 값은?
```C
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int value = 5;
int main() 
{
    pid_t pid,
    pid = fork();

    if (pid == 0) { // child process
        value += 15;
        return 0;
    }
    else if (pid > 0) { // parent process
        wait(NULL);
        printf("Parent : value = %d\n", value); 
    }
}
```
<img width="380" alt="image" src="https://user-images.githubusercontent.com/79896709/170009650-34c1fb1d-33b6-4a28-811c-ec71c2deaf04.png">


p.154 -> 몇개의 프로세스가 만들어지는가?
```C
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main() 
{
    fork();
    fork();
    fork();

    return 0
}
```
<img width="197" alt="image" src="https://user-images.githubusercontent.com/79896709/170009756-bc661186-fccc-45a6-a1fe-fdc1feb561d9.png">


p.905 -> 몇개의 프로세스가 만들어지는가?
```C
#include <stdio.h>
#include <unistd.h>

int main() 
{
    int i;

    for (i = 0; i < 4; i++) {
        pid = fork();
        printf("Hello, fork %d\n", pid); // 16개 나옴 
    }  

    return 0;
}
```
<img width="209" alt="image" src="https://user-images.githubusercontent.com/79896709/170009843-2747d247-b954-4925-b380-21c86de53af0.png">


- __execlp__ :프로세스는 이전과 아예 새로운 일을 하고 싶을 때 fork()를 통해 분기함.  
ex) launchre 프로그램(P0):  
새로운 버전이 있다면 업데이트함.    
새로운 버전 "없다면" 원래 실행 시키려는 프로그램(P1) 실행시킴(fork).   
이때,업데이트는 a.out이고, 원래 실행하려고 했던 프로그램은 b.out.  
근데 fork를 하면 a.out을 복사해서 P1의 메모리 레이아웃에 덮어씀  
이때 b.out을 메모리 레이아웃에 덮어쓰는 역할을 하는 __execlp__ 를 통해서 b.out을 메모리 컨텍스트에 올림 

p.905 LINE J는 reachable한가 ?
```C
int main()
{
    pid_t pid;
    pid = fork();

    if (pid == 0) { //child process
        execlp("/bin/ls", "ls", NULL); //execlp로 ls실행
        printf("LINE J\n");
    } else if (pid >0) { //parent process
        wait(NULL);
        printf("child comlete\n");
    }

    return 0;
}
```
<img width="419" alt="image" src="https://user-images.githubusercontent.com/79896709/170010090-e3caca92-6945-4e9b-93ee-c992ba332f20.png">



p.905 PID값들은?
```C
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
```
__정리!__ 
> OS 스케줄러는 n개의 프로세스를 concurrent하게 실행시키기 위해서 cpu time-sharing을 한다.   
이때, 이를 위해 CPU의 점유정보(register들의)를 저장하고, 복원하는데, 이것이 __context switching__ 이다. 

## 프로세스간 통신
- 프로세스의 Concurrent 실행의 두가지 경우의 수
    1. independent : 프로세스들이 자신의 메모리 영역을 가지고, 서로 공유하는 데이터나, 통신 없이 자신의 일을 함.
    2. cooperating : 프로세스간에 영향을 주고받음. 데이터를 공유하거나, 메시지를 주고받는다.  
    -> 이때, coopertaing process들간의 문제를 해결하는 __IPC(Inter Process Commnunication)__  

- IPC메커니즘은 data를 교환, 즉 보내고 받는 과정을 허용함.
- IPC의 두가지 모델
    1. 공유 메모리 사용  
    <img width="389" alt="image" src="https://user-images.githubusercontent.com/79896709/170155513-b5a4fdc9-d998-4018-909c-6fc74cb3d4d0.png">  



    2. 메시지 사용  

        <img width="382" alt="image" src="https://user-images.githubusercontent.com/79896709/170155571-2f0d00aa-49d5-4427-a8a1-5e286a14679e.png">
    
### __IPC in Shared memory__
- 생산자 - 소비자 문제 : 생산자는 정보를 생산하고, 소비자는 정보를 소비하는 모델.  
    - 컴파일러가 어셈블리 코드를 생성하면, 어셈블러는 이 코드를 소비하여 기계어를 생성.
    - 브라우저가 request를 하면, 웹 서버가 HTML파일을 전송(생산자), 브라우저는 이를 소비하여 화면을 띄워줌
- 생산자-소비자 문제를 생산자 process와 소비자 process로 나눠서 생각
    1. producer와 consumer는 자신들의 할일을 함(concurrently)
    2. 이때, 중간에 buffer를 사용하여,
        - producer는 buffer에 보내고 싶은 것을 채우고,
        - 소비자는 버퍼에 담긴 것을 가져간다.
    - 이때, 버퍼는 한계가 정해진 bounded 버퍼이기에, 버퍼가 가득 찰시에, 생산자는 wait하며 기다려야 한다.  
    소비자는 버퍼가 비어있으면, 버퍼가 찰 때까지 wait해야 한다.
    > 따라서,__버퍼를 shared memory로__ 만들면 된다

<img width="379" alt="image" src="https://user-images.githubusercontent.com/79896709/170161503-5bf3f958-03c2-47ed-bedf-44dcef7e5e6e.png">


__shared memory 방식의 문제__  
:메모리 영역을 공유하게 되면, 메모리 영역에 접근하고, 이를 받아오는 과정을 application 프로그래머가 명시적으로 모두 다 작성해야 함.  
- 다시말해, p와 q가 여러개인데, 이를 하나의 버퍼가 공유할때, 응용프로그램 짜는 사람이 이를 다 관리해줘야 함.

이 문제를 해결하기 위해, __Message_passing__ 사용.  
: OS가 shared memory에 관한 복잡한 문제들을 알아서 해결함.(Message-passing facility)  
- Message-passing facility의 기능은 단순한 __send, receive__ 만 존재함.
- 이때, P와 Q사이에는 __Communication Links__ 가 존재.
    - send와 receive의 두가지 시스템 콜만 제공.
    - 이 link를 구현하는 여러가지 방법
        1. direct OR indirect
        2. synchronous(동기) OR asynchronous(비동기)
        3. automatic OR explicit
    
1. __Direct / Indirect__ 
: 각 프로세스가 커뮤니케이션하고자 하는 상대방을 알고 있는 경우. 명시적으로 발신자와 수령자의 이름을 붙임.
- Direct
- send(P, message) : P프로세스에게 메시지 보냄.
- receive(Q, message) : Q프로세스로부터 메시지를 받음.
- 이 경우, PQ사이에 커뮤니케이션 링크가 __자동으로__ 생성됨. 또한 이 링크는 두개 프로세스쌍 간에, __하나만 있을 수 있음__

Indirect시, PQ사이에는 __매개체__ 가 필요함.
: 메시지는 __Mailboxes__ or __ports__ 로 부터 보내고 받아짐.(8000포트할때 그 포트 맞음)    
- ports는 객체이다.
- send(A, message): port A에 메시지를 보낸다.
- receive(A, message) : port A로부터 메시지를 받는다.
- 두개의 프로세스 사이에 shared box가 있을때, 비로소 링크가 생성됨.
- 이 링크는 두개의 프로세스 이상을 연결할 수 있음.

_OS는_ 
- 프로세스가 새로운 메일박스를 만들 수 있게 해주고,
- 메일박스를 통해 메시지를 주고받을 수 있게 해주고,
- 메일박스를 삭제할 수 있게 해주면 된다.

이 indirect를 구현할때의 디자인 옵션들
1. __Synchronous or Asynchronous__ (Blocking or non-blocking)  
<img width="418" alt="image" src="https://user-images.githubusercontent.com/79896709/170161572-f349c2f4-a6f0-42ff-aeb4-85fd8cac940d.png">

- blocking = synchronous의 뜻은? : blocking I/O를 사용했을 시, p는 전송이 끝날때까지 기다렸다, 끝나고 자신의 일을 함.  
따라서, send 호출 다음 문장으로 넘어왔단 뜻은, Q가 send 분량을 다 받았다는 뜻이기도 함.  
- 반대로 asynchrounous하다면, p는 보내놓고 자신의 일을 하고 있는데, Q가 중간에 다운돼서 receive를 못하더라도, P는 하던일 계속함.  

Ex) 영화 다운 후 -> 요금부과 시, synchrounous면 다운이 완료될때까지 요금이 부과되지 않지만, asynchronous라면 다운하다가 중간에 멈추더라도 요금은 부과 됨.

## IPC System의 예시
1. Shared Memory: POSIX Shared Memory
: Portable Operating System Interface(for unix)


2. Message Passing: Pipes
: UNIX의 기본적인 파이프 구조

### __POSIX__
: memory-mapped file을 이용해서 버퍼(shared memory)영역을 잡는다. 하드디스크가 아닌 메모리에 파일을 생성하여 성능을 향상시킴.

1. 일단 shared-memory object를 만든다  
``` fd = shm_open(name, O_CREAT | O_RDWR, 0666);```
  
    이름이 O_CREAT, ORDWR은 권한  

2. 객체의 크기를 정의함  
``` ftruncate(fd, 4096) ```  
    Read, write할때 정크의 크기를 4096으로 준다

3. memory-mapped filed을 shared memory에 매핑시켜준다  
``` mmap(0,SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);``` 


 [ __생산자 P0__ ](https://github.com/YEONG-CTRL/TIL/blob/main/OS/chapter3/3.16_shm_producer.c)


[ __소비자 P1__ ](https://github.com/YEONG-CTRL/TIL/blob/main/OS/chapter3/3.16_shm_consumer.c)


### __Pipes__ 
: Shared memory방식은 일일히 open해서 쓰고, 읽고, 닫아주고 해야 하기에 번거로운 과정임.  
반대로 Pipe는 간단함.  
- _Pipe 구현의 이슈들_  
    1. unidirectional(단방향) OR bidirectional?
    2. two-way communication의 경우에, half-duplex냐, full-duplex냐?
    > half : 통신선 두개(송신선/수신선)  
    > full : 통신선 하나
    3. 통신하는 프로세스간에 relationship이 존재해야 하는가?
    4. pipes는 network을 통해서 통신할 수 있는가?
        - 네트워크에서 쓸 수 있는 파이프 : __소켓__

- __Ordinary pipes__ 
    - 프로세스 바깥에서 접근 불가 
    - 일반적으로, 부모 프로세스가 파이프를 만들고, 이를 통해 child process와 통신함.
    <img width="633" alt="image" src="https://user-images.githubusercontent.com/79896709/170473184-ab9d693a-f881-4e06-9ed9-2cbf7c53d85c.png">

    - unidirectional한(단방향)인 파이프로 two-way communication하려다 보니 파이프를 두개 사용 

    ```C
    pipe(int fd[])
    fd[0] // read end of the pipe
    fd[1] // write end of the pipe
    ```

[ __pipe.c__ ](https://github.com/YEONG-CTRL/TIL/blob/main/OS/chapter3/pipe.c)

- __Named pipes__ 
    - 파이프에 이름을 붙여주었기에 parent-child relationship 필요없음.


### __Clinet-server system에서의 communication__ 
여태까지 해왔던 것은 컴퓨터 "내부"의 통신이고, 네트워크 안에서 다른 컴퓨터의 프로세스와 통신을 하고싶다면 어떻게 해야할까?
> __Socket__ 의 등장  

- 양 상대방 컴퓨터를 IP address를 통해 특정.  
- 이때 파이프는 어떻게 특정하는가? __Port__ 사용 
- 이 IP와 Port를 하나로 묶은것이 Socket

- Socket은 소통을 위한 __원격지의 두 endpoint__

Java가 제공하는 
- __Socket class__ : TCP소켓(connection oriented)
- DatagramSocket class : UDP소켓(connectionless)
- MultiSocket class : 특정한 recipients에게만.

[__Server__](https://github.com/YEONG-CTRL/TIL/blob/main/OS/chapter3/DateServer.java)

[__Client__](https://github.com/YEONG-CTRL/TIL/blob/main/OS/chapter3/DateClient.java)

__RPC__ 
- RPCs(Remote Procedure calls) - IPC의 확장개념
: 네트워크에 존재하는 원격에 있는 프로세스들 간의 추상화된 procedure call  
- 말이 좀 어려운데, 네트워크를 통해 원격에 있는 함수를 호출하는 것. 이라 생각하면 될듯.
- client는 remote host의 procedure를 일으킨다.
    - RPC system은 client에 __stub__ 을 넘겨줌.
    - client-side의 stub은 parameter들을 __marshaling__ 한다
        - 원격 서비스를 이용하는 두 API까지 주고받는 데이터를 정렬.
        - mashaling한 객체를 서로 주고받는 것.

# Thread와 Concurrency
- 하나의 프로세스는 여러개의 threads of control을 가질 수 있다.

__Thread__ 
- a lightweight process(LWP)
- CPU utilization의 basic unit(가장 기본적인 cpu를 점유하는 단위)
- 각 쓰레드는 thread ID, program counter, register set, stack 으로 구성.
<img width="407" alt="image" src="https://user-images.githubusercontent.com/79896709/170926390-65ba2b61-5fc4-46b4-9762-54c200006cc1.png">

__Multithreading의 장점__  

<img width="419" alt="image" src="https://user-images.githubusercontent.com/79896709/170926774-76c16975-7709-43c0-b62a-2e79ffdf2689.png">


1. Responsiveness  
: 지속적인 실행이 가능함. 유저 인터페이스등을 처리할때, blocking없이 계속 execution 가능하다.
2. Resource Sharing  
: 프로세스간 IPC의 경우는, 중간에 shared memory나 메시지 큐가 통신을 위해 필요하다.  
 그러나 쓰레드들은 code나 데이터 영역을 공유하기 때문에 굳이 shared memory 등이 없이도 쓰레드끼리는 리소스를 공유할 수 있다.  
3. Econmomy  
: 배틀그라운드 전체 코드를 복사해서 process creation을 하는 것보다, 그 안에서 여러 쓰레드를 사용하는 것이 훨씬 경제적이다.  
context switch의 경우에도, PCB를 옮기는 것보다 thread의 context의 switch가 훨씬 간단하다.
4. scalability  
: 프로세스는 multiprocessor architecture의 이점을 누릴 수 있다.

- __Threads in Java__ 
: 자바에서 명시적으로 쓰레드를 만드는 세가지 방법
1. extends from the Thread class
- Thread Class에서 비롯된 새로운 클래스를 만든다.
- 그리고 이것의 public void run() 메서드를 오버라이드 한다.
- 다중 상속이 지원되지 않는 자바 특성상, Thread 클래스 상속받고 나면 다른 클래스 상속받지 못하는 단점이 생긴다.

    ```Java 
    class MyThread1 extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Hello, Thread!");
                    Thread.sleep(500); // 약 0.5초
                }
            }
            catch (InterruptedException ie) {
                System.out.println("I'm interrupted");
            }
        }
    }

    public class ThreadExtendEx {
        public static final void main(String[] args) {
            MyThread1 thread = new MyThread1();
            thread.start();
            System.out.println("Hello, My Child!");
        }
    }
    ```
    결과는 Hello, My Child! 이후 Hello, Thread!가 0.5초에 한번씩 실행된다.
    1. start()로 새로운 쓰레드가 생기고
    2. main에서 마저 "Hello, My Child!"까지 실행한 이후 
    3. 쓰레드간의 context switch가 일어나서 run()이 실행돼서 Hello, Thread!실행 

2. Implementing the Runnable interface
- Runnable interface를 implement하는 새로운 클래스 만들고, 
- 그것의 public void run() 메서드 오버라이드.

    ``` Java
    class MyThread2 implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("Hello, Runnable!");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException ie) {
                System.out.println("I'm interrupted"); 
            }
        }
    }

    public class ThreadExample2 {
        public static final void main(String[] args) {
            Thread thread = new Thread(new MyThread());
            thread.start();
            System.out.println("Hello, my runnalbe child")
        }
    }
    ```

3. Lambda expression 활용(익명쓰레드)
- 새로운 클래스 선언하기 귀찮다!
- 자바 1.8 부터 지원

    ```Java
    public class ThreadExample3 {
        public static final void main(String[] args) {
            Runnable task = () -> {
                try {
                    while (true) {
                        System.out.println("Hello, Lambda Runnable");
                        Thread.sleep(500);
                    }
                }
                catch (InterruptedException ie) {
                System.out.println("I'm interrupted"); 
                }
            };
            Thread thread = new Thread(task);
            thread.start();
            System.out.println("Hello, my Lambda child")
        }
    }
    ```

- __부모 쓰레드의 대기__ 는 프로세스의 wait대신, __join__ 을 활용한다.
    ```Java
    public class ThreadExample4 {
        public static final void main(String[] args) {
            Runnable task = () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Hello, Lambda Runnable");
                    }
            };
            Thread thread = new Thread(task);
            thread.start(); // child thread시작 
            try {
                thread.join(); // main thread 멈춤 // childe thread 5번 다 실행하고
            }
            catch (InterruptedException ie) {
                System.out.println("parent thread is interrupted");
            }
            System.out.println("Hello, my joined child") // 다시 재개해서 이거 실행
        }
    }
    ```

-쓰레드의 종료는 __interrupt__ 를 걸어주면 된다.
```Java
    public class ThreadExample5 {
        public static final void main(String[] args) throws InterruptedException {
            Runnable task = () -> {
                try {
                    while (true) {
                        System.out.println("Hello, Lambda Runnable"); // 1.5번 가량 실행
                        Thread.sleep(100);
                    }
                }
                catch (InterruptedException ie) {
                    System.out.println("I'm interrrupted"); // 2. interrupt걸려서 실행
                }
            };
            Thread thread = new Thread(task);
            thread.start(); // child thread시작 
            thread.sleep(500);
            thread.interrupt();
            System.out.println("Hello, my Interrupted child") // 3.마지막으로 실행
            // 이 순서가 보장되지 않는다.
            // 부모 쓰레드는 interrupt가 걸리고 자기 할일을 하고, 자식은 interrupt받으면 자기 할일을 하고 종료하기에 누가 먼저라고 할 수는 없다.
        }
    }
```

- MultiCore 시스템에서의 Multithreading 
    - 여러개의 core를 concurrency 측면에서 더 효율적으로 사용할 수 있다
    - 4개의 쓰레드를 가진 application을 생각해보자
        1. single-core : thread들은 시간 사이사이에 interleaved(끼워넣어질) 될것 -> time-sharing
        2. multiple-cores : 몇개의 쓰레드들은 병렬적으로 돌 수 있다
        <img width="426" alt="image" src="https://user-images.githubusercontent.com/79896709/170939082-e421c288-949a-4d7b-b67e-3f250ef2223d.png">

        -> 이때, multicore시스템에서는 __해결해야할 문제__ 들이 생긴다.
            1. Identifying task: 어떤 부분들이 seperate하게 실행될 수 있는지 찾아내는 능력
                ex) 합을 구하는 과정은 다 나눌 수 있음, 그런데, 정렬하는 과정은 어디까지 쪼개야할지 어려움
            2. Balance : 데이터를 eqaul하게 나눠서 equal하게 작업할 수 있게 해야함.
            3. Data Spliting : 2번의 연장선.
            4. Data Dependency : task의 실행이 잘 동기화돼서 data dependency를 지킬 수 있어야 함.
            5. single thread보다 테스트와 디버깅이 훨씬 어려워짐.

- 병렬처리(parallelism)의 유형
    1. 데이터 병렬성
    <img width="526" alt="image" src="https://user-images.githubusercontent.com/79896709/170940375-ed8e16e7-5bea-4c8c-b58a-853a33e89dc8.png">

    2. task 병렬성
    <img width="533" alt="image" src="https://user-images.githubusercontent.com/79896709/170940450-8c9344c5-d4e6-4a7e-b773-f03f925424dc.png">

    > 현재는 분산시스템이 도입됐기 때문에, data나 task 병렬성을 고려할 필요가 크게 없다.  

- Amdahl의 법칙 : CPU코어는 무조건 많으면 좋은가??
: 전부 다 병렬처리 할 수 있는 것이 아니면, 코어의 개수와 속도 향상속도가 정비례하지는 않는다.

## 멀티쓰레딩

### 쓰레드의 두가지 타입 
<img width="462" alt="image" src="https://user-images.githubusercontent.com/79896709/171119235-32253409-28fa-48ff-aa78-e7a962fba7e8.png">
  

1. User 쓰레드 : 사용자 모드에서 사용하는 쓰레드.
    - 커널의 위에서,
    - 커널의 지원없이 관리된다.

2. Kernel 쓰레드 : OS입장에서, 코어에서 직접 쓰레딩을 할 수 있는 쓰레드. 
    - 운영체제에 의해 바로 지원되고 관리된다.

- 유저 쓰레드와 커널 쓰레드의 세가지 관계
    1. Many to One : 커널 쓰레드 하나가 여러 유저 쓰레드를 감당
    2. One to One : 유저 쓰레드 하나에 커널 쓰레드 하나 
    3. Many to Many : 다양한 유저 쓰레드가 다양한 커널쓰레드의 서비스를 받는다

- _쓰레드 라이브러리_ 
    - 쓰레드를 만들고 관리하는 API
    - 최근에 자주 사용되는 라이브러리들은
        1. POSIX Pthreads
        2. Windows thread
        3. Java thread : 그런데 자바의 JVM은 운영체제에 종속적이기에, windows운영체제면 윈도우 쓰레드 사용하고, 유닉스 운영체제면 Pthread를 사용한다

- Pthreads
파일링크ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

```C
pid_t pid;
pid = fork();
if (pid == 0) { /* child process*/
    fork();
    thread_create( . . .);
}
fork();
```
1. 몇개의 별개의 프로세스가 만들어지는가? => 6개  

    a. fork()시 P0에서 P1 생성  
    b. pid 가 0이 아닌 부모 프로세스는 맨 밑으로 가서 또 fork()해서 P2생성    

    c. pid가 0인경우(p1인 경우) 도 fork()를 하기 때문에 P1을 복제한 P3가 생성됨.  

    이때, thread_create()를 하기 때문에, P1과 P3는 thread를 만든다
    따라서 쓰레드는 두개 생성  
    
    d. thread_create()이후 if문 벗어나서 fork()를 또 하기에, p1과 p3의 자식 p4, p5도 생성된다.

2. 몇개의 별개의 쓰레드가 만들어지는가? => 2개

4.19 파일링크ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


4.17 파일링크00000000000  



__Implicit Threading__  
멀티코어 시스템에서 멀티 쓰레딩을 구현하기는 너무 어렵기에, 이 어려움을 컴파일러나, run-time 라이브러리에게 넘긴다.  
이를 위한 네가지 접근 방법.  
1. Thread Pools : 여러개의 쓰레드를 pool에 저장해두고, 작업을 기다리게 한다.  
2. Fork & Join : explicit threading 이지만, 이를 implicit하게 할 수도 있다.  
3. OpenMP : 컴파일러 지시어를 통해 C/C++에서 병렬처리를 할 수 있다.
4. GCD : 애플의 OS에서 쓰임  

__OpenMP__  
- parallel한 region만 지정해주면, 알아서 병렬적으로 실행이 된다.  
- 컴파일러에게 parallel region에 대해 지시를 한다.  

0penmp예제제제제제ㅔ제
