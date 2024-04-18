import java.lang.reflect.Array;
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
    public ArrayList<Student> retrieveStudentsHaving0Prefer(ArrayList<Student>stuList) {
        ArrayList<Student> total = new ArrayList<Student>();
        for (Student student : stuList) {
            boolean flag = false;
                for(Roster roster: this.rosterList) {
                    if(roster.isStuInRoster(student)) flag = true;
                }
                if(!flag) total.add(student);
        }
        return total;
    }

    public ArrayList<Student> retrieveStudentsHavingPrefer(int preferenceNumber) {
        ArrayList<Student> total = new ArrayList<Student>();
            for (Roster roster : this.rosterList) {
                for (Student student : roster.stuList) {
                    if(student.getPreferenceIndex(roster.session) == preferenceNumber) {
                            total.add(student);
                    }
                }
            }
            return total;
    }

    public boolean improveScoreStudentByStudent(ArrayList<Student> stuList, int preferNum) {
        //[current][new]
        ArrayList<Student> students;
        if(preferNum != 0) {
            students = retrieveStudentsHavingPrefer(preferNum);
        } else {
            students = retrieveStudentsHaving0Prefer(stuList);
        }
        for(Student student: students) {
            if(preferNum == 0) {
                for(int i = 1; i <= 4;i++) {
                    Session session = student.prefer[round-1].get(i-1);
                    if(session != null && bump(session,this.scoreChange[preferNum][i])) {
                        addStudent(session, student);
                        break;
                    }
                }
            } else {
                student.prefer[round-1].get(preferNum-1).limit++;
                for(int i = 1; i <= preferNum;i++) {
                    Session session = student.prefer[round-1].get(i-1);
                    if(session != null && bump(session,this.scoreChange[preferNum][i])) {
                        if(!addStudent(session, student)) {
                            System.out.println("cannot add correctly after bump");
                        }
                        removeStuFromRoster(student, student.prefer[round-1].get(preferNum-1));
                        break;
                    }
                }
                student.prefer[round-1].get(preferNum-1).limit--;
            }
        }
        return false;
    }

    //int i = roster.stuList.size()-1; i >= 0; i--

    //bump tries to push a student out of this session, returns true if succeussful
    public boolean bump(Session session, double bumpScore) {
        Roster roster = getRosterFromSession(session);
        for(int i = 0; i <= roster.stuList.size()-1; i++) {
            if(roster.stuList.get(i).getName().indexOf("Lillian") == 0) {
                int a = 0;
            }
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
                if(addStudent(student.prefer[round-1].get(i-1), student)) {
                    removeStuFromRoster(student, session);
                    return true;
                }
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

    public void removeStuFromRoster(Student student, Session session) {
        Roster roster = findRos(session);
        roster.stuList.remove(student);
    }

    public void printRound() {
        int rosterSize = 0;
        for(Roster roster: rosterList) {
         //   System.out.println(roster.stuList.size());
            rosterSize += roster.stuList.size();
        }
     //   System.out.println(rosterSize);
    }

    public int findRoster(Session session) {
        for(Roster roster: rosterList) {
            if(roster.session == session) {
                return rosterList.indexOf(roster);
            }
        }
        return -1;
    }

    public Roster findRos(Session session) {
        for(Roster roster: rosterList) {
            if(roster.session == session) {
                return roster;
            }
        }
        return null;
    }



    public boolean addStudent(Session session, Student student) {
        int matchRosterIndex = findRoster(session);
        if(matchRosterIndex != -1 && rosterList.get(matchRosterIndex).stuList.size() < session.limit) {
            rosterList.get(matchRosterIndex).stuList.add(student);
            return true;
        }
        return false;
    }

    public void printRoster() {
        for(Roster roster: rosterList) {
            System.out.println(roster);
        }
    }

    public Roster findMinRoster() {
        Roster smallest = this.rosterList.get(0);
        for(Roster roster: this.rosterList) {
            if(((double) roster.stuList.size() / roster.session.limit) < ((double) smallest.stuList.size() / smallest.session.limit)) {
                smallest = roster;
            }
        }
        return smallest;
    }

    public void fillNoPreferenceStudents(ArrayList<Student> stuList) {
        ArrayList<Student> students = retrieveStudentsHaving0Prefer(stuList);
        for(Student student: students) {
            Roster rosterToAdd = findMinRoster();
            rosterToAdd.stuList.add(student);
        }
    }
}
