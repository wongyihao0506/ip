public class Todo extends Task {
    public Todo(String taskName){
        super(taskName);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}
