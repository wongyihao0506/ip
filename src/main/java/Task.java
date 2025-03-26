/**
 * Represents a generic task with a name and a completion status.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a task with the specified name.
     * The task is initially marked as not done.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The format includes the completion status and the task name.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + taskName;
    }

    /**
     * Converts the task to a file-friendly format for saving to storage.
     * The format includes the task type, completion status, and task name.
     *
     * @return A formatted string suitable for file storage.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}
