import java.util.ArrayList;

public class Roster {
    public Session session;
    public ArrayList<Student> stuList;
    int groupScore;
    public Roster(Session session) {
        this.session = session;
        stuList = new ArrayList<Student>();
    }

    public Roster copy() {
        Roster copyRoster = new Roster(this.session);
        copyRoster.stuList = (ArrayList<Student>) this.stuList.clone();
        copyRoster.groupScore = this.groupScore;
        return copyRoster;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Group Title: " + session);
        for (Student student: stuList ) {
            output.append(" Student: " + student.getName());
        }
        return output.toString();
    }

    public ArrayList<Student> getStuList() {
        return stuList;
    }

}
