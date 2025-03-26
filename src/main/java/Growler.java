import java.util.Scanner;

/**
 * The Growler class is the main entry point for the Growler application.
 * It initializes the necessary components (Storage, Ui, Parser, TaskList) and processes user commands.
 * The program runs in a loop until the user types "bye", handling task management operations.
 */
public class Growler {

    /**
     * The main method starts the Growler application and processes user commands.
     * It initializes the storage, UI, parser, and task list, then enters a loop to process commands.
     * The loop continues until the user types "bye".
     *
     * @param args Command-line arguments (not used in this application).
     * @throws GrowlerException If there is an error while handling tasks (e.g., invalid task format).
     */
    public static void main(String[] args) throws GrowlerException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        Task[] tasks = new Task[100];
        int taskCount = storage.loadTasks(tasks);  // Load existing tasks from storage
        TaskList taskList = new TaskList(tasks, taskCount);
        ui.showWelcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();  // Get user input
            if (userInput.equals("bye")) {
                ui.showGoodbyeMessage();
                break;
            }
            try {
                parser.handleCommand(userInput, taskList, storage, ui);
            } catch (GrowlerException e) {
                ui.showError(e.getMessage());
            }
        }
        in.close();
    }
}
