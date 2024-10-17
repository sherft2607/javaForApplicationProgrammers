import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shandon Herft
 */

public class Directory {

    public Directory() {
        Map<String, Student> andrewIdMap = new HashMap<String, Student>();
    }

    public void addStudent(Student s) {
        s.get("England");

    }

    public void deleteStudent(String andrewId)
    public Student searchByAndrewId(String andrewId)
    public List<Student> searchByFirstName(String firstName)
    public List<Student> searchByLastName(String lastName)
    public int size()

}