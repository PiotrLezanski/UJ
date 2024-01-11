// Piotr Lezanski - 4
import java.util.Scanner;

// idea algorytmu - szukanie maksymalniej ilosci trojkatow, ktore mozna stworzyc z bokow o danych dlugosciach
// algorytm idzie po indeksach i, j, k sprawdzajac czy wartosci pod tymi indeksami spelaniaja warunek trojkata
// opis zlozonosci - algorytm posiada dwie petle for oraz jednego whilea (binary search), zatem zlozonosc pesymistyczna to O(lognn^2)

// klasa glowna
public class Source {
    public static Scanner sc = new Scanner(System.in); // obiekt scanner
    // glowa metoda
    public static void main(String[] args) {
        int sessions = sc.nextInt(); // pobranie ilosci sesji
        // glowna petla
        for(int s=1; s<=sessions; s++) {
            int elements = sc.nextInt(); // pobranie wielkosci tablicy
            int[] a = new int[elements]; // inicjalizacja tablicy
            if( s != 1 ) System.out.println();
            System.out.print(s+": n= "+elements); // wypisanie 1. lini wyjscia
            // przypisanie wartosci do tablicy
            for(int e=0; e<elements; e++) {
                a[e] = sc.nextInt(); 
            }
            // insertion sort - sortowanie tablicy
            InsertionSort(a);
            // wypisanie zawartosci posortowanej tablicy
            printArr(a);

            // glowny algorytm
            // glowna idea - iterujemy po 1. i 2. indeksie, wybierajac odpowiednio 3 indeks, tak, by sprawdzic wszystkie mozliwosci
            int numOfTriangles = 0; // zmienna kontrolujaca aktualna ilosc znalezionych trojkatow
            int counter = 0;
            int n = a.length;
            int index;
            int suma = 0;

            //petla iterujaca od 0 indeksu tablicy do ostatniego indesku tablicy, wskazuje ona na indeks najkrotszego boku trojkata
            for (int i = 0; i < n; i++) {
                //petla iterujaca od idneksu o 1 wiekszego od indeksu zewnetrzenj petli do ostatniego indeksu petli, wskazuje ona na indeks sredniego boku trojkata
                for (int j = i + 1; j < n; j++) {
                    suma = a[i] + a[j]; //suma dlugosci dwoch krotszych bokow trojkata, dzieki niej wiemy jaka maksymalna wartosc moze miec 3 bok trojkata
                    index = binarySearch(a, suma);   //wywolanie funkcji ktora wskaze nam indeks jaki moze miec maksymalny bok trojkata w danej iteracji petli
                    numOfTriangles += index - (j + 1); //zliczenie ilosci trojkatow jakie mozna zbudowac przy danych wartosciach tablicy i danych iteracjach petli
                    if(counter<=10) {   // warunek potrzebny aby wypisac do 10 trojek indeksow tablicy z ktorych mozna stworzyc trojkat
                        for (int k = j + 1; k < index; k++) {
                            counter++;
                            if (counter <= 10) {
                                System.out.print("(" + i + "," + j + "," + k + ")" + " ");  //wypisanie indeksow tablicy z ktorych wartosci mozna stworzyc trojkat
                            }   
                            else break;
                        }
                    }
                }
            }

            System.out.println();
            if( numOfTriangles == 0 ) { 
                // jezeli nie znaleziono zadnego trojkata, wypisz odpowiedni komentarz
                System.out.print("Triangles cannot be built");
            }
            else {
                // wypisanie ilosci znalezionych trojkatow
                System.out.print("Number of triangles: "+numOfTriangles);
            }
        }
    }

    public static void printArr(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            // wypisanie elementow tablicy, po 25 w jednym wersie
            if( i % 25 == 0 ) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
    }

    public static void InsertionSort(int[] arr) {
        // typowy algorytm "insertion sort"
        // idea - tablica podzielona jest na podtablice posortowana i podtablice nieposortowana. Iterujac po tablicy kazdy element wkladamy na odpowienie miejsce juz posortowanej tablicy. W konsewkwencji dostajemy posortowana tablice
        int x;
        int y;
        int tmp;
        for(x=1; x<arr.length; x++) {
            tmp = arr[x];
            y = x - 1;
            while( y >= 0 && arr[y] > tmp ) {
                arr[y+1] = arr[y]; 
                y--;
            }
            arr[y+1] = tmp;
        }
    }

    //funkcja na bazie Binary Search, polegajaca na wyznaczeniu idneksu poszukiwanego elemenntu w tablicy ktorym w tym przypadku jest x
    //jezeli takich elementow jest kilka to wyznaczenie pierwszego wystapienia tego eleementu
    //jezeli takiego elementu nie ma to wyznaczenie indeksu wartosci elementu ktorego wartosc jest > od tego elementu i jej wartosc -x jest najmniejsza
    public static int binarySearch(int tab[], int x) {
        int last=-1;
        int l = 0, r = tab.length-1;
        if(tab[0]>=x) {
            return 0;
        }
        else if(tab[r]<x) {
            return r+1;
        }
        else {
            while (l <= r) {
                int m = (l + r) / 2;
                if((m == 0 || tab[m - 1] < x) && tab[m] == x) {
                    return m;
                }
                if (tab[m] < x) {
                    l = m + 1;
                    last = l;
                } 
                else {
                    r = m - 1;
                    last = r;
                }
            }

            if (tab[last] > x) return last;
            else return last + 1;
        }
    }

} // koniec klasy Source

// Zlozonosc czasowa - O(n^2logn).



// TESTY
// IN
// 4 35 18 59 3
// 8 6 3 8 45 3 6 1 5
// 7 3 9 7 5 4 9 7
// 9 1 1 1 1 1 1 1 1 1
// 3 14 52 64

// OUT
// 1: n= 4
// 3 18 35 59
// Triangles cannot be built

// 2: n= 8
// 1 3 3 5 6 6 8 45 
// (0,1,2) (0,4,5) (1,2,3) (1,3,4) (1,3,5) (1,4,5) (1,4,6) (1,5,6) (2,3,4) (2,3,5)
// Number of triangles: 17

// 3: n= 7
// 3 4 5 7 7 9 9 
// (0,1,2) (0,2,3) (0,2,4) (0,3,4) (0,3,5) (0,3,6) (0,4,5) (0,4,6) (0,5,6) (1,2,3)
// Number of triangles: 27

// 4: n= 9
// 1 1 1 1 1 1 1 1 1 
// (0,1,2) (0,1,3) (0,1,4) (0,1,5) (0,1,6) (0,1,7) (0,1,8) (0,2,3) (0,2,4) (0,2,5)
// Number of triangles: 84

// 5 14 52 64

// 14 52 64 
// (0,1,2)
// Number of triangles: 1
