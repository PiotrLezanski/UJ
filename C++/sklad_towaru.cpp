//Piotr Lezanski
#include <iostream>

const unsigned char MAX_SIZE = 128;
const unsigned short MAX_PLACES = 65535;

long long sum = 0;

struct Place {
    unsigned short quantity;
    char label[2];

    void put(unsigned short);
    void pop(int);
    void mov(Place&, int);
    void PrintLabel();
};

void Place::put(unsigned short a) {
    if( a + quantity > ::MAX_PLACES ) {
        a = ::MAX_PLACES;
        ::sum += (a - quantity);
        quantity = a;
    }
    else {
        ::sum += a;
        quantity += a;
    }
}

void Place::pop(int a) {
    if( a > quantity ) {
        ::sum -= quantity;
        quantity = 0;
    }
    else {
        ::sum -= a;
        quantity -= a;
    }
}

void Place::mov(Place& moveto, int a) {
    if( a > quantity ) {
        a = quantity;
    }
    if( a + moveto.quantity > ::MAX_PLACES ) {
        a = ::MAX_PLACES - moveto.quantity;
    }
    
    quantity -= a;
    moveto.quantity += a;
}

void Place::PrintLabel() {
    if(label[0] == 0 && label[1] == 0 ) {    
        std::cout<<"--"<<std::endl;
    }
    else {
        std::cout<< label[0] << label[1] << std::endl;
    }   
}

struct Shelf {
    Place places[128];

    unsigned short Places_num;
    long get();
    void set(int);
};

long Shelf::get() {
    unsigned long long sum = 0;
    for(int i=0; i<Places_num; i++) {
        sum += places[i].quantity;
    }
    return sum;
}

void Shelf::set(int p) {
    for(int j=Places_num; j<p; j++) {
        places[j].quantity = 0;
    }

    for(int i=p; i<Places_num; i++) {
        ::sum -= places[i].quantity;
        places[i].quantity = 0;
    }   
    
    Places_num = p;
}

struct Rack {
    Shelf shelfs[128];

    unsigned short Shelfs_num;
    long get();
    void set(int, int);
    void pop(int, int, int);
};

long Rack::get() {
    unsigned long long sum = 0;
    for(int i=0; i<Shelfs_num; i++) {
        sum += shelfs[i].get();
    }
    return sum;
}

void Rack::set(int s, int p) {
    for(int i=0; i<s; i++) {
        shelfs[i].set(p);
    }

    for(int i=s; i<Shelfs_num; i++) {
        shelfs[i].set(0);
    }

    Shelfs_num = s;
}

struct Warehouse {
    Rack racks[128];
    Shelf HandyShelf;

    unsigned short Racks_num;
    unsigned long long get();
    void set(int, int, int);
};

unsigned long long Warehouse::get() {
    unsigned long long sum = 0;
    for(int i=0; i<Racks_num; i++) {
        sum += racks[i].get();
    }
    sum += HandyShelf.get();
    return sum;
}

void Warehouse::set(int r, int s, int p) {
    for(int j=0; j<r; j++) {
        racks[j].set(s, p);
    }
    for(int i=r; i<Racks_num; i++) {
        racks[i].set(0, 0);
    }

    Racks_num = r;
}

struct Storehouse {
    Warehouse warehouses[128];

    Rack HandyRack;
    Shelf HandyShelf;
    unsigned short Warehouses_num;

    unsigned long long get();
    void fill(int, int, int, int, int);
    void set(int, int, int, int);
};

unsigned long long Storehouse::get() {
    return ::sum;
}

void Storehouse::set(int w, int r, int s, int p) {
    for(int i=0; i<w; i++) {
        warehouses[i].set(r, s, p);
    }
    for(int j=w; j<Warehouses_num; j++) {
        warehouses[j].set(0, 0, 0);
    }

    Warehouses_num = w;
}

void Storehouse::fill(int w, int r, int s, int p, int a) {
    if( a > ::MAX_PLACES ) a = ::MAX_PLACES;

    Warehouses_num = w;
    for(int i=0; i<w; i++) {
        warehouses[i].Racks_num = r;
        
        for(int j=0; j<r; j++) {
            warehouses[i].racks[j].Shelfs_num = s;

            for(int k=0; k<s; k++) {
                warehouses[i].racks[j].shelfs[k].Places_num = p; 

                for(int l=0; l<p; l++) {
                    warehouses[i].racks[j].shelfs[k].places[l].quantity = a;
                }
            }
        }
        
        warehouses[i].HandyShelf.Places_num = p;
        for(int l=0; l<p; ++l) {
            warehouses[i].HandyShelf.places[l].quantity = a;
        }
        
    }

    HandyRack.Shelfs_num = s;
    for(int k=0; k<s; k++) {
        HandyRack.shelfs[k].Places_num = p;
        for(int l=0; l<p; l++) {
            HandyRack.shelfs[k].places[l].quantity = a;
        }
    }

    HandyShelf.Places_num = p;
    for(int l=0; l<p; l++) {
        HandyShelf.places[l].quantity = a;
    }

    ::sum += (unsigned long long)w * r * s * p * a + (unsigned long long)w * p * a + (unsigned long long)s * p * a + (unsigned long long)p * a;
}

Storehouse storehouse;

int main()
{
    // SET
    int w; // nr magazynu
    int r; // nr regału
    int s; // nr połki
    int p; // ilość miejsc na wartość/towary

    // PUT
    int a; // ilość towaru do dodania 

    // MOV
    int w2;
    int r2;
    int s2;

    // LABELS
    char dd[3];

    std::string command;

    do
    {
        std::cin>> command;
    // SET 
        if( command == "SET-AP")
        {
            std::cin>> w >> r >> s >> p;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || w>=storehouse.Warehouses_num || r>=storehouse.warehouses[w].Racks_num || s>=storehouse.warehouses[w].racks[r].Shelfs_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].set(p);
            }    
        }
        else if( command == "SET-AS")
        {
            std::cin>> w >> r >> s >> p;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || w>=storehouse.Warehouses_num || r>=storehouse.warehouses[w].Racks_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].set(s, p);
            }  
        }
        else if( command == "SET-AR")
        {
            std::cin>> w >> r >> s >> p;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || w>= storehouse.Warehouses_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].set(r, s, p);
            }  
        }
        else if( command == "SET-AW")
        {
            std::cin>> w >> r >> s >> p;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.set(w, r, s, p);
            }  
        }
        else if( command == "SET-HW")
        {
            std::cin>> w >> p;
            if( w > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || p<0 || w>=storehouse.Warehouses_num ) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].HandyShelf.set(p);
            }  
        }
        else if( command == "SET-HR")
        {
            std::cin>> s >> p;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || s<0 || p<0){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.set(s, p);
            }  
        }
        else if( command == "SET-HS")
        {
            std::cin>> p;
            if( p > ::MAX_SIZE || p<0){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyShelf.set(p);
            }  
        }
    // PUT
        else if( command == "PUT-W")
        {
            std::cin>> w >> r >> s >> p >> a;

            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].put(a);
            }  
        }
        else if( command == "PUT-H")
        {
            std::cin>> w >> p >> a;
            if( w > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || w >= storehouse.Warehouses_num || p >= storehouse.warehouses[w].HandyShelf.Places_num) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].HandyShelf.places[p].put(a);
            }  
        }
        else if( command == "PUT-R")
        {
            std::cin>>s >> p >> a;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || s<0 || r<0 || p<0 || s >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyRack.shelfs[s].Places_num) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.shelfs[s].places[p].put(a);
            }  
        }
        else if( command == "PUT-S")
        {
            std::cin>> p >> a;
            if( p > ::MAX_SIZE || p >= storehouse.HandyShelf.Places_num ) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyShelf.places[p].put(a);
            }  
        }

    // FILL
         if( command == "FILL")
        {
            std::cin>> w >> r >> s >> p >> a;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w<0 || r<0 || s<0 || p<0 || a<0){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.fill(w,r,s,p,a);
            }  
        }

    // POP
        else if( command == "POP-W")
        {
            std::cin>> w >> r >> s >> p >> a;
            if( w > ::MAX_SIZE || a < 0 || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].pop(a);
            }  
        }
        else if( command == "POP-H")
        {
            std::cin>> w >> p >> a;
            if( w > ::MAX_SIZE || p > ::MAX_SIZE || a < 0 || w >= storehouse.Warehouses_num || p >= storehouse.warehouses[w].HandyShelf.Places_num){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].HandyShelf.places[p].pop(a);
            }  
        }
        else if( command == "POP-R")
        {
            std::cin>> s >> p >> a;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || a < 0 || s >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyRack.shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.shelfs[s].places[p].pop(a);
            }  
        }
        else if( command == "POP-S")
        {
            std::cin>> p >> a;
            if( p > ::MAX_SIZE || a < 0 || p >= storehouse.HandyShelf.Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyShelf.places[p].pop(a);
            }  
        }
    // MOV
        else if( command == "MOV-W")
        {
            std::cin>> w >> r >> s >> w2 >> r2 >> s2 >> p >> a;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || w2 > ::MAX_SIZE || r2 > ::MAX_SIZE || s2 > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || w2 >= storehouse.Warehouses_num || r2 >= storehouse.warehouses[w2].Racks_num || s2 >= storehouse.warehouses[w2].racks[r2].Shelfs_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num || p >= storehouse.warehouses[w2].racks[r2].shelfs[s2].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].mov(storehouse.warehouses[w2].racks[r2].shelfs[s2].places[p], a);
            }  
        }
        else if( command == "MOV-H")
        {
            std::cin>> w >> r >> s >> p >> a;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || p >= storehouse.warehouses[w].HandyShelf.Places_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].mov(storehouse.warehouses[w].HandyShelf.places[p], a);
            }  
        }
        else if( command == "MOV-R")
        {
            std::cin>> w >> r >> s >> s2 >> p >> a;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || s2 > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || s2 >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyRack.shelfs[s2].Places_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].mov(storehouse.HandyRack.shelfs[s2].places[p], a);
            }  
        }
        else if( command == "MOV-S")
        {
            std::cin>> s >> p >> a;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || s >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyShelf.Places_num || p >= storehouse.HandyRack.shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.shelfs[s].places[p].mov(storehouse.HandyShelf.places[p], a);
            }  
        }
    // GET
        else if( command == "GET-E")
        {
            std::cout<< storehouse.get() << std::endl;
        }
        else if( command == "GET-W")
        {
            std::cin>> w;
            if( w > ::MAX_SIZE || w<0 || w >= storehouse.Warehouses_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                std::cout << storehouse.warehouses[w].get() << std::endl;
            }  
        }
        else if( command == "GET-RW")
        {
            std::cin>> w >> r;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || w<0 || r<0 || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                std::cout << storehouse.warehouses[w].racks[r].get() << std::endl;
            }  
        }
        else if( command == "GET-RH")
        {
            std::cout << storehouse.HandyRack.get() << std::endl;
        }
        else if( command == "GET-SW")
        {
            std::cin>> w >> r >> s;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || w<0 || s<0 || r<0 || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num){
                std::cout<<"error"<<std::endl;
            }
            else{
                std::cout << storehouse.warehouses[w].racks[r].shelfs[s].get() << std::endl;
            }  
        }
        else if( command == "GET-SH")
        {
            std::cin>> w;
            if( w > ::MAX_SIZE || w<0 || w >= storehouse.Warehouses_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                std::cout << storehouse.warehouses[w].HandyShelf.get() << std::endl;
            }  
        }
        else if( command == "GET-SR")
        {
            std::cin>> s;
            if( s > ::MAX_SIZE || s<0 || s >= storehouse.HandyRack.Shelfs_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                std::cout << storehouse.HandyRack.shelfs[s].get() << std::endl;
            }  
        }
        else if( command == "GET-S")
        {   
            std::cout << storehouse.HandyShelf.get() << std::endl;
        }

    // SET i GET (labels)
        else if( command == "SET-LW")
        {
            std::cin>> w >> r >> s >> p >> dd;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].label[0] = dd[0];
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].label[1] = dd[1];
            }  
        }
        else if( command == "SET-LH")
        {
            std::cin>> w >> p >> dd;
            if( w > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || p >= storehouse.warehouses[w].HandyShelf.Places_num ) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].HandyShelf.places[p].label[0] = dd[0];
                storehouse.warehouses[w].HandyShelf.places[p].label[1] = dd[1];
            }  
        }
        else if( command == "SET-LR")
        {
            std::cin>> s >> p >> dd;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || s >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyRack.shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.shelfs[s].places[p].label[0] = dd[0];
                storehouse.HandyRack.shelfs[s].places[p].label[1] = dd[1];
            }  
        }
        else if( command == "SET-LS")
        {
            std::cin>> p >> dd;
            if( p > ::MAX_SIZE || p >= storehouse.HandyShelf.Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyShelf.places[p].label[0] = dd[0];
                storehouse.HandyShelf.places[p].label[1] = dd[1];
            }  
        }
        else if( command == "GET-LW")
        {
            std::cin>> w >> r >> s >> p;
            if( w > ::MAX_SIZE || r > ::MAX_SIZE || s > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || r >= storehouse.warehouses[w].Racks_num || s >= storehouse.warehouses[w].racks[r].Shelfs_num || p >= storehouse.warehouses[w].racks[r].shelfs[s].Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].racks[r].shelfs[s].places[p].PrintLabel();
            }  
        }
        else if( command == "GET-LH")
        {
            std::cin>> w >> p;
            if( w > ::MAX_SIZE || p > ::MAX_SIZE || w >= storehouse.Warehouses_num || p >= storehouse.warehouses[w].HandyShelf.Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.warehouses[w].HandyShelf.places[p].PrintLabel();
            }  
        }
        else if( command == "GET-LR")
        {
            std::cin>> s >> p;
            if( s > ::MAX_SIZE || p > ::MAX_SIZE || s >= storehouse.HandyRack.Shelfs_num || p >= storehouse.HandyRack.shelfs[s].Places_num ) {
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyRack.shelfs[s].places[p].PrintLabel();
            }  
        }
        else if( command == "GET-LS")
        {
            std::cin>> p;
            if( p > ::MAX_SIZE || p >= storehouse.HandyShelf.Places_num ){
                std::cout<<"error"<<std::endl;
            }
            else{
                storehouse.HandyShelf.places[p].PrintLabel();
            }  
        }

    } while (command != "END");
    

    return 0;
}



