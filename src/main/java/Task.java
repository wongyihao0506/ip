public class Task {
    protected String taskName;
    protected boolean isDone;
    protected static int TotalNumberOfTasks = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        TotalNumberOfTasks++;
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