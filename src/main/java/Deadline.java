public class Deadline extends Task{ //partially taken from Level4 A-Inheritance https://nus-cs2103-ay2526s1.github.io/website/schedule/week2/project.html
    protected String by;

    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveFormat(){
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
