import java.util.*;

public class main {

    public static void main(String[] args) {
        Test.defaultGraphsTest();
        Scanner userInput = new Scanner(System.in);
        boolean stop = false;
        while(!stop) {
            System.out.println("Do you want to input a graph to run the vertex cover algorithms on? (yes/no)");
            String answer = userInput.nextLine();
            if (Objects.equals(answer, "no")) {
                stop = true;
            } else {
                Test.userGraphTest();
            }
        }
    }

}
