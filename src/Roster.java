import java.util.ArrayList;

public class Roster {
    public Session session;
    public ArrayList<Student> stuList;
    double rosterScore;

    public int[] count;
    public Roster(Session session) {
        this.session = session;
        stuList = new ArrayList<Student>();
    }

    public Roster copy() {
        Roster copyRoster = new Roster(this.session);
        copyRoster.stuList = (ArrayList<Student>) this.stuList.clone();
        copyRoster.rosterScore = this.rosterScore;
        return copyRoster;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Group Title: " + session);
        for (Student student: stuList ) {
            output.append(" Student: " + student.getName());
        }
        return output.toString();
    }


    public void score(int round) {
        rosterScore = 0;
        count = new int[4];
        for (int i = 0; i < stuList.size(); i++) {
            Student curr = stuList.get(i);
            ArrayList<Session> listOfPrefer = curr.prefer[round-1];
            for (int k = 0; k < listOfPrefer.size(); k++) {
               if(listOfPrefer.get(k) == session) {
                       switch (k) {
                           case 0:
                               rosterScore += 4;
                               count[0]++;
                               break;
                           case 1:
                               rosterScore += 3.3;
                               count[1]++;

                               break;
                           case 2:
                               rosterScore += 2.5;
                               count[2]++;

                               break;
                           case 3:
                               rosterScore += 1.6;
                               count[3]++;

                               break;
                   }
               }
            }
        }
    }
    public ArrayList<Student> getStuList() {
        return stuList;
    }

}
