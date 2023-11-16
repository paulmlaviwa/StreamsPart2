package com.pluralsight.streams;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        int[] ages = {23, 34, 12, 16, 33, 15, 70, 19, 29, 30};

        List<Person> people = createPeopleList(firstNames, lastNames, ages);
        Scanner scanner = new Scanner(System.in);

        // Prompting user for a search name
        System.out.print("Enter a name to search: ");
        String searchName = scanner.nextLine();

        // Finding matching names using
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

        // Calculating average age using Java Streams
        double averageAge = people.stream()
                .mapToInt(person -> person.age)
                .average()
                .orElse(0);

        // Finding the age of the oldest person
        int oldestAge = people.stream()
                .mapToInt(person -> person.age)
                .max()
                .orElse(0);

        // Finding the age of the youngest person
        int youngestAge = people.stream()
                .mapToInt(person -> person.age)
                .min()
                .orElse(0);

        // Display results
        System.out.println("Average age: " + averageAge);
        System.out.println("Oldest person's age: " + oldestAge);
        System.out.println("Youngest person's age: " + youngestAge);
    }

    private static List<Person> createPeopleList(String[] firstNames, String[] lastNames, int[] ages) {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < firstNames.length; i++) {
            people.add(new Person(firstNames[i], lastNames[i], ages[i]));
        }

        return people;
    }
}
