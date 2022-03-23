{% include navigation.html %}

## Data Structures Work

### TT1 Data Structures (3/21/2022)

[Repl for challenge](https://replit.com/@PaulBokelman/Challenge-TT1#LinkedLists.java)

**Linked Lists**

- a Linked list is a type of dynamic array. The list increases if we add/remove items. Elements are not stored continuously, there is no need to increase the size. LinkedList is implemented using the doubly linked list data structure. Between a normal linked list and a doubly LinkedList is that a doubly linked list contains an extra pointer, typically called the previous pointer, together with the next pointer and data which are there in the singly linked list.

**Java Generics**

- Java Generics are a mechanism that allows us to write code that can work with multiple types of objects without having to write separate code for each type.

**Queue Add and Delete**

- The Queue interface in the java.util package extends the interface o Collection is used to hold elements processed in FIFO(First In First Out) order. It is an ordered list of objects with its use limited to insert elements at the end of the list and deleting elements from the start of the list, (i.e.), it follows the FIFO or the First-In-First-Out principle.

- `remove()` of Interface (queue) returns and removes the element in the container at the front. It deletes the head of the container. The method throws an NoSuchElementException when the Queue is empty.

**Merge Queues**

- In order to merge 2 queues there is a list of steps you must go through:

1. Make pointers for both of the queues.
1. Make another queue, add the node having least value from the other two queues(the pointer's node having the smallest value) to it and move that pointer to the next node.
1. Again compare the node's values and add the node having the smallest value to the queue and move that pointer to the next node.
1. Keep doing this until rear of both the queues are reached. bear

**Reversing a Queue**

- Reversing the queue is simply reinserting the data in an inverse order. So now our task is to choose such data-structure which can serve the purpose. According to the approach, the data-structure should have the property of ‘LIFO’ as the last element to be inserted in the data structure should actually be the first element of the reversed queue. The stack could help in approaching this problem. This will be a two-step process:

1. Pop the elements from the queue and insert into the stack. (Topmost element of the stack is the last element of the queue)
1. Pop the elements of the stack to insert back into the queue. (The last element is the first one to be inserted into the queue) duck
   today

**Build Stack**

- There is a multitude of different functions one can perform with a stack, and with these are pretty much endless possibilities as to what you can do with these functions:

1. push (to add a new item to the top)
1. pop (to remove the most top item)
1. peek (to get the most top item)
1. isEmpty (to check whether the stack is empty)
1. size (to get the size of the stack)
1. search (to search for objects)
1. Here's an example of some functions above at work: deer
