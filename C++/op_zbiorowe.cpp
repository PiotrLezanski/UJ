//Piotr Lezanski
#include <iostream>
using namespace std;

void Emplace(char*, int*);
void Insert(char*, int*);
void Erase(char*, int*);
bool Emptiness(int);
bool Nonempty(int);
bool Member(char*, int);
bool Inclusion(int, int);
void Union(int, int, int*);
void Intersection(int, int, int*);
void Symmetric(int, int, int*);
void Difference(int, int, int*);
void Complement(int, int*);
bool Disjoint(int, int);
bool Conjunctive(int, int);
bool Equality(int, int);
bool LessThen(int, int);
bool LessEqual(int, int);
bool GreatEqual(int, int);
bool GreatThen(int, int);

// additional
int parse(char* arr) {
    int x = 1;
    if( *(arr) == '1' ) { x <<= 16; };
    if( *(arr + 1) == '1' ) { x <<= 8; };
    if( *(arr + 2) == '1' ) { x <<= 4; };
    if( *(arr + 3) == '1' ) { x <<= 2; };
    if( *(arr + 4) == '1' ) { x <<= 1; };
    return x;
}
// end


// functions
void Emplace(char* arr, int* num) {
    *num = 0;
    Insert(arr, num);
}

void Insert(char* arr, int* num) {
    if( *arr != '\0' ) {
        if( *arr == '0' || *arr == '1' ) {
            *num |= parse(arr);
            arr += 4;
        }
        Insert((arr+1), num);
    }
    else return;
}

void Print(int num, char* arr, int curr=0, int i=31)
{
    if( i < 0 ) {
        *(arr + curr) = '\0';
        i--;
        return;
    }

    if( num == 0 ) {
        *(arr + curr++) = 'e';
        *(arr + curr++) = 'm';
        *(arr + curr++) = 'p';
        *(arr + curr++) = 't';
        *(arr + curr++) = 'y';
        *(arr + curr) = '\0';
    }
    else {
        if( num & (1<<i) ) {
            int tmp;
            tmp = i;

            if( tmp/16 == 1 ) {
                *(arr + curr++) = '1';
                tmp -= 16;
            }
            else {
                *(arr + curr++) = '0';
            }

            if( tmp/8 == 1 ) {
                *(arr + curr++) = '1';
                tmp -= 8;
            }
            else {
                *(arr + curr++) = '0';
            }

            if( tmp/4 == 1 ) {
                *(arr + curr++) = '1';
                tmp -= 4;
            }
            else {
                *(arr + curr++) = '0';
            }

            if( tmp/2 == 1 ) {
                *(arr + curr++) = '1';
                tmp -= 2;
            }
            else {
                *(arr + curr++) = '0';
            }

            if( tmp == 1 ) {
                *(arr + curr++) = '1';
                tmp -= 1;
            }
            else {
                *(arr + curr++) = '0';
            }

            *(arr + curr++) = ' ';
        }
        i--;
        Print(num, arr, curr, i);
    }
}

void Erase(char* arr, int* num) {
    if( *arr != '\0' ) {
        if( *arr == '0' || *arr == '1' ) {
            *num &= ~(parse(arr));
            arr += 4;
        }
        Erase(arr+1, num);
    }
    else {
        return;
    }
}

bool Conjunctive(int num1, int num2) {
    return (num1 & num2) != 0;
}

bool Member(char* arr, int num) {
    if( *arr != '\0' ) {
        if( *arr == '1' || *arr == '0' ) {
            int tmp = parse(arr);
            return num & tmp;
        }
        Member(arr+1, num);
    }
    else {
        return false;
    }
}

bool Emptiness(int num) {
    return num == 0;
}

bool Nonempty(int num) {
    return num != 0;
}

bool Disjoint(int num1, int num2) {
    return (num1 & num2) == 0;
}

bool Equality(int num1, int num2) {
    return num1 == num2;
}

bool Inclusion(int num1, int num2) {
    return (num1 | num2) == num2;
}

void Union(int num1, int num2, int* num3) {
    *num3 = (num1 | num2);
}

void Intersection(int num1, int num2, int* num3) {
    *num3 = (num1 & num2);
}

void Symmetric(int num1, int num2, int* num3) {
    *num3 = (num1 ^ num2);
}

void Difference(int num1, int num2, int* num3) {
    *num3 = (num1 & (~num2));
}

void Complement(int num1, int* num2) {
    *num2 = (~num1);
}

bool is(int num, int i) {
    if( num & (1 << i) ) return true;
    else return false;
}

int Cardinality(int num, int i=0) {
    if( i <= 31 ) {
        if( is(num, i) ) {
            return Cardinality(num, i+1) + 1;
        }
        return Cardinality(num, i+1);
    }
    return 0;
}

bool LessThen(int num1, int num2) {
    if( Cardinality(num1) < Cardinality(num2) ) return true;
    else if( Cardinality(num1) > Cardinality(num2) ) return false;
    else if( num1 < num2 ) return true;
    else return false;
}

bool GreatEqual(int num1, int num2) {
    return !LessThen(num1, num2);
}

bool LessEqual(int num1, int num2) {
    if( Cardinality(num1) < Cardinality(num2) ) return true;
    else if( Cardinality(num1) > Cardinality(num2) ) return false;
    else if( num1 <= num2 ) return true;
    else return false;
}

bool GreatThen(int num1, int num2) {
    return !LessEqual(num1, num2);
}

