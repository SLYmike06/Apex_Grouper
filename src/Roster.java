import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
//        return output.toString();
        output.append(" Roster Score: " + rosterScore + "   Score: " + count[0] + " " + count[1] + " " + count[2] + " " + count[3]);
        return output.toString();
    }


    public void score(int round) {
        rosterScore = 0;
        count = new int[4];
        if(session.presentation.indexOf("Sist") == 0) {
            int a = 0;
        }
        for (int i = 0; i < stuList.size(); i++) {
            Student curr = stuList.get(i);
            ArrayList<Session> listOfPrefer = curr.prefer[round-1];
            Set<Session> set = new HashSet<Session>();
            for (int k = 0; k < listOfPrefer.size(); k++) {
               if(listOfPrefer.get(k) != null && listOfPrefer.get(k) == session && !set.contains(listOfPrefer.get(k))) {
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
                            //   System.out.println(curr);
                               break;
                   }
               }
               set.add(listOfPrefer.get(k));
            }
        }
    }
    public boolean isStuInRoster(Student student) {
        for(Student stu: stuList) {
            if(stu == student) {
                return true;
            }
        }
        return false;
    }



}
