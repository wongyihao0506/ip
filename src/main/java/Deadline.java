/**
 * Represents a task with a deadline.
 * A Deadline task has a name and a due date, in addition to the standard task properties.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs a Deadline task with the specified task name and due date.
     *
     * @param taskName The name of the task.
     * @param dueDate  The due date of the task.
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Deadline task, including its type,
     * task name, and due date.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Returns the Deadline task in a format suitable for file storage.
     *
     * @return A formatted string representing the Deadline task for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + dueDate;
    }
}
