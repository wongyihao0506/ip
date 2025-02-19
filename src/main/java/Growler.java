import java.util.Scanner;
import java.util.ArrayList;


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
            } else if (userInput.startsWith("mark ")) {
                markList(userInput, true);
            } else if (userInput.startsWith("unmark ")) {
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
        if (userInput.startsWith("todo ")) {
            String taskName = userInput.substring(5);
            tasks[taskIndex] = new Todo(taskName);
            System.out.println("Got it. I've added this task:\n  [T][ ] " + taskName);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ", 2);
            tasks[taskIndex] = new Deadline(parts[0], parts[1]);
            System.out.println("Got it. I've added this task:\n  [D][ ] " + parts[0] + " (by: " + parts[1] + ")");
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from ", 2);
            String[] timeParts = parts[1].split(" /to ", 2);
            tasks[taskIndex] = new Event(parts[0], timeParts[0], timeParts[1]);
            System.out.println("Got it. I've added this task:\n  [E][ ] " + parts[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
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
}
