import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Student {
    private String name;
    private ArrayList<Preference>  prefer;
    private String email;
    public Student(String name, String email) {
        this.name = name;
        prefer = new ArrayList<Preference>();
    }

    public String toString() {
        StringBuilder output = new StringBuilder("name: " + name + "Email: " + email);
        for (Preference curPreference: prefer ) {
            output.append(" preference: ").append(curPreference);
        }
        return output.toString();
    }

    public String getName() {
        return name;
    }
}
