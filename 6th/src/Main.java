import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your word");
        String inp = scan.next();
        char[] letters = inp.toCharArray();
        int length = inp.length();
        char[] reverse= new char[length];
        for(int i=0;i< length; i++){
            reverse[i]= letters[length-i-1];
        }
        String Rev = "";
        for(int i=0;i< length; i++){
            Rev+=reverse[i];
        }
        System.out.println("Your word backward " + Rev);
    }
}
