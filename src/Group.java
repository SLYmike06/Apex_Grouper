import java.util.ArrayList;

public class Group {
    private String title;
    private ArrayList<Student> stuList;
    int groupScore;
    public Group(String title) {
        stuList = new ArrayList<Student>();
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Group Title: " + title);
        for (Student student: stuList ) {
            output.append(" Student: " + student.getName());
        }
        return output.toString();
    }
}
