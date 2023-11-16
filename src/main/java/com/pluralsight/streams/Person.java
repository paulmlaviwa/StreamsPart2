package com.pluralsight.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        List<Person> people = createRandomPeopleList(10);
        Scanner scanner = new Scanner(System.in);

        // Prompting user for a search name
        System.out.print("Enter a name to search: ");
        String searchName = scanner.nextLine();

        // Finding matching names
        List<Person> matchingPeople = new ArrayList<>();
        for (Person person : people) {
            if (person.firstName.equalsIgnoreCase(searchName) || person.lastName.equalsIgnoreCase(searchName)) {
                matchingPeople.add(person);
            }
        }

        System.out.println("Matching people:");
        for (Person person : matchingPeople) {
            System.out.println(person.firstName + " " + person.lastName);
        }

        // Average, oldest, and youngest age
        int totalAge = 0;
        int oldestAge = Integer.MIN_VALUE; //Initialized to the smallest possible integer value
        int youngestAge = Integer.MAX_VALUE; //Initialized to the largest possible integer value

        for (Person person : people) {
            totalAge += person.age;

            if (person.age > oldestAge) {
                oldestAge = person.age;
            }

            if (person.age < youngestAge) {
                youngestAge = person.age;
            }
        }

        // Average age
        double averageAge = (double) totalAge / people.size();

        // Display results
        System.out.println("Average age: " + averageAge);
        System.out.println("Oldest person's age: " + oldestAge);
        System.out.println("Youngest person's age: " + youngestAge);
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
}
