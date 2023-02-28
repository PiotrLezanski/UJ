// Piotr Lezanski - 4

import java.util.Scanner;

// glowna idea - program zamienia postac infiksowa na posfiksowa i odwrotnie, kontroluje przy tym poprawnosc danych wejsciowych i omija nielegalne znaki

// stos do przechowywania wynikow poszczegolnych sesji
class Results {
    static final int maxSize = 1000;
    String[] string_arr;
    int top;

    Results() {
        string_arr = new String[maxSize];
        top = -1;
    }

    void push(String x) {
        if( top < maxSize-1 ) {
            string_arr[++top] = x;
        }
    }   

    void display() {
        for(int i=0; i<top+1; i++) {
            System.out.print(string_arr[i]);
            System.out.println();
        }
    }
}

// stos do przechowywania priorytetow operatorow
class IntStack {
    static final int maxSize = 1000;
    int[] int_arr;
    int top;

    IntStack() {
        int_arr = new int[maxSize];
        top = -1;
    }

    boolean isEmpty() {
        return (top < 0);
    }

    void push(int x) {
        if( top < maxSize-1 ) {
            int_arr[++top] = x;
        }
    }   

    int pop() {
        if( isEmpty() ) {
            return -1;
        }
        else {
            return int_arr[top--];
        }
    }

    int peek() {
        if( isEmpty() ) {
            return -1;
        }
        else {
            return int_arr[top];
        }
    }
}

// stos to przechowywania tymczasowego wyniku w postaci stringa
class StringStack {
    static final int maxSize = 1000;
    String[] string_arr;
    int top;

    StringStack() {
        string_arr = new String[maxSize];
        top = -1;
    }

    boolean isEmpty() {
        return (top < 0);
    }

    void push(String x) {
        if( top < maxSize-1 ) {
            string_arr[++top] = x;
        }
    }   

    String pop() {
        if( isEmpty() ) {
            return "";
        }
        else {
            return string_arr[top--];
        }
    }

    String peek() {
        if( isEmpty() ) {
            return "";
        }
        else {
            return string_arr[top];
        }
    }

    void display() {
        for(int i=0; i<top+1; i++) {
            System.out.print(string_arr[i] + " ");
        }
        System.out.println();
    }
}

// stos do przechowywania pojedynczych znakow
class CharStack {
    static final int maxSize = 1000;
    char[] char_arr;
    int top;

    CharStack() {
        char_arr = new char[maxSize];
        top = -1;
    }

    boolean isEmpty() {
        return (top < 0);
    }

    void push(char x) {
        if( top < maxSize-1 ) {
            char_arr[++top] = x;
        }
    }   

    char pop() {
        if( isEmpty() ) {
            return ' ';
        }
        else {
            return char_arr[top--];
        }
    }

    char peek() {
        if( isEmpty() ) {
            return ' ';
        }
        else {
            return char_arr[top];
        }
    }

    void display() {
        for(int i=0; i<top+1; i++) {
            System.out.print(char_arr[i] + " ");
        }
        System.out.println();
    }
}

// klasa glowna
public class Source {
    // funkcja okreslajaca priorytet operatora
    public static int priority(char x) {
        if( x == '!' || x == '~' ) return 8;
        else if( x == '^' ) return 7;
        else if( x == '*' || x == '/' || x == '%' ) return 6;
        else if( x == '+' || x == '-' ) return 5;
        else if( x == '>' || x == '<' ) return 4;
        else if( x == '?' ) return 3;
        else if( x == '&' ) return 2;
        else if( x == '|' ) return 1;
        else if( x == '=' ) return 0;
        else return 9;
    }

    // funkcja okreslajaca prawostronnosc operatora
    public static boolean isRight(char element) {
        return (element == '~' || element == '!' || element == '^' || element == '=' );
    }

    // funkcja okreslajaca unarnosc operatora
    public static boolean isUnary(char element) {
        return (element == '~' || element == '!');
    }

    // funkcja okreslajaca binarnosc operatora
    public static boolean isBinary(char element) {
        switch(element) {
            case '^':
            case '*':
            case '/':
            case '%':
            case '+':
            case '-':
            case '<':
            case '>':
            case '?':
            case '&':
            case '|':
            case '=':
                return true;
        }
        return false;
    }

    // funkcja konwerujaca zapis infiksowy na postofksowy
    static String infixToPostfix(String str) {
        String result = "";
        CharStack charStack = new CharStack();
        boolean space = false;
        int state = 0; // kontrolowanie stanu
        int count = 0; // kontrolowanie ilosci nawiasow
        // glowna petla
        for(int i=0; i<str.length(); i++) {
            char element = str.charAt(i);
            if( isOK(element) || element == ')' || element == '(' ) {
                // sprawdzanie poprawnosci danych wejsciowych, wedlug automatu podanego na wykladzie
                if( state == 0 ) {
                    if( element == '(' ) {
                        state = 0;
                        count++;
                    }
                    else if( element == '~' || element == '!' ) {
                        state = 2;
                    }
                    else if( element >= 'a' && element <= 'z' ) {
                        state = 1;
                    }
                    else return "error";
                }
                else if( state == 1 ) {
                    if( element == ')' ) {
                        state = 1;
                        count--;
                    }
                    else if( element == '^' || element == '*' || element == '/' || element == '%' || element == '+' || element == '-' || element == '<' || element == '>' || element == '?' || element == '&' || element == '|' || element == '=' ) {
                        state = 0;
                    }
                    else return "error";
                }
                else if( state == 2 ) {
                    if( element == '~' || element == '!' ) {
                        state = 2;
                    }
                    else if( element == '(' ) {
                        state = 0;
                        count++;
                    }
                    else if( element >= 'a' && element <= 'z' ) {
                        state = 1;
                    }
                    else return "error";
                }
                // koniec sprawdzania poprawnosci


                // glowny algorytm
                if( element >= 'a' && element <= 'z' ) {
                    if(space) {
                        result += " ";
                    }
                    result += element;
                    space = true;
                }
                else {
                    // pobieranie i wysylanie na stos odpowiednich wartosci, po porownaniu ich krotnosci oraz priorytetow
                    if( element == '(' ) {
                        charStack.push(element);
                    }
                    else if( element == ')' ) {
                        if( !charStack.isEmpty() ) {
                            char tmp = charStack.pop();
                            // zdjecie ze stosu wszystkich znakow az do napotkania "("
                            while( tmp != '(' ) {
                                result += " ";
                                result += tmp;
                                tmp = charStack.pop();
                            }
                        }
                    }
                    else {
                        if( !charStack.isEmpty() && charStack.peek() != '(' ) {
                            while( !charStack.isEmpty() && !isRight(element) && priority(charStack.peek()) >= priority(element) && charStack.peek() != '(' ) {
                                result += " ";
                                result += charStack.pop();
                            }
                        }
                        charStack.push(element);
                    }
                }
            }
        }

        // sprawdzanie, czy ONP posiada odpowiedni zapis, stan musi byc rowny 1 oraz musi zgadzac sie ilosc nawiasow
        if( state == 1 && count == 0 ) {
            while( !charStack.isEmpty() ) {
                result += " ";
                result += charStack.pop();
            }
            return result;
        }
        else {
            return "error";
        }
        
    }

    // zamiana z postaci postoksowej na infiksowa
    public static String postfixToInfix(String str) {
        String result = ""; // miejsce na wynik
        StringStack stringStack = new StringStack(); // stos dla wyjscia
        IntStack intStack = new IntStack(); // stos dla priorytetow
        char element; // pojedynczy element
        String tmp; // strin tymczasowy, dodawany ostatecznie do wyniku
        int p = 0; // liczba sledzaca poprawnosc danych wejsciowych
        for (int i = 0; i < str.length(); i++) {
            // sprawdzanie czy dane sa poprawne i legalne
            if( isOK(str.charAt(i)) ) {
                element = str.charAt(i);
                if (element >= 'a' && element <= 'z') {
                    p++;
                    stringStack.push(""+element);
                    intStack.push(priority(element));
                } else {
                    if( isBinary(element) ) {
                        // operator binarny
                        p -= 2;
                        if( p < 0 ) return "error";
                        p++;
                    }
                    else if( isUnary(element)) {
                        // operator unarny
                        p--;
                        if( p < 0 ) return "error";
                        p++;
                    } 
                    // jezeli nie, wypisanie "error"

                    tmp = "";

                    // glowna petla 
                    if (element != '!' && element != '~') { 
                        stringStack.peek();
                        if (element != '=' && intStack.peek() <= priority(element)) {
                            if (element == '^' && intStack.peek() == priority(element)) {
                                tmp = stringStack.pop();
                            } 
                            else {
                                tmp = "("+ " " + stringStack.pop()+ " " + ")";
                                stringStack.peek();
                            }
                        } 
                        else {
                            tmp = stringStack.pop();
                        }
                        // usuniecie operatora ze stosu
                        intStack.pop();

                        if (!(intStack.peek() >= priority(element))) {
                            tmp = "(" + " " +  stringStack.pop() + " " + ")" + " " + element + " " + tmp;
                        } 
                        else {
                            tmp = stringStack.pop() + " " + element + " " + tmp;
                        }
                        intStack.pop();
                        stringStack.peek();
                    } 
                    else {
                        // specjalne traktowanie operatorow
                        if( intStack.peek() >= priority(element)) {
                            tmp = element+ " " + stringStack.pop();
                        }
                        else {
                            tmp = element+ " " + "("+ " " + stringStack.pop()+ " " + ")";
                        }
                        intStack.pop();
                        stringStack.peek();
                    }
                    stringStack.peek();
                    intStack.push(priority(element));
                    stringStack.push(tmp);
                }
            }
        }
        // sprawdzenie poprawnosci danych wejsciowych i wypisanie "error", jezeli nie sa poprawne
        if( p == 1 ) {
            result = stringStack.pop();
            stringStack.peek();
            return result;
        }
        else {
            return "error";
        }
    }

    // funkcja sprawdzajaca czy operator jest odpowiedni
    public static boolean isOK(char element) {
        if (element >= 'a' && element <= 'z') {
            return true;
        } else {
            switch (element) {
                case '=':
                case '!':
                case '<':
                case '?':
                case '>':
                case '+':
                case '&':
                case '-':
                case '*':
                case '/':
                case '|':
                case '%':
                case '^':
                case '~':
                    return true;
            }
        }
        return false;
    }

    // = = = = = = = = = = = = = = = =
    public static Scanner sc = new Scanner(System.in); // obiekt scanner
    // glowna metoda
    public static void main(String[] args) {
        Results results = new Results(); // nowy stos na odpowiedz
        int sessions = Integer.parseInt(sc.nextLine()); // ilosc sesji danych
        for (int s=0; s<sessions; s++) {
            String str = sc.nextLine(); // pobranie strina
            String type = str.substring(0, 5); // odizolowanie sposobu zapisu wyrazenie
            str = str.replace(type, ""); // usuniecie sposobu zapisu
            if( type.equals("INF: ") ) {
                str = infixToPostfix(str); // zamiana
                results.push("ONP: " + str); // wrzucenie na stos wyniku
            }
            else if( type.equals("ONP: ") ) {
                str = postfixToInfix(str); // zamiana 
                results.push("INF: " + str); // wrzucenie na stos wyniku
            }
        }
        results.display(); // wyswietlenie wyniku
    }
} // koniec klasy Source


/* INPUT
INF: a+b+(~a-a)
INF: a+b+(~a-a)
INF: x=~~a+b*c
INF: t = ~ a < x < ~b
INF: ~a-~~b<c+d&!p|!!q   ? error rly?  
INF: a^b*c-d<xp|q+x
INF: x=~a*b/c-d+e%~f
INF: x=a^b^c               
INF: ((a+b*c))
INF: x=a=b=c^d^e           

ONP: ( a,b,.).c;-,*
ONP: -abc-+de^/
ONP: a
ONP: ab*cd**
ONP: ab+a~a-+
ONP: ab+cd++
ONP: ab/c*
ONP: abc++def++g+++
ONP: abc-+de^/
ONP: xabc**=
*/

/* OUTPUT
ONP: ab+a~a-+
ONP: ab+a~a-+
ONP: xa~~bc*+=
ONP: ta~x<b~<=
ONP: error
ONP: error
ONP: xa~b*c/d-ef~%+=
ONP: xabc^^=
ONP: abc*+
ONP: xabcde^^===

INF: a*(b-c)
INF: error
INF: a
INF: a*b*(c*d)
INF: a+b+(~a-a)
INF: a+b+(c+d)
INF: a/b*c
INF: error
INF: (a+(b-c))/d^e
INF: x=a*(b*c)
*/

