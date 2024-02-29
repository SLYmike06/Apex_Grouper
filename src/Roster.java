import java.util.ArrayList;

public class Roster {
    private String sessionTitle;
    private ArrayList<Student> stuList;
    int groupScore;
    public Roster(String sessionTitle) {
        this.sessionTitle = sessionTitle;
        stuList = new ArrayList<Student>();
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Group Title: " + sessionTitle);
        for (Student student: stuList ) {
            output.append(" Student: " + student.getName());
        }
        return output.toString();
    }
}
