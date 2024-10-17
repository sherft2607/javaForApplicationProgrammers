public class Main {
    public static void main(String[] args) {
        // Create a new directory
        Directory directory = new Directory();

        // Create a student and add them to the directory
        Student student1 = new Student("eunsunl");
        student1.setFirstName("Terry");
        student1.setLastName("Lee");
        student1.setPhoneNumber("412-268-1078");
        directory.addStudent(student1);

        // Add another student
        Student student2 = new Student("johnd");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setPhoneNumber("555-123-4567");
        directory.addStudent(student2);

        // Search by Andrew ID
        System.out.println(directory.searchByAndrewId("eunsunl"));

        // Search by first name
        List<Student> studentsWithFirstNameJohn = directory.searchByFirstName("John");
        for (Student s : studentsWithFirstNameJohn) {
            System.out.println(s);
        }

        // Search by last name
        List<Student> studentsWithLastNameLee = directory.searchByLastName("Lee");
        for (Student s : studentsWithLastNameLee) {
            System.out.println(s);
        }

        // Delete a student
        directory.deleteStudent("eunsunl");

        // Get the size of the directory
        System.out.println("Directory size: " + directory.size());
    }
}
