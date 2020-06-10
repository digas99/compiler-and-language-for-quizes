import java.util.HashMap;

public class Question {
    public static HashMap<String, Question> questions;


    private String id;
    private String title;
    private String type;
    private String difficulty;
    private int tries;
    private int time;
    private int points;
    private String[] rightAns;
    private String[] wrongAns;

    public Question(String id, String title, String type, String difficulty, int tries, int time, int points, String[] rightAns, String[] wrongAns){
        id = this.id;
        title = this.title;
        type = this.type;
        difficulty = this.difficulty;
        tries = this.tries;
        time = this.time;
        points = this.points;
        rightAns = this.rightAns;
        wrongAns = this.wrongAns;
        questions.put(id, this);
    }
    

    static{
        questions = new HashMap<>();
    }

}