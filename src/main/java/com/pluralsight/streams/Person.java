package com.pluralsight.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Person {
    String firstName;
    String lastName;
    int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    private static List<Person> createRandomPeopleList(int numberOfPeople) {
        List<Person> people = new ArrayList<>();
        Random random = new Random();

        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

        for (int i = 0; i < numberOfPeople; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            int age = random.nextInt(40) + 20; // Random age between 20 and 59

            people.add(new Person(firstName, lastName, age));
        }

        return people;
    }

    public static void main(String[] args) {
        List<Person> people = createRandomPeopleList(10);
        Scanner scanner = new Scanner(System.in);

        // Prompting user for a search name
        System.out.print("Enter a name to search: ");
        String searchName = scanner.nextLine();

        // Finding matching names using Java Streams
        List<Person> matchingPeople = people.stream()
                .filter(person -> person.firstName.equalsIgnoreCase(searchName) || person.lastName.equalsIgnoreCase(searchName))
                .collect(Collectors.toList());

        // Display matching people
        if (matchingPeople.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println("Matching people:");
            matchingPeople.forEach(person -> System.out.println(person.firstName + " " + person.lastName));
        }

        // Average age
        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        // Oldest person's age
        int oldestAge = people.stream()
                .mapToInt(Person::getAge)
                .max()
                .orElse(0);

        // Youngest person's age
        int youngestAge = people.stream()
                .mapToInt(Person::getAge)
                .min()
                .orElse(0);

        // Display results
        System.out.println("Average age: " + averageAge);
        System.out.println("Oldest person's age: " + oldestAge);
        System.out.println("Youngest person's age: " + youngestAge);
    }
}
