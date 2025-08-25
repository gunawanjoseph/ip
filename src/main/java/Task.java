public abstract class Task { //partially taken from Level 3 A-Classes at https://nus-cs2103-ay2526s1.github.io/website/schedule/week2/project.html
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
