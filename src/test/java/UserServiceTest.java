import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    public void shouldCreateUser() {
        UserService userService = new UserService();
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
        Assertions.assertTrue(userService.getAllUsers().stream()
                .filter(user -> user.getFirstName().equals("Test") && user.getLastName().equals("Testko") && user.getEmail().equals("test@testko.hr") && user.getPhoneNo().equals("0941243546"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should not create user when first name is null!")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser(null, "Testko", "test@testko.hr", "0941243546");
        });
    }

    @Test
    @DisplayName("Should not create user when last name is null!")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", null, "test@testko.hr", "0941243546");
        });
    }

    @Test
    @DisplayName("Should not create user when email is null!")
    public void shouldThrowRuntimeExceptionWhenEmailIsNull() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", "Testko", null, "0941243546");
        });
    }

    @Test
    @DisplayName("Should not create user when email don't contain @!!")
    public void shouldThrowRuntimeExceptionWhenEmailDontContainAt() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", "Testko", "test.testko.hr", "0941243546");
        });
    }

    @Test
    @DisplayName("Should not create user when phone number is null!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoIsNull() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", "Testko", "test@testko.hr", null);
        });
    }

    @Test
    @DisplayName("Should not create user when phone number include letters!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoIncludeLetters() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", "Testko", "test@testko.hr", "0123sads4564");
        });
    }

    @Test
    @DisplayName("Should not create user when phone number don't start with 0!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoDontStartWithZero() {
        UserService userService = new UserService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser("Test", "Testko", "test@testko.hr", "1234564");
        });
    }

}