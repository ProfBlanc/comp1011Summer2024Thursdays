package ca.georgiancollege.comp1011summer2024thursdays;

import com.google.gson.Gson;

public class DogCeoAPI extends APIUtility{

    private String uri, json;
    private Gson gson = new Gson();
    public Dog getRandomImage(){

        uri = "https://dog.ceo/api/breeds/image/random";
        json = sendRequest(uri);
        Dog dog = gson.fromJson(json, Dog.class);

        return dog;
    }

    //create a method that fetches all dog subbreeds
    public DogSubBreeds getAllSubbreeds(){
        uri = "https://dog.ceo/api/breed/hound/list";
        return gson.fromJson(sendRequest(uri), DogSubBreeds.class);
    }

    //in Week11, output the result of the api call to get all sub-breeds

    public DogAllBreeds getAllBreeds(){
        uri = "https://dog.ceo/api/breeds/list/all";
        return gson.fromJson(sendRequest(uri), DogAllBreeds.class);
    }

}




