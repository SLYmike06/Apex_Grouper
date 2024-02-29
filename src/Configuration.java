import java.util.ArrayList;

public class Configuration {
    private int configScore = 0;
    private ArrayList<ArrayList<Roster>> configs;
    public Configuration(int configScore, ArrayList<ArrayList<Roster>> configs) {
        this.configScore = configScore;
        this.configs = configs;
    }
}
