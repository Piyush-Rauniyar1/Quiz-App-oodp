package model;

/**
 * Represents a person's name with support for First, Middle, and Last names.
 * Developed for 5CS019 Object Oriented Design and Programming.
 * @author Piyush
 * @version 2.0
 */
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    // Constructor 1: First, Middle, Last
    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Constructor 2: First, Last (Middle name defaults to empty)
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.middleName = "";
        this.lastName = lastName;
    }

    public String getFullName() {
        if (middleName.isEmpty()) return firstName + " " + lastName;
        return firstName + " " + middleName + " " + lastName;
    }

    public String getInitials() {
        String initials = "" + firstName.charAt(0);
        if (!middleName.isEmpty()) initials += middleName.charAt(0);
        initials += lastName.charAt(0);
        return initials.toUpperCase();
    }

    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
}