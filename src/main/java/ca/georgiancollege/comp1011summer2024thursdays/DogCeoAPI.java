package ca.georgiancollege.comp1011summer2024thursdays;

import com.google.gson.Gson;

public class DogCeoAPI extends APIUtility{

    public Dog getRandomImage(){

        String uri = "https://dog.ceo/api/breeds/image/random";

        Gson gson = new Gson();
        String json = sendRequest(uri);

        Dog dog = gson.fromJson(json, Dog.class);

        return dog;
    }

}
