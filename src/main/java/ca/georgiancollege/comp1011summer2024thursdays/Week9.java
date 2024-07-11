package ca.georgiancollege.comp1011summer2024thursdays;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
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


        public static void main(String[] args) {
            example6();
    }
}
