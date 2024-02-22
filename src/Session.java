public class Session {
    private String presentation;
    private int roundNum;
    private int limit;

    public Session(String name, int roundNum, int limit) {
        this.presentation = name;
        this.roundNum = roundNum;
        this.limit = limit;
    }

    public String getPresentation() {
        return presentation;
    }

    public String toString() {
        return " presentation: " + presentation + " round number: " + roundNum + " limit: " + limit;
    }
}