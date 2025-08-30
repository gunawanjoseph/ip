import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{ //partially taken from Level4 A-Inheritance https://nus-cs2103-ay2526s1.github.io/website/schedule/week2/project.html
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
