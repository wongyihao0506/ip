public class Ui {
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Growler \nWhat can I do for you? \n");
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public static void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    public static void showTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    public static void showError(String message) {
        System.out.println("Error: " + message);
    }
    public static void showMessage(String message) {
        System.out.println("Message: " + message);
    }

    public void showSearchResults(Task[] tasks, int taskCount, String keyword) {
        Ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }
}
