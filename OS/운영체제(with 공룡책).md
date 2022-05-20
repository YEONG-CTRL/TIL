# 목차
- [운영체제란? & O/S Structure](#운영체제란-무엇인가?)
    - [운영체제란 무엇인가?](#운영체제란-무엇인가?)


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
        3. main memory , 즉 __RAM__ 
        4. SSD(solid state disk)
        5. HDD(하드디스크)

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







