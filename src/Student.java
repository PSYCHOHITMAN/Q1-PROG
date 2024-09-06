import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    // Scanner for user input
    public static Scanner scanner = new Scanner(System.in);
    
    // ArrayList to store student records
    public static ArrayList<Student> studentDatabase = new ArrayList<>();

    // Student attributes
    private String studentId;
    private String name;
    private int age;
    private String email;
    private String course;

    // Constructor for Student class
    public Student(String studentId, String name, int age, String email, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    // Getters for student attributes
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }

    public static void main(String[] args) {
        // Display main application message
        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("*********************************");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        System.out.println("  ");

        // Get initial input from user
        String initialChoice = scanner.nextLine();
        if (!"1".equals(initialChoice)) {
            System.out.println("Exiting the application. Goodbye!");
            System.exit(0); // Exit if user doesn't choose '1'
        }

        // Loop for main menu
        while (true) {
            showMenu(); // Show menu options
            String choice = scanner.nextLine();

            // Handle user's choice
            switch (choice) {
                case "1":
                    SaveStudent(); // Capture new student
                    break;
                case "2":
                    SearchStudent(); // Search for a student
                    break;
                case "3":
                    DeleteStudent(); // Delete a student
                    break;
                case "4":
                    StudentReport(); // Print report of all students
                    break;
                case "5":
                    ExitStudentApplication(); // Exit the application
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid menu option");
            }
        }
    }

    // Method to display the menu
    private static void showMenu() {
        System.out.println("  ");
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Save a new student.");
        System.out.println("(2) Search for a student.");
        System.out.println("(3) Delete a student.");
        System.out.println("(4) Print student report.");
        System.out.println("(5) Exit application.");
        System.out.println("  ");
    }

    // Method to save a new student (SaveStudent)
    private static void SaveStudent() {
        System.out.println("SAVE A NEW STUDENT");
        System.out.println("*********************************");

        System.out.print("Enter the student id: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter the student name: ");
        String name = scanner.nextLine();

        int age = captureStudentAge(); // Capture and validate student age

        System.out.print("Enter the student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter the student course: ");
        String course = scanner.nextLine();

        // Create new student object and add to database
        Student student = new Student(studentId, name, age, email, course);
        studentDatabase.add(student);

        System.out.println("Student saved successfully.");
    }

    // Method to search for a student by ID (SearchStudent)
    private static void SearchStudent() {
        System.out.print("Enter the student id to search: ");
        String studentId = scanner.nextLine();

        // Find student by ID
        Student student = findStudentById(studentId);
        if (student != null) {
            // Display student details
            System.out.println("-----------------------------------------");
            System.out.println("STUDENT ID: " + student.getStudentId());
            System.out.println("STUDENT NAME: " + student.getName());
            System.out.println("STUDENT AGE: " + student.getAge());
            System.out.println("STUDENT EMAIL: " + student.getEmail());
            System.out.println("STUDENT COURSE: " + student.getCourse());
            System.out.println("-----------------------------------------");
        } else {
            // Student not found message
            System.out.println("-----------------------------------------");
            System.out.println("Student with Student ID: " + studentId + " was not found!");
            System.out.println("-----------------------------------------");
        }
    }

    // Method to delete a student by ID (DeleteStudent)
    private static void DeleteStudent() {
        System.out.print("Enter the student id to delete: ");
        String studentId = scanner.nextLine();

        System.out.println("Are you sure you want to delete student with ID: " + studentId + "? (y/n)");
        String confirmation = scanner.nextLine();

        if ("y".equalsIgnoreCase(confirmation)) {
            // Find and remove student if exists
            Student student = findStudentById(studentId);
            if (student != null) {
                studentDatabase.remove(student);
                System.out.println("-----------------------------------------");
                System.out.println("Student with Student Id: " + studentId + " was deleted!");
                System.out.println("-----------------------------------------");
            } else {
                // Student not found message
                System.out.println("-----------------------------------------");
                System.out.println("Student with Student Id: " + studentId + " was not found!");
                System.out.println("-----------------------------------------");
            }
        }
    }

    // Method to print a report of all students (StudentReport)
    private static void StudentReport() {
        if (studentDatabase.isEmpty()) {
            System.out.println("No students found.");
        } else {
            // Print details for each student
            System.out.println("STUDENT REPORT");
            System.out.println("*********************************");
            for (Student student : studentDatabase) {
                System.out.println("STUDENT ID: " + student.getStudentId());
                System.out.println("STUDENT NAME: " + student.getName());
                System.out.println("STUDENT AGE: " + student.getAge());
                System.out.println("STUDENT EMAIL: " + student.getEmail());
                System.out.println("STUDENT COURSE: " + student.getCourse());
                System.out.println("-----------------------------------------");
            }
        }
    }

    // Method to exit the application (ExitStudentApplication)
    private static void ExitStudentApplication() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0); // Exit the application
    }

    // Method to capture and validate student age
    private static int captureStudentAge() {
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the student age: ");
            String ageInput = scanner.nextLine();
            try {
                age = Integer.parseInt(ageInput);
                if (age < 16) { // Ensure age is valid
                    System.out.println("You have entered an incorrect student age!");
                    System.out.println("Please re-enter the student age >>");
                } else {
                    validAge = true; // Age is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect student age!");
                System.out.println("Please re-enter the student age >>");
            }
        }
        return age; // Return valid age
    }

    // Method to find a student by ID
    private static Student findStudentById(String studentId) {
        // Search through the student database
        for (Student student : studentDatabase) {
            if (student.getStudentId().equals(studentId)) {
                return student; // Return if found
            }
        }
        return null; // Return null if not found
    }
}
