/**
 * Represents the user interface for displaying messages and information to the user.
 * This class contains static methods to interact with the user, including showing
 * messages, task lists, and search results.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Growler \nWhat can I do for you? \n");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Displays a message indicating a task has been added to the task list.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after the addition.
     */
    public static void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been removed from the task list.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks in the list after the removal.
     */
    public static void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public static void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Displays the list of tasks in the user's task list.
     *
     * @param tasks     An array of tasks to display.
     * @param taskCount The total number of tasks in the list.
     */
    public static void showTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public static void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public static void showMessage(String message) {
        System.out.println("Message: " + message);
    }

    /**
     * Displays the results of a task search to the user.
     *
     * @param tasks     An array of tasks that match the search criteria.
     * @param taskCount The total number of matching tasks.
     * @param keyword   The keyword used for the search.
     */
    public void showSearchResults(Task[] tasks, int taskCount, String keyword) {
        Ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }
}
