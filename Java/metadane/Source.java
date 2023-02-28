// Piotr Lezanski - 4

import java.util.Scanner;

/*
    idea programu - program sortuje metadane podane w konsoli wedlug podanej kolumny oraz podanej tendencji
    zlozonosc - zlozonosc quicksort O(nlogn), selection sort O(n^2)
    zlozonosc srednia - O(nlogn)
*/

// klasa glowna
public class Source {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int sessions = sc.nextInt(); // ilosc sesji danych
        sc.nextLine();
        for(int i=0; i<sessions; ++i) {
            String[] tmp_data = sc.nextLine().split(","); // pobranie wartosci okreslajacych zachowanie operacji
            int row_num = Integer.parseInt(tmp_data[0]); // ilosc wierszy
            int col_accTo = Integer.parseInt(tmp_data[1]); // kolumna wedlug ktorej sortujemy
            int behav = Integer.parseInt(tmp_data[2]); // tendencja

            String[] categories = sc.nextLine().split(","); // kategorie podawanych wartosci
            int col_num = categories.length; // ilosc kolumn

            String[][] data = new String[row_num][col_num]; // glowna tablica 2d

            // pobranie danych do tablicy
            for(int j=0; j<row_num; ++j) {
                String[] row_data = sc.nextLine().split(",");
                for(int k=0; k<col_num; ++k) {
                    data[j][k] = row_data[k];
                }
            }

            // jezeli ilosc jest mniejsza niz 5, wykonac ma sie selecion sort
            if( row_num < 5 ) {
                if( behav < 0 ) selectionSort(data, col_accTo, false);
                else selectionSort(data, col_accTo, true); 
                shiftElem(categories, data, col_accTo-1); // przesuniecie elementow by wypisac w odpowiedniej kolejnosci
            }
            // w przeciwnym wypadku quicksort
            else {
                if( behav < 0 ) quickSort(data, col_accTo-1, false);
                else quickSort(data, col_accTo-1, true);
                shiftElem(categories, data, col_accTo-1); // przesuniecie elementow by wypisac w odpowiedniej kolejnosci
            }

            // wypisanie kategorii
            for(int j=0; j<col_num; ++j) {
                if( j == col_num - 1 ) System.out.print(categories[j]);
                else System.out.print(categories[j] + ",");
            }
            System.out.println();
            // wypisanie tablicy 2d
            for(int j=0; j<row_num; ++j) {
                for(int k=0; k<col_num; ++k) {
                    if( k == col_num-1 ) System.out.print(data[j][k]);
                    else System.out.print(data[j][k] + ",");
                }
                System.out.println();
            }
            if( i != sessions-1 ) System.out.println();
        }

    }

    // algorytm quicksort
    public static void quickSort(String[][] data, int col_num, boolean ascending) {
        int size = data.length;
        int l = 0;
        int r = size - 1;
        int q, i = 0;
        int tmpr = r;
        // jezeli jest numeryczny
        if( isNumeric(data[0][col_num]) ) {
            // jedna z kilku implementacji quicksort, bazujaca na nieskonczonej petli, z ktorej wyjscie dokonuje sie jedynie, jak indeks zejdzie ponizej 0
            while (true) {
                i--;
                while (l < tmpr) {
                    q = partition(data, col_num, l, tmpr, true, ascending); // wyznaczanie szukanego indeksu
                    tmpr = q - 1;
                    ++i;
                }
                if (i < 0)
                    break; // wyjscie
                l++;
                tmpr = size - 1; // szukanie kolejnego indeksu
            }
        }
        // jezeli nie jest numeryczny - komentarze analogiczne jak wyzej
        else {
            while (true) {
                i--;
                while (l < tmpr) {
                    q = partition(data, col_num, l, tmpr, false, ascending);
                    tmpr = q - 1;
                    ++i;
                }
                if (i < 0)
                    break;
                l++;
                tmpr = size - 1;
            }
        }
    }
    
    private static int partition(String[][] data, int col_num, int l, int r, boolean numeric, boolean ascending) {
        // jezeli jest numeryczny
        if( numeric ) {
            int pivot = Integer.parseInt(data[(l + r) / 2][col_num]); // tymczasowo wybierany pivot
            while (l <= r) {
                // dla rosnacej tendencji
                if( ascending ) {
                    while (Integer.parseInt(data[r][col_num]) > pivot) // szukanie pierwszego elementu <= od pivota
                        r--;
                    while (Integer.parseInt(data[l][col_num]) < pivot) // szukanie pierwszego elementu >= od pivota
                        l++;
                }
                // dla malejacej tendencji
                else {
                    while (Integer.parseInt(data[r][col_num]) < pivot) // szukanie pierwszego elementu <= od pivota
                        r--;
                    while (Integer.parseInt(data[l][col_num]) > pivot) // szukanie pierwszego elementu >= od pivota
                        l++;
                }
                if (l <= r) {
                    // swap wszystkich elementow dwoch kolumn
                    for(int x=0; x<data[0].length; ++x) {
                        String tmp = data[r][x];
                        data[r][x] = data[l][x];
                        data[l][x] = tmp;
                    }
                    l++;
                    r--;
                }
            }
            return l;
        }
        // jezeli nie jest numeryczny - komentarze analogiczne jak wyzej
        else {
            String pivot = data[(l + r) / 2][col_num];
            while (l <= r) {
                if( ascending ) {
                    while (data[r][col_num].compareTo(pivot) > 0)
                        r--;
                    while (data[l][col_num].compareTo(pivot) < 0)
                        l++;
                }
                else {
                    while (data[r][col_num].compareTo(pivot) < 0)
                        r--;
                    while (data[l][col_num].compareTo(pivot) > 0)
                        l++;
                }
                if (l <= r) {
                    for(int x=0; x<data[0].length; ++x) {
                        String tmp = data[r][x];
                        data[r][x] = data[l][x];
                        data[l][x] = tmp;
                    }
                    l++;
                    r--;
                }
            }
            return l;
        }
    }

    // algorytm selection sort
    public static void selectionSort(String[][] data, int col_num, boolean ascending) {
        // dla wejscia numerycznego
        if( isNumeric(data[0][col_num-1]) ) {
            for(int i = 0; i<data.length-1; ++i) {
                int min = i; // ustawienie indeksu minimalnego/maksymalnego
                for(int j = i + 1; j<data.length; ++j) {
                    // tendencja malejaca
                    if( !ascending ) {
                        
                        if( Integer.parseInt(data[j][col_num-1]) > Integer.parseInt(data[min][col_num-1]) ) {
                            min = j; // nowy maksymalny indeks
                        }
                    }
                    // tendencja rosnaca
                    else {
                        if( Integer.parseInt(data[j][col_num-1]) < Integer.parseInt(data[min][col_num-1]) ) {
                            min = j; // nowy minimalny indeks
                        }
                    }
                }

                // swap wszystkich elementow podanych kolumn, jezeli min == i to nie ma potrzeby nic robic
                if( min != i ) {
                    for(int k=0; k<data[i].length; ++k) {
                        String tmp = data[i][k];
                        data[i][k] = data[min][k];
                        data[min][k] = tmp;
                    }
                }
            }
        }
        // dla wejscia nienumerycznego - komentarze analogiczne jak wyzej
        else {
            for(int i = 0; i<data.length-1; ++i) {
                int min = i;
                for(int j = i + 1; j<data.length; ++j) {
                    if( !ascending ) {
                        
                        if( data[j][col_num-1].compareTo(data[min][col_num-1]) > 0 ) {
                            min = j;
                        }
                    }
                    else {
                        if( data[j][col_num-1].compareTo(data[min][col_num-1]) < 0 ) {
                            min = j;
                        }
                    }
                }

                // swap
                if( min != i ) {
                    for(int k=0; k<data[i].length; ++k) {
                        String tmp = data[i][k];
                        data[i][k] = data[min][k];
                        data[min][k] = tmp;
                    }
                }
            }
        }
    } 

    // przesuwanie elementow wszystkich kolumn tak, by jako pierwsza opisana byla kolumna wedlug ktorej sortujemy
    public static void shiftElem(String[] labels, String[][] data, int new_first) {
        String tmp;
        // zamiana elementow w tablicy kategorii
        tmp = labels[new_first];
        for(int i=new_first; i>0; --i) {
            labels[i] = labels[i-1]; // shift
        }
        labels[0] = tmp;

        // zamiana elementow w glownej tablicy 2d
        int row = data.length;
        for(int i=0; i<row; ++i) {
            tmp = data[i][new_first];
            for(int j=new_first; j>0; --j) {
                data[i][j] = data[i][j-1]; // shift 
            }
            data[i][0] = tmp;
        }
    }

    // funkcja zwracajaca true dla liczb oraz false dla nie-liczb
    public static boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } 
        catch(NumberFormatException e){  
            return false;  
        }  
    }

}

/*

IN:
1
3,2,1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56

3
3,1,-1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56
3,2,1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56
3,4,-1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56

1
5,1,1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Californication,1999,15,56
Unlimited Love,2022,17,73
House,2020,24,19
Nationa,1399,59,63

1
5,4,-1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Californication,1999,15,56
Unlimited Love,2022,17,73
House,2020,24,19
Nationa,1399,59,63

2
3,2,1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56
3,4,-1
Album,Year,Songs,Length
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73
Californication,1999,15,56


OUT:
Year,Album,Songs,Length
1999,Californication,15,56
2006,Stadium Arcadium,28,122
2022,Unlimited Love,17,73


Album,Year,Songs,Length
Unlimited Love,2022,17,73
Stadium Arcadium,2006,28,122
Californication,1999,15,56

Year,Album,Songs,Length
1999,Californication,15,56
2006,Stadium Arcadium,28,122
2022,Unlimited Love,17,73

Length,Album,Year,Songs
122,Stadium Arcadium,2006,28
73,Unlimited Love,2022,17
56,Californication,1999,15


Album,Year,Songs,Length
Californication,1999,15,56
House,2020,24,19
Nationa,1399,59,63
Stadium Arcadium,2006,28,122
Unlimited Love,2022,17,73


Length,Album,Year,Songs
122,Stadium Arcadium,2006,28
73,Unlimited Love,2022,17
63,Nationa,1399,59
56,Californication,1999,15
19,House,2020,24


Year,Album,Songs,Length
1999,Californication,15,56
2006,Stadium Arcadium,28,122
2022,Unlimited Love,17,73

Length,Album,Year,Songs
122,Stadium Arcadium,2006,28
73,Unlimited Love,2022,17
56,Californication,1999,15
*/