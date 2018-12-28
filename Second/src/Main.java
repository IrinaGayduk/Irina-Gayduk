import java.util.*;
public class Main {

    public static void main(String[] args) {

        List <Integer> alfa = new ArrayList<>(150);
        for( int i=0; i< 150; i ++){
            Integer item = (int)( Math.random() * 199 + 1);
            alfa.add(item);
        }
        Collections.sort(alfa);
        List<Integer> betta = new ArrayList<>(alfa.subList(alfa.size() - 15, alfa.size()));
        System.out.println(betta);
        alfa.clear();
        betta.clear();
    }
}
