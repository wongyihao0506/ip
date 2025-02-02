import java.util.Scanner;
public class Growler {
    public static void main(String[] args) {
        String greet = "Hello! I'm Growler \nWhat can I do for you? \n";
        String Farewell = "Bye. Hope to see you again soon!";
        System.out.println(greet);
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        if (userInput.equals("bye")) {
            System.out.println(Farewell);
        } else {
            System.out.println(userInput);
        }
    }
}
