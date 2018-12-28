import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {

     static void sortFile(String name,int language) {

        File file = new File(name);
        Scanner scanner;

        try {
            if (language ==0){
                scanner = new Scanner(file, "ISO-8859-1");
            }
            else {
                scanner = new Scanner(file, "windows-1251");
            }


            Set<String> wordList= new HashSet<>();
            System.out.println("\n\nCONTENT OF FILE:\n");

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                System.out.println(line);
                line= line.replaceAll("[,;.:-]","");
                String[] words = line.split(" ");


                for (String word : words)
                    if (!word.isEmpty()) {
                        wordList.add(word);
                    }
            }

            List sortedWordList = new ArrayList(wordList);
            Collections.sort(sortedWordList, String.CASE_INSENSITIVE_ORDER);
            System.out.println("\nCONTENT OF SORTED FILE:\n");
            System.out.println(sortedWordList);

            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Incorrect file!");
        }

    }

    public static void main(String[] args) {
        int languageUkr=2, languageEng=0, languageRus=1;
        sortFile("D:\\КПИ\\3 курс\\Тестировка\\5th\\english.txt",languageEng);
        sortFile("D:\\КПИ\\3 курс\\Тестировка\\5th\\russian.txt",languageRus);
        sortFile("D:\\КПИ\\3 курс\\Тестировка\\5th\\ukraine.txt",languageUkr);
    }

}
