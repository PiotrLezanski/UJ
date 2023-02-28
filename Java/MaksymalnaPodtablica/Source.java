// Piotr Lezanski - 4

import java.util.Scanner;

// klasa glowna
class Source {
    public static Scanner sc = new Scanner(System.in); // obiekt scanner
    // glowa metoda
    public static void main(String[] args) {
        int session = sc.nextInt();

        // ile roznych wykonan?
        for (int s = 1; s <= session; s++) {
            int c = sc.nextInt(); // wczytanie numera wejscia
            sc.next(); // wczytanie dwukropka
            int n = sc.nextInt(); // wczytanie ilosci wierszy
            int m = sc.nextInt(); // wczytanie ilosci kolumn
            int[][] arr = new int[n][m]; // glowa tablica
            int[][] tmp_arr = new int[n][m]; // tablica do przechowywania tymczasowych elementow

            boolean empty = true; // jezeli tablica jest pusta (to znaczy wszystkie elementy sa mniejsze od 0)
            // przypisanie wartosci do glownej tablicy
            for(int g=0; g<n; g++) {
                for(int h=0; h<m; h++) {
                    arr[g][h] = sc.nextInt();
                    if( arr[g][h] >= 0 ) empty = false;
                }
            }

            // tablica nie moze byc pusta
            if (!empty) {
                int max = 0;        // maksymalna suma
                int row_start = 0;  // numer poczatkowego wiersza dla najwiekszej podtablicy
                int col_start = 0;  // numer poczatkowej kolumny dla najwiekszej podtablicy
                int row_end = 0;    // numer koncowego wiersza dla najwiekszej podtablicy
                int col_end = 0;    // numer koncowej kolumny dla najwiekszej podtablicy
                int min_size = n*m; // minimalny rozmiar podstacy (potrzebne do odpowiedniej walidacji najwiekszej podtablicy

                // glowna petla
                for (int b = 0; b < n*m; b++) {
                    int x = b/m; // x sledzi przechodzenie indeksow po wierszach
                    int y = b%m; // y sledzi przechodzenie indeksow po kolumnach

                    for (int i = x; i < n; i++) {
                        for (int j = y; j < m; j++) {
                            
                            tmp_arr[i][j] = arr[i][j];
                            // glowne obliczenia 
                            // operacje na elementach podtablic, liczenie ich wielkosci czastkowych, a nastepnie porownanie ich
                            if (i > x) {
                                tmp_arr[i][j] += tmp_arr[i-1][j];
                            }
                            if (j > y) {
                                tmp_arr[i][j] += tmp_arr[i][j-1];
                            }
                            if (i > x && j > y) {
                                tmp_arr[i][j] -= tmp_arr[i-1][j-1];
                            }
                            
                            // sprawdzanie rozmiaru podtablicy, by moc znalezc najmniejsza tablice posiadajaca maksymalna wartosc elementow
                            int size = (i-x+1)*(j-y+1);

                            if (tmp_arr[i][j] > max || (tmp_arr[i][j] == max && size < min_size)) {
                                // inijalizacja nowej najwiekszej podtablicy
                                // inicjalizacja nowych wartosci dla nowej najwiekszej podtablicy
                                max = tmp_arr[i][j];
                                row_start = x;
                                col_start = y;
                                row_end = i;
                                col_end = j;
                                min_size = size;
                            }
                        }
                    }
                }
                // wypisywanie ostatecznego wyniku
                System.out.println(c+": "+"n = "+n+" m = "+m+", s = "+max+", mst = a["+row_start+".."+row_end+"]["+col_start+".."+col_end+"]");
            } 
            else {
                // tablica jest pusta
                System.out.println(c+": n = "+n+" m = "+m+", s = 0, mst is empty");
            }
        }
    }
} // koniec klasy Source

// TESTY
// test.in
// 8
// 1 : 2 5
// 1 1 -1 -1 0
// 1 1 -1 -1 4

// 2 : 4 5
// 1 2 -1 -4 -20
// -8 -3 4 2 1
// 3 8 10 1 3
// -4 -1 1 7 -6

// 3 : 2 6
// -2 7 -4 8 -5 4
// -2 7 -4 8 -5 4

// 4 : 3 6
// 5 3 6 7 3 -3
// 4 -5 72 -4 -1 -6
// -5 -73 13 6 2 5

// 5 : 1 8
// 9 8 -3 9 1 1 -2 3

// 6 : 4 5
// 5 -7 -1 -4 -2
// -8 3 4 2 1
// -7 8 10 -1 3
// -4 2 1 7 1 

// 7 : 2 5
// 2 2 -1 -1 6
// 2 2 -2 -2 4

// 8 : 2 6
// -1 -7 -4 -9 -5 4
// 7 7 -5 8 -5 -8

// testy.out
// 1: n = 2 m = 5, s = 4, mst = a[1..1][4..4]
// 2: n = 4 m = 5, s = 29, mst = a[1..3][1..3]
// 3: n = 2 m = 6, s = 22, mst = a[0..1][1..3]
// 4: n = 3 m = 6, s = 104, mst = a[0..2][2..4]
// 5: n = 1 m = 8, s = 26, mst = a[0..0][0..7]
// 6: n = 4 m = 5, s = 41, mst = a[1..3][1..4]
// 7: n = 2 m = 5, s = 12, mst = a[0..1][0..4]
// 8: n = 2 m = 6, s = 17, mst = a[1..1][0..3]

















