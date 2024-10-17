/**
 * @author Shandon Herft
 */

public class Student {
    /**first name.*/
    private String firstName;
    /**last name.*/
    private String lastName;
    /**andrew id.*/
    private final String andrewId;
    /**phone number.*/
    private String phoneNumber;

    public Student(String andrewId) {
        if (andrewId == null || andrewId.trim().isEmpty()) {
            throw new IllegalArgumentException("Andrew ID cannot be null or empty");
        }
        this.andrewId = andrewId;
    }

    public String getAndrewId() {
        return andrewId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Andrew ID: " + andrewId + ", Phone Number: " + phoneNumber + ")";
    }
}
