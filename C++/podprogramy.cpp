//Piotr Lezanski
#include <iostream>
#include <string>
#include <cstdarg>

namespace functions {

    std::string omitZeros(std::string str, int i=0) {
        if( i < str.length() && *(str.begin()+i) == '0' ) {
            return omitZeros(str, i+1);
        }
        else {
            return str.substr(i, str.length());
        }
    }

    std::string reverse(const std::string& str, int i=0)
    {
        std::string tmp = "";
        if (!(i >= str.length())) {
            tmp += *(str.rbegin()+i);
            return tmp + reverse(str, i+1);
        }
        else {
            return "";
        }
    }

    std::string addStrings(const std::string& str1, const std::string& str2, int i=0, int carry=0) {
        if( (i < str1.length() && i < str2.length()) || carry != 0 ) {
            std::string result = "";
        
            char x = carry;

            if( i < str1.length() ) x += (*(str1.begin()+i) - '0');
            else x += 0;
            if( i < str2.length() )x += (*(str2.begin()+i) - '0');
            else x += 0;

            if( x > 9 ) {
                x -= 10;
                carry = 1;
            }
            else {
                carry = 0;
            }
            
            result += (x + '0');
            result += addStrings(str1, str2, i+1, carry);
            return i == 0 ? reverse(result) : result;
        }
        else if( i < str2.length() ) {
            return str2.substr(i, str2.length());
        }
        else if( i < str1.length() ) {
            return str1.substr(i, str1.length());
        }
        else {
            return "";
        }        
    } 

    std::string subStrings(const std::string str1, const std::string str2, int i=0, int carry=0) {
        if( (i < str1.length() && i < str2.length()) || carry != 0 ) {
            std::string result = "";

            char tmp_carry = carry;
            char x = 0; // albo 1 jezeli carry == 1

            if( i < str1.length() ) x += (*(str1.begin()+i) - '0') - tmp_carry;
            else x += 0;
            if( i < str2.length() )x -= (*(str2.begin()+i) - '0');
            else x += 0;

            if( x < 0 ) {
                x += 10;
                carry = 1;
            }
            else {
                carry = 0;
            }
            
            result += (x + '0');
            result += subStrings(str1, str2, i+1, carry);
            return i == 0 ? reverse(result) : result;
        }
        else if( i < str1.length() ) {
            return str1.substr(i, str1.length());
        }
        else {
            return "";
        }        
    }

    std::string sum(std::string str1, std::string str2) {
        bool isANeg = false;
        bool isBNeg = false;

        if( str1[0] == '-' ) isANeg = true;
        if( str2[0] == '-' ) isBNeg = true;

        if( isANeg || str1[0] == '+' ) str1 = str1.substr(1, str1.length());
        if( isBNeg || str2[0] == '+' ) str2 = str2.substr(1, str2.length());

        str1 = omitZeros(str1);
        str2 = omitZeros(str2);

        bool isABigger = ((str1.length() > str2.length()) || (str1.length() == str2.length() && str1 > str2)) ? true : false;

        str1 = reverse(str1);
        str2 = reverse(str2);

        if( str1 == "" && str2 == "" ) {
            return "0";
        }
        else if( str2 == "" ) {
            if( isANeg ) return "-" + reverse(str1);
            else return reverse(str1);
        }
        else if( str1 == "" ) {
            if( isBNeg ) return "-" + reverse(str2);
            else return reverse(str2);
        }
        else if( isANeg && isBNeg ) {
            return "-" + addStrings(str1, str2);
        }
        else if( (isANeg || isBNeg) && str1 == str2 ) {
            return "0";
        }
        else if( isANeg ) {
            if( isABigger ) return "-" + omitZeros(subStrings(str1, str2));
            else return omitZeros(subStrings(str2, str1));
        }
        else if( isBNeg ) {
            if( isABigger ) return omitZeros(subStrings(str1, str2));
            else return "-" + omitZeros(subStrings(str2, str1));
        }
        else {
            return omitZeros(addStrings(str1, str2));
        }
    }

    std::string multOne(const std::string str1, std::string str2, std::string result="", int i=0, int carry=0, int shift=0) { 
        if( i < str1.length() ) {
            char x = 0;

            x += (*(str1.begin()+i) - '0');
            x *= (*(str2.begin()+shift) - '0');
            x += carry;

            if( x >= 10 ) {
                carry = x/10;
                x %= 10;
            }
            else {
                carry = 0;
            }

            result += (x + '0');
            return multOne(str1, str2, result, i+1, carry, shift);
        }
        else {
            if( carry != 0 ) result += (carry + '0');
            if( shift < str2.length() - 1 ) {
                std::string tmp_result = std::string(shift, '0')+'0';
                return functions::sum(reverse(result), multOne(str1, str2, tmp_result, 0, 0, shift+1));
            }
            else {
                return reverse(result); // or not reverse?
            }   
        }
    }


    std::string multStrings(std::string str1, std::string str2, std::string carry="", int i=0) {
        return multOne(str1, str2, "", 0);       
    }

    std::string mult(std::string str1, std::string str2) {     
        bool isANeg = false;
        bool isBNeg = false;

        if( str1[0] == '-' ) isANeg = true;
        if( str2[0] == '-' ) isBNeg = true;

        if( isANeg || str1[0] == '+' ) str1 = str1.substr(1, str1.length());
        if( isBNeg || str2[0] == '+' ) str2 = str2.substr(1, str2.length());

        str1 = omitZeros(str1);
        str2 = omitZeros(str2);

        bool isALonger = (str1.length() > str2.length() || str1 > str2) ? true : false;

        str1 = reverse(str1);
        str2 = reverse(str2);

        if( str1 == "" || str2 == "" ) {
            return "0";
        }
        else if( isANeg && isBNeg ) {
            if( isALonger ) return multStrings(str1, str2);
            else return multStrings(str2, str1);
        }
        else if( isANeg ) {   
            if( isALonger ) return "-" + omitZeros(multStrings(str1, str2));
            else return "-" + omitZeros(multStrings(str2, str1));
        }
        else if( isBNeg ) {
            if( isALonger ) return "-" + omitZeros(multStrings(str1, str2));
            else return "-" + omitZeros(multStrings(str2, str1));
        }
        else if (isALonger) {
            return omitZeros(multStrings(str1, str2));
        }
        else {
            return omitZeros(multStrings(str2, str1));
        }
        // BE CAREFUL WITH THIS OMIT ZEROS IN MULT
    }

}

namespace helpers {

    std::string mult(int n, int num, va_list arguments) {

        if( n < num ) {
            std::string first = va_arg(arguments, char*);
            return functions::sum(first, mult(n+1, num, arguments));
        }
        else {
            return "0";
        }

        va_end(arguments);
    }

    std::string sum(int n, int num, va_list arguments ) {

        if( n < num ) {
            std::string first = va_arg(arguments, char*);
            return functions::sum(first, sum(n+1, num, arguments));
        }
        else {
            return "0";
        }

        va_end(arguments);
    }

    std::string Sum(int num, const std::string* str, int i) {
        if( i < num ) {
        return functions::sum(*(str+i), Sum(num, str, i+1));
        }
        else {
            return "0";
        }
    }

    std::string mult(int num, const std::string* str, int i) {
        if( i < num ) {
            return functions::mult(*(str+i), mult(num, str, i+1));
        }
        else {
            return "1";
        }
    }

    void toArray(int num, va_list arguments, std::string array[], int i) {
        if( i < num ) {
            array[i] = va_arg(arguments, char*);
            return toArray(num, arguments, array, i+1);
        }
        return;
    }
}

// main functions
// SUM
std::string Sum(int num, const std::string* str) {
    return helpers::Sum(num, str, 0);
}

std::string Sum(int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string result;
    result = helpers::sum(0, num, arguments);
    va_end(arguments);
    return result;
}

void Sum(std::string& result, int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    result = helpers::sum(0, num, arguments);
    va_end(arguments);
}

void Sum(std::string* result, int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    *result = helpers::sum(0, num, arguments);
    va_end(arguments);
}

void Sum(std::string& result, int num, const std::string* str) {
    result = Sum(num, str);
}

void Sum(std::string* result, int num, const std::string* str) {
    *result = Sum(num, str);
}

// MULT
std::string Mult(int num, const std::string* str) {
    return helpers::mult(num, str, 0);
}

std::string Mult(int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* result = new std::string[num];
    helpers::toArray(num, arguments, result, 0);
    va_end(arguments);
    return Mult(num, result); 
}

void Mult(std::string* result, int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* tmp = new std::string[num];
    helpers::toArray(num, arguments, tmp, 0);
    *result = Mult(num, tmp);
    va_end(arguments);
    delete [] tmp;
}

void Mult(std::string* result, int num, const std::string* str) {
    *result = Mult(num, str);
}

void Mult(std::string& result, int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* tmp = new std::string[num];
    helpers::toArray(num, arguments, tmp, 0);
    result = Mult(num, tmp);
    va_end(arguments);
    delete [] tmp;
}

void Mult(std::string& result, int num, const std::string* str) {
    result = Mult(num, str);
}

// OPERATIONS
std::string Operation(std::string(*f)(int, const std::string*), int num, const std::string* str ) {
    return f(num, str);
}

std::string Operation(std::string(*f)(int, const std::string*), int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* tmp = new std::string[num];
    helpers::toArray(num, arguments, tmp, 0);
    std::string result = f(num, tmp);
    va_end(arguments);
    delete [] tmp;
    return result;
}

void Operation(std::string* result, std::string(*f)(int, const std::string*), int num, const std::string* str) {
    *result = f(num, str);
}

void Operation(std::string* result, std::string(*f)(int, const std::string*), int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* tmp = new std::string[num];
    helpers::toArray(num, arguments, tmp, 0);
    va_end(arguments);
    *result = f(num, tmp);
    delete [] tmp;
}

void Operation(std::string& result, void(*f)(std::string*, int, const std::string*), int num, const std::string* str) {
    f(&result, num, str);
}

void Operation(std::string& result, void(*f)(std::string*, int, const std::string*), int num, ...) {
    va_list arguments;
    va_start(arguments, num);
    std::string* tmp = new std::string[num];
    helpers::toArray(num, arguments, tmp, 0);
    va_end(arguments);
    f(&result, num, tmp);
    delete [] tmp;
}
