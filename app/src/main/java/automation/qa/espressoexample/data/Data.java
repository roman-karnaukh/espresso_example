package automation.qa.espressoexample.data;

import java.util.ArrayList;

import automation.qa.espressoexample.R;
import automation.qa.espressoexample.models.Person;

/**
 * Created by Karnaukh Roman on 26.09.2016.
 */
public class Data {

    public ArrayList<Person> personsData(){
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
        persons.add(new Person("Angelina Joly", "41 years old", R.drawable.angelina));
        persons.add(new Person("Sasha Gray", "28 years old", R.drawable.sasha));
        persons.add(new Person("Jason Statham", "49 years old", R.drawable.jason));
        persons.add(new Person("Strawberry", "young", R.drawable.strawberry));
        return persons;
    }
}
