public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public void markDone() {
        isDone = true;
    }
    public void unmarkDone() {
        isDone = false;
    }
    public String toString(){
        return (isDone ? "[X]" : "[ ]") + " " + taskName;
    }
}