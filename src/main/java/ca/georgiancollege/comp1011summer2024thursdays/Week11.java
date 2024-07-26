package ca.georgiancollege.comp1011summer2024thursdays;

import javax.swing.text.GapContent;

public class Week11 {

    static void example1(){

        APIUtility apiUtility = new APIUtility();
        String json = apiUtility.sendRequest("https://dog.ceo/api/breeds/image/random");
        System.out.println(json);
    }

    static void example2(){
        DogCeoAPI api = new DogCeoAPI();
        Dog dog = api.getRandomImage();
        System.out.println(dog.getMessage());
        System.out.println(dog.getStatus());
    }
    static void example3(){
        DogCeoAPI api = new DogCeoAPI();
        DogSubBreeds subBreeds = api.getAllSubbreeds();
        System.out.println(subBreeds.getStatus());
        subBreeds.getMessage().forEach(System.out::println);
    }
    static void example4(){
    DogCeoAPI api = new DogCeoAPI();
    DogAllBreeds allBreeds = api.getAllBreeds();
        System.out.println(allBreeds.getMessage().bulldog.get(1));
        System.out.println(allBreeds.getMessage().bulldog.get(2));

    }
    public static void main(String[] args) {
        example4();
    }
}
