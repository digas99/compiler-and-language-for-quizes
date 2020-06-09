
public class Question {
    private String id;
    private String title;
    private String type;
    private String difficulty;
    private int tries;
    private int time;
    private int points;
    private String rightAns;
    private String wrongAns;

    public Question(String id, String title, String type, String difficulty, int tries, int time, int points, String rigthAns, String wrongAns){
        id = this.id;
        title = this.title;
        type = this.type;
        difficulty = this.difficulty;
        tries = this.tries;
        time = this.time;
        points = this.points;
        rigthAns = this.rightAns;
        wrongAns = this.wrongAns;

    }
    

}