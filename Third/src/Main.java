import java.util.*;
import java.io.*;

public class Main {
    abstract static class Worker implements Comparable<Worker>{
        String name;
        int id;
        double SalaryInMonth;

        Worker(String inputName, int inputId) {
            name = inputName;
            id = inputId;

        }
        Worker() {
            this.name = "John";
            this.id = 1;
        }

        private String getName() { return this.name; }
        public void setName(String name) {
            this.name=name;
        }

        private int getId() { //геттер
            return this.id;
        }
        public void setId(Integer id) {
            this.id=id;
        }

        private double getAverageInMonth(){
            return this.SalaryInMonth;
        }
        protected void setAverageInMonth(double salaryInMonth) {
            this.SalaryInMonth = salaryInMonth;
        }

        public String toString() {
            return (this.getId() + " " + this.getName() + " average: " + this.getAverageInMonth());
        }

        //сравнение зарплат
        public int compareTo(Worker w) {
            if(this.SalaryInMonth < w.SalaryInMonth) return -1;
            else if(this.SalaryInMonth > w.SalaryInMonth) return 1;
            else return this.getName().compareTo(w.getName())*(-1);
        }


    }

    static class PartTime extends Worker {
        private double payPerHour;

        PartTime(String inputName, int inputId,double inputPayPerHour) {
            super(inputName,inputId);
            payPerHour = inputPayPerHour;
            monthSalary(inputPayPerHour);
        }
        PartTime( ) {
            payPerHour = 1000;
            monthSalary(1000);
        }


        private double getPayPerHour() {
            return this.payPerHour;
        }

        public void setPayPerHour(double payPerHour) {
            this.payPerHour=payPerHour;
        }

        @Override
        public String toString() {
            String s = super.toString();
            return (s+ "     per hour: "+this.getPayPerHour());
        }

        void monthSalary(double income) {
            setAverageInMonth( 20.8 * 8 * income);

        }

    }

    public static class FullTime extends Worker {
        private double payPerMonth;

        FullTime(String inputName, int inputId,double inputPayPerMonth) {
            super(inputName,inputId);
            payPerMonth = inputPayPerMonth;
            monthSalary(inputPayPerMonth);
        }

        FullTime() {
            payPerMonth = 1000;
            monthSalary(1000);
        }

        private double getPayPerMonth() { return this.payPerMonth; }
        public void setPayPerMonth(double payPerMonth) { this.payPerMonth=payPerMonth; }

        @Override
        public String toString() {
            String s = super.toString();
            return (s+ "     per month: "+this.getPayPerMonth());
        }

        void monthSalary(double income) {
            setAverageInMonth(income);
        }
    }


    public static void main(String[] args) {
            try {
                File fromFile = new File("D:\\КПИ\\3 курс\\Тестировка\\Third\\firm.txt");
                List<Worker> myList = readFromFile(fromFile);
                if (myList.isEmpty()) System.out.println("EMPTY FILE");
                else {
                    printDataBase(myList);
                    File toFile = new File("D:\\КПИ\\3 курс\\Тестировка\\Third\\my_firm.txt");
                    writeToFile(myList, toFile);
                    System.out.println("\n\nSort");
                    sortDataBase(myList);
                    System.out.println("\n_______________________________________________");

                    //output 5 first names of sorted myList
                    System.out.println("Output 5 first names of sorted list");
                    for (int i = 0; i < 5; i++) System.out.println(myList.get(i).getName());
                    System.out.println("_______________________________________________");

                    //output 3 last id of sorted myList
                    System.out.println("Output 3 last id of sorted list");
                    for (int i = myList.size() - 1; i > myList.size() - 4; i--) System.out.println(myList.get(i).getId());
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("INCORRECT FILE");
            }
        }
        private static void sortDataBase(List<Worker> myList){
            Collections.sort(myList,Collections.reverseOrder());
            for (Worker aMyList : myList) {
                System.out.println(aMyList.getId() + " " + aMyList.getName() + " average: " + aMyList.getAverageInMonth());
            }

        }

        private static void printDataBase(List<Worker> myList){
            for (Worker aMyList : myList) {
                System.out.println(aMyList.toString());
            }
        }

        private static void writeToFile(List<Worker> myList,File file){
            try {
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (Worker aMyList : myList) {
                    printWriter.print(aMyList.toString() + "\n");
                }
                printWriter.close();
            }
            catch (IOException i) {
                System.out.println("\nIOException occurred.");
            }
        }

        private static List<Worker>readFromFile(File file) throws FileNotFoundException {
            Scanner scanner = new Scanner(file, "ISO-8859-1");
            List<Worker> myList = new ArrayList<Worker>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                if (correctRecord(words)) {
                    if (words[3].equalsIgnoreCase("m")) {
                        FullTime f = new FullTime(words[1], Integer.parseInt(words[0]), Double.parseDouble(words[2]));
                        myList.add(f);
                    }
                    if (words[3].equalsIgnoreCase("h")) {
                        PartTime p = new PartTime(words[1], Integer.parseInt(words[0]), Double.parseDouble(words[2]));
                        myList.add(p);
                    }
                }
            }
            scanner.close();
            return myList;
        }

        private static boolean correctRecord(String[] words){
            if (words.length == 4)
                if (words[0].matches("[0-9]+") && words[2].matches("[0-9]+(\\.[0-9]*)?"))
                    if (words[1].matches("[a-zA-Z]+"))
                        if (words[3].equalsIgnoreCase("m") ||words[3].equalsIgnoreCase("h"))
                            return true;
            return false;
        }
}
