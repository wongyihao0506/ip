public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + dueDate;
    }
}
