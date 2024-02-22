import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Session>[]  prefer;
    private String email;
    private int maxSessions;
    public Student(String name, String email, int maxSessions) {
        this.name = name;
        this.email = email;
        this.maxSessions = maxSessions;
        prefer = new ArrayList[maxSessions];
    }
    public void setPrefer(ArrayList<Session>[] prefer) {
        this.prefer = prefer;
    }
    public String toString() {
        StringBuilder output = new StringBuilder("Name: " + name + " Email: " + email + " Max Sessions: " + maxSessions);
        for(int i = 0; i < maxSessions;i++) {
            for (Session curPreference: prefer[i]) {
                output.append(" preference: ").append(curPreference);
            }
        }
        return output.toString();
    }

    public String getName() {
        return name;
    }
}
