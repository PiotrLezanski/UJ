// Piotr Lezanski - 4

import java.util.Scanner; // import scannera

/*
    ogolna idea programu - program wykonuje rekurencyjnie obliczenia, potrzebne do wyznaczenie elementow tablicy, ktore sumuja sie do okreslonej
    liczby. Istnieje wersja rekurecyjna oraz iteracyjna, bedaca symulacja rekurencji (zastapienie stosu systemowego stosem wlasnym)
*/

// stos do symulowania dzialania rekurencji
class Stack {
    Params[] arr; // tablica obiektow Params
    int top;
    int SIZE;
    // konstruktor
    Stack(int SIZE) {
        top = -1;
        this.SIZE = SIZE;
        arr = new Params[SIZE];
    }

    boolean isEmpty() {
        return (top<0);
    }

    void push(Params p) {
        if( top < SIZE-1 ) {
            arr[++top] = p;
        }
    }

    Params pop() {
        return arr[top--];
    }

    Params peek() {
        return arr[top];
    }
}

// klasa Params do przetrzymywania kolejnych danych rekurencyjnych
class Params {
    int index; // sledzenie aktualnego indeksu
    int sum; // aktualna suma
    String label; // label pozwalajacy wrocic do dobrego miejsca w wywolaniach iteracyjnych

    // konstruktor
    Params(int index, int sum, String label) {
        this.index = index;
        this.sum = sum;
        this.label = label;
    }
}

// klasa glowna
public class Source {
    public static Scanner sc = new Scanner(System.in); // obiekt scanner
    public static int[] solution; // tablica pomocnicza do sledzenie uzytych indeksow 
    public static int sum; // suma ktorej szykamy
    static Stack stack; // stos
    
    public static void main(String[] args) {

        int sessions = sc.nextInt(); // pobranie ilosci sesji
        for(int s=0; s<sessions; s++) {
            
            sum = sc.nextInt(); // pobranie szukanej sumy

            int N = sc.nextInt(); // wielkosc tablicy
            int[] elements = new int[N]; // tworzenie tablic
            solution = new int[N+1];
            solution[elements.length] = -2; // syganlizacja, ze suma nie zostala znaleziona
            for(int i=0; i<N; i++) {
                elements[i] = sc.nextInt(); // pobranie elementow
            }
            rec_pakuj(elements, 0, 0); // wykonanie funkcji rekurencyjnej
            if( solution[elements.length] == -2 ) {
                System.out.println("BRAK"); // wypisanie brak jezeli nie znaleziono sumy
            }
            else {
                for(int i=0; i<solution.length-1; i++) {
                    solution[i] = 0; // wyzerowanie tablicy pomocniczej
                }

                iter_pakuj(elements, sum); // wykonanie funkcji iteracyjnej
            }
        }
    }

    // wersja iteracyjna
    public static void iter_pakuj(int[] elements, int sum) {
        stack = new Stack(2*elements.length); // deklaracja stosu
        int index = 0;
        String label;
        int currSum;
        Params Par;

        stack.push(new Params(0, 0, "CALL")); // push 1. elementu

        // wykonuj dopoki stos niepusty
        while(!stack.isEmpty()) {
            Par = stack.pop(); // pobranie elementu i ustawienie jego wartosci jako domyslne
            index = Par.index;
            label = Par.label;
            currSum = Par.sum;
            switch( label ) {
                case "CALL":
                    // pierwsze wykonania
                    if( currSum == sum ) {
                        System.out.print("ITER: " + sum + " =");
                        for(int i=0; i<solution.length-1; i++) {
                            if( solution[i] == 1 ) {
                                System.out.print(" " + elements[i]); // wypisanie elementow
                            }
                        }
                        System.out.println();
                        return; // return konczonacy wykonanie wersji iteracyjnej
                    }
                    else if( index >= elements.length || currSum > sum ) { 
                        // jezeli doszlismy do konca, badz suma jest wieksza niz ta, ktorej szukamy, zakoncz
                        break;
                    }
                    else {
                        solution[index] = 1;
                        currSum += elements[index]; // dodanie (uwzgledniamy ten element w sumie i tablicy pomocniczej)
                        stack.push(new Params(index, currSum, "RESUME1")); // push 2 nowych elementow
                        stack.push(new Params(index+1, currSum, "CALL"));
                    }
                break;

                case "RESUME1":
                    // druga sesja
                    solution[index] = 0; // wyzerowanie wartosci pod indeksem w tablicy pomocniczej
                    currSum -= elements[index]; // odjecie od sumy (nie bierzemy tego elementu pod uwage)
                    stack.push(new Params(index+1, currSum, "CALL")); // push
                break;
            }
        }
    }

    // wersja rekurencyjna
    public static void rec_pakuj(int[] elements, int currSum, int index) {
        // wykonuj rekurencje, tylko, jezeli suma nie zostala jeszcze znaleziona
        if( solution[elements.length] != -1 ) {
            if( currSum == sum ) {
                // jezeli znalezlismy sume, wypisz indeksy
                solution[elements.length] = -1;
                System.out.print("REC:  " + sum + " =");
                for(int i=0; i<solution.length; i++) {
                    if( solution[i] == 1 ) {
                        System.out.print(" " + elements[i]);
                    }
                }
                System.out.println();
            }
            else if( index >= elements.length || currSum > sum ) { // dodalem to co po lub
                return;
            }
            else {
                if( solution[elements.length] != -1 ) {
                    solution[index] = 1; // 
                    currSum = currSum + elements[index]; // wziecie elementu pod uwage, wywowalnie funkcji dla nowej sumy i nowego indeksu
                    rec_pakuj(elements, currSum, index+1);
                }
                if( solution[elements.length] != -1 ) {
                    solution[index] = 0;
                    currSum = currSum - elements[index]; // odjecie tego elementu, gdyz nie spelnia warunkow
                    rec_pakuj(elements, currSum, index+1); // wywolanie funkcji z nowymi parametrami, o indeksie dalszym
                }
            }
            return; // zakoncz
        }
        else {
            return; // zakoncz
        }
        
    }
} // koniec klasy Source

// IN
// 30
// 11
// 18
// 6 2 2 6 3 5 9 11 7 3 7 7 3 3 5 6 5 4 
// 9
// 6
// 6 2 3 4 6 9 
// 12
// 6
// 3 5 6 5 6 9 
// 26
// 16
// 13 18 10 5 11 19 11 16 22 7 24 23 12 25 5 12 
// 13
// 4
// 13 8 5 11 
// 11
// 12
// 2 8 7 5 11 1 2 4 2 11 7 8 
// 24
// 8
// 7 17 9 23 8 15 1 13 
// 14
// 3
// 3 5 9 
// 9
// 8
// 1 8 9 9 6 9 3 9 
// 21
// 17
// 11 6 8 1 13 3 13 5 19 17 6 3 17 2 8 4 7 
// 20
// 18
// 16 11 16 10 15 9 10 20 14 3 15 18 17 15 15 14 3 1 
// 8
// 3
// 2 4 5 
// 8
// 3
// 8 8 1 
// 20
// 17
// 5 15 19 19 10 12 2 19 18 10 10 9 11 18 6 10 4 
// 26
// 10
// 5 23 16 12 9 17 18 18 4 5 
// 9
// 18
// 9 6 5 7 1 1 9 3 9 4 2 7 8 6 4 7 6 8 
// 15
// 13
// 8 14 7 14 3 2 3 13 2 7 10 5 4 
// 20
// 20
// 19 15 3 7 5 12 6 1 20 8 9 5 14 3 10 8 12 15 19 1 
// 30
// 10
// 10 14 7 24 14 26 18 27 4 27 
// 30
// 13
// 10 30 25 1 23 2 10 17 19 7 9 6 18 
// 11
// 18
// 6 10 9 3 7 7 9 2 5 5 2 1 10 11 7 7 9 9 
// 22
// 20
// 11 15 11 3 5 18 7 21 10 17 14 18 20 10 21 10 8 9 19 17 
// 10
// 20
// 1 4 10 10 2 4 7 8 6 9 1 5 1 2 9 4 1 8 3 10 
// 13
// 11
// 1 10 13 4 5 8 7 6 6 10 10 
// 11
// 5
// 6 10 10 10 8 
// 8
// 15
// 8 6 6 3 1 4 4 5 4 4 5 8 2 1 3 
// 18
// 13
// 1 16 16 17 15 14 10 12 8 5 14 18 17 
// 13
// 18
// 2 11 8 1 13 13 8 12 12 3 13 9 6 10 3 7 11 8 
// 22
// 19
// 19 16 10 10 8 22 16 4 22 20 5 11 14 2 7 8 7 16 15 
// 24
// 19
// 14 18 12 13 8 3 4 15 17 15 24 22 1 19 23 22 21 19 6 

// OUT:
// REC:  11 = 6 2 3
// ITER: 11 = 6 2 3
// REC:  9 = 6 3
// ITER: 9 = 6 3
// REC:  12 = 3 9
// ITER: 12 = 3 9
// REC:  26 = 10 5 11
// ITER: 26 = 10 5 11
// REC:  13 = 13
// ITER: 13 = 13
// REC:  11 = 2 8 1
// ITER: 11 = 2 8 1
// REC:  24 = 7 17
// ITER: 24 = 7 17
// REC:  14 = 5 9
// ITER: 14 = 5 9
// REC:  9 = 1 8
// ITER: 9 = 1 8
// REC:  21 = 11 6 1 3
// ITER: 21 = 11 6 1 3
// REC:  20 = 16 3 1
// ITER: 20 = 16 3 1
// BRAK
// REC:  8 = 8
// ITER: 8 = 8
// REC:  20 = 5 15
// ITER: 20 = 5 15
// REC:  26 = 5 16 5
// ITER: 26 = 5 16 5
// REC:  9 = 9
// ITER: 9 = 9
// REC:  15 = 8 7
// ITER: 15 = 8 7
// REC:  20 = 19 1
// ITER: 20 = 19 1
// REC:  30 = 26 4
// ITER: 30 = 26 4
// REC:  30 = 10 1 2 10 7
// ITER: 30 = 10 1 2 10 7
// REC:  11 = 6 3 2
// ITER: 11 = 6 3 2
// REC:  22 = 11 11
// ITER: 22 = 11 11
// REC:  10 = 1 4 2 1 1 1
// ITER: 10 = 1 4 2 1 1 1
// REC:  13 = 1 4 8
// ITER: 13 = 1 4 8
// BRAK
// REC:  8 = 8
// ITER: 8 = 8
// REC:  18 = 1 17
// ITER: 18 = 1 17
// REC:  13 = 2 11
// ITER: 13 = 2 11
// REC:  22 = 16 4 2
// ITER: 22 = 16 4 2
// REC:  24 = 14 3 1 6
// ITER: 24 = 14 3 1 6
