package automation.qa.espressoexample.models;

/**
 * Created by Karnaukh Roman on 23.09.2016.
 */
public class Person {
    public String name;
    public String age;
    public int photoId;
    public Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
}