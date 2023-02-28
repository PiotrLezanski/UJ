//Piotr Lezanski
#include <string>  
#include <iostream>


std::string NajwiekszeSlowo(std::string word) {
    std::string biggest;
    std::string temp;

    int i = 0;

    while( word[i] != ' ' && i < word.size() ) {
        biggest += word[i];
        i++;
    }
    i++;

    for(i=i; i<word.size(); i++) {
        while( word[i] != ' ' && i < word.size() ) {
            temp += word[i];
            i++;
        }

        if( temp > biggest ) {
            biggest = temp;
        }
        temp.resize(0);
    }

    return biggest;
}

std::string FormatujNapis(std::string word, std::string str1, std::string str2, std::string str3 ) {
    std::string result;
    
    for(int i=0; i<word.size(); i++) {
        if( word[i] == '{' ) {
            if( word[i+1] == 'p' ) {
                int n = (int)word[i+3] - '0';
                char letter = (char)word[i+5];
                result += std::string(n, letter);
                i += 6;
            }
            else if( word[i+1] == 'u' ) {
                int n = (int)word[i+3] - '0';
                i += 4 + n;
            }
            else if( word[i+1] == 'U' ) {
                int n = (int)word[i+3] - '0';

                result.resize(result.size() - n);

                i += 4;
            }
            else if( word[i+1] == 'w' ) {
                int n = (int)word[i+3] - '0';
                if( n == 1 ) {
                    result += str1;
                }
                else if( n == 2 ) {
                    result += str2;
                }
                else if( n == 3 ) {
                    result += str3;
                }
                i += 4;
            }
            else if( word[i+1] == 'W' ) {
                int n = (int)word[i+3] - '0';
                int index = (int)word[i+5] - '0';
                if( n == 1 ) {
                    for(int j=0; j<index; j++) {
                        if( j >= str1.size() ) {
                            result += ' ';
                        }
                        else {
                            result += str1[j];
                        }   
                    }
                }
                else if( n == 2 ) {
                    for(int j=0; j<index; j++) {
                        if( j >= str2.size() ) {
                            result += ' ';
                        }
                        else {
                            result += str2[j];
                        }   
                    }
                }
                else if( n == 3 ) {
                    for(int j=0; j<index; j++) {
                        if( j >= str3.size() ) {
                            result += ' ';
                        }
                        else {
                            result += str3[j];
                        }   
                    }
                }
                i += 6;
            }   
        }
        else {
            result += word[i];
        }
    }

    return result;
}

std::string NormalizujNapis(std::string word) {
    std::string result;
    int index=0;


    while( word[index] == ' ' || word[index] == '.' || word[index] == ',' ) {
        index++;
    }
    
    while( index < word.size() ) {

        while( word[index] == ' ' && index+1 < word.size() && ( word[index+1] == ' '  || word[index+1] == '.' || word[index+1] == ',' )) {
            index++;
        }
        result += word[index];

        while( (word[index] == '.' || word[index] == ',') && (word[index+1] == '.' || word[index+1] == ',') && index + 1 < word.size() ) {
            index++;
        }

        if( index < word.size() - 1 && (word[index] == '.' || word[index] == ',') && word[index+1] != ' ' ) {
            result += ' ';
        }
        index++;
    }

    if( result[result.size()-1] == ' ' ) {
        result.resize(result.size()-1);
    }
    return result;
} 

std::string UsunSlowo(std::string word, int n) {
    std::string result;
    int start_index = 0;
    int end_index;

    // omitting first spaces or dots
    while( (word[start_index] == ' ' || word[start_index] == '.' || word[start_index] == ',') && start_index < word.size() ) {
        start_index++;
    }

    // std::cout<< start_index <<std::endl;
    for(int i=0; i<n-1; i++) {
        while( word[start_index] != ' ' && start_index < word.size() ) {
            start_index++;
        }
        start_index++;

        while( (word[start_index] == ' ' || word[start_index] == '.' || word[start_index] == ',') ) {
            start_index++;
        }
    }

    end_index = start_index;
    for(int i=start_index; word[i]!=' ' && word[i]!=',' && word[i]!='.' && word[i] != '\0'; i++) {
        end_index++;
    }
    end_index -= 1;

    for(int i=0; i<word.size(); i++) {
        if( i < start_index || i > end_index ) {
            result += word[i];
        }
    }

    return result;
}

int main()
{
    std::string word;
    int n;
    while(1) {
        getline(std::cin, word);
        std::cin>>n;

        std::cout<< UsunSlowo(word, n) <<std::endl;
       getchar();
    }

    return 0;
}

