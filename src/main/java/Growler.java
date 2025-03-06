import java.util.Scanner;


public class Growler {
    private static Task[] tasks = new Task[100];
    static int taskIndex = 0;
    public static void main(String[] args) {
        String greet = "Hello! I'm Growler \nWhat can I do for you? \n";
        String Farewell = "Bye. Hope to see you again soon!";
        System.out.println(greet);
        String userInput;
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(Farewell);
                break;
            } else if (userInput.startsWith("mark")) {
                // Validate input and mark the task as done
                if (!isValidTaskCommand(userInput, "mark")) {
                    System.out.println("Invalid format! Use: mark <task number>");
                    return;
                }
                markList(userInput, true);
            } else if (userInput.startsWith("unmark")) {
                // Validate input and mark the task as not done
                if (!isValidTaskCommand(userInput, "unmark")) {
                    System.out.println("Invalid format! Use: unmark <task number>");
                    return;
                }
                markList(userInput, false);
            } else if (userInput.equals("list")) {
                showList();
            } else {
                addTask(userInput);
            }
        }
        in.close();
    }

    private static void addTask(String userInput) {
        if (userInput.startsWith("todo")) {
            String taskName = userInput.substring(4).trim(); // Trim removes spaces
            if (taskName.isEmpty()) {
                System.out.println("PLz tell me what you need to do");
                return;
            } else {
                tasks[taskIndex] = new Todo(taskName);
                System.out.println("Got it. I've added this task:\n  [T][ ] " + taskName);
            }
        } else if (userInput.startsWith("deadline")) {
            String remainingInput = userInput.substring(8).trim();
            if (!remainingInput.contains(" /by ")) {
                System.out.println("Invalid format! Use: deadline <task> /by <time>");
                return;
            }
            String[] parts = remainingInput.split(" /by ", 2);
            if (parts[0].isEmpty() || parts[1].isEmpty()) {
                System.out.println("Invalid format! Task and deadline time cannot be empty.");
                return;
            }
            tasks[taskIndex] = new Deadline(parts[0].trim(), parts[1].trim());
            System.out.println("Got it. I've added this task:\n  [D][ ] " + parts[0] + " (by: " + parts[1] + ")");
        } else if (userInput.startsWith("event ")) {
            String remainingInput = userInput.substring(6).trim();
            if (!remainingInput.contains(" /from ") || !remainingInput.contains(" /to ")) {
                System.out.println("Invalid format! Use: event <task> /from <start time> /to <end time>");
                return;
            }

            String[] parts = remainingInput.split(" /from ", 2);
            if (parts.length < 2 || parts[0].isEmpty()) {
                System.out.println("Invalid format! Task description cannot be empty.");
                return;
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
                System.out.println("Invalid format! Start and end times cannot be empty.");
                return;
            }
            tasks[taskIndex] = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            System.out.println("Got it. I've added this task:\n  [E][ ] " + parts[0] +
                    " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
        } else {
            System.out.println("Invalid task format. Please specify todo, deadline, or event.");
            taskIndex--;
        }
        taskIndex++;
        System.out.println("Now you have " + taskIndex + " tasks in the list.");
    }

    private static void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskIndex; i++){
            System.out.println((i+1) + "." + tasks[i]);
        }
    }
    private static void markList(String userInput, boolean mark){
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = tasks[taskNumber];

        if(mark) {
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            task.unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }
    private static boolean isValidTaskCommand(String userInput, String commandPrefix) {
        try {
            // Check if the input starts with the correct prefix and has a valid number
            String numberPart = userInput.substring(commandPrefix.length()).trim();
            int taskNumber = Integer.parseInt(numberPart); // Convert to integer
            if (taskNumber <= 0 || taskNumber > taskIndex) {
                System.out.println("Task number must be between 1 and " + taskIndex + ".");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            // Handle cases where the input is not a number
            System.out.println("Please provide a valid task number.");
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            // Handle cases where no number is provided
            System.out.println("Please specify a task number.");
            return false;
        }
    }
}

