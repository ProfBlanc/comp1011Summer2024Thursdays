package ca.georgiancollege.comp1011summer2024thursdays;

public class Camera {

    /*
    d: powerState: enum
p: color: string
v: storage: int
v: make: string
v: model:string
v:shutter: enum
v:battery: double
v: numberOfPhotos: int
v: hoursOfViewRecorded: double

p:takePhoto(mode: string, int howMany): boolean
p: savePhoto(photoId: int): boolean
p:recordVideo(howLong: double): boolean


     */
    public String color;
    private String make, model;
    public enum PowerStates{ON, OFF}
    public enum ShutterTypes{OPEN, CLOSED}
    private PowerStates powerState = PowerStates.OFF;
    private ShutterTypes shutter = ShutterTypes.CLOSED;
    private int numberOfPhotos;
    private double hoursOfVideoRecordings;

    public boolean takePhoto(String mode, int howMany){
        numberOfPhotos++;
        System.out.println("Take photo in mode " + mode);
        return true;
    }
    public boolean savePhoto(int photoId){
        System.out.println("Saving photo with ID=" + photoId);
        return true;
    }
    public boolean recordVideo(double howLong){
        hoursOfVideoRecordings+= howLong;
        return true;
    }

    public Camera(){}
}
