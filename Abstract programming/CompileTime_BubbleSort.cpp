#include <iostream>

template<typename T, int N, int I, bool doContinue>
class InternalBubbleLoop;
template<typename T, int N, int I, int J, bool doContinue>
class ExternalBubbleLoop;

template<typename T>
inline void swap(T& a, T& b)
{
    T temp = std::move(a);
    a = std::move(b);
    b = std::move(temp);
}

template<typename T, int N>
class BubbleSort
{
public:
    static inline void sort(T* first)
    {
        InternalBubbleLoop<T, N, 0, true>::loop(first);
    }
};

template<typename T, int N, int I, bool doContinue>
class InternalBubbleLoop
{
public:
    static inline void loop(T* data)
    {
        ExternalBubbleLoop<T, N, I, I+1, true>::loop(data);
        InternalBubbleLoop<T, N, I+1, I+1 < N>::loop(data);
    }
};

template<typename T, int N, int I>
class InternalBubbleLoop<T, N, I, false>
{
public:
    static inline void loop(T* data) {}
};

template<typename T, int I, int J>
class CompareAndSwap
{
public:
    static inline void swap(T* data)
    {
        if(data[I] > data[J])
            ::swap<T>(data[I], data[J]);
    }
};

template<typename T, int N, int I, int J, bool doContinue>
class ExternalBubbleLoop
{
public:
    static inline void loop(T* val) {}
};

template<typename T, int N, int I, int J>
class ExternalBubbleLoop<T, N, I, J, true>
{
public:
    static inline void loop(T* val) 
    {
        CompareAndSwap<T, I, J>::swap(val);
        ExternalBubbleLoop<T, N, I, J+1, J < N>::loop(val);
    }
};

int main()
{
    int values[] = { 60,50,10,30,40,40,1,9,20,105,15,15,16,1,6,357,9,25,246,2,6,27,357,35,53,73,35,7,537,57,37 };
    constexpr int size = sizeof(values) / sizeof(values[0]);
    BubbleSort<int, size>::sort(values);
    for (int n : values) 
        std::cout << n << ' ';
}
