import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class apex {
    private ArrayList<Student> studentList;
    private ArrayList<Configuration> configs;
    private int sizeLimit;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public apex() {
        studentList = new ArrayList<>();
        configs = new ArrayList<>();
        readFile();
    }
    public void readFile() {
        try {
            Reader file1 = Files.newBufferedReader(Paths.get("./realDATA.csv"));
            CSVParser csvParser = new CSVParser(file1, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.getRecordNumber() == 1) {
                    num = Integer.parseInt(csvRecord.get(0));
                } else if (csvRecord.getRecordNumber() == 2) {
                    groupSize = Integer.parseInt(csvRecord.get(0));
                } else {
                    int size = csvRecord.size();
                    Student newStudent = new Student(csvRecord.get(0));
                    ArrayList<Preference> newStudentPreference = new ArrayList<Preference>();
                    for (int i = 1; i < size; i += 2) {
                        if (!csvRecord.get(i).isEmpty() || !csvRecord.get(i + 1).isEmpty()) {
                            // System.out.println(" desire: " + csvRecord.get(i));
                            newStudentPreference.add(new Preference(csvRecord.get(i).charAt(0), csvRecord.get(i + 1)));
                        }
                    }
                    newStudent.setPrefer(newStudentPreference);
                    studentList.add(newStudent);
                }
            }
        }
        catch(Exception e) {

        }
    }

}