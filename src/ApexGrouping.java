import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ApexGrouping {
    private ArrayList<Student> studentList;
    private ArrayList<Session> sessionList;
    private ArrayList<Configuration> configs;
    private int sizeLimit;

    public static void main(String[] args) {
        ApexGrouping test = new ApexGrouping();
        test.printSession();
        test.printStudent();

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
                            System.out.println("Student " + stu.getName() + " - round " + round + " - preferences not found: " + csvRecord.get(i+1));
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

//    public boolean verifyData(Session element) {
//        for(int i = 0; i < preferenceList.size();i++) {
//            if(element)
//        }
//        return false;
//    }

}