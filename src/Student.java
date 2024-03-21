import java.util.ArrayList;

public class Student {
    public String name;
    public ArrayList<Session>[]  prefer;
    public String email;
    public int maxSessions;
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

    public int getPreferenceIndex(Session session) {
        for(int i = 0; i < prefer[session.roundNum].size();i++) {
            if(prefer[session.roundNum].get(i) == session) {
                return i;
            }
        }
        return -1;
    }

    public String getName() {
        return name;
    }
}
