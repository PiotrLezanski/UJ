// Piotr Lezanski - 4

import java.util.Scanner;

// glowna idea - program obsluguje operacje na wagonach i pociagach

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.PrintStream;

// lista pociagow
class TrainList {
    // klasa pociagu
    class Train {
        String T_id;
        public CarriageList CList;
        Train next;
        
        Train() {
            this.next = null;
        }
        Train(String ID) {
            this.T_id = ID;
            this.next = null;
            CList = new CarriageList();
        }

        public void displayCarriages() {
            System.out.print(T_id + ": ");
            CList.displayCarriages();
        }
    }
    Train trains; // wskaznik na pierwszy pociag listy

    TrainList() {
        trains = null;
    }

    // dodawanie pociagu
    public void addTrain(String train_id) {
        Train new_train = new Train(train_id);
        if( trains != null ) {
            new_train.next = trains;
        }
        trains = new_train;
    }

    // lokalizowanie pociagu w liscie i zwracanie referencji do znalezionego pociagu
    public Train findTrain(String train_id) {
        if( trains != null ) {
            Train tmp = trains;
            int a = 0;
            while (tmp != null && (tmp != trains || (tmp == trains && a == 0))) {
                a = 1;
                if (tmp.T_id.equals(train_id)) {
                    return tmp;
                }
                tmp = tmp.next;
            }
        }
        return null;
    }

    // usuwanie pociagu z listy
    public void deleteTrain(String train_name) {
        if( trains.T_id.equals(train_name) ) {
            trains = trains.next;
        }
        else {
            Train tmp = trains;
            while( !tmp.next.T_id.equals(train_name) ) {
                tmp = tmp.next;
            }
            tmp.next = tmp.next.next;
        }
    }

    // wyswietlanie listy pociagow
    public void displayTrains() {
        Train tmp = trains;
        int first = 1;
        System.out.print("Trains: ");
        while( tmp != null ) {
            if( first == 1 ) {
                first = 0;
                System.out.print(tmp.T_id);
            }
            else System.out.print(" " + tmp.T_id);
            
            tmp = tmp.next;
        }
        System.out.println();
    }
}

// lista wagonikow
class CarriageList {
    class Carriage {
        String W_id;
        Carriage prev;
        Carriage next;
        
        Carriage() {
            this.prev = null;
            this.next = null;
        }
        Carriage(String ID) {
            W_id = ID;
            prev = null;
            next = null;
        }
    }
    
    Carriage first; // referencja na pierwszy wagonik

    CarriageList() {
        first = null;
    }

    // dodawanie wagonika na poczatek listy
    public void addCarriage(String carriage_name) {
        Carriage new_carriage = new Carriage(carriage_name);
        if( first != null ) {
            new_carriage.prev = first.prev;
            first.prev = new_carriage;
            new_carriage.next = first;
            first = new_carriage;
            first.prev.next = first;
        }
        else { // first == null
            new_carriage.next = new_carriage;
            new_carriage.prev = new_carriage;
            first = new_carriage;
        }
    }

    // dodawanie wagonika o danym ID na koniec listy
    public void addCarriageLast(String carriage_name) {
        Carriage new_carriage = new Carriage(carriage_name);
        if( first != null ) {
            first.prev.next = new_carriage;
            new_carriage.prev = first.prev;
            first.prev = new_carriage;
            first.prev.next = first;
        }
        else { // first == null
            new_carriage.next = new_carriage;
            new_carriage.prev = new_carriage;
            first = new_carriage;
        }
    }

    // dodanie konkretnego wagonika na koniec listy wagonow
    public void addCarriageLast(TrainList.Train new_train) {
        // pociag do ktorego dodajemy nie jest pusty
        Carriage tmp = first.prev;
        first.prev.next = new_train.CList.first;
        new_train.CList.first.prev.next = first;
        first.prev = new_train.CList.first.prev;
        new_train.CList.first.prev = tmp;
    }

    // usuwanie pierwszego wagonika
    public void delFirstCarriage() {
        if( first != null ) {
            if( first.next != first ) {
                first.next.prev = first.prev;
                first.prev.next = first.next;
                first = first.next;
            }
            else { // first.next == first
                first = null;
            }
        }
    }

    // usuwanie ostatniego wagonika
    public void delLastCarriage() {
        // tylko wtedy, gdy istnieje jakikolwiek wagonik
        if( first != null ) {
            if( first != first.prev ) {
                first.prev = first.prev.prev;
                first.prev.next = first;
            }
            else { 
                first = null;
            }
        }
    }

    // wysietlanie wagonikow z uwzglednieniem "swapa", ktory zamienia referencje prev i next, jezeli spelniony jest warunek
    // dzieki temu przechodzenie po liscie odbywa sie do referencjach "next"
    public void displayCarriages() {
        Carriage tmp = first;
        int a = 0;
        int e = 1;
        // petla przechodzaca po wszystkich elementach
        while( tmp != null && (tmp != first || (tmp == first && a == 0)) ) {
            if(!(tmp.next.prev == tmp)) {
                // swap - zamiana prev z nextem
                Carriage tmp2 = tmp.next.next;
                tmp.next.next = tmp.next.prev;
                tmp.next.prev = tmp2;
            }
            // wypisanie spacji przed kazdym elementem oprocz pierwszego
            if( e == 1 ) {
                e = 0;
                System.out.print(tmp.W_id);
            }
            else System.out.print(" " + tmp.W_id);
            
            a = 1;
            tmp = tmp.next;
        }
        System.out.println();
    }

    // obracanie listy wagonow
    // zamiana referencjji pierwszego wagonika z pierszym oraz swap ich referencji prev i next
    public void reverse() { 
        Carriage tmp = first.next;
        first.next = first.prev;
        first.prev = tmp;
        Carriage tmp2 = first.next.next;
        first.next.next = first.next.prev;
        first.next.prev = tmp2;
        first = first.next; 
    }
}

// klasa glowna
public class Source {
    public static Scanner sc = new Scanner(System.in); // obiekt scannerx
    public static void main(String[] args) {
        int e;
        e = sc.nextInt();
        String operation;
        String train_name;
        String train_name2;
        String carriage_name;

        // try {
        //     File file = new File("out.txt");
        //     FileOutputStream fos = new FileOutputStream(file);
        //     PrintStream ps = new PrintStream(fos);
        //     System.setOut(ps);
        // }
        // catch(FileNotFoundException ex) {}

        for(int i=0; i<e; i++) {
            int p;
            // stworzenie nowych obiektow potrzebnych do obslugi operacji
            TrainList trains = new TrainList();
            TrainList.Train tmp = null; 
            TrainList.Train tmp2 = null; 
            p = sc.nextInt();
            for(int j=0; j<p; j++) {
                operation = sc.next();
                switch (operation) {
                    case "New":
                        // tworzenie nowego pociagu, wypisanie bledu jezeli taki juz istnieje
                        train_name = sc.next(); 
                        carriage_name = sc.next();
                        tmp = trains.findTrain(train_name);
                        if( tmp == null ) {
                            trains.addTrain(train_name);
                            trains.trains.CList.addCarriage(carriage_name);
                        }
                        else {
                            System.out.println("Train " + train_name + " already exists");
                        }
                        break;

                    case "InsertFirst":
                        // dodanie wagonika na poczatek listy wagonow, wypisanie bledu jezeli pociag nie istnieje
                        train_name = sc.next();
                        carriage_name = sc.next();
                        tmp = trains.findTrain(train_name);
                        if( tmp != null ) {
                            tmp.CList.addCarriage(carriage_name);
                        }
                        else {
                            System.out.println("Train " + train_name + " does not exist");
                        }
                        break;

                    case "InsertLast":
                        // dodanie wagonika na koniec listy wagonow, wypisanie bledu jezeli pociag nie istniej
                        train_name = sc.next();
                        carriage_name = sc.next();
                        tmp = trains.findTrain(train_name);
                        if( tmp != null ) {
                            tmp.CList.addCarriageLast(carriage_name);
                        }
                        else {
                            System.out.println("Train " + train_name + " does not exist");
                        }
                        break;

                    case "Display":
                        // wyswietlenie listy wagonow okreslonego pociagu, wypisanie bledu, jezeli pociag nie istnieje
                        train_name = sc.next();
                        tmp = trains.findTrain(train_name);
                        if( tmp != null ) {
                            tmp.displayCarriages();
                        }
                        else {
                            System.out.println("Train " + train_name + " does not exist");
                        }
                        break;

                    case "Trains":
                        // wyswietlenie listy pociagow
                        trains.displayTrains();
                        break;

                    case "Reverse":
                        // obrocenie listy wagonow, wypisanie bledu jezeli pociag nie istnieje
                        train_name = sc.next();
                        tmp = trains.findTrain(train_name);
                        
                        if( tmp != null ) {
                            tmp.CList.reverse();
                        }
                        else {
                            System.out.println("Train " + train_name + " does not exist");
                        }
                        break;

                    case "Union":
                        // polaczenie wagonow pociagu danego pierwszym argumentem z pociagiem danym drugim argumentem, wypisanie bledu jezeli pociagi nie istnieja
                        train_name = sc.next();
                        train_name2 = sc.next();
                        tmp = trains.findTrain(train_name);
                        tmp2 = trains.findTrain(train_name2);
                        if( tmp != null ) {
                            if( tmp2 != null ) {
                                // MAIN
                                tmp.CList.addCarriageLast(tmp2);
                                trains.deleteTrain(train_name2);
                            }
                            else {
                                System.out.println("Train " + train_name2 + " does not exist");    
                            }
                        }
                        else {
                            System.out.println("Train " + train_name + " does not exist");
                        }
                        break;

                    case "DelFirst":
                        // usuniecie pierrwszego wagonika w podanym pociagu, wypisanie bledu jezeli pociag nie istnieje, usuniecie pociagu jezeli jest pusty
                        train_name = sc.next();
                        train_name2 = sc.next();
                        tmp = trains.findTrain(train_name);
                        tmp2 = trains.findTrain(train_name2);
                        
                        if( tmp2 == null ) {
                            if( tmp != null ) {
                                // MAIN
                                String tmp_carr = tmp.CList.first.W_id;
                                tmp.CList.delFirstCarriage();
                                if( tmp.CList.first == null ) trains.deleteTrain(tmp.T_id);
                                trains.addTrain(train_name2);
                                tmp2 = trains.findTrain(train_name2);
                                tmp2.CList.addCarriage(tmp_carr);
                            }
                            else {
                                System.out.println("Train " + train_name + " does not exist");
                            }
                        }
                        else {
                            System.out.println("Train " + train_name2 + " already exists");
                        }
                        break;

                    case "DelLast":
                        // usuniecie ostatniego wagonika w podanym pociagu, wypisanie bledu jezeli pociag nie istnieje, usuniecie pociagu jezeli jest pusty
                        train_name = sc.next();
                        train_name2 = sc.next();
                        tmp = trains.findTrain(train_name);
                        tmp2 = trains.findTrain(train_name2);
                        
                        if( tmp2 == null ) {
                            if( tmp != null ) {
                                // MAIN
                                String tmp_carr = tmp.CList.first.prev.W_id;
                                tmp.CList.delLastCarriage();
                                if( tmp.CList.first == null ) trains.deleteTrain(tmp.T_id);
                                trains.addTrain(train_name2);
                                tmp2 = trains.findTrain(train_name2);
                                tmp2.CList.addCarriage(tmp_carr);
                            }
                            else {
                                System.out.println("Train " + train_name + " does not exist");
                            }
                        }
                        else {
                            System.out.println("Train " + train_name2 + " already exists");
                        }
                        break;
                }
            }
        }
    }
} // koniec klasy Source


/*
IN:
2
32
New A W1
InsertFirst A W0
InsertLast A W2
InsertLast A W3
New B W7
InsertFirst B W6
InsertFirst B W5
InsertFirst B W4
Trains
Display A
Display B
Union A B
Trains
Display A
DelLast A B
DelFirst A C
DelLast A D
DelFirst A E
DelLast A F
DelFirst A G
DelLast A H
DelFirst A I
Trains
Display A
Display B
Display C
Display D
Display E
Display F
Display G
Display H
Display I
34
New A W1
InsertFirst A W0
InsertLast A W2
InsertLast A W3
New B W7
InsertFirst B W6
InsertFirst B W5
InsertFirst B W4
Trains
Display A
Display B
Union A B
Trains
Display A
Reverse A
Display A
DelLast A B
DelFirst A C
DelLast A D
DelFirst A E
DelLast A F
DelFirst A G
DelLast A H
DelFirst A I
Trains
Display A
Display B
Display C
Display D
Display E
Display F
Display G
Display H
Display I

OUT:
Trains: B A
A: W0 W1 W2 W3
B: W4 W5 W6 W7
Trains: A
A: W0 W1 W2 W3 W4 W5 W6 W7
Trains: I H G F E D C B
Train A does not exist
B: W7
C: W0
D: W6
E: W1
F: W5
G: W2
H: W4
I: W3
Trains: B A
A: W0 W1 W2 W3
B: W4 W5 W6 W7
Trains: A
A: W0 W1 W2 W3 W4 W5 W6 W7
A: W7 W6 W5 W4 W3 W2 W1 W0
Trains: I H G F E D C B
Train A does not exist
B: W0
C: W7
D: W1
E: W6
F: W2
G: W5
H: W3
I: W4
*/

/*
IN:
1
43
New A A2
New B A5
New C A8
New D A10
New E A11
New F A14
New G A16
InsertLast A A3
InsertFirst A A1
InsertLast B A6
InsertFirst B A4
InsertLast C A7
InsertFirst C A9
InsertLast E A12
InsertLast E A13
InsertLast D A12
InsertLast D A11
InsertLast G A15
InsertFirst G A17
Union E F
Reverse G
Union E G
Display E
Union A B
Display A
Reverse C
Union A C
Display A
Trains
Display D
DelLast D H
Union H D
DelLast H D
Union D H
Display D
Reverse D
Union A D
Display A
DelFirst E B
DelFirst E C
Union A E
Display A
Trains

OUT:
E: A11 A12 A13 A14 A15 A16 A17
A: A1 A2 A3 A4 A5 A6
A: A1 A2 A3 A4 A5 A6 A7 A8 A9
Trains: E D A
D: A10 A12 A11
D: A12 A11 A10
A: A1 A2 A3 A4 A5 A6 A7 A8 A9 A10 A11 A12
A: A1 A2 A3 A4 A5 A6 A7 A8 A9 A10 A11 A12 A13 A14 A15 A16 A17
Trains: C B A
*/

/*
IN:
1 21
New T1 W1
New T2 W4
InsertLast T1 W2
InsertLast T1 W3
InsertFirst T2 W5
Reverse T1
Union T2 T1
Display T1
Display T2
DelLast T2 T1
Reverse T1
Reverse T2
DelLast T2 T5
DelLast T2 T4
DelLast T2 T3
Trains
Display T1
Display T2
Display T3
Display T4
Display T5

OUT:
Train T1 does not exist
T2: W5 W4 W3 W2 W1
Trains: T3 T4 T5 T1 T2
T1: W1
T2: W2
T3: W3
T4: W4
T5: W5
*/

/*
IN:
1
18
New T1 x
InsertFirst T1 y 
Display T1
Reverse T1
Display T1
InsertFirst T1 a
Display T1
InsertLast T1 o
Display T1
DelFirst T1 T2
Trains
Display T1
Display T2
Trains
New T3 l
Trains
DelFirst T2 T4
Trains

OUT:
T1: y x
T1: x y
T1: a x y
T1: a x y o
Trains: T2 T1
T1: x y o
T2: a
Trains: T2 T1
Trains: T3 T2 T1
Trains: T4 T3 T1
*/