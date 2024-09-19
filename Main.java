import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person other) {
        return this.age - other.age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class DataProcessor {
    public List<Person> readFromFile(String fileName) {
        List<Person> people = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                people.add(new Person(name, age));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return people;
    }

    public void sortData(List<Person> people) {
        Collections.sort(people);
    }

    public void saveToFile(String fileName, List<Person> people) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();

            writer.write(name + "," + age);
            writer.newLine();

            people.add(new Person(name, age));
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        String fileName = "data.txt";

        List<Person> people = processor.readFromFile(fileName);
        
        System.out.println("Unsorted Data:");
        for (Person person : people) {
            System.out.println(person);
        }

        processor.sortData(people);
        System.out.println("\nSorted Data:");
        for (Person person : people) {
            System.out.println(person);
        }

        processor.saveToFile(fileName, people);

        System.out.println("\nUpdated Data:");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
