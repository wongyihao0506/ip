public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + taskName;
    }
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}
