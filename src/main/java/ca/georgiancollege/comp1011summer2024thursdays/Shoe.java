package ca.georgiancollege.comp1011summer2024thursdays;

import com.google.gson.annotations.SerializedName;

public class Shoe {

    @SerializedName("Shoe Name")
    private String name;

    @SerializedName("Shoe Release Year")
    private  int yearReleased;


    public Shoe(String name, int yearReleased) {
        this.name = name;
        this.yearReleased = yearReleased;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "name='" + name + '\'' +
                ", yearReleased=" + yearReleased +
                '}';
    }
}
