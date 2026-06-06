import java.util.Scanner;

// Step 1: Base class Person
class Person {
    String name;
    int age;

    // Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void showRole() {
        System.out.println("I am a person with a general role.");
    }

    void showDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Step 2: Teacher class extends Person
class Teacher extends Person {
    String subject;
    double salary;

    // Constructor
    Teacher(String name, int age, String subject, double salary) {
        super(name, age); // call Person constructor
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    void showRole() {
        System.out.println("I am a Teacher. I teach students.");
    }

    void showTeacherDetails() {
        showDetails();
        System.out.println("Subject: " + subject + ", Salary: " + salary);
    }
}

// Step 3: Student class extends Person
class Student extends Person {
    int rollNumber;
    String course;

    // Constructor
    Student(String name, int age, int rollNumber, String course) {
        super(name, age); // call Person constructor
        this.rollNumber = rollNumber;
        this.course = course;
    }

    @Override
    void showRole() {
        System.out.println("I am a Student. I study subjects.");
    }

    void showStudentDetails() {
        showDetails();
        System.out.println("Roll No: " + rollNumber + ", Course: " + course);
    }
}

// Step 4, 5, 6: Test class with main method
public class school {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read teacher details from user input
        System.out.print("Enter teacher name: ");
        String teacherName = scanner.nextLine();
        System.out.print("Enter teacher age: ");
        int teacherAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter teacher subject: ");
        String teacherSubject = scanner.nextLine();
        System.out.print("Enter teacher salary: ");
        double teacherSalary = Double.parseDouble(scanner.nextLine());

        Teacher t1 = new Teacher(teacherName, teacherAge, teacherSubject, teacherSalary);

        // Read student details from user input
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student age: ");
        int studentAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter student roll number: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter student course: ");
        String studentCourse = scanner.nextLine();

        Student s1 = new Student(studentName, studentAge, rollNumber, studentCourse);

        // Polymorphic array of Person (Step 4)
        Person[] people = new Person[2];
        people[0] = t1; // Teacher object
        people[1] = s1; // Student object

        // Dynamic method dispatch: correct showRole() called at runtime (Step 5)
        for (Person p : people) {
            p.showRole();  // polymorphic call
        }

        // Extra: show specific details (optional)
        System.out.println("--- Details ---");
        t1.showTeacherDetails();
        s1.showStudentDetails();

        scanner.close();
    }
}