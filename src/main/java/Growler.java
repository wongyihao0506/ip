import java.util.Scanner;
import java.util.ArrayList;

public class Growler {
    private static ArrayList<String> list = new ArrayList<>();
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
        list.add(userInput);
    }
    private static void showList(){
        for (int i = 0; i < list.size(); i++){
            System.out.println((i+1) + ". " + list.get(i));
        }
    }
}
