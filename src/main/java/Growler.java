import java.util.Scanner;

public class Growler {
    private static Task[] tasks = new Task[100];
    private static int taskCount;

    public static void main(String[] args) throws GrowlerException {
        Storage storage = new Storage();
        taskCount = storage.loadTasks(tasks);

        String greet = "Hello! I'm Growler \nWhat can I do for you? \n";
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(greet);

        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(farewell);
                break;
            } else if (userInput.startsWith("mark")) {
                handleMarkCommand(userInput, true, storage);
            } else if (userInput.startsWith("unmark")) {
                handleMarkCommand(userInput, false, storage);
            } else if (userInput.startsWith("delete")) {
                handleDeleteCommand(userInput, storage);
            } else if (userInput.equals("list")) {
                showList();
            } else {
                handleAddTask(userInput, storage);
            }
        }
        in.close();
    }

    private static void handleAddTask (String userInput, Storage storage) throws GrowlerException {
        Task newTask = null;
        if (userInput.startsWith("todo")) {
            String taskName = userInput.substring(4).trim();
            if (taskName.isEmpty()) {
                throw new GrowlerException("Please tell me what you need to do.");
                return;
            }
            newTask = new Todo(taskName);
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.substring(8).trim().split(" /by ", 2);
            if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new GrowlerException("Invalid format! Use: deadline <task> /by <time>");
                return;
            }
            newTask = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.substring(6).trim().split(" /from ", 2);
            if (parts.length < 2 || !parts[1].contains(" /to ")) {
                throw new GrowlerException("Invalid format! Use: event <task> /from <start> /to <end>");
                return;
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        }
        if (newTask != null) {
            tasks[taskCount++] = newTask;
            System.out.println("Got it. I've added this task:\n  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            storage.saveTasks(tasks, taskCount);
        } else {
            throw new GrowlerException("Invalid task format. Please specify todo, deadline, or event.");
        }
    }

    private static void handleMarkCommand(String userInput, boolean isMark, Storage storage) throws GrowlerException {
        if (!isValidTaskCommand(userInput)) return;
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            if (isMark) {
                tasks[taskIndex].markDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskIndex]);
            } else {
                tasks[taskIndex].unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskIndex]);
            }
            storage.saveTasks(tasks, taskCount);
        }
    }

    private static void handleDeleteCommand(String userInput, Storage storage) throws GrowlerException {
        if (!isValidTaskCommand(userInput)) return;
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            Task removedTask = tasks[taskIndex];
            System.arraycopy(tasks, taskIndex + 1, tasks, taskIndex, taskCount - taskIndex - 1);
            tasks[--taskCount] = null;
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            storage.saveTasks(tasks, taskCount);
        }
    }

    private static boolean isValidTaskCommand(String userInput) throws GrowlerException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new GrowlerException("Task number must be between 1 and " + taskCount + ".");
            }
            return true;
        } catch (NumberFormatException e) {
            throw new GrowlerException("Invalid format! Task number must be an integer.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GrowlerException("Invalid format! Use: <command> <task number>");
        }
    }

    private static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }
}

