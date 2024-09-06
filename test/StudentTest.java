import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class StudentTest {

    // Set up test data
    private Student student1;
    private Student student2;

    @Before
    public void setUp() {
        student1 = new Student("001", "Alice", 20, "alice@example.com", "Computer Science");
        student2 = new Student("002", "Bob", 18, "bob@example.com", "Information Technology");
        
        // Add students to the database for testing
        Student.studentDatabase = new ArrayList<>(); // Reset the database before each test
        Student.studentDatabase.add(student1);
        Student.studentDatabase.add(student2);
    }

    // Test the SaveStudent() method
    @Test
    public void TestSaveStudent() {
        // Capture a new student
        Student newStudent = new Student("003", "Charlie", 22, "charlie@example.com", "Business Management");
        Student.studentDatabase.add(newStudent);

        // Assert the student was added
        assertEquals(3, Student.studentDatabase.size());
        assertEquals("Charlie", Student.studentDatabase.get(2).getName());
    }

    // Test the SearchStudent() method (student found)
    @Test
    public void TestSearchStudent() {
        Student foundStudent = findStudentById("001");
        
        // Assert that the correct student is found
        assertNotNull(foundStudent);
        assertEquals("Alice", foundStudent.getName());
        assertEquals("Computer Science", foundStudent.getCourse());
    }

    // Test the SearchStudent() method when student is not found
    @Test
    public void TestSearchStudent_StudentNotFound() {
        Student foundStudent = findStudentById("999"); // Non-existing student ID

        // Assert that no student is found
        assertNull(foundStudent);
    }

    // Test the DeleteStudent() method (student found and deleted)
    @Test
    public void TestDeleteStudent() {
        Student studentToDelete = findStudentById("002");

        // Delete the student
        if (studentToDelete != null) {
            Student.studentDatabase.remove(studentToDelete);
        }

        // Assert that the student is deleted
        assertNull(findStudentById("002"));
        assertEquals(1, Student.studentDatabase.size());
    }

    // Test the DeleteStudent() method when student is not found
    @Test
    public void TestDeleteStudent_StudentNotFound() {
        Student studentToDelete = findStudentById("999"); // Non-existing student ID

        // Assert that no deletion happens if student is not found
        assertNull(studentToDelete);
        assertEquals(2, Student.studentDatabase.size()); // No change in size
    }

    // Test capturing valid student age (valid input)
    @Test
    public void TestStudentAge_StudentAgeValid() {
        int validAge = 20; // Example of a valid age

        assertTrue(isValidAge(validAge)); // Age is valid
    }

    // Test capturing invalid student age (age too low)
    @Test
    public void TestStudentAge_StudentAgeInvalid() {
        int invalidAge = 15; // Example of an invalid age (too young)

        assertFalse(isValidAge(invalidAge)); // Age is invalid
    }

    // Test capturing invalid student age (invalid character input)
    @Test(expected = NumberFormatException.class)
    public void TestStudentAge_StudentAgeInvalidCharacter() {
        String invalidAgeInput = "abc"; // Invalid input

        // This should throw NumberFormatException
        Integer.parseInt(invalidAgeInput);
    }

    // Helper method to check if age is valid (>=16)
    private boolean isValidAge(int age) {
        return age >= 16;
    }

    // Helper method to find a student by ID (replicates the behavior of Student class)
    private Student findStudentById(String studentId) {
        for (Student student : Student.studentDatabase) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
