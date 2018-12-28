import java.util.*;

public class Main {

        public static void main(String[] args) {
            long i = 1234567;


            List<Integer> linked = new LinkedList<>();
            List<Integer> array = new ArrayList<>();
            TreeSet <Integer> tree = new TreeSet<>();
            HashSet <Integer> hash = new HashSet <>();
            Map <String, Long> result = new HashMap<>();


//add element
            long startArray = System.currentTimeMillis();
            for (int index = 0; index <= i; index++) {
                array.add(index);
            }
            long endArray = System.currentTimeMillis() - startArray;
            result.put("Array List", endArray);

            long startLinked = System.currentTimeMillis();
            for (int index = 0; index <= i; index++) {
                linked.add(index);
            }
            long endLinked = System.currentTimeMillis() - startLinked;
            result.put("Linked List", endLinked);

            long startTree = System.currentTimeMillis();
            for (int index = 0; index <= i; index++) {
                tree.add(index);
            }
            long endTree = System.currentTimeMillis() - startTree;
            result.put("TreeSet", endTree);

            long startHash = System.currentTimeMillis();
            for (int index = 0; index <= i; index++) {
                hash.add(index);
            }
            long endHash = System.currentTimeMillis() - startHash;
            result.put("HashSet", endHash);

            System.out.println("\nAdd " + i + " elements");
            System.out.println("Array: " + endArray);
            System.out.println("LinkedList:" + endLinked);
            System.out.println("Tree:" + endTree);
            System.out.println("Hash:" + endHash);

//find element
            int find = 23456;
            System.out.println("\nFind element " + find);
            startArray = System.currentTimeMillis();
            System.out.println("\nArray found element - "+array.get(find));
            endArray = System.currentTimeMillis() - startArray;

            startLinked = System.currentTimeMillis();
            System.out.println("List found element - "+linked.get(find));
            endLinked = System.currentTimeMillis() - startLinked;


            Iterator<Integer> it;
            it = tree.iterator();
            int k = 0;
            Integer current = null;
            startTree = System.currentTimeMillis();
            while(it.hasNext() && k < find+1) {
                current = it.next();
                k++;
            }
            endTree = System.currentTimeMillis() - startTree;
            System.out.println("Tree found element - "+current);


            it = hash.iterator();
            k = 0;
            current = null;
            startHash = System.currentTimeMillis();
            while(it.hasNext() && k < find+1) {
                current = it.next();
                k++;
            }
            endHash = System.currentTimeMillis() - startHash;
            System.out.println("Hash found element - "+current);

            System.out.println("\nSearch " + find + " element");
            System.out.println("Array: " + endArray);
            System.out.println("LinkedList:" + endLinked);
            System.out.println("Tree:" + endTree);
            System.out.println("Hash:" + endHash);
// remove
            startArray = System.currentTimeMillis();
            array.remove(find);
            endArray = System.currentTimeMillis() - startArray;

            startLinked = System.currentTimeMillis();
            linked.remove(find);
            endLinked = System.currentTimeMillis() - startLinked;

            startTree = System.currentTimeMillis();
            tree.remove(find);
            endTree = System.currentTimeMillis() - startTree;

            startHash = System.currentTimeMillis();
            hash.remove(find);
            endHash = System.currentTimeMillis() - startHash;


            System.out.println("\nRemove " + find + " elements");
            System.out.println("Array: " + endArray);
            System.out.println("LinkedList:" + endLinked);
            System.out.println("Tree:" + endTree);
            System.out.println("Hash:" + endHash);

        }


    }