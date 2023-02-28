// Piotr Lezanski - 4

import java.util.Scanner;

/*
    ogolna idea - program uklada po kolei piosenki z jednej czesci oraz drugiej, ponadto wyznacza ich najwiekszy wspolny prefix
    
    zlozonosc - czas potrzebny na operacje uzyte do rozwiazania zadania przedstawione sa nizej. T(n/2) oznacza rekurencjyjne wykonanie metody Merge(). n/2 - swapowanie elementow tablic. n - szukanie prefiksu. 1 - instrukcje warunkowe i przypisania. 
    
    obliczenia:
    T(n) = 2T(n/2) + n+1+1+n/2+n ---> T(n) = 2T(n/2) + n  --(z tw. o rekurencji zwyczajnej)--> O(nlogn)
*/

// klasa glowna
public class Source {
    static Scanner sc = new Scanner(System.in);
    public static String prefix = ""; // zmienna do przechowywania prefiksu
    public static String tmp_pref = "";
    public static void main(String[] args) {
        int sessions = sc.nextInt(); // ilosc sesji
        for(int i=0; i<sessions; i++) {
            int songs_num = sc.nextInt(); // liczba piosenek w tablicy
            sc.nextLine();
            String songs = sc.nextLine(); // elementy glownej tablicy
            String[] arr = songs.split(" "); // zapisanie elementow do tablicy

            prefix = arr[0]; // ustawienie prefiksa jako pierwszy element
            if( songs_num % 2 == 0 ) {
                Merge(arr, 0, arr.length-1, songs_num); // wykonanie funkcji dla nieparzystej ilosci elementow
            }
            else {
                Merge(arr, 0, arr.length, songs_num); // dla parzuystej
            }

            for(int k=0; k<arr.length; k++) {
                System.out.print(arr[k] + " "); // wypisanie tablict
            }
            System.out.println();
            System.out.println(prefix); // wypisanie prefiksu
        }
    }

    public static void Merge(String[] arr, int start, int end, int length) {
        // prefix
        tmp_pref = "";
        if( prefix != "" && arr[start] != prefix ) {
            prefix(arr[start], prefix, 0); // szukanie najwiekszego prefiksu
            prefix = tmp_pref; // podmiana
        }
        tmp_pref = "";
        if( end < arr.length && arr[end] != prefix && prefix != "" ) {
            prefix(arr[end], prefix, 0); // szukanie najwiekszego prefiksu
            prefix = tmp_pref; // podmiana
        }

        // swaps
        if( length <= 2 ) {
            return; // jezeli dlugosc mniejsza lub rowna 2, to return, gdyz nie ma co zamieniac
        }
        if( start+1 == end ) return;
        else {
            int middle_first_half = start + (end-start+1)/4;
            int middle_second_half = start + (end-start+1)/2;

            if( (end-start+1) % 4 != 0 ) {
                swapArrays(arr, middle_first_half, middle_second_half, middle_second_half - middle_first_half - 1, (end-start+1)/2); // zamiana pierwszych elementow z pierwszego oraz drugiego zetsawu
                shift(arr, middle_second_half-1, (end-start+1)/2); // jezeli wystapila nieparzysta ilosc elementow, to nalezy zrobic shifta, ktory ostatni element pierwszego zestawu wrzuci na dobre miejsce
                middle_second_half--;
                end -= 2;
            }   
            else {
                swapArrays(arr, middle_first_half, middle_second_half, middle_second_half - middle_first_half, 0); // zamiana elementow dla parzystej ilosci
            }

            Merge(arr, start, middle_second_half-1, length/2); // wywolanie rekurencji dla lewej czesci
            Merge(arr, middle_second_half, end, length/2); // wywowalnie rekurencji dla prawej czesci
        }
    }

    public static void prefix(String str1, String str2, int index_str) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = (len1 >= len2) ? len2 : len1; // minimalna wielkosc 
        if( index_str >= minLen ) return;
        else if( str1.charAt(index_str) != str2.charAt(index_str) ) return;
        else {
            tmp_pref += str2.charAt(index_str); // dodanie do tmp+pref aktualnego najwieszego prefiksu obu slow
            prefix(str1, str2, index_str+1); // wywowanie rekurencyjne dla kolejnego indeksu
        }
    }

    public static void swapArrays(String[] arr, int startFirst, int startSecond, int num, int length) {
        if( num <= 0 ) {
            return;
        }
        else {
            if( length % 2 != 0 ) {
                swap(arr, startFirst, startSecond); // zamiana elementow
                swapArrays(arr, startFirst+1, startSecond+1, num-1, length); // rekurencja
            }
            else {
                swap(arr, startFirst, startSecond); // zamiana elementow
                swapArrays(arr, startFirst+1, startSecond+1, num-1, length); // rekurencja
            }
        }
    }

    public static void swap(String[] arr, int i, int j) { // zwyczajny swap dwoch elementow tablicy
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void shift(String[] arr, int start, int num) { // shift, ktory przesuwa wszystkie elementy od indeksu start, o ilosc miejsc num
        String tmp = arr[start];
        int i;
        for(i=0; i<num-1; i++) {
            arr[start+i] = arr[start+i+1];
        }
        arr[start+i] = tmp;
    }
}

/*
TESTY:
IN:
1
8
a1 a2 a3 a4 b1 b2 b3 b4
*/

/*
1
12
a1 a2 a3 a4 a5 a6 b1 b2 b3 b4 b5 b6
*/

/*
1
16
a1 a2 a3 a4 a5 a6 a7 a8 ab1 ab2 ab3 ab4 ab5 ab6 ab7 b8
*/

/*
1
10
a1 a2 a3 a4 a5 b1 b2 b3 b4 b5
*/

/*
1
3
abb1 abb2 abb3
*/

/*
1
11
a1 a2 a3 a4 a5 a6 b1 b2 b3 b4 b5
*/

/*
1
11
a1 a2 a3 a4 a5 a6 a7 a8 a9 a10 a11
*/

/*
1
6
a1 a2 a3 b1 b2 b3
*/

/*
OUT:
a1 b1 a2 b2 a3 b3 a4 b4 

a1 b1 a2 b2 a3 b3 a4 b4 a5 b5 a6 b6 

a1 ab1 a2 ab2 a3 ab3 a4 ab4 a5 ab5 a6 ab6 a7 ab7 a8 b8 

a1 b1 a2 b2 a3 b3 a4 b4 a5 b5 

abb1 abb3 abb2 
abb
a1 b1 a2 b2 a3 b3 a4 b4 a5 b5 a6 

a1 b1 a2 b2 a3 b3 

*/
