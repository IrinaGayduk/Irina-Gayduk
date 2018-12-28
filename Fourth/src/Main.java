import java.util.Scanner;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        int k = (int)(Math.random()*100);
        int start=0, end = 100;
        List<Integer> numbers = new LinkedList<>();
        numbers.add(start);
        numbers.add(end);
        Boolean win = true;
        Scanner in = new Scanner(System.in);
        while(win) {
            System.out.println("Try number between " + start + " " + end);
            String enteredS = in.next();
            if (!enteredS.matches("[0-9]+")) {
                System.out.println("Sorry,you entered incorrect data. Try again");
            } else {
                int entered = Integer.parseInt(enteredS);
                if (entered == k) win = false;
                else {
                    numbers.add(entered);
                    while (!numbers.isEmpty()) {
                        int next = ((LinkedList<Integer>) numbers).pop();
                        if (Math.abs(next - k) < end - k && next > k) end = next - 1;
                        if (Math.abs(next - k) < k - start && next < k) start = next + 1;
                    }
                }
            }
        }
        System.out.println("You win!");
    }
}
