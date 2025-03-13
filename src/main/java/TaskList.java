public class TaskList {
    protected Task[] tasks;
    private int taskCount;

    public TaskList(Task[] tasks, int taskCount) {
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    public void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public void deleteTask(int index) {
        Task removedTask = tasks[index];
        System.arraycopy(tasks, index + 1, tasks, index, taskCount - index - 1);
        tasks[--taskCount] = null;
    }

    public void showTasks(Ui ui) {
        ui.showTaskList(tasks, taskCount);
    }

    public int getTaskCount() {
        return taskCount;
    }

    // New method to find tasks
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
