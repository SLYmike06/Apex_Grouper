public class Preference {
    private String presentation;
    private int roundNum;

    public Preference(String name, int roundNum) {
        this.presentation = name;
        this.roundNum = roundNum;
    }


    public String toString() {
        return " presentation: " + presentation + "round number: " + roundNum;
    }
}