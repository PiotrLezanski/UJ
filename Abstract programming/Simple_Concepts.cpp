#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <concepts>
#include <functional>

template<typename T>
concept Container = requires(T con)
{
    typename T::iterator;
    typename T::value_type;
    
    { con.begin() } -> std::same_as<typename T::iterator>;
    { con.end() } -> std::same_as<typename T::iterator>;
    { con.insert(con.begin(), typename T::value_type{}) };
};

template<typename T, typename U>
concept Predicate = requires(T fun, U value)
{
    { fun(value) } -> std::convertible_to<bool>;
};

template <Container T, typename U>
requires Predicate<U, typename T::value_type>
T filterContainer(T cont, U pred)
{
    T result;
    for (auto it = cont.begin(); it != cont.end(); ++it)
    {
        if (pred(*it))
            result.insert(result.end(), *it);
    }
    return result;
}

int main() 
{
    std::vector<int> numbers = {1, 2, 3, 4, 5, 6};
    auto isEven = [](int x) { return x % 2 == 0; };
    auto evenNumbers = filterContainer(numbers, isEven);
    std::cout << "Even numbers: ";
    for (int n : evenNumbers) std::cout << n << " ";
    std::cout << std::endl;

    std::list<std::string> words = {"apple", "banana", "cherry", "date"};
    auto startsWithB = [](const std::string& word) { return word.starts_with("b"); };
    auto bWords = filterContainer(words, startsWithB);
    std::cout << "Words starting with 'b': ";
    for (const auto& word : bWords) std::cout << word << " ";
    std::cout << std::endl;

    return 0;
}

