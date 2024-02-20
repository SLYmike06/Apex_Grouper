import java.util.ArrayList;
import java.util.Random;

public class Configuration {
    private int configScore = 0;
    private ArrayList<Integer> scores;
    private ArrayList<Group> groupList;
    public Configuration(int configScore, ArrayList<Group> groupList, ArrayList<Integer> scores, double sd, ArrayList<Student> studentList, int groupSize) {
        this.configScore = configScore;
        this.groupList = groupList;
        this.scores = scores;
    }

    public Configuration(ArrayList<Student> studentList, int groupSize) {
        configScore = 0;
        groupList = new ArrayList<Group>();
        scores = new ArrayList<>();
    }

}
