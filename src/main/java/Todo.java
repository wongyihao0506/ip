/**
 * Represents a Todo task, a specific type of Task with no additional time constraints.
 * A Todo task includes a task name and its completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified task name.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the Todo task, including its type indicator.
     *
     * @return A formatted string in the form of "[T][ ] taskName" or "[T][X] taskName".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo task into a formatted string for saving to a file.
     *
     * @return A string in the format "T | 1 | taskName" or "T | 0 | taskName",
     *         where 1 indicates the task is completed, and 0 indicates it is not.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}