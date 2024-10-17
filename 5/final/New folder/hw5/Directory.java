import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shandon Herft
 */

public class Directory {
    /**andrew id to student.*/
    private Map<String, Student> andrewIdMap;
    /**first name to student list.*/
    private Map<String, List<Student>> firstNameMap;
    /**last name to student list.*/
    private Map<String, List<Student>> lastNameMap;

    public Directory() {
        andrewIdMap = new HashMap<String, Student>();
        firstNameMap = new HashMap<String, List<Student>>();
        lastNameMap = new HashMap<String, List<Student>>();
    }

    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (andrewIdMap.containsKey(s.getAndrewId())) {
            throw new IllegalArgumentException("Student Andrew ID is already present");
        }

        andrewIdMap.put(s.getAndrewId(), s);

        if (!firstNameMap.containsKey(s.getFirstName())) {
            firstNameMap.put(s.getFirstName(), new ArrayList<>());
        }
        firstNameMap.get(s.getFirstName()).add(s);

        if (!lastNameMap.containsKey(s.getLastName())) {
            lastNameMap.put(s.getLastName(), new ArrayList<>());
        }
        lastNameMap.get(s.getLastName()).add(s);
    }

    public void deleteStudent(String andrewId) {
        Student student = andrewIdMap.get(andrewId);
        if (student == null) {
            throw new IllegalArgumentException("No student with Andrew ID found");
        }

        andrewIdMap.remove(andrewId);

        List<Student> firstNameList = firstNameMap.get(student.getFirstName());
        firstNameList.remove(student);

        if (firstNameList.isEmpty()) {
            firstNameMap.remove(student.getFirstName());
        }

        List<Student> lastNameList = lastNameMap.get(student.getLastName());
        lastNameList.remove(student);

        if (lastNameList.isEmpty()) {
            lastNameMap.remove(student.getLastName());
        }
    }

    public Student searchByAndrewId(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException("Andrew ID cannot be null");
        }
        return andrewIdMap.get(andrewId);
    }

    public List<Student> searchByFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        return firstNameMap.getOrDefault(firstName, new ArrayList<>());
    }

    public List<Student> searchByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
        return lastNameMap.getOrDefault(lastName, new ArrayList<>());
    }

    public int size() {
        return andrewIdMap.size();
    }
}
