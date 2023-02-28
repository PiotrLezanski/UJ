//Piotr Lezanski
#include <iostream>

int SIZE = 69;

typedef unsigned char BYTE;

struct OBJECT_TYPE {
    int a;
};

struct NODE_STRUCT {
    OBJECT_TYPE* object;
    BYTE use;
    NODE_STRUCT* next;
};

NODE_STRUCT* NewNode() {
    NODE_STRUCT* new_node = new NODE_STRUCT;
    new_node->object = new OBJECT_TYPE[SIZE];
    new_node->use = 0;
    new_node->next = NULL;
    return new_node;
}

void DeleteNode(NODE_STRUCT* delete_node) {
    delete [] delete_node->object;
    delete [] delete_node; // ??
    free(delete_node);
}

void Clear(NODE_STRUCT** node_list) {
    NODE_STRUCT* node;
    node = *node_list;

    while(node != NULL) { // or node->next != NULL? - dc
        NODE_STRUCT* next_node;
        next_node = node;

        DeleteNode(node);
        node = next_node->next;
    }
    node = NULL;
}


// PROPABLY TO CHANGE -- need to check all nodes and in case every is full, add new node at the beginning
// "(**node_list).use" is for sure right, but node should be the same. If problems, try changing it
void AddFirst(NODE_STRUCT** node_list, OBJECT_TYPE* add_object) { 
    NODE_STRUCT *node;
    node = *node_list; 

    if( node != NULL ) { 
        // the list is not NULL        
        if( node->use >= SIZE ) {
            // node is full
            NODE_STRUCT* new_node = NewNode();
            *new_node->object = *add_object;
            new_node->use = 1;
            new_node->next = node;

            // move head of the list to point to the new_node
            *node_list = new_node;
        }
        else {
            // there is a place for the new object
            for(int i=node->use; i>0; i--) {
                *((node->object) + i) = *((node->object) + i - 1);
            }
            *(node->object) = *(add_object);
            node->use++;
        } 
    }
    else {
        // ** points to NULL -- should I even do this, when there is no node?
        NODE_STRUCT* new_node = NewNode();
        *(new_node->object) = *add_object;
        new_node->use = 1;
        new_node->next = NULL;

        // move head of the list to point to the new_node
        *(node_list) = new_node;
    }
}

void AddLast(NODE_STRUCT** node_list, OBJECT_TYPE* add_object) {
    NODE_STRUCT *node;
    node = *node_list; 
    
    if( node != NULL ) {
        // the list is not NULL
        NODE_STRUCT* last = node;
        while( last->next != NULL ) {
            last = last->next;
        }

        if( node->use >= SIZE ) {
            // node is full
            NODE_STRUCT* new_node = NewNode();
            last->next = new_node; // last node points to the new node
            *(new_node->object) = *add_object;
            new_node->use = 1;
            new_node->next = NULL;
        }
        else {
            // there is a place for the new object
            *(last->object + last->use) = *add_object;
            node->use++;
        } 
    }
    else {
        // ** points to NULL -- should I even do this, when there is no node?
        NODE_STRUCT* new_node = NewNode();
        *(new_node->object) = *add_object;
        new_node->use = 1;
        new_node->next = NULL;
    }
}

// 
// IF PROBLEMS, MAYBE THE LIST OF NODES IS PASSED, WHERE EVERY NODE IS EMPTY
// OR NEXT/PREVIOUS NODE IS EMPTY, SO THERE SHOULD BE NULL NOT FIRST/LAST INDEX
//
void GetFirst(NODE_STRUCT* node_list, NODE_STRUCT** node_object, BYTE* index) {
    if( node_list != NULL && node_list->use > 0 ) {
        *node_object = node_list; 
        *index = 0;
    }
    else 
        *node_object = NULL;
}

void GetPrev(NODE_STRUCT* node_list, NODE_STRUCT* object_node, BYTE index, NODE_STRUCT** prev_object_node, BYTE* prev_index) {
    if( node_list != NULL && (object_node != node_list || index != 0) ) {
        if( index == 0 ) { 
            // need to take last object from the previous node
            *prev_object_node = node_list;
            while( (*prev_object_node)->next != object_node ) {
                *prev_object_node = (*prev_object_node)->next;
            }
            *prev_index = (*prev_object_node)->use - 1;
        }
        else {
            // just previous object 
            *prev_object_node = object_node;
            *prev_index = index - 1;
        }
    }
    else 
        *prev_object_node = NULL;
}

void GetNext(NODE_STRUCT* node_list, NODE_STRUCT* object_node, BYTE index, NODE_STRUCT** next_object_node, BYTE* next_index) {
    if( node_list != NULL && (object_node->next != NULL || index != object_node->use - 1)) {
        if( index == object_node->use - 1 ) { 
            // need to take first object from the next node
            *next_object_node = object_node->next;
            *next_index = 0;
        }
        else {
            // just next object 
            *next_object_node = object_node;
            *next_index = index + 1;
        }
    }
    else 
        *next_object_node = NULL;
}

void GetLast(NODE_STRUCT* node_list, NODE_STRUCT** node_object, BYTE* index) {
    // need to check, whether there is at least one object in the node list
    bool is_object = false;
    NODE_STRUCT* node = node_list;
    while( node != NULL && !is_object ) {
        if( node->use > 0 ) {
            is_object = true;
        }
        node = node->next;
    }
    
    if( node_list != NULL && node_list->use > 0 ) {
        *node_object = node_list;
        while( (*node_object)->next != NULL ) {
            *node_object = (*node_object)->next;
        }
        *index = (*node_object)->use - 1;
    }
    else 
        *node_object = NULL;
}



void InsertPrev(NODE_STRUCT* node_list, NODE_STRUCT** node_object, BYTE* index) {

}

void InsertNext(NODE_STRUCT* node, NODE_STRUCT* node_object, BYTE index, OBJECT_TYPE* add_object) {
    if(node_object->use == SIZE) {
        // need to add new node after node_object
        NODE_STRUCT* new_node;
        new_node->use = 1;
        new_node->next = node_object->next;
        node_object->next = new_node;
        *new_node->object = *(node_object->object + SIZE - 1);

        for(int i=SIZE - 1; i>index; i--) {
            *((node->object) + i) = *((node->object) + i - 1);
        }
        *((node_object->object) + index + 1) = *add_object;
    }
    else {
        // possible to add a new object
        for(int i=node_object->use; i>index; i--) {
            *((node_object->object) + i) = *((node_object->object) + i - 1);
        }
        *((node_object->object) + index + 1) = *add_object;
    }
}

void RemoveFirst(NODE_STRUCT** node_list) {
    if( (*node_list) != NULL ) {
        (*node_list)->use--;
        
        if( (*node_list)->use == 0 ) {
            // list is empty after decrementation
            DeleteNode(*node_list); // if problems, try deleting this
            // move head to the next node
            *node_list = (*node_list)->next;
        }
        else {
            // list is not empty after decrementation
            for(int i=0; i<(*node_list)->use; i++) {
               *((*node_list)->object + i) = *((*node_list)->object + i + 1);
            }
        }                
    }
}

void RemovePrev(NODE_STRUCT** node_list, NODE_STRUCT* node_object, BYTE index) {
    if( (*node_list) != NULL ) {
        // do nothing when there is no previous element
        if( index != 0 || node_object != *node_list ) {
            if( index == 0 ) {  
                // need to take last element from the previous node
                NODE_STRUCT* previous;
                previous = *node_list;
                while( previous->next != node_object ) {
                    previous = previous->next;
                }
                // deleting last element from previous
                previous->use--;

                if( previous->use == 0 ) {
                    if( previous == *node_list ) {
                        // the head neads to be changed
                        *node_list = previous->next;
                    }
                    else {
                        // just delete node in the middle
                        NODE_STRUCT* pre_previous;
                        pre_previous = *node_list;
                        while( pre_previous->next != previous ) {
                            pre_previous = pre_previous->next;
                        }
                        pre_previous->next = previous->next;
                    }
                    DeleteNode(previous);
                }
            }
            else {
                // index != 0
                node_object->use--;
                for(int i=index-1; i<node_object->use; i++) {
                    *((node_object->object) + i) = *((node_object->object) + i + 1);
                }
            }
        }
    }
}

void RemoveCurrent(NODE_STRUCT** node_list, NODE_STRUCT* node_object, BYTE index) {
    if( (*node_list) != NULL ) {
        node_object->use--;

        if( node_object->use == 0 ) {
            // node is empty after decrementation
            if( node_object == *node_list ) {
                // deleting first node (change head)
                *node_list = node_object->next;
            }
            else if ( node_object->next == NULL ) {
                // deleting last node (node_object point to the last node)
                NODE_STRUCT* previous;
                previous = *node_list;
                while( previous->next != node_object ) {
                    previous = previous->next;
                }
                previous->next = NULL;
            }
            else {
                // deleting node in the middle
                NODE_STRUCT* previous; 
                previous = *node_list;
                while( previous->next != node_object ) {
                    previous = previous->next;
                }
                previous->next = node_object->next; 
            }

            DeleteNode(node_object);
        }
        else {
            // node is not empty after decrementation
            for(int i=index; i<node_object->use; i++) {
                *(node_object + i) = *(node_object + i + 1);
            }
        }
    }
}

void RemoveNext(NODE_STRUCT** node_list, NODE_STRUCT* node_object, BYTE index) {
    if( *(node_list) != NULL ) {
        // when there is no next object, do nothing
        if( node_object->next != NULL || index != (node_object->use - 1) ) {
            if( index == (node_object->use - 1) ) {
                // need to take first element from the next node
                NODE_STRUCT* next;
                next = node_object->next;

                next->use--;
                if( next->use == 0 ) {
                    node_object->next = next->next;
                    DeleteNode(next);
                }
                else {
                    for(int i=0; i<next->use; i++) {
                        *(next->object + i) = *(next->object + i + 1);
                    }
                }
            }
            else {
                // lust delete next object in the 'node_object'
                for(int i=index+1; i<(node_object->use); i++) {
                    *(node_object->object + i) = *(node_object->object + i + 1);
                }
            }
        }
    }
        
}

void RemoveLast(NODE_STRUCT** node_list) {
    if( *(node_list) != NULL ) {
        NODE_STRUCT* last;
        last = *node_list;
        while( last->next != NULL ) {
            last = last->next;
        }

        last->use--; // deleting last object
        if( last->use == 0 ) {
            // deleting last node
            NODE_STRUCT* pre_last;
            pre_last = *node_list;
            while( pre_last->next != last ) {
                pre_last = pre_last->next;
            }
            pre_last->next = NULL; // 'pre_last' is actual last node (deleting 'last')

            DeleteNode(last);
        }
    }
}

void Find(NODE_STRUCT* node_list, OBJECT_TYPE* search_object, NODE_STRUCT** node_object, BYTE* index) {
    bool found = false;
    while( node_list != NULL && !found ) {
        for(int i=0; i<node_list->use; i++) {
            if( *(node_list->object + i) == *search_object ) { // if problems, delete '*'
                found = true;
                *index = i;
                *node_object = node_list;
            }
        }

        node_list = node_list->next;
    }  

    if( !found ) {
        *node_object = NULL;
    }
}

void Compress(NODE_STRUCT* node_list) {
    NODE_STRUCT* next;
    next = node_list->next;
    while( node_list != NULL && (node_list->use != 0 || (node_list->next->use != 0 && node_list->next != NULL))) {
        int i=node_list->use;
        while( i < SIZE ) {
            if( next->use > 0 ) {
                *(node_list->object + i) = *next->object;
                node_list->use++;
                
                // shift
                for(int j=0; j<next->use; j++) {
                    *(next->object + j) = *(next->object + j + 1);
                }
                next->use--;
            }
            else {
                while( next->use == 0 && next != NULL) {
                    next = next->next;
                }
                if( next == NULL ) break;

                *(node_list->object + i) = *next->object;
                node_list->use++;

                // shift
                for(int j=0; j<next->use; j++) {
                    *(next->object + j) = *(next->object + j + 1);
                }
                next->use--;
            }
        }
        node_list = node_list->next;
        next = node_list->next;
    }
}


void Reverse(NODE_STRUCT* node_list) {
    NODE_STRUCT* head;
    head = node_list;

    // reverse arrays of objects
    while( head != NULL ) {
        for(int i=0, j=head->use - 1; i<(head->use / 2); i++, j--) {
            OBJECT_TYPE* temp;
            *temp = *(head->object + i);
            *(head->object + i) = *(head->object + j);
            *(head->object + j) = *temp;
        }
        head = head->next;
    }

    NODE_STRUCT* prev;
    NODE_STRUCT* current;
    NODE_STRUCT* next;
    current = node_list;

    prev = NULL;
    while( current != NULL ) {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    // change head to the last node
    node_list = prev;

    Compress(node_list);
}

int main()
{

    return 0;
}

void Compress ( NODE_STRUCT* list )
{
    if ( list == NULL )
    {
        return;
    }
    else
    {
        NODE_STRUCT* helper = NewNode();
        NODE_STRUCT* pointer = list;
        while ( pointer != NULL )
        {
            while ( pointer->use != 0 )
            {
                for ( int i = 0; i < pointer->use; i++ )
                {
                    if ( helper->use == SIZE )
                    {
                        NODE_STRUCT* added = NewNode();
                        helper->next = added;
                        helper = helper->next;
                        *( helper->object ) = *( pointer->object + i );
                        helper->use = 1;
                    }
                    else 
                    {
                        *( helper->object + helper->use ) = *( pointer->object + i );
                        helper->use++;
                    }
                }
                pointer->use = 0;
            }
            pointer = pointer->next;
        }

        NODE_STRUCT* temp = list;
        while ( helper != NULL )
        {
            while ( helper->use != 0 )
            {
                for ( int i = 0; i < helper->use; i++ )
                {
                    if ( temp->use == SIZE )
                    {
                        NODE_STRUCT* node = NewNode();
                        temp->next = node;
                        temp = temp->next;
                        *( temp->object ) = *( helper->object + i );
                        temp->use = 1;
                    }
                    else
                    {
                        *( temp->object + temp->use ) = *( helper->object + i );
                        temp->use++;
                    }
                }
                temp->use = helper->use;
            }
            helper = helper->next;
        }
        
        //usuwanie niepotrzebnych node'ow
        NODE_STRUCT* toDelete = list;
        while (true)
        {
            while ( toDelete->next != NULL ) 
            {
                while ( toDelete->use != 0 ) 
                {
                    NODE_STRUCT* beforeToDelete = list;
                    while ( beforeToDelete->next != toDelete )
                    {
                        beforeToDelete = beforeToDelete->next;
                    }
                    DeleteNode(toDelete);
                    beforeToDelete->next = NULL;
                    if ( beforeToDelete->use != 0 )
                    {
                        break;
                    }
                }
                toDelete = toDelete->next;
            }
        } 
        //czyszczenie pomocniczej
        while ( helper != NULL )
        {
            if ( helper->next == NULL )
            {
                DeleteNode(helper);
            }
            while ( helper->next != NULL )
            {
                NODE_STRUCT* next;
                next = helper->next;
                DeleteNode(helper);
                helper = next;
            }
            helper = NULL;
        }  
        
    }
}

