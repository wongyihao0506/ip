import java.util.Scanner;

public class Growler {
    public static void main(String[] args) throws GrowlerException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        Task[] tasks = new Task[100];
        int taskCount = storage.loadTasks(tasks);
        TaskList taskList = new TaskList(tasks, taskCount);
        ui.showWelcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
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