/**
 * The Parser class handles user commands for managing tasks.
 * It processes various commands such as marking, unmarking, deleting tasks,
 * adding tasks (to-do, deadline, event), and searching tasks.
 */
public class Parser {

    /**
     * Handles the user input command and delegates the corresponding task action.
     *
     * @param userInput The command entered by the user.
     * @param taskList The task list to manipulate.
     * @param storage The storage for saving tasks.
     * @param ui The user interface for displaying results.
     * @throws GrowlerException If an error occurs during command processing.
     */
    public void handleCommand(String userInput, TaskList taskList, Storage storage, Ui ui) throws GrowlerException {
        if (userInput.startsWith("mark")) {
            handleMarkCommand(userInput, true, taskList, storage, ui);
        } else if (userInput.startsWith("unmark")) {
            handleMarkCommand(userInput, false, taskList, storage, ui);
        } else if (userInput.startsWith("delete")) {
            handleDeleteCommand(userInput, taskList, storage, ui);
        } else if (userInput.equals("list")) {
            taskList.showTasks(ui);
        } else if (userInput.startsWith("find")) {
            handleFindCommand(userInput, taskList, ui);
        } else {
            handleAddTask(userInput, taskList, storage, ui);
        }
    }

    /**
     * Adds a new task based on the user input.
     * Supports to-do, deadline, and event tasks.
     *
     * @param userInput The command entered by the user.
     * @param taskList The task list to add the new task.
     * @param storage The storage to save the updated task list.
     * @param ui The user interface to display results.
     * @throws GrowlerException If the task format is invalid.
     */
    private void handleAddTask(String userInput, TaskList taskList, Storage storage, Ui ui) throws GrowlerException {
        Task newTask = null;
        if (userInput.startsWith("todo")) {
            String taskName = userInput.substring(4).trim();
            if (taskName.isEmpty()) {
                throw new GrowlerException("Please tell me what you need to do.");
            }
            newTask = new Todo(taskName);
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.substring(8).trim().split(" /by ", 2);
            if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new GrowlerException("Invalid format! Use: deadline <task> /by <time>");
            }
            newTask = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.substring(6).trim().split(" /from ", 2);
            if (parts.length < 2 || !parts[1].contains(" /to ")) {
                throw new GrowlerException("Invalid format! Use: event <task> /from <start> /to <end>");
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        }
        if (newTask != null) {
            taskList.addTask(newTask);
            ui.showTaskAdded(newTask, taskList.getTaskCount());
            storage.saveTasks(taskList.tasks, taskList.getTaskCount());
        } else {
            throw new GrowlerException("Invalid task format. Please specify todo, deadline, or event.");
        }
    }

    /**
     * Marks or unmarks a task as done based on the user input.
     *
     * @param userInput The command entered by the user.
     * @param isMark Whether to mark the task as done (true) or unmark it (false).
     * @param taskList The task list to modify.
     * @param storage The storage to save the updated task list.
     * @param ui The user interface to display results.
     * @throws GrowlerException If the task index is invalid.
     */
    private void handleMarkCommand(String userInput, boolean isMark, TaskList taskList, Storage storage, Ui ui) throws GrowlerException {
        int taskIndex = getTaskIndex(userInput, taskList);
        Task task = taskList.getTask(taskIndex);
        if (isMark) {
            task.markDone();
            ui.showTaskMarked(task);
        } else {
            task.unmarkDone();
            ui.showTaskUnmarked(task);
        }
        storage.saveTasks(taskList.tasks, taskList.getTaskCount());
    }

    /**
     * Deletes a task based on the user input.
     *
     * @param userInput The command entered by the user.
     * @param taskList The task list to delete the task from.
     * @param storage The storage to save the updated task list.
     * @param ui The user interface to display results.
     * @throws GrowlerException If the task index is invalid.
     */
    private void handleDeleteCommand(String userInput, TaskList taskList, Storage storage, Ui ui) throws GrowlerException {
        int taskIndex = getTaskIndex(userInput, taskList);
        Task removedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        ui.showTaskDeleted(removedTask, taskList.getTaskCount());
        storage.saveTasks(taskList.tasks, taskList.getTaskCount());
    }

    /**
     * Finds and displays tasks matching a keyword.
     *
     * @param userInput The command entered by the user.
     * @param taskList The task list to search.
     * @param ui The user interface to display results.
     */
    private void handleFindCommand(String userInput, TaskList taskList, Ui ui) {
        String keyword = userInput.substring(4).trim();
        if (keyword.isEmpty()) {
            ui.showMessage("Please specify a keyword to search for.");
        } else {
            taskList.findTasks(keyword, ui);
        }
    }

    /**
     * Retrieves the task index from the user input.
     *
     * @param userInput The command entered by the user.
     * @param taskList The task list to validate the index.
     * @return The index of the task (0-based).
     * @throws GrowlerException If the task index is invalid or the format is incorrect.
     */
    private int getTaskIndex(String userInput, TaskList taskList) throws GrowlerException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
                throw new GrowlerException("Task number must be between 1 and " + taskList.getTaskCount() + ".");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new GrowlerException("Invalid format! Task number must be an integer.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GrowlerException("Invalid format! Use: <command> <task number>");
        }
    }
}
