//Piotr Lezanski
#include <iostream>
#include "memory_check.h"

// wiazanie
struct Bond {
    char cord_id1;
    char cord_id2;
    char cord_id3;
    short bead_id;
    Bond* next = NULL;
    Bond(char id1, char id2, char id3, short id) : cord_id1(id1), cord_id2(id2), cord_id3(id3), bead_id(id) {};

    // helpers
};

// koral
struct Bead {
    short id;
    Bond* from = NULL;
    Bond* to = NULL;
    Bead* next = NULL;
    Bead(short _id) : id(_id) {};

    // methods
    void add_bond();

    // helpers
    //bool hasBond();
    void add_bond(short, char, char, char, short, char, char, char);
};

// sznur
struct Cord {
    char id1;
    char id2;
    char id3;
    Cord* head;
    Cord* next;
    Bead* first_bead; // first bead
    Cord(char _id1, char _id2, char _id3) : id1(_id1), id2(_id2), id3(_id3) {};
    Cord() { first_bead = NULL; next = NULL; head = NULL; };

    // // methods
    void add(char, char, char);
    void add_bead(short, char, char, char);
    void add_bond(short, char, char, char, short, char, char, char);
    void delete_bond(short, char, char, char, short, char, char, char);
    void delete_bead(short, char, char, char);
    void move_bead(short, char, char, char, char, char, char);
    void delete_cord(char, char, char);

    // helpers
    Cord* find_cord(char, char, char);
    void DeleteBondsTo(short, char, char, char);
    void DeleteBondsFrom(short, char, char, char);
    Bead* find_bead(Cord*, short);
    bool hasCordId(char, char, char);
    void display_cords();
    void delete_cords();
};

// helpers
bool hasBead(Cord*, short);
void del(void* ptr) {if(ptr) delete ptr;}
// end

bool goNext(char a_, char b_, char c_, char a, char b, char c) {
    if( a < a_ ) return false;
    if( a > a_ ) return true;
    if( a == a_ && b < b_) return false;
    if( a == a_ && b > b_) return true;
    if( a == a_ && b == b_ && c > c_ ) return true;
    if( a == a_ && b == b_ && c < c_ ) return false;;
    // possible to have the same ID?
}

bool goNextBond(short sK, char s1, char s2, char s3, short dK, char d1, char d2, char d3) {
    if( s1 == d1 && s2 == d2 && s3 == d3 ) {
        if( sK < dK ) {
            return true;
        }
        else if ( sK > dK ) {
            return false;
        }
    }
    else {
        return goNext(s1, s2, s3, d1, d2, d3);
    }
}

void Cord::add(char a, char b, char c) {
    if( !hasCordId(a,b,c) ) {
        Cord* newCord = new Cord(a, b, c);
        if( head != NULL ) {
            Cord* curr = head;
            Cord* prev_curr = head;

            while( curr != NULL && goNext(curr->id1, curr->id2, curr->id3, a, b, c) ) {
                curr = curr->next;
            }

            if( curr == head ) {
                newCord->next = curr;
                head = newCord;
            }
            else {
                while( prev_curr->next != curr ) {
                    prev_curr = prev_curr->next;
                }
                prev_curr->next = newCord;
                newCord->next = curr;
            }   
        }
        else {
            head = newCord;
            head->next = NULL;
        }
    }
}

void Cord::add_bead(short kr, char sn1, char sn2, char sn3) {
    if( head != NULL ) {
        Cord* curr = head;
        while( curr != NULL && (curr->id1 != sn1 || curr->id2 != sn2 || curr->id3 != sn3) ) {
            curr = curr->next;
        }
        //if( !hasBead(curr, kr) ) {
            if( curr != NULL ) {
                // adding bead
                Bead* new_bead = new Bead(kr);
                if( curr->first_bead != NULL ) {
                    Bead* curr_bead = curr->first_bead;
                    while( curr_bead != NULL && curr_bead->id < kr ) {
                        curr_bead = curr_bead->next; 
                    }
                    
                    if( curr_bead == curr->first_bead ) {
                        curr->first_bead = new_bead;
                        new_bead->next = curr_bead;
                    }
                    else {
                        Bead* prev_curr_bead = curr->first_bead;
                        while( prev_curr_bead->next != curr_bead ) {
                            prev_curr_bead = prev_curr_bead->next;
                        }
                        prev_curr_bead->next = new_bead;
                        new_bead->next = curr_bead;
                    }
                }
                else {
                    new_bead->next = NULL;
                    curr->first_bead = new_bead;
                }
            }
        //}
    }
}

void Cord::add_bond(short sK, char sS1, char sS2, char sS3, short dK, char dS1, char dS2, char dS3) {
    Cord* cord = find_cord(sS1, sS2, sS3);
    if( cord != NULL ) {
        Bead* bead = find_bead(cord, sK);
        if( bead != NULL ) {
            Bond* new_bond = new Bond(dS1, dS2, dS3, dK);
            Bond* curr_bond = bead->to;
            Bond* prev_curr_bond = bead->to;
            while( curr_bond != NULL && goNextBond(curr_bond->bead_id, curr_bond->cord_id1, curr_bond->cord_id2, curr_bond->cord_id3, dK, dS1, dS2, dS3) ) {
                curr_bond = curr_bond->next;
            }

            if( curr_bond == bead->to ) {
                new_bond->next = curr_bond;
                bead->to = new_bond;
            }
            else {
                while( prev_curr_bond->next != curr_bond ) {
                    prev_curr_bond = prev_curr_bond->next;
                }
                prev_curr_bond->next = new_bond;
                new_bond->next = curr_bond;
            }
        }
    } 
}

void Cord::delete_bond(short sK, char sS1, char sS2, char sS3, short dK, char dS1, char dS2, char dS3) {
    Cord* cord = find_cord(sS1, sS2, sS3);
    if( cord != NULL ) {
        Bead* bead = find_bead(cord, sK);
        Bond* curr_bond = bead->to;
        if( curr_bond != NULL ) {
            while( curr_bond != NULL && (curr_bond->bead_id != dK || curr_bond->cord_id1 != dS1 || curr_bond->cord_id2 != dS2 || curr_bond->cord_id3 != dS3) ) {
                curr_bond = curr_bond->next;
            }
            if( curr_bond != NULL ) {
                if( curr_bond != bead->to ) {
                    Bond* prev_curr_bond = bead->to;
                    while( prev_curr_bond->next != curr_bond ) {
                        prev_curr_bond = prev_curr_bond->next;
                    }
                    del(curr_bond);
                    prev_curr_bond->next = curr_bond->next;
                }
                else {
                    del(curr_bond);
                    bead->to = curr_bond->next;
                }
            }
        }
    }
}

void Cord::delete_bead(short id, char s1, char s2, char s3) {
    DeleteBondsTo(id, s1, s2, s3);
    DeleteBondsFrom(id, s1, s2, s3);
    Cord* cord = find_cord(s1, s2, s3);
    if( cord != NULL ) {
        Bead* bead = find_bead(cord, id);
        if( bead != NULL ) {
            if( bead == cord->first_bead ) {
                cord->first_bead = bead->next;
            }
            else {
                Bead* prev_bead = cord->first_bead;
                while( prev_bead->next != bead ) {
                    prev_bead = prev_bead->next;
                }
                prev_bead->next = bead->next;
            }
            del(bead);
        }
    }
}

void Cord::move_bead(short id, char s1, char s2, char s3, char d1, char d2, char d3) {
    Cord* from_cord = find_cord(s1, s2, s3);
    Bead* from_bead = find_bead(from_cord, id);
    Bead* copy = from_bead;
    if( from_bead != NULL ) {
        if( from_bead == from_cord->first_bead ) {
            from_cord->first_bead = from_bead->next;
        }
        else {
            Bead* prev = from_cord->first_bead;
            while( prev->next != from_bead ) {
                prev = prev->next;
            }
            prev->next = from_bead->next;
        }
    }

    Cord* add_cord = find_cord(d1, d2, d3);
    if( add_cord != NULL ) {
        // adding bead
        Bead* from_bead = copy;
        if( from_bead != NULL ) {
            if( add_cord->first_bead != NULL ) {
                Bead* curr_bead = add_cord->first_bead;
                while( curr_bead != NULL && curr_bead->id < id ) {
                    curr_bead = curr_bead->next; 
                }
                
                if( curr_bead == add_cord->first_bead ) {
                    add_cord->first_bead = from_bead;
                    from_bead->next = curr_bead;
                }
                else {
                    Bead* prev_curr_bead = add_cord->first_bead;
                    while( prev_curr_bead->next != curr_bead ) {
                        prev_curr_bead = prev_curr_bead->next;
                    }
                    prev_curr_bead->next = from_bead;
                    from_bead->next = curr_bead;
                }
            }
            else {
                from_bead->next = NULL;
                add_cord->first_bead = from_bead;
            }
        }
    }
}

void Cord::delete_cord(char s1, char s2, char s3) {
    Cord* cord_to_delete = find_cord(s1, s2, s3);
    Bead* bead_to_delete = cord_to_delete->first_bead;
    while( bead_to_delete != NULL ) {
        Bead* tmp = bead_to_delete;
        delete_bead(bead_to_delete->id, cord_to_delete->id1, cord_to_delete->id2, cord_to_delete->id3);
        bead_to_delete = tmp->next;
    }

    Cord* prev_cord = head;
    if( cord_to_delete == head ) {
        head = cord_to_delete->next;
    }
    else {
        while( prev_cord->next != cord_to_delete ) {
            prev_cord = prev_cord->next;
        }
        prev_cord->next = cord_to_delete->next;
    }
    del(cord_to_delete);
}

// helpers
Cord* Cord::find_cord(char sS1, char sS2, char sS3) {
    Cord* curr = head;
    while( curr != NULL && (curr->id1 != sS1 || curr->id2 != sS2 || curr->id3 != sS3 )) {
        curr = curr->next;
    }
    return curr;
}

Bead* Cord::find_bead(Cord* cord, short sK) {
    Bead* curr_bead = cord->first_bead;
    while( curr_bead != NULL && sK != curr_bead->id ) {
        curr_bead = curr_bead->next;
    }
    return curr_bead;
}

bool Cord::hasCordId(char a, char b, char c) {
    Cord* curr = head;
    while( curr != NULL ) {
        if( curr->id1 == a && curr->id2 == b && curr->id3 == c ) {
            return true;
        }
        curr = curr->next;
    }
    return false;
}

void Cord::display_cords() {
    Cord* curr_cord = head;
    Bead* curr_bead;
    while( curr_cord != NULL ) {
        std::cout<< curr_cord->id1<<curr_cord->id2<<curr_cord->id3<<std::endl;
        curr_bead = curr_cord->first_bead;
        while( curr_bead != NULL ) {
            std::cout<< curr_bead->id;
            Bond* first_bond = curr_bead->to;
            while( first_bond != NULL ) {
                std::cout<<" "<<first_bond->cord_id1<<first_bond->cord_id2<<first_bond->cord_id3<<" "<<first_bond->bead_id;
                first_bond = first_bond->next;
            }
            std::cout<<std::endl;
            curr_bead = curr_bead->next;
        }
        curr_cord = curr_cord->next;
    }
}

bool hasBead(Cord* curr, short id) {
    Bead* curr_bead = curr->first_bead;
    while( curr_bead != NULL ) {
        if( curr_bead->id == id ) {
            return true;
        }
        curr_bead = curr_bead->next;
    }
    return false;
}

void Cord::delete_cords() {
    Cord* curr_cord = head;
    Bead* curr_bead;
    while( curr_cord != NULL ) {
        curr_bead = curr_cord->first_bead;
        while( curr_bead != NULL ) {
            Bond* first_bond = curr_bead->to;
            while( first_bond != NULL ) {
                Bond* tmp = first_bond;
                del(first_bond);
                first_bond = tmp->next;
            }
            Bead* tmp_bead = curr_bead;
            del(curr_bead);
            curr_bead = tmp_bead->next;
        }
        Cord* tmp_cord = curr_cord;
        del(curr_cord);
        curr_cord = tmp_cord->next;
    }
}

void Cord::DeleteBondsTo(short id, char s1, char s2, char s3) {
    Cord* curr_cord = head;
    while( curr_cord != NULL ) {
        Bead* curr_bead = curr_cord->first_bead;
        while( curr_bead != NULL ) {
            Bond* curr_bond = curr_bead->to;
            while( curr_bond != NULL ) {
                if( curr_bond->bead_id == id && curr_bond->cord_id1 == s1 && curr_bond->cord_id2 == s2 && curr_bond->cord_id3 == s3 ) { 
                    if( curr_bond == curr_bead->to ) {
                        curr_bead->to = curr_bond->next;
                        del(curr_bond);
                    }
                    else {
                        Bond* prev_curr_bond = curr_bead->to;
                        while( prev_curr_bond->next != curr_bond ) {
                            prev_curr_bond = prev_curr_bond->next;
                        }
                        prev_curr_bond->next = curr_bond->next;
                        del(curr_bond);
                    }
                }
                curr_bond = curr_bond->next;
            }
            
            curr_bead = curr_bead->next;
        }
        curr_cord = curr_cord->next;
    }
}

void Cord::DeleteBondsFrom(short id, char s1, char s2, char s3) {
    Cord* curr_cord = find_cord(s1, s2, s3);
    if( curr_cord != NULL ) {
        Bead* curr_bead = find_bead(curr_cord, id);
        if( curr_bead != NULL ) {
            Bond* curr_bond = curr_bead->to;
            while( curr_bond != NULL ) {
                Bond* tmp = curr_bond;
                del(curr_bond);
                curr_bond = tmp->next;
            }
        }
    }
}
// helpers end

int main() 
{
    // S, B
    char op;
    char sn1;
    char sn2; 
    char sn3;
    short kr;
    char sS;

    // L, K
    short sK;
    char sS1;
    char sS2;
    char sS3;
    short dK;
    char dS1;
    char dS2; 
    char dS3;

    Cord* cord = new Cord();
    do {
        std::cin>> op;
        switch(op) {
            case 'S':
                std::cin>> sn1>> sn2>>sn3;
                cord->add(sn1, sn2, sn3);
            break;

            case 'B':
                std::cin>> kr>>sn1>>sn2>>sn3;
                cord->add_bead(kr, sn1, sn2, sn3);
            break;

            case 'L':
                std::cin>> sK>>sS1>>sS2>>sS3>> dK>>dS1>>dS2>>dS3;
                cord->add_bond(sK, sS1, sS2, sS3, dK, dS1, dS2, dS3);
            break;

            case 'U':
                std::cin>> sK>>sS1>>sS2>>sS3>> dK>>dS1>>dS2>>dS3;
                cord->delete_bond(sK, sS1, sS2, sS3, dK, dS1, dS2, dS3);
            break;

            case 'D':
                std::cin>>sK>>sS1>>sS2>>sS3;
                cord->delete_bead(sK, sS1, sS2, sS3);
            break;

            case 'M':
                std::cin>>sK>>sS1>>sS2>>sS3>>dS1>>dS2>>dS3;
                cord->move_bead(sK, sS1, sS2, sS3, dS1, dS2, dS3);
            break;

            case 'R':
                std::cin>> sS1>>sS2>>sS3;
                cord->delete_cord(sS1, sS2, sS3);
            break;

            case 'P':
                cord->display_cords();
            break;
        }
    } while( op != 'F' ); 

    cord->delete_cords();
    del(cord);

    _MemoryCheck();
    return 0;
}


