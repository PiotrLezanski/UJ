//Piotr Lezanski
#include <string>
#include <iostream>

#define uInt unsigned int

class ARENA_CLASS;
class CAESAR_CLASS;
class BEAST_CLASS;
class HUMAN_CLASS;
class SQUAD_CLASS;
class PLAYER_CLASS;

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLAYER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class PLAYER_CLASS {
public:
    uInt max_hp;
    uInt curr_hp;
    uInt attack;
    uInt agility;

    friend class CAESAR_CLASS;
    friend class SQUAD_CLASS;


    PLAYER_CLASS() {}
    //PLAYER_CLASS(std::string _id, uInt _max_hp, uInt _curr_hp, uInt _attack, uInt _agility) : id(_id) , max_hp(_max_hp), curr_hp(_curr_hp), attack(_attack), agility(_agility) {}
    PLAYER_CLASS(uInt _max_hp, uInt _curr_hp, uInt _attack, uInt _agility) : max_hp(_max_hp), curr_hp(_curr_hp), attack(_attack), agility(_agility) {}

    virtual uInt getRemainingHealth() {
        return (curr_hp * 100) / max_hp;
    }

    virtual uInt getDamage() {
        return attack;
    }

    virtual uInt getAgility() {
        return agility;
    }

    virtual void takeDamage(uInt x) {
        
        if( x < curr_hp ) {
            curr_hp -= x;
            if( getRemainingHealth() <= 0 ) {
                die();
            }
        }
        else {
            die();
        }
    }

    virtual void applyWinnerReward() {
        attack += 2;
        agility += 2;
    }

    virtual void cure() {
        curr_hp = max_hp;
    }

    virtual void printParams() {
        std::cout<< max_hp << ":" << curr_hp << ":" << getRemainingHealth() << "%:" << getDamage() << ":" << getAgility();
    }

    virtual std::string getId() = 0;

protected:
    virtual void die() {
        curr_hp = 0;
    }

};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END PLAYER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CAESAR CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class CAESAR_CLASS {
public:
    uInt attackCounter = 0;
    uInt judgesCounter = 0;

    // CAESAR_CLASS() : attackCounter(0), judgesCounter(0) {}

    void judgeDeathOfLife(PLAYER_CLASS* player) {
        ++judgesCounter;
        if( judgesCounter % 3 == 0 ) {
            if (attackCounter % 2 == 0) {
                player->die();
            }
            judgesCounter = 0;
        }
    }


};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END CAESAR CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ HUMAN CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class HUMAN_CLASS : public virtual PLAYER_CLASS {
protected:
    
    int defense;

public:
    std::string id;
    //HUMAN_CLASS(std::string _id) : PLAYER_CLASS(_id, 200, 200, 30, 10) {}
    HUMAN_CLASS(std::string _id) : PLAYER_CLASS(200, 200, 30, 10), defense(10), id(_id) {}

    virtual void takeDamage(uInt d) {
        int real_d = d - defense - getAgility();
        if( real_d > 0 ) {
            PLAYER_CLASS::takeDamage(real_d);
        }
    }

    virtual void printParams() {
        if( getRemainingHealth() > 0 ) {
            std::cout<< id << ":";
            PLAYER_CLASS::printParams();
            std::cout<<":" << defense <<std::endl;
        }
        else {
            std::cout<< id << ":R.I.P." << std::endl;
        }
    }

    virtual std::string getId() {
        return id;
    }
};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END HUMAN CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BEAST CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class BEAST_CLASS : public virtual PLAYER_CLASS {
protected:
    

public:
    std::string id;
    //BEAST_CLASS(std::string _id) : PLAYER_CLASS(_id, 200, 200, 30, 10) {}
    BEAST_CLASS(std::string _id) : PLAYER_CLASS(150, 150, 40, 20), id(_id) {}

    virtual uInt getDamage() {
        if( getRemainingHealth() < 25 ) {
            return  2 * attack;
        }
        return attack;
    }

    virtual void takeDamage(uInt d) {
        int real_d = d - (getAgility() / 2);
        if( real_d > 0 ) {
            PLAYER_CLASS::takeDamage(real_d);
        }
    }

    virtual void printParams() {
        if( getRemainingHealth() > 0 ) {
            std::cout<< id << ":";
            PLAYER_CLASS::printParams();
            std::cout<< std::endl;
        }
        else {
            std::cout<< id << ":R.I.P." << std::endl;
        }
    }

    virtual std::string getId() {
        return id;
    }
};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END BEAST CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BERSERKER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class BERSERKER_CLASS : public virtual HUMAN_CLASS, public virtual BEAST_CLASS {
protected:
    std::string human_id;
    std::string beast_id;
    

public:
// std::string id;
    BERSERKER_CLASS(std::string h_id, std::string b_id) : PLAYER_CLASS(200, 200, 35, 5), HUMAN_CLASS(h_id), BEAST_CLASS(b_id) {
        defense = 15;
        // id = h_id;
    }

    void takeDamage(uInt d) {
        if( getRemainingHealth() < 25 ) {
            BEAST_CLASS::takeDamage(d);
        }
        else {
            HUMAN_CLASS::takeDamage(d);
        }
    }

    uInt getDamage() {
        if( getRemainingHealth() > 0 && getRemainingHealth() < 25 ) {
            return BEAST_CLASS::getDamage();
        }
        else {
            return HUMAN_CLASS::getDamage();
        }
    }

    uInt getAgility() {
        if( getRemainingHealth() > 0 && getRemainingHealth() < 25 ) {
            return BEAST_CLASS::getAgility();
        }
        else {
            return HUMAN_CLASS::getAgility();
        }
    }

    void printParams() {
        if( getRemainingHealth() > 0 && getRemainingHealth() < 25 ) {
            BEAST_CLASS::printParams();
        }
        else {
            HUMAN_CLASS::printParams();
        }
    }

    std::string getId() {
        if( getRemainingHealth() < 25 ) {
            return BEAST_CLASS::getId();
        }
        else {
            return HUMAN_CLASS::getId();
        }
    }
};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END BERSERKER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLAYER LIST ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Player {
public:
    PLAYER_CLASS* player;
    Player* next;
    Player* prev;

    Player(PLAYER_CLASS* _player) {
        this->player = _player;
        next = NULL;
        prev = NULL;
    }
};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END PLAYER LIST ~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Player* insertionSortList(Player* head) {
    if (head == NULL) return NULL; 
    
    for(Player* temp=head;temp!=NULL;temp=temp->next)
    {
        for(Player* curr=head;curr!=temp;curr=curr->next)
        {
            Player* ptr1 = curr;
            Player* ptr2 = temp;
            bool done  = false;
            if (ptr1->player->getId() > temp->player->getId()) {
                // std::cout<< "COMPARE : " << ptr1->player->getId() << " " << ptr1->next->player->getId() <<std::endl; 
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
            if( !done && (ptr1->player->getId() == temp->player->getId()) && (ptr1->player->max_hp > temp->player->max_hp) ) {
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
            if( !done && (ptr1->player->getId() == temp->player->getId()) && (ptr1->player->max_hp == temp->player->max_hp) && (ptr1->player->curr_hp > temp->player->curr_hp) ) {
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
            if( !done && (ptr1->player->getId() == temp->player->getId()) && (ptr1->player->max_hp == temp->player->max_hp) && (ptr1->player->curr_hp == temp->player->curr_hp) && (ptr1->player->getRemainingHealth() > temp->player->getRemainingHealth()) ) {
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
            if( !done && (ptr1->player->getId() == temp->player->getId()) && (ptr1->player->max_hp == temp->player->max_hp) && (ptr1->player->curr_hp == temp->player->curr_hp) && (ptr1->player->getRemainingHealth() == temp->player->getRemainingHealth()) && (ptr1->player->getDamage() > temp->player->getDamage()) ) {
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
            if( !done && (ptr1->player->getId() == temp->player->getId()) && (ptr1->player->max_hp == temp->player->max_hp) && (ptr1->player->curr_hp == temp->player->curr_hp) && (ptr1->player->getRemainingHealth() == temp->player->getRemainingHealth()) && (ptr1->player->getDamage() == temp->player->getDamage()) && (ptr1->player->getAgility() > temp->player->getAgility()) ) {
                PLAYER_CLASS* ans=curr->player;
                curr->player=temp->player;
                temp->player=ans;
                done = true;
            }
        }
    }
    return head;  
}


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SQUAD CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class SQUAD_CLASS : public virtual PLAYER_CLASS {
protected:

    Player* first;
    Player* last;
    int size;    

public:
    std::string id;

    SQUAD_CLASS(std::string _id) : id(_id), first(NULL), last(NULL), size(0) {}

    void addPlayer(PLAYER_CLASS* _new_player) {
        if( _new_player->getRemainingHealth() > 0 ) {
            for(Player* curr = first; curr != NULL; curr = curr->next) {
                if( curr->player == _new_player ) {
                    return;
                }
            }

            Player* new_player = new Player(_new_player);
            if( first == NULL ) {
                first = new_player;
                last = first;
            }
            else {
                last->next = new_player;
                new_player->prev = last;
                last = new_player;
            }
            ++size;
        }
    }

    uInt getAgility() {
        if( size > 0 ) {
            uInt min = first->player->getAgility();
            for(Player* curr = first->next; curr != NULL; curr = curr->next) {
                uInt value = curr->player->getAgility();
                if( value < min ) {
                    min = value;
                }
            }
            return min;
        }
        return 0;
    }

    uInt getDamage() {
        uInt sum = 0;
        for(Player* curr = first; curr != NULL; curr = curr->next) {
            sum += curr->player->getDamage();
        }
        return sum;
    }

    void applyWinnerReward() {
        for(Player* curr = first; curr != NULL; curr = curr->next) {
            curr->player->applyWinnerReward();
        }
    }

    void deletePerson(PLAYER_CLASS* del_player) {
        Player* curr;
        for(curr = first; curr != NULL; curr = curr->next) {
            if( curr->player == del_player ) {
                break;
            }
        }

        if( curr != NULL ) {
            if( curr == first && curr == last ) {
                first = NULL;
                last = NULL;
            }
            else if( curr == first ) {
                first = first->next;
            }
            else if( curr == last ) {
                last = last->prev;
            }
            else {
                curr->prev->next = curr->next;
                curr->next->prev = curr->prev;
            }
            --size;
        }
        
    }

    void takeDamage(uInt d) {
        uInt real_d = d / size;
        for(Player* curr = first; curr != NULL; curr = curr->next) {
            curr->player->takeDamage(real_d);
            if( curr->player->getRemainingHealth() <= 0 ) {
                deletePerson(curr->player);
            }
        }
    }

    uInt getRemainingHealth() {
        if( size > 0 ) {
            uInt max_health = first->player->getRemainingHealth();
            for(Player* curr = first->next; curr != NULL; curr = curr->next) {
                uInt tmp_health = curr->player->getRemainingHealth();
                if( tmp_health > max_health ) {
                    max_health = tmp_health;
                }
            }
            return max_health;
        }
        
        return 0;
    }

    void printParams() {
        if( size > 0 ) {
            std::cout<< id << ":" << size << ":" << getRemainingHealth() << "%:" << getDamage() << ":" << getAgility() << std::endl;
            // playerList->bubbleSort();
            first = insertionSortList(first);
            for(Player* curr = first; curr != NULL; curr = curr->next) {
                curr->player->printParams();
            }
        }
        else {
            std::cout<< id << ":nemo" <<std::endl;
        }
    }

    std::string getId() {
        return id;
    }

    void die() {
        for(Player* curr = first; curr != NULL; curr = curr->next) {
            curr->player->die();
            deletePerson(curr->player);
        }
        first = NULL;
        last = NULL;
    }

    void cure() {
        for(Player* curr = first; curr != NULL; curr = curr->next) {
            curr->player->cure();
        }
    }
};


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END SQUAD CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ARENA CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class ARENA_CLASS {
private:
    CAESAR_CLASS* caesar;

public:
    ARENA_CLASS(CAESAR_CLASS* _caesar) : caesar(_caesar) {}

    void fight(PLAYER_CLASS* first, PLAYER_CLASS* second) {
        
        if( first->getRemainingHealth() > 0 && second->getRemainingHealth() > 0 ) {
            if( first->getAgility() < second->getAgility() ) {
                PLAYER_CLASS* tmp = first;
                first = second;
                second = tmp;
            }

            first->printParams();
            second->printParams();

            for(int i=0; i<40; ++i) {
                if( first->getRemainingHealth() >= 10 && second->getRemainingHealth() >= 10 ) {
                    second->takeDamage(first->getDamage());
                    second->printParams();
                    caesar->attackCounter++;
                    if( second->getRemainingHealth() >= 10  ) {
                        first->takeDamage(second->getDamage());
                        first->printParams();
                        caesar->attackCounter++;
                    }
                    else break;
                }
                else break;
            }

            // ceasar judges

            if( first->getRemainingHealth() > 0 ) {
                caesar->judgeDeathOfLife(first);
                first->printParams();
                if( first->getRemainingHealth() > 0 ) {
                    first->cure();
                    first->applyWinnerReward();
                }
            }
            if( second->getRemainingHealth() > 0 ) {
                caesar->judgeDeathOfLife(second);
                second->printParams();
                if( second->getRemainingHealth() > 0 ) {
                    second->cure();
                    second->applyWinnerReward();
                }
            }

            first->printParams();
            second->printParams();

            caesar->attackCounter = 0;
        }
    }
};
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END ARENA CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

int main() {
    CAESAR_CLASS caesar;
    ARENA_CLASS arena(&caesar);

    HUMAN_CLASS h1("h1");
    HUMAN_CLASS h2("h2");

    SQUAD_CLASS squad1("FIRST");
    SQUAD_CLASS squad2("SECON");

    squad1.addPlayer(&h1);
    squad1.addPlayer(&h2);
    squad2.addPlayer(&h1);
    squad2.addPlayer(&h2);

    arena.fight(&squad1, &squad2);
    arena.fight(&squad2, &squad1);
}