/**
 * The Event class represents a task that occurs between a start date and an end date.
 * It extends the Task class and adds fields for the event's start and end dates.
 */
public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructs an Event with the specified task name, start date, and end date.
     *
     * @param taskName The name of the task.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the Event task.
     * The format is "[E] <taskName> (from: <startDate> to: <endDate>)".
     *
     * @return A string representing the Event task in the specified format.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    /**
     * Returns the Event task in a format suitable for saving to a file.
     * The format is "E | <isDone> | <taskName> | <startDate> | <endDate>".
     *
     * @return A string representing the Event task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskName + " | " + startDate + " | " + endDate;
    }
}
