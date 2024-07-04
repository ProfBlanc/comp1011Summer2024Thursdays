package ca.georgiancollege.comp1011summer2024thursdays;

import java.util.*;

public class Week8 {

    static void example1(){
        /*

            Interface: entity role list of requirements

            Collections: grandfather interface

List            Queue       Set         Map

List        ordered series. order = indexes. no restriction on values (except same data type)
Queue       ordered series. order = value (natural). no indexes. FIFO
Stack*       ordered by LIFO ordering
Sets        unique values. can or cannot be ordered by values. no indexes
Map         key-value pairs of data. associative array. dictionaries
         */

/*
can an interface be instantiated? No
Each interface has an object

 *///class-wrapper

Integer i = 123;
//Ref       //Prim
//boxing
int i2 = i;

//Prim    //Ref
        //unboxing
//Objects of the List interface
Stack<Integer> stack = new Stack<>();
ArrayList<Integer> arrayList = new ArrayList<>();
LinkedList<Integer> linkedList = new LinkedList<>();

arrayList.add(1234);  //boxing
        arrayList.add(-100);
        arrayList.add(50);
        arrayList.add(-50);

int value = arrayList.get(0); //unboxing
Integer i3 = arrayList.get(0);
Iterator<Integer> iterator = arrayList.iterator();
iterator.hasNext(); //is there a next value from point of cursor
iterator.next();     //returns values of next element
/*
        1234        -100        50          -50
     ^
                ^

 */
stack.listIterator();
ListIterator<Integer> listIterator = arrayList.listIterator();
linkedList.descendingIterator();
linkedList.listIterator();
/*
    uniliteral iterator
*/
        listIterator.next();
        listIterator.hasNext();
        listIterator.next();
        listIterator.hasPrevious();
        listIterator.previous();


//Queue
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
priorityQueue.iterator();

priorityQueue.add(100);
priorityQueue.add(-100);
priorityQueue.add(50);
priorityQueue.add(-50);
        System.out.println(priorityQueue.size());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.size());
        System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue.size());

        priorityQueue.offer(123); //exception-free adding method

    while(priorityQueue.size() > 0){
        System.out.println(priorityQueue.poll());
    }
        System.out.println(priorityQueue.size()); //
//Set
TreeSet<Integer> treeSet = new TreeSet<>();
HashSet<Integer> hashSet = new HashSet<>();
treeSet.iterator();
hashSet.iterator();
//Map
TreeMap<String, Double> treeMap = new TreeMap<>();
HashMap<String, Double> hashMap = new HashMap<>();

    }
    public static void main(String[] args) {
        example1();
    }
}
