import java.util.HashMap;

public class Question {
    private static HashMap<String, Question> questions;

    private static int defaultTries;
    private static int defaultPoints;

    private String id;
    private String title;
    private String type;
    private String difficulty;
    private int tries;
    private int points;
    private String[] rightAns;
    private String[] wrongAns;

    public Question(String id, String title, String type, String difficulty, int tries, int points, String[] rightAns, String[] wrongAns){
        this.id = id;
        this.title = title;
        this.type = type;
        this.difficulty = difficulty;
        this.tries = tries;
        this.points = points;
        this.rightAns = rightAns;
        this.wrongAns = wrongAns;
        questions.put(id, this);
    }
    

    //getters

    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getType(){
        return type;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public int getTries(){
        return tries;
    }
    public int getPoints(){
        return points;
    }
    public String[] getRightAns(){
        return rightAns;
    }
    public String[] getWrongAns(){
        return wrongAns;
    }

    //setters
    public void setId(String n){
        this.id = n;
    }
    public void setTitle(String n){
        this.title = n;
    }
    public void setType(String n){
        this.type = n;
    }
    public void setDifficulty(String n){
        this.difficulty = n;
    }
    public void setTries(int n){
        this.tries = n;
    }
    public void setPoints(int n){
        this.points = n;
    }
    public void setRightAns(String[] n){
        this.rightAns = n;
    }
    public void setWrongAns(String[] n){
        this.wrongAns = n;
    }

    public static HashMap<String, Question> getQuestions(){
        return questions;
    }

    public static Question getQuestion(String id ){
        return questions.get(id);
    }
    
    public static void setDefaults(int tries, int points){
        defaultTries = tries;
        defaultPoints = points;
    }
    public static int getDefaultTries(){
        return defaultTries;
    }
    public static int getDefaultPoints(){
        return defaultPoints;
    }



    static{
        questions = new HashMap<>();
    }

}