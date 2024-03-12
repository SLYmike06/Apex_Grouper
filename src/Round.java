import java.util.ArrayList;
import java.util.Random;

public class Round {
    public ArrayList<Roster> rosterList;
    public int round;
    public int[] count;

    public double roundScore;


    public Round(int round, ArrayList<Session> sessionList) {
        this.rosterList = new ArrayList<Roster>();
        this.round = round;
        for(Session session: sessionList) {
            if(session.roundNum == round) {
                rosterList.add(new Roster(session));
            }
        }
        count = new int[4];
    }

    public void score() {
        for(Roster roster: rosterList) {
            roster.score(round);
            roundScore += roster.rosterScore;
            count[0] +=roster.count[0];
            count[1] +=roster.count[1];
            count[2] +=roster.count[2];
            count[3] +=roster.count[3];
        }
    }

//    public Round copy() {
//        Round copyRound = new Round();
//        ArrayList<Roster> rosList = new ArrayList<>();
//        for(Roster roster: this.rosterList) {
//            rosList.add(roster.copy());
//        }
//        copyRound.rosterList = rosList;
//        return copyRound;
//    }


    public void generateRandomRound(ArrayList<Student> studentList) {
        Random rand = new Random();
        for(Student student: studentList) {
                Session possible = student.prefer[round-1].get(rand.nextInt(student.prefer[round-1].size()));
                boolean check = addStudent(possible, student);
                if(!check) {
                    for(Session session: student.prefer[round-1]) {
                        if(addStudent(session,student)) {
                            break;
                        }
                    }
                }
            }
    }

    public void printRound() {
        int rosterSize = 0;
        for(Roster roster: rosterList) {
            System.out.println(roster.stuList.size());
            rosterSize += roster.stuList.size();
        }
        System.out.println(rosterSize);
    }

    public int findRoster(Session session) {
        for(Roster roster: rosterList) {
            if(roster.session == session) {
                return rosterList.indexOf(roster);
            }
        }
        return -1;
    }

    public boolean addStudent(Session session, Student student) {
        int matchRosterIndex = findRoster(session);
        if(matchRosterIndex != -1 && rosterList.get(matchRosterIndex).stuList.size() < session.limit) {
            rosterList.get(matchRosterIndex).stuList.add(student);
            return true;
        }
        return false;
    }
}
