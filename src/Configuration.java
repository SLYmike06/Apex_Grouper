import java.util.ArrayList;

public class Configuration {
    private int configScore = 0;
    public ArrayList<Round> rounds;
    public Configuration(int configScore, ArrayList<Round> configs) {
        this.configScore = configScore;
        this.rounds = configs;
    }

    public Configuration() {
        configScore = 0;
        rounds = new ArrayList<Round>();
    }
}
