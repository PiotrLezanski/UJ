// Piotr Lezanski - 4
import java.util.Scanner;

/*
    idea programu - program sortuje n ciagow przy uzyciu max kopca
    zlozonosc - kopiec ma wielkosc n, zatem operacje heapify oraz usuwania maksymalnego elementu dzialaja w czasie logn. Kopiec tworzony jest z tablicy dwuwymiarowej n*m, zatem algorytm dziala w zlozonosci nmlogn
*/

class Node {
    int value; // wartosc
    int seq_num; // indeks ciagu, z ktorego pochodzi element
    int index; // indeks w tym ciagu

    Node(int v, int seq, int in) {
        this.value = v;
        this.seq_num = seq;
        this.index = in;
    }
    Node(int v) {
        value = v;
    }
}

// klasa reprezentujaca min heap
class Heap {
    static Node heap_data[];
    static int heap_max_size; // maksymalny rozmiar
    static int heap_curr_size; // aktualny rozmiar

    Heap(int size) {
        heap_data = new Node[size];
        heap_max_size = size;
        heap_curr_size = 0;
    }

    // dodanie elementu do kopca - dodanie go na koniec tablicy, a nastepnie przesianie go w gore
    public void insert(Node new_node) {
        if( heap_curr_size < heap_max_size ) {
            heap_data[heap_curr_size] = new_node;

            heapifyUp(heap_curr_size);

            heap_curr_size++;
        }
    }

    // algorytm przesiewania w gore, porownuje dziecko z rodzicem i zamienia je miejscami, jezeli rodzin jest wiekszy od dziecka
    public void heapifyUp(int i) {
        int parent = (i - 1) / 2; // indeks rodzica

        if( heap_data[parent].value > heap_data[i].value ) {
            Node tmp = heap_data[parent];
            heap_data[parent] = heap_data[i];
            heap_data[i] = tmp;

            heapifyUp(parent);
        }
    }

    // sprawdzenie, czy heap jest pelny
    public boolean isFull() {
        return (heap_curr_size == heap_max_size) ? true : false;
    }

    // sprawdzenie czy heap jest pusty
    public boolean isEmpty() {
        return (heap_curr_size == 0) ? true : false;
    }

    // usuniecie minimalnego elementu (ktory zawsze znajduje sie w korzeniu), nastepnie przesianie w dol indeksu 0, by wrocic do warunku kopca
    public int deleteMin() {
        if( heap_data[0] != null ) {
            int min = heap_data[0].value;
            int index_from = heap_data[0].seq_num;
            heap_data[0] = heap_data[heap_curr_size-1];

            heapifyDown(0);
            --heap_curr_size;

            System.out.print(min + " ");
            return index_from;
        }
        return -1;
    }

    // algorytm przesiewania w dol, porownuje lewe oraz prawe dziecko, znajduje najwiekszy element, oraz zamienia go z rodzicem
    private static void heapifyDown(int i) {
        // i - indeks wezla, ktory nalezy dolaczyc
        int smaller = i;
        int l = 2*i + 1; // indeks jego lewego dziecka
        int r = 2*i + 2; // indeks jego prawego dziecka

        if (l < heap_curr_size && heap_data[l].value < heap_data[smaller].value) {
            smaller = l; 
        }

        if (r < heap_curr_size && heap_data[r].value < heap_data[smaller].value) {
            smaller = r; 
        }

        if (smaller != i) { 
            Node tmp = heap_data[i];
            heap_data[i] = heap_data[smaller];
            heap_data[smaller] = tmp;

            heapifyDown(smaller);
        } 
    }
}

class Source {
    public static Scanner sc = new Scanner(System.in);
    public static int last_deleted = -1; // indeks ciagu, z ktorego zostal usuniety ostatni element
    public static void main(String[] args) {

        int sessions = sc.nextInt(); // ilosc sesji
        for(int s=0; s<sessions; ++s) {
            int size = sc.nextInt();
            int[] sizes = new int[size];
            for(int j = 0; j<size; ++j) {
                sizes[j] = sc.nextInt(); // tablica na rozmiary
            }

            int max_size = find_max(sizes);
            Node[][] data = new Node[size][max_size+1]; // tablica 2d

            // uzupelnienie tablicy rozmiarami ciagow oraz wartosciami
            for(int i=0; i<size; ++i) {
                data[i][0] = new Node(sizes[i]); // dodanie do pierwszej kolumny dlugosci ciagu
                for(int j=1; j<=data[i][0].value; ++j) {
                    int value = sc.nextInt();
                    Node new_node = new Node(value, i, j);
                    data[i][j] = new_node; // dodanie nowego elementu
                }
            }

            Heap heap = new Heap(size); // heap glowny
            while( true ) { // algorytm ma sie wykonywa do czasu, az wezmiemy wszystkie elementy z tablicy 2d
                boolean isElement = false;
                for(int i=0; i<size; ++i) {
                    if( data[i][0].value > 0 ) {
                        isElement = true;
                        if(!heap.isFull()) {
                            heap.insert(data[i][1]);

                            // shift - przesuniecie elementow o 1 miejsce, by wziac nastepnym razem kolejny element
                            for(int j = 1; j < data[i][0].value; j++) {
                                if( j == max_size ) {
                                    data[i][j] = null;
                                }
                                else {
                                    data[i][j] = data[i][j+1];
                                }
                            }
                            // redukcja o 1, gdyz wzielismy z tego ciagu element
                            data[i][0].value -= 1;
                        }
                    }
                }
                int tmp_sum = 0; // not neccessary 
                for(int j=0; j<size; j++) {
                    tmp_sum += data[j][0].value;
                }

                if( !isElement || tmp_sum == 0 ) {
                    // clear all the heap
                    while( !heap.isEmpty() ) {
                        heap.deleteMin(); // jezeli nie ma juz wiecej elementow do wziecia, czyscimy kopiec i zdejmujemy z niego kolejno minimalny element
                    }
                    break; // zakonczenie petli, gdyz wzielismy juz wszystkie elementy z tablicy 2d
                }
                
                last_deleted = heap.deleteMin(); // usuniecie z kopca minimalnego elementy, przesianie, oraz dodanie kolejnego elementu z ciagu, ktorego indeks zapisany jest w zmiennej last_deleted

                // dodanie elementu z ciagu, z ktorego zostal usuniety ostatni element
                if( data[last_deleted][0].value > 0 ) {
                    heap.insert(data[last_deleted][1]); // dodanie do kopca
                    isElement = true;
                    // shift - przesuniecie elementow o 1 miejsce, by wziac nastepnym razem kolejny element
                    for(int j = 1; j < data[last_deleted][0].value; j++) {
                        if( j == max_size ) {
                            data[last_deleted][j] = null;
                        }
                        else {
                            data[last_deleted][j] = data[last_deleted][j+1];
                        }
                    }
                    data[last_deleted][0].value -= 1;
                }
                // jezeli ten ciag jest juz pusty, to idziemy dalej od poczatku
                else {
                    int min_index = heap.heap_data[0].seq_num;
                    if( data[min_index][0].value > 0 ) {
                        heap.insert(data[min_index][1]);
                        isElement = true;
                        // shift - przesuniecie elementow o 1 miejsce, by wziac nastepnym razem kolejny element
                        for(int j = 1; j < data[min_index][0].value; j++) {
                            if( j == max_size ) {
                                data[min_index][j] = null;
                            }
                            else {
                                data[min_index][j] = data[min_index][j+1]; 
                            }
                        }
                        data[min_index][0].value -= 1;
                    }
                }
            }
            System.out.println();   
        }
    }

    // funkcja znajdujaca maksymalny element w tablicy, przydaje sie do wyznaczenia maksymalnego rozmiaru 
    public static int find_max(int[] arr) {
        int max = arr[0];
        for(int i=1; i<arr.length; ++i) {
            if( arr[i] > max ) {
                max = arr[i];
            }
        }
        return max;
    }
}

/*
2ejscie:
1
4
1 7 3 10
0      
1 3 5 7 9 11 13
2 4 6
1 2 3 4 5 6 7 8 9 10
wyjscie: 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 9 9 10 11 13

wejscie:
1
5
2 5 4 7 8
1 3 
5 7 9 11 13
3 4 6 8
5 7 8 6 4 3 7
4 4 6 7 5 7 8 9
wyjscie: 1 3 3 3 4 4 4 4 5 5 6 6 5 7 7 7 7 8 6 7 8 8 9 9 11 13

wejscie:
1
5
1 1 1 1 1
5
6
7
5
2
wyjscie: 2 5 5 6 7

wejscie: 
1 
5
2 3 5 1 2
5 3
6 8 4
1 1 3 5 6
3
6 3
wyjscie: 1 1 3 3 3 3 4 5 5 6 6 6 8

wejscie:
1
5
3 1 1 1 2 
4 7 4
4
7 
1
5 3
wyjscie: 1 3 4 4 4 5 7 7
*/

