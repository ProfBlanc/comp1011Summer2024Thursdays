package ca.georgiancollege.comp1011summer2024thursdays;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Week9 {

    static void example1(){


        IntStream is1 = IntStream.range(1, 11);
        IntStream is2 = IntStream.rangeClosed(1, 10);

        is1.forEach(System.out::println);

        IntStream.rangeClosed(1, 10).forEach(System.out::println);
        int result = is2.min().orElse(0);

        //System.out.println(is2.max().getAsInt());

        System.out.println(result);

    }

    static void example2(){

       Stream<Object> s1 =  Stream.of(1, 1.1, true, "hello", 2f);
        Stream<Integer> s2 = Stream.of(1, 11, 100, -100);
    }

    static void example3() {

        Stream<Object> s1 = Stream.builder().add(123).add("hello").add(false).build();

    }

    static void example4() {

        ArrayList<String> names = new ArrayList<>();
        names.add("Ben");
        names.add("John");
        names.add("Christopher");
        names.add("Jennifer");
        names.add("Mary");

        names.stream().forEach(s-> System.out.println(s));

    }
    static void example5(){
        Random random = new Random();
        SecureRandom secureRandom = new SecureRandom();

        random.ints(5, 1, 101)
                .distinct()
                .forEach(System.out::println);

        secureRandom.doubles(10, 0.1, 0.50);
    }

    static void example6(){

        IntStream.rangeClosed(1, 10)
                .filter(v -> v % 2 == 1)
                .forEach(System.out::println);

        System.out.println("*".repeat(20));

        IntStream.rangeClosed(3, 30)
                .filter(anyletter -> anyletter % 3 == 0)
                .forEach(System.out::println);

        System.out.println("*".repeat(20));

        IntStream.rangeClosed(1, 10)
                .map(anyletter -> anyletter * 3)
                .forEach(System.out::println);

        System.out.println("*".repeat(20));

        ArrayList<String> names = new ArrayList<>();
        names.add("Ben");
        names.add("John");
        names.add("Christopher");
        names.add("Jennifer");
        names.add("Mary");
        names.add("Edward");

        //output all names that are longer than 5 characters and contain and e
        names.stream()
                .map(v -> v.toLowerCase())
                .filter(v -> v.contains("e") && v.length() >= 5)
                .forEach(System.out::println);


        for(String name : names){
            if(name.length() >= 5 && name.toLowerCase().contains("e")){
                System.out.println(name);
            }
        }

    }

    static void funTask(){

        ArrayList<String> names = new ArrayList<>();
        names.add("Ben");
        names.add("John");
        names.add("Christopher");
        names.add("Jennifer");
        names.add("Mary");
        names.add("Edward");
        names.add("Heather");
        names.add("Pauline");
        names.add("Daniel");
        names.add("James");

        Random random = new Random();

        int[] ages = random.ints(100, 18, 66).toArray();
        double[] grades = random.doubles(100, 40, 96).toArray();

        LinkedList<Student> students = new LinkedList<>();

        for(int i = 0; i < 100; i++){
            students.add(new Student(
                    names.get(random.nextInt(names.size())),
                    ages[i],
                    grades[i]
            ));
        }

        System.out.println("Output all student names with letter a and at least 4 character");
        students.stream()
                .map(s->s.getName().toLowerCase())
                .filter(s->s.contains("a") && s.length() >= 4)
                .forEach(System.out::println);
        System.out.println("Output all Mature Students (age 30+)");
        students.stream()
                .filter(s-> s.getAge() >= 30)
                .forEach(System.out::println);

        System.out.println("Output all HonorRoll students");
        students.stream()
                .filter(s-> s.getGrade() >= 80)
                .forEach(System.out::println);

        System.out.println("Output all Teenage students that are failing");
        List<Student> saved = students.stream()
                .filter(s-> s.getGrade() < 50 && s.getAge()  < 20)
                .collect(Collectors.toList());
                //                .toList();
//                .forEach(System.out::println);


    }


    static void example7(){

        System.out.println("Starting thread " + Thread.currentThread().getName());
        Thread t1 = new Thread();//Thread-N, Thread-0
        t1.start();

        Thread t2 = new Thread("My thread");
        Thread t3 = new Thread(); // Thread-1
        t3.start();

        Runnable task = () -> {
            System.out.println("Hello from Thread " + Thread.currentThread().getName());
            //Integer.parseInt("abc");
            System.out.println("STATE = " + Thread.currentThread().getState());
        };

        Thread t4 = new Thread(task);
        t4.start();

        System.out.println("Ending thread " + Thread.currentThread().getName());

        task.run();

    }
        public static void main(String[] args) {
            example7();
            //funTask();
    }
}
