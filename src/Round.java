import java.util.ArrayList;
import java.util.Random;

public class Round {
    public ArrayList<Roster> rosterList;
    public int round;
    public int[] count;

    public double roundScore;
    public double[][] scoreChange = {{+0.0, +4.0, +3.3, +2.5, +1.6},
                                     {-4.0, +0.0, -0.7, -1.5, -2.4},
                                     {-3.3, +0.7, +0.0, -0.8, -1.7},
                                     {-2.5, +1.5, +0.8, +0.0, -0.9},
                                     {-1.6, +2.4, +1.7, +0.9, +0.0}}
    ;



    public Round(int round, ArrayList<Session> sessionList) {
        this.rosterList = new ArrayList<Roster>();
        this.round = round;
        for(Session session: sessionList) {
            if(session.roundNum == round) {
                rosterList.add(new Roster(session));
            }
        }
    }

    public void score() {
        count = new int[4];
        roundScore = 0;
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
       // Random rand = new Random();
        for(Student student: studentList) {
               // Session possible = student.prefer[round-1].get(rand.nextInt(student.prefer[round-1].size()));
               // boolean check = addStudent(possible, student);
                //if(!check) {
                    for(Session session: student.prefer[round-1]) {
                        if(addStudent(session,student)) {
                            break;
                        }
                    }
               // }
            }
    }
    public ArrayList<Student> retrieveStudentsHavingPrefer(int preferenceNumber) {
        ArrayList<Student> total = new ArrayList<Student>();
        if(preferenceNumber == 0) {
            for (Roster roster : this.rosterList) {
                for (Student student : roster.stuList) {
                    ArrayList<Session> preference = student.prefer[round - 1];
                    for (int i = 0; i < preference.size(); i++) {
                        if (roster.session != preference.get(i)) {
                            total.add(student);
                        }
                    }
                }
            }
            return total;
        } else {
            for (Roster roster : this.rosterList) {
                for (Student student : roster.stuList) {
                    ArrayList<Session> preference = student.prefer[round - 1];
                    for (int i = 0; i < preference.size(); i++) {
                        if (roster.session == preference.get(i) && i + 1 == preferenceNumber) {
                            total.add(student);
                        }
                    }
                }
            }
            return total;
        }
    }

    public boolean improveScoreStudentByStudent(int preferNum) {
        //[current][new]
        ArrayList<Student> students = retrieveStudentsHavingPrefer(preferNum);
        for(Student student: students) {
            for(int i = 1; i <= preferNum;i++) {
                Session session = student.prefer[round-1].get(i-1);
                if(bump(session,this.scoreChange[preferNum][i])) {
                    addStudent(session, student);
                }
            }

        }
        return false;
    }
    //bump tries to push a student out of this session, returns true if succeussful
    public boolean bump(Session session, double bumpScore) {
        Roster roster = getRosterFromSession(session);
        for(int i = roster.stuList.size()-1; i >= 0; i--) {
            if(bumpStudent(roster.stuList.get(i),session, bumpScore)){
                return true;
            }
        }
        return false;
    }

    public boolean bumpStudent(Student student, Session session, double bumpScore) {
        int currentPrefer = student.getPreferenceIndex(session);
        for(int i = currentPrefer+1; i <= 4; i++) {
            if(Math.abs(scoreChange[currentPrefer][i]) < bumpScore) {
                return addStudent(session, student);
            }
        }
        return false;
    }
    public Roster getRosterFromSession(Session session) {
        for(Roster roster: this.rosterList) {
            if(roster.session == session) {
                return roster;
            }
        }
        return null;
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
