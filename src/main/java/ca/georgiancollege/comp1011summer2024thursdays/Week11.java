package ca.georgiancollege.comp1011summer2024thursdays;

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
    public static void main(String[] args) {
        example2();
    }
}
