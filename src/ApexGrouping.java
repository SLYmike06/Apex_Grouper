import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ApexGrouping {
    public static ArrayList<Student> studentList;
    public ArrayList<Session> sessionList;
    public ArrayList<Configuration> configs;
    private  PriorityQueue<Configuration> pq = new PriorityQueue<Configuration>(new Comparator<Configuration>() {
        public int compare(Configuration s1, Configuration s2) {
            if(s1.configScore < s2.configScore) {
                return 1;
            } else if(s1.configScore >= s2.configScore) {
                return -1;
            }
            return 0;
        }
    });

        public int sizeLimit;

    public static void main(String[] args) {
        ApexGrouping test = new ApexGrouping();

        Configuration t = test.generateBestConfig(1);
//        System.out.println("before: " + t.configScore);
//
//        System.out.println("First preference for round one : " + t.count[0]);
//        System.out.println("Second preference: " + t.count[1]);
//        System.out.println("Third preference: " + t.count[2]);
//        System.out.println("Fourth preference: " + t.count[3]);
//        System.out.println("Total students that got their preference: " + (t.count[0] + t.count[1] + t.count[2] + t.count[3]));


        for(Round rounds: t.rounds) {
            rounds.improveScoreStudentByStudent(studentList,0);
            rounds.improveScoreStudentByStudent(studentList,4);
            rounds.improveScoreStudentByStudent(studentList,3);
//            ArrayList<Roster> rosters = t.rounds.get(3).rosterList;
//            for(Roster roster: rosters) {
//                System.out.println(roster);
//            }
            rounds.score();
            System.out.println("AFTER: First preference for round one : " + rounds.count[0]);
            System.out.println("AFTER: Second preference: " + rounds.count[1]);
            System.out.println("AFTER: Third preference: " + rounds.count[2]);
            System.out.println("AFTER: Fourth preference: " + rounds.count[3]);
            System.out.println("AFTER: Total students that got their preference: " + (rounds.count[0] + rounds.count[1] + rounds.count[2] + rounds.count[3]));
            System.out.println("Round number: " + rounds.round + " Score:" + rounds.roundScore);

            rounds.fillNoPreferenceStudents(studentList);



        }
        ArrayList<Roster> rosters = t.rounds.get(2).rosterList;
        for(Roster roster: rosters) {
            System.out.println(roster);
        }
    System.out.println("dawdnbwoiadnwoidnwoidnoiwanidawninioeffdsaefdsaefdsaefdsesgdgsfgd");
        System.out.println("First preference for round one : " + t.rounds.get(3).count[0]);
        System.out.println("Second preference: " + t.rounds.get(3).count[1]);
        System.out.println("Third preference: " + t.rounds.get(3).count[2]);
        System.out.println("Fourth preference: " + t.rounds.get(3).count[3]);
        System.out.println("Round score for testing: " + t.rounds.get(3).roundScore);
        t.score();
        System.out.println("after: " + t.configScore);
        System.out.println("First preference for round one : " + t.count[0]);
        System.out.println("Second preference: " + t.count[1]);
        System.out.println("Third preference: " + t.count[2]);
        System.out.println("Fourth preference: " + t.count[3]);
        System.out.println("Total students that got their preference: " + (t.count[0] + t.count[1] + t.count[2] + t.count[3]));
    }

    private void printStudent() {
        for(Student a: studentList) {
            System.out.println(a);
        }
    }

    private void printSession() {
        for(Session a: sessionList) {
            System.out.println(a);
        }
    }

    public void tttt() {
        generateRandomConfig();
        Configuration cc = configs.get(0);
        Round dd = cc.rounds.get(0);
        ArrayList<Student> fd = dd.retrieveStudentsHavingPrefer(1);
        System.out.println("dddd " + fd.size());

    }

    public void printRound() {

    }

    public ApexGrouping() {
        sessionList = new ArrayList<>();
        studentList = new ArrayList<>();
        configs = new ArrayList<>();
        readFile();
    }
    public void readFile() {
        try {
            int maxSessions = 0;
            Reader file1 = Files.newBufferedReader(Paths.get("./Apex Sessions.csv"));
            CSVParser csvParser = new CSVParser(file1, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.getRecordNumber() != 1) {
                    if(Integer.parseInt(csvRecord.get(0)) > maxSessions) {
                        maxSessions = Integer.parseInt(csvRecord.get(0));
                    }
                    sessionList.add(new Session(csvRecord.get(1),Integer.parseInt(csvRecord.get(0)),Integer.parseInt(csvRecord.get(2))));
                }
            }
            Reader file2 = Files.newBufferedReader(Paths.get("./Apex Signups.csv"));
            CSVParser csvParser2 = new CSVParser(file2, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser2) {
                if(csvRecord.getRecordNumber() != 1) {
                    Student stu = new Student(csvRecord.get(0),csvRecord.get(1), maxSessions);
                    ArrayList<Session>[] list = new ArrayList[maxSessions];
                    for(int j = 0; j < list.length;j++) {
                        list[j] = new ArrayList<>();
                    }
                    for(int i = 2; i < csvRecord.size();i+=2) {
                        int round = Integer.parseInt(csvRecord.get(i));
                        list[round-1].add(getSession(csvRecord.get(i+1)));
                        if(getSession(csvRecord.get(i+1)) == null) {
                         //   System.out.println("Student " + stu.getName() + " - round " + round + " - preferences not found: " + csvRecord.get(i+1));
                        }
                    }
                    stu.setPrefer(list);
                    studentList.add(stu);
                }
            }

        }
        catch(Exception ignored) {
            System.out.println(ignored);
        }
    }
    public Session getSession(String title) {
        for(int i = 0; i < sessionList.size();i++) {
            if(sessionList.get(i).getPresentation().equals(title)) {
                return sessionList.get(i);
            }
        }
        return null;
    }

    public void printPreferences() {
        for(Session session: sessionList) {
            if(session.roundNum == 1) {
                System.out.print("Session name: " + session.presentation);
                for (int i = 0; i < 4; i++) {
                    int count = 0;
                    for (Student student : studentList) {
                        if (student.prefer[0].get(i) == session) {
                            count++;
                        }
                    }
                    System.out.print(" " + count);
                }
                System.out.println();
            }
        }
    }

    public void generateRandomConfig() {
        Configuration config = new Configuration();
        for(int i = 1; i <= 4; i ++) {
            Round round = new Round(i, sessionList);
            round.generateRandomRound(studentList);
            round.score();
            round.printRound();
            config.rounds.add(round);
        }
        config.score();
        configs.add(config);
    }

    public Configuration generateBestConfig(int num) {
        for(int i = 0; i < num;i++) {
            generateRandomConfig();
            pq.add(configs.get(i));
        }
        return pq.peek();
    }

    public void generateAllCombinations(ArrayList<Configuration> confg, Configuration currentConfig, int roundCount, Roster roster) {
        if(roundCount == 30) {

        }
    }

//    private static Configuration copy(Configuration config) {
//        Configuration copied = new Configuration();
//        ArrayList<Round> rounds = new ArrayList<>();
//        for(Round round: copied.rounds) {
//            rounds.add(round.copy());
//        }
//        copied.rounds = rounds;
//        return copied;
//    }

//    public boolean verifyData(Session element) {
//        for(int i = 0; i < preferenceList.size();i++) {
//            if(element)
//        }
//        return false;
//    }

}