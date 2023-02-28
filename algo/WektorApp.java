// Tablica jednowymiarowa
//==============================================
import java.util.Random;
import java.util.Scanner;

class Wektor{
    public int[] vector; // referencja do wektora
    public int maxSize; // maksymalna długość wektora
    public int currentSize; // aktualna długość wektora
    //============================================= konstruktor
    public Wektor (int maxSize_) {
        maxSize = maxSize_;
        currentSize = 0;
        vector = new int[maxSize];
    }
    //==================================== ustawienie wektora
    public void setVector(int[] vector) {
        if(maxSize < vector.length){
            this.vector = vector;
            // Nadpisuje wcześniejsze ustawienie maxSize.
            maxSize = vector.length;
        }
        else {
            System.arraycopy(vector, 0, this.vector, 0, vector.length);
        }
        // Nadpisuje wcześniejsze ustawienie currentSize dla obu powyższych przypadków.
        currentSize = vector.length;
    }
    //==================================== wczytywanie wektora
    public void readVector(int nrOfIntegers, Scanner sc) {
        for (int i = 0; i < nrOfIntegers; ) {
            if (sc.hasNextInt()) {
                vector[i] = sc.nextInt();
                ++i;
            } else {
                // Zbiera to, co zostało wpisane i nie było typu int.
                sc.nextLine();
            }
        }
    }
    //===================================== losowanie wektora
    /** Losowanie nrOfIntegers liczb z przedziału [min,max] do wektora vector.*/
    public void randVector(int nrOfIntegers, int min, int max) {
        System.out.println ("Losowanie "+nrOfIntegers+" liczb z przedzialu [" +min+","+max+"]");
        for( int i=0; i<nrOfIntegers; i++)
            // max+1, żeby przedział był domknięty z góry.
            vector[i] = new Random().nextInt(max+1-min)+min;
    }
    //===================================== wyświetlanie wektora
    public void display(){
        System.out.println("Liczba elementow = " + currentSize);
        System.out.println("Zawartosc wektora ");
        for (int i = 0; i<currentSize; i++) {
            System.out.print(vector[i]+", ");
            if((i+1)%10==0) System.out.println();
        }
        System.out.println();
    }
    public void display(int begin, int end){
        int size = end - begin + 1;
        if(size>currentSize) {
            System.out.println("Liczba elementów nie może być większa niż " + currentSize);
            return;
        }
        System.out.println("Liczba elementow = " + size);
        System.out.print("Indeks początku=" + begin + " Indeks końca=" + end +". ");
        System.out.println("Zawartość podwektora ");
        // i<=end mniejsze lub równe, ponieważ end jest indeksem.
        for (int i = begin; i<=end; i++) {
            System.out.print(vector[i]+", ");
            if((i-begin+1)%10==0) System.out.println();
        }
        System.out.println();
    }

    // ============================= A - Adding elements at the end
    public void addNotFull(int element) {
        // adding element at the end
        if( currentSize >= maxSize ) {
            // vector is full
            System.out.println("Vector is full. You need to delete something.");
        }
        else {
            // vector is not full
            vector[currentSize++] = element;
        }
    }

    public void addEvenWhenFull(int element, Scanner sc) {
        if( currentSize >= maxSize ) {
            // vector is full
            System.out.print("How many times would you like to extend the vector [%]:");
            int percentage = sc.nextInt();
            addAndExtend(element, percentage);
        }
        else {
            // vector is not full
            vector[currentSize++] = element;
        }
    }

    // adding element at the end and extending the vector
    public void addAndExtend(int element, int percentage) {
        int extendBy = currentSize * percentage / 100; 
        maxSize += (extendBy==0 ? 1:extendBy);

        int[] new_vector = new int[maxSize];
        System.arraycopy(vector, 0, new_vector, 0, vector.length);
        vector = new_vector;
        vector[currentSize++] = element;
    }

    // ============================= B - Deleting element
    // delete first appearance of the element
    public void deleteFirst(int element) {
        for(int i=0; i<currentSize; i++) {
            if( vector[i] == element ) {
                System.arraycopy(vector, i+1, vector, i, --currentSize - i);
                break;
            }
        }
    }

    // delete every appearance of the element
    public void deleteAll(int element) {
        for(int i=0; i<currentSize; i++) {
            if( vector[i] == element ) {
                System.arraycopy(vector, i+1, vector, i, --currentSize - i);
                --i;
            }
        }
    }

    public boolean ascendingOrder() {
        for(int i=0; i<currentSize-1; i++) {
            if( vector[i] > vector[i++] ) {
                return false;
            }
        }
        return true;
    }

    public boolean descendingOrder() {
        for(int i=0; i<currentSize-1; i++) {
            if( vector[i] < vector[i++] ) {
                return false;
            }
        }
        return true;
    }

    // ============================= C - checking the tendency
    public String checkOrder() {
        if( currentSize > 1 ) {
            if( ascendingOrder() && descendingOrder() ) {
                return "Constant";
            }
            if( ascendingOrder() ) {
                return "Ascending";
            }
            if( descendingOrder() ) {
                return "Descending";
            }
        }
        
        return "Unable to see a tendency when there is only one object.";
    }

    // ============================= D - min max
    public int findMax() {
        int max = vector[0];
        for(int i=1; i<currentSize; i++) {
            if( vector[i] > max ) {
                max = vector[i];
            }
        }
        return max;
    }
    public int findMin() {
        int min = vector[0];
        for(int i=1; i<currentSize; i++) {
            if( vector[i] < min ) {
                min = vector[i];
            }
        }
        return min;
    }

    // ============================= E - Horner
    public int hornersMethod(int n) {
        int result = 0;
        int i = 0;


        while( i < currentSize ) {
            result *= n;
            result += vector[i];
            i++;
        }

        return result;
    }

    // ============================= E - delete duplicates
    public void deleteDuplicates() {
        int[] new_vector = new int[maxSize];

        int newCurrentSize = 0;
        for(int i=0, y = vector[i]; i<currentSize; i++) {
            if( !isInside(y, new_vector) ) {
                new_vector[newCurrentSize] = y;
                newCurrentSize++;
            }
        }
        currentSize = newCurrentSize;
        vector = new_vector;
    }
    
    public boolean isInside(int x, int[] tmp_vector) {
        for(int i=0; i<currentSize; i++) {
            if( tmp_vector[i] == x ) return true;
        }
        return false;
    }

    // ============================= G - longest non-decreasing subarray
    public void nonDecSubArray() {
        int[] tmp_vector = new int[maxSize];
        int size = 0;
        int subMaxSize = -1;
        
        int e;
        for(int s=0; s<currentSize-1; s++, size=0) {

            e = s;
            while(e < currentSize-1) {
                if( vector[e] <= vector[e+1] ) {
                    e++;
                }
                else break;
            }
            size = e - s + 1;

            if( size > subMaxSize ) {
                subMaxSize = size;
                System.arraycopy(vector, s, tmp_vector, 0, size);
            }
        }
        if( subMaxSize <= 1 ) System.out.println("The sequence is stictly descending or to little points are given");
        else {
            vector = tmp_vector;
            currentSize = subMaxSize;
        }
    }
}   


//End of class wektor
////////////////////////////////////////////////////////////    
/**
 *     Klasa wektorApp, zawierająca metodę main(), pozwalającą na wybór i ilustrację
 *     działania podanych operacji na tablicy.
 */
class WektorApp {
    /** Zapobiega błędom gdy nie int.*/
    public static int getInt(Scanner sc) {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        }
        // Zbiera to, co wpisane i nie typu int.
        sc.nextLine();
        return -1;
    }
    /** Ustawia i wyświetla elementy wektora (ręcznie, losowo, predefiniowany) */
    public static void setVectorElements(Scanner sc, Wektor wektor, int[] vec) {
        System.out.print("Podaj aktualną dlugosc wektora, "+" <= "+wektor.maxSize +": ");
        int currentSizeTmp = getInt(sc);
        while(currentSizeTmp<=0 || currentSizeTmp>wektor.maxSize) {
            System.out.println("Niepoprawna długość wektora");
            System.out.print("Podaj nowa dlugosc wektora: ");
            currentSizeTmp = getInt(sc);
        }
        wektor.currentSize = currentSizeTmp;
        System.out.println("Wybierz: 1-czytanie, 2-losowanie, 3-predefiniowany, inne - koniec");
        int choice = getInt(sc);
        switch(choice){
            case 1:
                System.out.println("Czytanie "+currentSizeTmp+" liczb integer");
                wektor.readVector(currentSizeTmp, sc );
                break;
            case 2:
                System.out.println("Losowanie "+currentSizeTmp+" liczb integer");
                System.out.print("Podaj minimum: ");
                int min = sc.nextInt();
                System.out.print("Podaj maksimum: ");
                int max = sc.nextInt();
                if(max<min) max=min;
                wektor.randVector(currentSizeTmp,min,max);
                break;
            case 3:
                wektor.setVector(vec);
                break;
            default:
                return;
        }
        wektor.display();
    }
    /** setVectorElements(Scanner sc, Wektor wektor, int[] vec = new int[]{2,-6,2,-1}) */
    public static void setVectorElements(Scanner sc, Wektor wektor) {
        setVectorElements(sc, wektor, new int[]{2,-6,2,-1});
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Wektor wektor = new Wektor(100);
        WektorApp.setVectorElements(sc, wektor, new int[]{1,4,-1,4,1,1,5,7,5,2});
        wektor.nonDecSubArray();

        wektor.display();
    }
}//End of class wektorApp
