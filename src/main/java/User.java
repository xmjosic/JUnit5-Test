public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

    public User(String firstName, String lastName, String email, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    public void validateFirstName() {
        if (this.firstName.isBlank()) throw new RuntimeException("First name cannot be null or empty!");
    }

    public void validateLastName() {
        if (this.lastName.isBlank()) throw new RuntimeException("Last name cannot be null or empty!");
    }

    public void validateEmail() {
        if (this.email.isBlank()) {
            throw new RuntimeException("Email cannot be null or empty!");
        }
        if (!this.email.contains("@")) {
            throw new RuntimeException("Email not correct!");
        }
    }

    public void validatePhoneNo() {
        if (this.phoneNo.isBlank()) {
            throw new RuntimeException("Phone number cannot be null or empty!");
        }
        if (!this.phoneNo.matches("\\d+")) {
            throw new RuntimeException("Phone number can contain only digits!");
        }
        if (!this.phoneNo.startsWith("0")) {
            throw new RuntimeException("Phone number should start with 0!");
        }
    }
}
