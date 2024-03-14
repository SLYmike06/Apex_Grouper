import java.util.ArrayList;

public class Configuration {
    public double configScore = 0;
    public int[] count;
    public ArrayList<Round> rounds;
    public Configuration(ArrayList<Round> configs) {
        this.configScore = configScore;
        this.rounds = configs;
    }

    public void score() {
        count = new int[4];
        for(Round round: rounds) {
            round.score();
            configScore += round.roundScore;
            count[0] +=round.count[0];
            count[1] +=round.count[1];
            count[2] +=round.count[2];
            count[3] +=round.count[3];
        }
    }

    public Configuration() {
        configScore = 0;
        rounds = new ArrayList<Round>();
        count = new int[4];

    }
}
