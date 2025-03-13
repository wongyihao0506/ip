/**
 * Manages a list of tasks, providing operations to add, retrieve, delete, and search tasks.
 */
public class TaskList {
    protected Task[] tasks;
    private int taskCount;

    /**
     * Constructs a TaskList with the specified array of tasks and the current task count.
     *
     * @param tasks     The array of tasks to initialize the TaskList.
     * @param taskCount The initial count of tasks in the TaskList.
     */
    public TaskList(Task[] tasks, int taskCount) {
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks[index];
    }

    /**
     * Deletes a task at the specified index.
     * Shifts remaining tasks to fill the gap.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        Task removedTask = tasks[index];
        System.arraycopy(tasks, index + 1, tasks, index, taskCount - index - 1);
        tasks[--taskCount] = null;
    }

    /**
     * Displays the list of tasks.
     *
     * @param ui The UI instance used to display the tasks.
     */
    public void showTasks(Ui ui) {
        ui.showTaskList(tasks, taskCount);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Searches for tasks containing the specified keyword and displays the results.
     *
     * @param keyword The keyword to search for.
     * @param ui      The UI instance used to display the search results.
     */
    public void findTasks(String keyword, Ui ui) {
        Task[] matchingTasks = new Task[taskCount];
        int matchCount = 0;

        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getTaskName().contains(keyword)) {
                matchingTasks[matchCount++] = tasks[i];
            }
        }
        if (matchCount > 0) {
            ui.showSearchResults(matchingTasks, matchCount, keyword);
        } else {
            ui.showError("No tasks matched your search for: " + keyword);
        }
    }
}
