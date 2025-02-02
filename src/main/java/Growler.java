import java.util.Scanner;
import java.util.ArrayList;


public class Growler {
    private static ArrayList<Task> list = new ArrayList<>();
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
                addToList(userInput);
                System.out.println("added: " + userInput);
            }
        }
        in.close();
    }
        private static void addToList(String userInput){
        Task newTask = new Task(userInput);
        list.add(newTask);
    }
    private static void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++){
            System.out.println((i+1) + "." + list.get(i));
        }
    }
    private static void addTask(String description){
        list.add(new Task(description));
    }
    private static void markList(String userInput, boolean mark){
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task task = list.get(taskNumber);

        if(mark) {
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            task.unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }
}
