// Piotr Lezanski - 4
import java.util.Scanner;

/*
    idea programu - wykorzystuje algorytm median median, by wyznaczyc k-ty najmniejszy element w zbiorze. Algorytm dzieli tablice na 5 elementowe zbiory, sortuje ja oraz oblicza mediane median oraz wedlug tego wywoluje algorytm partition
                    Algorytm dziala podobnie do algorytmu quicksort, lecz bierze pod uwage tylko podtablice na prawo, badz lewo od pivota.

    Zlozonosc pesymistyczna - O(n)
*/

// klasa glowna
public class Source {
    static Scanner sc = new Scanner(System.in); // ilosc scanner
    static int median_num = 0;
    public static void main(String[] args) {

        int sessions = sc.nextInt(); // ilosc sesji
        for(int s=0; s<sessions; ++s) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; ++i) {
                arr[i] = sc.nextInt(); // uzupelnienie tablicy wartosciami
            }

            int m = sc.nextInt();
            for(int i=0; i<m; ++i) {
                int set = sc.nextInt();

                // algorytm glowny
                if( !(set >= 1 && set <= arr.length) ) {
                    System.out.print(set + " brak"); // wypisanie braku
                }
                else {
                    System.out.print(set + " " + select(arr, set-1, 0, arr.length)); // wywolanie funkcji
                }
                System.out.println();
            }
        }

    }

    // tradycyjny algorytm partition, ktory ustawia elementy mniejsze od pivota, na lewo od niego, a wieksze na prawo od niego
    static void partition(int[] arr, int target, int left, int right) {
        int r = right-1;
        int i = left;
        while (i <= r) {
            if (arr[i] < target) {
                swap(arr, left, i);
                ++left;
                ++i;
            } 
            else if (arr[i] > target) {
                swap(arr, i, r);
                --r;
            } 
            else {
                ++i;
            }
        }
    }

    // Median of medians; complexity O(n)
    static int select(int[] arr, int target, int left, int right) {
        int m = 0; // ilosc median
        if (!(right - left > 1)) {
            return arr[left]; // base case
        }

        for (int i = left; i < right; i = i + 5) {
            int n;
            // wyznaczenie indeksu konca sortowania, jezeli nie ma wystarczajacej ilosci elementow, by stworzyc zbior 5-elementowy, bierzemy pod uwage zbior (right - i + 1) elementowy
            if( i + 5 < right ) {
                n = i + 5;
            }
            else {
                n = right;
            }
            insertionSort(arr, i, n); // sortowania
            swap(arr, left+m, (i+n)/2); // mediany ustawiane sa na poczatku tablicy, nie ma to znaczenia, gdyz nastepnie wykonujemy algorytm partition
            ++m;
        }

        int median = select(arr, (m+1)/2, left, left + m); // wyznaczenie mediany median

        partition(arr, median, left, right); // partition wedlug mediany median

        int i = left;

        while (arr[i] < median) {
            ++i;
        }
        int smaller = i - 1; // indeks ostatniego elementu, ktory jest mniejszy od pivota

        while (i < right && arr[i] == median) {
            ++i;
        }
        int last = i - 1; // indeks ostatniego elementu rownego rownego pivota

        if (!(target > smaller - left)) {
            return select(arr, target, left, smaller+1); // wywolanie rekurencji dla przedzialu mniejszego niz pivot
        } 
        else if (!(target > last - left)) {
            return median; // zwrocenie wyniku
        } 
        else {
            return select(arr, target - (last - left + 1), last + 1, right); // wywolanie rekurencyjne dla przedzialu wiekszego niz pivot
        }
    }

    // algorytm insertion sort, wykonywany na zbiorach 5 elementowych, zatem zlozonosc czasowa to O(1)
    public static void insertionSort(int[] arr, int l, int r) {
        int j;
        for (int p = l; p < r; p++) {
            int tmp = arr[p];
            for(j = p; j > 0 && tmp < arr[j - 1]; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }

    // algorytm do zamiany miejscami elementow o indeksach i oraz j
    public static void swap(int []arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}

/*
1
5
5 3 4 4 3
5
2 5 1 3 4

3
5
1 2 3 4 5
3
1 2 3
5
5 3 4 4 3
5
2 5 1 3 4
10 1 1 1 1 1 1 1 1 1 1 
5
1 10 0 20 11

1
5
5 3 4 4 3
5
2 5 1 3 4

1
5
1 2 3 4 5
3
1 2 3
*/


