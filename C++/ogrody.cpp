//Piotr Lezanski
#include <cstdlib>
#include <iostream>

typedef unsigned int uInt;
// heading

class BRANCH_CLASS;
class FRUIT_CLASS;
class GARDEN_CLASS;
class TREE_CLASS;

// end

class GARDEN_CLASS {
private:
    uInt tree_num;
    uInt gaps_num;

    uInt branch_num;
    uInt fruit_num;
    uInt weights_total;

    TREE_CLASS* first;
    TREE_CLASS* last;

public:
    GARDEN_CLASS();
    ~GARDEN_CLASS();

    uInt getBranchesTotal();
    uInt getTreesTotal();
    uInt getFruitsTotal();
    uInt getWeightsTotal();
    void plantTree();
    void extractTree(uInt);
    void growthGarden();
    void fadeGarden();
    void harvestGarden(uInt);
    TREE_CLASS* getTreePointer(uInt);
    void cloneTree(uInt);

    void addWeight(uInt);
    void reduceWeight(uInt);
    void addFruit(uInt);
    void reduceFruit(uInt);
    void addBranch();
    void reduceBranch() ;
    void addTree();
    void reduceTree();
    void displayAll();
    
};


// WOOD CLASS

class TREE_CLASS {
private:
    uInt id;
    uInt height;
    uInt branch_num;
    uInt fruit_num;
    uInt weights_total;

    GARDEN_CLASS* daddy;

    BRANCH_CLASS* first;
    BRANCH_CLASS* last;

    TREE_CLASS* prev;
    TREE_CLASS* next;

public:
    TREE_CLASS();
    TREE_CLASS(uInt, GARDEN_CLASS*);
    TREE_CLASS(const TREE_CLASS&, uInt); 
    TREE_CLASS(const TREE_CLASS&); 
    TREE_CLASS(const TREE_CLASS&, uInt, GARDEN_CLASS* dad);
    ~TREE_CLASS();

    

    uInt getBranchesTotal();
    uInt getFruitsTotal();
    uInt getWeightsTotal();
    uInt getNumber();
    uInt getHeight();
    void growthTree();
    void fadeTree();
    void harvestTree(uInt);
    void cutTree(uInt);
    void cloneBranch(BRANCH_CLASS*);
    GARDEN_CLASS* getGardenPointer();
    BRANCH_CLASS* getBranchPointer(uInt);

    void setDaddy(GARDEN_CLASS*);
    void setId(uint);

    void addWeight(uInt);
    void reduceWeight(uInt);
    void addFruit(uInt);
    void reduceFruit(uInt);
    void addBranch();
    void reduceBranch();

    GARDEN_CLASS* getDaddy() {return daddy;}

    void setPrev(TREE_CLASS*);
    void setNext(TREE_CLASS*);
    TREE_CLASS* getPrev();
    TREE_CLASS* getNext();

    void displayAll();
};

void TREE_CLASS::setDaddy(GARDEN_CLASS* d) {
    daddy = d;
}

void TREE_CLASS::setId(uInt _id) {
    id = _id;
}

// END WOOD CLASS



// DECLARATIONS
class BRANCH_CLASS {
private:
    uInt length;
    uInt fruit_num;
    uInt daddy_height;
    TREE_CLASS* daddy;
    uInt weights_total;

    FRUIT_CLASS* first;
    FRUIT_CLASS* last;

    BRANCH_CLASS* next;
    BRANCH_CLASS* prev;

public:

    BRANCH_CLASS();
    BRANCH_CLASS(uInt, TREE_CLASS*);
    BRANCH_CLASS(const BRANCH_CLASS&); // coping constructor
    BRANCH_CLASS(const BRANCH_CLASS&, uInt); // coping constructor
    BRANCH_CLASS(const BRANCH_CLASS&, uInt, TREE_CLASS*);
    ~BRANCH_CLASS();

    TREE_CLASS* getDaddy() {return daddy;}

    uInt getFruitsTotal();
    uInt getWeightsTotal();
    uInt getHeight();
    uInt getLength();
    void growthBranch();
    void fadeBranch();
    void harvestBranch(uInt);
    void cutBranch(uInt);

    FRUIT_CLASS* getFruitPointer(uInt);
    TREE_CLASS* getTreePointer();

    void setDaddy(TREE_CLASS*);
    void setId(uint);

    void setdaddyHeight(uInt);
    void setTreePointer(TREE_CLASS*); // !!!!!!!!

    void addWeight(uInt);
    void reduceWeight(uInt);
    void addFruit();
    void reduceFruit();

    void setPrev(BRANCH_CLASS*);
    void setNext(BRANCH_CLASS*);
    BRANCH_CLASS* getPrev();
    BRANCH_CLASS* getNext();

    void displayAll();
};

void BRANCH_CLASS::setDaddy(TREE_CLASS* d) {
    daddy = d;
}

// END DECLARATIONS

// ----------------  FRUIT ----------------
class FRUIT_CLASS {
private:
    uInt weight;
    uInt daddy_length;
    BRANCH_CLASS* daddy;

    FRUIT_CLASS* next;
    FRUIT_CLASS* prev;

public:
    FRUIT_CLASS();
    FRUIT_CLASS(uInt, BRANCH_CLASS*);
    FRUIT_CLASS(uInt, uInt, BRANCH_CLASS*);
    FRUIT_CLASS(const FRUIT_CLASS&);
    FRUIT_CLASS(const FRUIT_CLASS&, BRANCH_CLASS*);
    ~FRUIT_CLASS();

    uInt getLength();
    uInt getWeight();
    void growthFruit();
    void fadeFruit();
    void pluckFruit();
    BRANCH_CLASS* getBranchPointer();

    void setPrev(FRUIT_CLASS*);
    void setNext(FRUIT_CLASS*);
    FRUIT_CLASS* getPrev();
    FRUIT_CLASS* getNext();
    void setBranchPointer(BRANCH_CLASS*); // !!!!!!!!
};

FRUIT_CLASS::FRUIT_CLASS() : weight(0), daddy_length(0), daddy(NULL), next(NULL), prev(NULL) {
}
FRUIT_CLASS::FRUIT_CLASS(uInt len, BRANCH_CLASS* par) : weight(0), daddy_length(len), daddy(par), next(NULL), prev(NULL) {
    if(daddy!=NULL)daddy->addFruit();
}

FRUIT_CLASS::FRUIT_CLASS(uInt len, uInt wei, BRANCH_CLASS* par) : daddy_length(len), weight(wei), daddy(par), next(NULL), prev(NULL) {
    if(daddy!=NULL)daddy->addWeight(this->weight);
    if(daddy!=NULL)daddy->addFruit();
}

FRUIT_CLASS::FRUIT_CLASS(const FRUIT_CLASS& parent) 
: weight(parent.weight), daddy_length(parent.daddy_length), next(NULL), prev(NULL) {
    // daddy->addWeight(this->weight); // ?????
    if(daddy!=NULL)daddy->addFruit();
}

FRUIT_CLASS::FRUIT_CLASS(const FRUIT_CLASS& parent, BRANCH_CLASS* dad) 
: weight(parent.weight), daddy_length(parent.daddy_length), daddy(dad), next(NULL), prev(NULL) {
    if(daddy!=NULL)daddy->addWeight(this->weight);
    if(daddy!=NULL)daddy->addFruit();
}

FRUIT_CLASS::~FRUIT_CLASS() {
    if(daddy!=NULL)daddy->reduceWeight(this->weight);
    weight = 0;
    daddy_length = 0;
    if(daddy!=NULL)daddy->reduceFruit();
    daddy = NULL;
} 

uInt FRUIT_CLASS::getLength() {
    return daddy_length;
}

uInt FRUIT_CLASS::getWeight() {
    return weight;
}

void FRUIT_CLASS::growthFruit() {
    ++weight;
    if(daddy!=NULL)daddy->addWeight(1);
}

void FRUIT_CLASS::fadeFruit() {
    if(weight > 0) {
        --weight;
        if(daddy!=NULL)daddy->reduceWeight(1);
    }
}

void FRUIT_CLASS::pluckFruit() {
    if(daddy!=NULL)daddy->reduceWeight(this->weight);
    weight = 0;
}


BRANCH_CLASS* FRUIT_CLASS::getBranchPointer() {
    return daddy;
}

void FRUIT_CLASS::setPrev(FRUIT_CLASS* p) { 
    prev = p; 
}

void FRUIT_CLASS::setNext(FRUIT_CLASS* n) { 
    next = n; 
}

FRUIT_CLASS* FRUIT_CLASS::getPrev() { 
    return prev; 
}

FRUIT_CLASS* FRUIT_CLASS::getNext() { 
    return next; 
}

void FRUIT_CLASS::setBranchPointer(BRANCH_CLASS* branch) {
    daddy = branch;
}

// -------------  END FRUIT  -------------




// ----------------  BRANCH ----------------

BRANCH_CLASS::BRANCH_CLASS() : length(0), fruit_num(0), daddy_height(0), daddy(NULL), first(NULL), last(NULL), next(NULL), prev(NULL), weights_total(0) {}
BRANCH_CLASS::BRANCH_CLASS(uInt hei, TREE_CLASS* par) 
: length(0), fruit_num(0), daddy_height(hei), daddy(par), first(NULL), last(NULL), next(NULL), prev(NULL), weights_total(0) {
    if(daddy!=NULL)daddy->addBranch();
}

BRANCH_CLASS::BRANCH_CLASS(const BRANCH_CLASS& parent, uint _height) 
: length(parent.length), daddy_height(_height), fruit_num(0), daddy(parent.daddy), next(NULL), prev(NULL), weights_total(0) {

    if(daddy!=NULL)daddy->addBranch();
    if( length > 0 ) {
        FRUIT_CLASS* new_fruit;
        for(FRUIT_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            // new_fruit = new FRUIT_CLASS(curr->getLength(), curr->getWeight(), this);
            new_fruit = new FRUIT_CLASS(*curr, this);
            if( curr == parent.first ) {
                this->first = new_fruit;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_fruit);
                new_fruit->setPrev(this->last);
                this->last = new_fruit;
            }
        }
    }
}

BRANCH_CLASS::BRANCH_CLASS(const BRANCH_CLASS& parent, uint _height, TREE_CLASS* dad) 
: length(parent.length), daddy_height(_height), fruit_num(0), daddy(dad), next(NULL), prev(NULL), weights_total(0) {
    if(daddy!=NULL)daddy->addBranch();
    
    if( length > 0 ) {
        FRUIT_CLASS* new_fruit;
        for(FRUIT_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            // new_fruit = new FRUIT_CLASS(curr->getLength(), curr->getWeight(), this);
            new_fruit = new FRUIT_CLASS(*curr, this);
            if( curr == parent.first ) {
                this->first = new_fruit;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_fruit);
                new_fruit->setPrev(this->last);
                this->last = new_fruit;
            }
        }
    }
}

BRANCH_CLASS::BRANCH_CLASS(const BRANCH_CLASS& parent) 
: length(parent.length), daddy_height(parent.daddy_height), fruit_num(0), daddy(parent.daddy), next(NULL), prev(NULL), weights_total(0) {
    if(daddy!=NULL)daddy->addBranch();
    
    if( length > 0 ) {
        FRUIT_CLASS* new_fruit;
        for(FRUIT_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            // new_fruit = new FRUIT_CLASS(curr->getLength(), curr->getWeight(), this);
            new_fruit = new FRUIT_CLASS(*curr, this);
            if( curr == parent.first ) {
                this->first = new_fruit;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_fruit);
                new_fruit->setPrev(this->last);
                this->last = new_fruit;
            }
        }
    }
}

// other version at the end - probably wrong

BRANCH_CLASS::~BRANCH_CLASS() {
    if(daddy!=NULL)daddy->reduceBranch();

    FRUIT_CLASS* tmp;
    for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        tmp = curr;
        delete curr;
        curr = tmp;
    }
    length = 0;
    fruit_num = 0;
    daddy_height = 0;
    daddy = NULL;
    weights_total = 0;

    first = NULL;
    last = NULL;
}

void BRANCH_CLASS::displayAll() {
    std::cout << "-----fruit_count:" << getFruitsTotal() << " ";
    std::cout << "weights:" << getWeightsTotal() << std::endl;
    int max = getFruitsTotal(), i = 2;
    if (max>0) std::cout << "-----fruits:\n";
    while (i <= 2*max){
        FRUIT_CLASS * fruit = getFruitPointer(i);
        if (fruit != NULL) {
            std:: cout << "       *fruit at " << i << std::endl;
            std::cout <<"       *length: " << fruit->getLength() << " weight: "<< fruit->getWeight() << std::endl;}
        i+=2;
    }
}

uInt BRANCH_CLASS::getFruitsTotal() {
    return fruit_num;
}

uInt BRANCH_CLASS::getWeightsTotal() {
    return weights_total;
}

uInt BRANCH_CLASS::getHeight() {
    return daddy_height;
}

uInt BRANCH_CLASS::getLength() {
    return length;
}

void BRANCH_CLASS::growthBranch() {
    ++length;

    for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->growthFruit();
    }
    if( length % 2 == 0 && length > 0 ) {
        // new fruit that weights 0
        // ++fruit_num;
        FRUIT_CLASS* new_fruit = new FRUIT_CLASS(length, this);
        if( first == NULL && last == NULL ) {
            first = new_fruit;
            last = first;
        }
        else {
            last->setNext(new_fruit);
            new_fruit->setPrev(last);
            last = new_fruit;
        }
    }
}

void BRANCH_CLASS::fadeBranch() { // add first != NULL or not?
    if( length > 0 ) {
        --length;

        for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
            curr->fadeFruit();
        }

        /*
            there is not need to make a loop, since only one fruit can be at the exact length.
            so just delete just object if its length is bigger, or?
            PREVIOUS VERSION DOWN        
        */
        if( last != NULL && last->getLength() > length ) {
            FRUIT_CLASS* tmp = last;
            if( last == first ) {
                first = NULL;
                last = NULL;
            }
            else {
                last->getPrev()->setNext(NULL);
                last = last->getPrev();
            }
            delete tmp;
            // --fruit_num;
        }

        
    }
}

void BRANCH_CLASS::harvestBranch(uInt _weight) {
    
    for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getWeight() >= _weight ) {
            curr->pluckFruit();
        }
    }
}

void BRANCH_CLASS::cutBranch(uInt _length) {
    length = _length;
    for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getLength() > _length ) {
            if( curr == first ) {
                fruit_num = 0;
                while( curr != NULL ) {
                    FRUIT_CLASS* tmp = curr->getNext();
                    delete curr;
                    curr = tmp;
                }
                first = NULL;
                last = NULL;
                break;
            }
            else if( curr == last ) {
                FRUIT_CLASS* new_last = curr->getPrev();
                new_last->setNext(NULL);
                delete curr;
                last = new_last;
            }
            else {
                FRUIT_CLASS* new_last = curr->getPrev();
                new_last->setNext(NULL);
                while( curr != NULL ) {
                    FRUIT_CLASS* tmp = curr->getNext();
                    delete curr;
                    curr = tmp;
                }
                last = new_last;
                break;
            }
        }
    }
}

FRUIT_CLASS* BRANCH_CLASS::getFruitPointer(uInt _length) {
    if( _length > 0 && _length%2 == 0 && _length <= length ) {
        for(FRUIT_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
            if( curr->getLength() == _length ) {
                return curr;
            }
        }
    }
    return NULL;
}

TREE_CLASS* BRANCH_CLASS::getTreePointer() {
    return daddy;
}

void BRANCH_CLASS::setdaddyHeight(uInt _height) {
    daddy_height = _height;
}

void BRANCH_CLASS::setTreePointer(TREE_CLASS* wood) {
    daddy = wood;
}

void BRANCH_CLASS::setPrev(BRANCH_CLASS* p) {
    prev = p;
}

void BRANCH_CLASS::setNext(BRANCH_CLASS* n) {
    next = n;
}
 
BRANCH_CLASS* BRANCH_CLASS::getPrev() {
    return prev;
}

BRANCH_CLASS* BRANCH_CLASS::getNext() {
    return next;
}


// ----------------  END BRANCH  ----------------




// ----------------  WOOD ----------------


TREE_CLASS::TREE_CLASS() : id(0), height(0), branch_num(0), first(NULL), last(NULL), daddy(NULL), next(NULL), prev(NULL), fruit_num(0), weights_total(0) {}
TREE_CLASS::TREE_CLASS(uInt _id, GARDEN_CLASS* p) : id(_id), height(0), branch_num(0), first(NULL), last(NULL), daddy(p), next(NULL), prev(NULL), fruit_num(0), weights_total(0) {
    if(daddy!=NULL)daddy->addTree();
}

TREE_CLASS::TREE_CLASS(const TREE_CLASS& parent) 
: height(parent.height), branch_num(0), next(NULL), prev(NULL), daddy(parent.daddy), weights_total(0), fruit_num(0) {
    if(daddy!=NULL)daddy->addTree();
    if( height > 0 ) {
        BRANCH_CLASS* new_branch;
        for(BRANCH_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            new_branch = new BRANCH_CLASS(*curr, curr->getHeight(), this);
            if( curr == parent.first ) {
                this->first = new_branch;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_branch);
                new_branch->setPrev(this->last);
                this->last = new_branch;
            }
        }
    }
}

TREE_CLASS::TREE_CLASS(const TREE_CLASS& parent, uInt _id) 
: id(_id), height(parent.height), branch_num(0), next(NULL), prev(NULL), daddy(parent.daddy), weights_total(0), fruit_num(0) {
    if(daddy!=NULL)daddy->addTree();
    if( height > 0 ) {
        BRANCH_CLASS* new_branch;
        for(BRANCH_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            new_branch = new BRANCH_CLASS(*curr, curr->getHeight(), this);
            if( curr == parent.first ) {
                this->first = new_branch;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_branch);
                new_branch->setPrev(this->last);
                this->last = new_branch;
            }
        }
    }
}

TREE_CLASS::TREE_CLASS(const TREE_CLASS& parent, uInt _id, GARDEN_CLASS* dad) 
: id(_id), height(parent.height), branch_num(0), next(NULL), prev(NULL), daddy(dad), weights_total(0), fruit_num(0) {
    if(daddy!=NULL)daddy->addTree();
    if( height > 0 ) {
        BRANCH_CLASS* new_branch;
        for(BRANCH_CLASS* curr = parent.first; curr != NULL; curr = curr->getNext()) {
            new_branch = new BRANCH_CLASS(*curr, curr->getHeight(), this);
            new_branch = new BRANCH_CLASS(*curr);


            if( curr == parent.first ) {
                this->first = new_branch;
                this->last = this->first;
            }
            else {
                this->last->setNext(new_branch);
                new_branch->setPrev(this->last);
                this->last = new_branch;
            }
        }
    }
}

TREE_CLASS::~TREE_CLASS() {
    id = 0;
    height = 0;
    if(daddy!=NULL)daddy->reduceTree();
    

    BRANCH_CLASS* tmp;
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        tmp = curr;
        delete curr;
        curr = tmp;
    }
    daddy = NULL;
    branch_num = 0;
    fruit_num = 0;
    weights_total = 0;
    first = NULL;
    last = NULL;
}

uInt TREE_CLASS::getBranchesTotal() {
    return branch_num;
}

uInt TREE_CLASS::getFruitsTotal() {
    return fruit_num;
}

uInt TREE_CLASS::getWeightsTotal() {
    return weights_total;
}

uInt TREE_CLASS::getNumber() {
    return id;
}

uInt TREE_CLASS::getHeight() {
    return height;
}

void TREE_CLASS::growthTree() {
    ++height;
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->growthBranch();
    }
    if( height%3 == 0 && height > 0 ) {
        BRANCH_CLASS* new_branch = new BRANCH_CLASS(height, this);
        if( first == NULL ) {
            first = new_branch;
            last = first;
        }
        else {
            last->setNext(new_branch);
            new_branch->setPrev(last);
            last = new_branch;
        }
    }
}

void TREE_CLASS::fadeTree() {
    if( height > 0 ) {
        --height;

        for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
            curr->fadeBranch();
        }

        if( last != NULL && last->getHeight() > height ) {
            BRANCH_CLASS* tmp = last;
            if( last == first ) {
                first = NULL;
                last = NULL;
            }
            else {
                last->getPrev()->setNext(NULL);
                last = last->getPrev();
            }
            delete tmp;
        }
    }
}

void TREE_CLASS::harvestTree(uInt _weight) {
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->harvestBranch(_weight);
    }
}

void TREE_CLASS::cutTree(uInt _height) {
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getHeight() > _height ) {
            if( curr == first ) {
                while( curr != NULL ) {
                    BRANCH_CLASS* tmp = curr->getNext();
                    delete curr;
                    curr = tmp;
                }
                first = NULL;
                last = NULL;
                break;
            }
            else if( curr == last ) {
                BRANCH_CLASS* new_last = last->getPrev();
                delete last;
                new_last->setNext(NULL);
                last = new_last;
            }
            else {
                BRANCH_CLASS* new_last = curr->getPrev();
                while( curr != NULL ) {
                    BRANCH_CLASS* tmp = curr->getNext();
                    delete curr;
                    curr = tmp;
                }
                new_last->setNext(NULL);
                last = new_last;
                break;
            }
        }
    }
    height = _height;
}

void TREE_CLASS::cloneBranch(BRANCH_CLASS* _branch) {
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getLength() == 0 ) {
            BRANCH_CLASS* new_branch = new BRANCH_CLASS(*_branch);
            new_branch->setDaddy(this);
            new_branch->setdaddyHeight(curr->getHeight());
            new_branch->getDaddy()->addBranch();

            if( curr == first && curr == last ) {
                first = new_branch;
                last = new_branch;
            }
            else if( curr == first ) {
                new_branch->setNext(curr->getNext());
                curr->getNext()->setPrev(new_branch);
                first = new_branch;
            }
            else if( curr == last ) {
                new_branch->setPrev(curr->getPrev());
                curr->getPrev()->setNext(new_branch); 
                last = new_branch;
            }
            else {
                curr->getPrev()->setNext(new_branch);
                curr->getNext()->setPrev(new_branch);
                new_branch->setPrev(curr->getPrev());
                new_branch->setNext(curr->getNext());
            }
            delete curr;
            break;
        }
    }
}

GARDEN_CLASS* TREE_CLASS::getGardenPointer() {
    return daddy;
}

BRANCH_CLASS* TREE_CLASS::getBranchPointer(uInt _height) {
    for(BRANCH_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getHeight() == _height ) {
            return curr;
        }
    }
    return NULL;
}

void TREE_CLASS::setPrev(TREE_CLASS* p) {
    prev = p;
}

void TREE_CLASS::setNext(TREE_CLASS* n) {
    next = n;
}

TREE_CLASS* TREE_CLASS::getPrev() {
    return prev;
}

TREE_CLASS* TREE_CLASS::getNext() {
    return next;
}

// ----------------  END WOOD  ----------------




// ----------------  GARDEN ----------------


GARDEN_CLASS::GARDEN_CLASS() : tree_num(0), gaps_num(0), first(NULL), last(NULL), branch_num(0), fruit_num(0), weights_total(0) {}
GARDEN_CLASS::~GARDEN_CLASS() {
    
    TREE_CLASS* tmp;
    for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        tmp = curr;
        delete curr;
        curr = tmp;
    }
    tree_num = 0;
    gaps_num = 0;
    branch_num = 0;
    fruit_num = 0;
    weights_total = 0;

    first = NULL;
    last = NULL;
}

uInt GARDEN_CLASS::getTreesTotal() {
    return tree_num;
}

uInt GARDEN_CLASS::getBranchesTotal() {
    return branch_num;
}

uInt GARDEN_CLASS::getFruitsTotal() {
    return fruit_num;
}

uInt GARDEN_CLASS::getWeightsTotal() {
    return weights_total;
}

void GARDEN_CLASS::plantTree() {
    uInt tmp_id = 0;
    TREE_CLASS* new_tree;

    if( gaps_num != 0 ) {
        --gaps_num;
        for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
            if( curr->getNumber() == tmp_id ) {
                ++tmp_id;
            }
            else {
                new_tree = new TREE_CLASS(tmp_id, this);
                if( tmp_id == 0 ) {
                    if( first == NULL ) {
                        first = new_tree;
                        last = first;
                    }
                    else {
                        new_tree->setNext(first);
                        first->setPrev(new_tree);
                        first = new_tree;
                    }
                }
                else {
                    curr->getPrev()->setNext(new_tree);
                    new_tree->setPrev(curr->getPrev());
                    new_tree->setNext(curr);
                    curr->setPrev(new_tree);
                }
                break;
            }
        }
    }
    else { // no gaps, add wood to the last
        if( first == NULL ) {
            new_tree = new TREE_CLASS(0, this);
            first = new_tree;
            last = first;
        }
        else {
            new_tree = new TREE_CLASS(last->getNumber()+1, this);
            last->setNext(new_tree);
            new_tree->setPrev(last);
            last = new_tree;
        }
    }
}

void GARDEN_CLASS::extractTree(uInt _id) {
    if( tree_num > 0 ) {
        TREE_CLASS* tmp_wood;

        if( first->getNumber() == _id && last->getNumber() == _id ) {
            gaps_num = 0;
            delete first;
            first = NULL;
            last = NULL;
        }
        else if( first->getNumber() == _id ) {
            ++gaps_num;
            tmp_wood = first->getNext();
            delete first;
            first = tmp_wood;
        }
        else if( last->getNumber() == _id ) {
            tmp_wood = last->getPrev();
            delete last;
            last = tmp_wood;
        }
        else{
            for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()){
                if( curr->getNumber() == _id ) {
                    ++gaps_num;
                    // --tree_num;
                    curr->getPrev()->setNext(curr->getNext());
                    curr->getNext()->setPrev(curr->getPrev());
                    delete curr;
                    break;
                }
            }
        }
    }
}

void GARDEN_CLASS::growthGarden() {
    for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->growthTree();
    }
}

void GARDEN_CLASS::fadeGarden() {
    for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->fadeTree();
    }
}

void GARDEN_CLASS::harvestGarden(uInt _weight) {
    for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        curr->harvestTree(_weight);
    }
}

TREE_CLASS* GARDEN_CLASS::getTreePointer(uInt _id) {
    for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
        if( curr->getNumber() == _id ) {
            return curr;
        }
    }
    return NULL;
}

void GARDEN_CLASS::cloneTree(uInt _id) {
    TREE_CLASS* parent = this->getTreePointer(_id);
    if( parent != NULL ) {
        TREE_CLASS* clone;
        uInt tmp_id = 0;

        if( gaps_num != 0 ) {
            --gaps_num;
            for(TREE_CLASS* curr = first; curr != NULL; curr = curr->getNext()) {
                if( curr->getNumber() == tmp_id ) {
                    ++tmp_id;
                }
                else {
                    // clone = new TREE_CLASS(*parent, tmp_id, this);
                    clone = new TREE_CLASS(*parent);
                    clone->setId(tmp_id);
                    clone->setDaddy(this);
                    
                    // ++tree_num;
                    if( tmp_id == 0 ) {
                        if( first == NULL ) {
                            first = clone;
                            last = first;
                        }
                        else {
                            clone->setNext(first);
                            first->setPrev(clone);
                            first = clone;
                        }
                    }
                    else {
                        curr->getPrev()->setNext(clone);
                        clone->setPrev(curr->getPrev());
                        clone->setNext(curr);
                        curr->setPrev(clone);
                    }
                    break;
                }
            }
        }
        else { // no gaps, add wood to the last
            // ++tree_num;
            if( first == NULL ) {
                // clone = new TREE_CLASS(*parent, 0, this);
                clone = new TREE_CLASS(*parent);
                clone->setId(0);
                clone->setDaddy(this);
                first = clone;
                last = first;
            }
            else {
                // clone = new TREE_CLASS(*parent, last->getNumber()+1, this);
                clone = new TREE_CLASS(*parent);
                clone->setId(last->getNumber()+1);
                clone->setDaddy(this);
                last->setNext(clone);
                clone->setPrev(last);
                last = clone;
            }
        }
    }
}

// ----------------  END GARDEN  ----------------

// BRANCH
void BRANCH_CLASS::addWeight(uInt _weight) {
    weights_total += _weight;
    if(daddy!=NULL)daddy->addWeight(_weight);
}

void BRANCH_CLASS::reduceWeight(uInt _weight) {
    if( weights_total > 0 ) weights_total -= _weight;
    if(daddy!=NULL)daddy->reduceWeight(_weight);
}

void BRANCH_CLASS::addFruit() {
    fruit_num += 1;
    if(daddy!=NULL)daddy->addFruit(1);
}

void BRANCH_CLASS::reduceFruit() {
    if( fruit_num > 0 ) fruit_num -= 1;
    if(daddy!=NULL)daddy->reduceFruit(1);
}

// TREE
void TREE_CLASS::addWeight(uInt _weight) {
    weights_total += _weight;
    if(daddy!=NULL)daddy->addWeight(_weight);
}
void TREE_CLASS::reduceWeight(uInt _weight) {
    if( weights_total > 0 ) weights_total -= _weight;
    if(daddy!=NULL)daddy->reduceWeight(_weight);
}
void TREE_CLASS::addFruit(uInt _num) {
    fruit_num += _num;
    if(daddy!=NULL)daddy->addFruit(_num);
}
void TREE_CLASS::reduceFruit(uInt _num) {
    if( fruit_num > 0 ) fruit_num -= _num;
    if(daddy!=NULL)daddy->reduceFruit(_num);
}
void TREE_CLASS::addBranch() {
    branch_num += 1;
    if(daddy!=NULL)daddy->addBranch();
}
void TREE_CLASS::reduceBranch() { 
    if( branch_num > 0 ) branch_num -= 1;
    if(daddy!=NULL)daddy->reduceBranch();
}

// GARDEN
void GARDEN_CLASS::addWeight(uInt _weight) {
    weights_total += _weight;
}
void GARDEN_CLASS::reduceWeight(uInt _weight) {
    if( weights_total > 0 ) weights_total -= _weight;
}
void GARDEN_CLASS::addFruit(uInt _num) {
    fruit_num += _num;
}
void GARDEN_CLASS::reduceFruit(uInt _num) { 
    if( fruit_num > 0 ) fruit_num -= _num;
}
void GARDEN_CLASS::addBranch() {
    branch_num += 1;
}
void GARDEN_CLASS::reduceBranch() { 
    if( branch_num > 0 ) branch_num -= 1;
}
void GARDEN_CLASS::addTree() {
    tree_num += 1;
}
void GARDEN_CLASS::reduceTree() { 
    if( tree_num > 0 ) tree_num -= 1;
}

void TREE_CLASS::displayAll() {
    std::cout << ">>fruit_count:" << getFruitsTotal() << " ";
    std::cout << "branch_count:" << getBranchesTotal() << " ";
    std::cout << "weights:" << getWeightsTotal() << std::endl;
    int max = getBranchesTotal(), i = 3;
    if (max>0) std::cout << ">>branches:\n";
    while (i <= 3*max){
        BRANCH_CLASS * branch = getBranchPointer(i);
        if (branch != NULL) {
            std:: cout << "-----branch at " << i << std::endl;
            branch->displayAll();}
        i+=3;
    }
}

void GARDEN_CLASS::displayAll() {
        std::cout << "GARDEN:" << std::endl;
        std::cout << "fruit_count:" << getFruitsTotal() << " ";
        std::cout << "tree_count:" << getTreesTotal() << " ";
        std::cout << "branch_count:" << getBranchesTotal() << " ";
        std::cout << "weights:" << getWeightsTotal() << std::endl;
        std::cout << "trees: \n";
        int max = getTreesTotal(), i = 0;
        while (i <= max){
            TREE_CLASS * wood = getTreePointer(i);
            if (wood != NULL) {
                std:: cout << "tree nr " << i << std::endl;
                wood->displayAll();
                std::cout << std::endl;
            }
            i++;
        }
        std::cout << std::endl;
        std::cout << "/ / / / / / / /";
        std::cout << std::endl<< std::endl;
    }
