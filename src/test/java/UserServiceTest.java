import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    UserService userService;

    @BeforeAll
    public void setupAll() {
        System.out.println("Should print before all tests!");
    }

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    @Test
    @DisplayName("Should create user!")
    public void shouldCreateUser() {
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
        Assertions.assertTrue(userService.getAllUsers().stream()
                .anyMatch(user -> user.getFirstName().equals("Test") &&
                        user.getLastName().equals("Testko") &&
                        user.getEmail().equals("test@testko.hr") &&
                        user.getPhoneNo().equals("0941243546")));
    }

    @Test
    @DisplayName("Disabled should create user!")
    @Disabled
    public void disabledShouldCreateUser() {
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
        Assertions.assertTrue(userService.getAllUsers().stream()
                .anyMatch(user -> user.getFirstName().equals("Test") &&
                        user.getLastName().equals("Testko") &&
                        user.getEmail().equals("test@testko.hr") &&
                        user.getPhoneNo().equals("0941243546")));
    }

    @Test
    @DisplayName("Should not create user when first name is null!")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser(null, "Testko", "test@testko.hr", "0941243546")
        );
    }

    @Test
    @DisplayName("Should not create user when last name is null!")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser("Test", null, "test@testko.hr", "0941243546")
        );
    }

    @Test
    @DisplayName("Should not create user when email is null!")
    public void shouldThrowRuntimeExceptionWhenEmailIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser("Test", "Testko", null, "0941243546")
        );
    }

    @Test
    @DisplayName("Should not create user when email don't contain @!!")
    public void shouldThrowRuntimeExceptionWhenEmailDontContainAt() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser("Test", "Testko", "test.testko.hr", "0941243546")
        );
    }

    @Test
    @DisplayName("Should not create user when phone number is null!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser("Test", "Testko", "test@testko.hr", null)
        );
    }

    @Test
    @DisplayName("Should not create user when phone number include letters!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoIncludeLetters() {
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.addUser("Test", "Testko", "test@testko.hr", "0123sads4564")
        );
    }

    @Test
    @DisplayName("Should not create user when phone number don't start with 0!")
    public void shouldThrowRuntimeExceptionWhenPhoneNoDontStartWithZero() {
        Assertions.assertThrows(RuntimeException.class, () ->
            userService.addUser("Test", "Testko", "test@testko.hr", "1234564")
        );
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should execute after each test!");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the test!");
    }

    @Test
    @DisplayName("Should create user only on MAC OS!")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on MAC OS")
    public void shouldCreateUserOnlyOnMac() {
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
        Assertions.assertTrue(userService.getAllUsers().stream()
                .anyMatch(user -> user.getFirstName().equals("Test") &&
                        user.getLastName().equals("Testko") &&
                        user.getEmail().equals("test@testko.hr") &&
                        user.getPhoneNo().equals("0941243546")));
    }

    @Test
    @DisplayName("Should not create user on Windows OS!")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    public void shouldNotCreateUserOnWin() {
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
        Assertions.assertTrue(userService.getAllUsers().stream()
                .anyMatch(user -> user.getFirstName().equals("Test") &&
                        user.getLastName().equals("Testko") &&
                        user.getEmail().equals("test@testko.hr") &&
                        user.getPhoneNo().equals("0941243546")));
    }

    @Test
    @DisplayName("Should test user creation on DEV environment!")
    public void shouldTestUserCreationOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    @DisplayName("Repeat user creation test 5 times!")
    @RepeatedTest(value = 5, name = "Repetition {currentRepetition}/{totalRepetitions}")
    public void shouldTestUserCreationRepeatedly() {
        userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    @DisplayName("User creation test using ValueSource!")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0987654321", "012345687"})
    public void shouldTestUserCreationUsingValueSource(String phoneNo) {
        userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    @DisplayName("User creation test using MethodSource!")
    @ParameterizedTest
    @MethodSource(value = "phoneNoList")
    public void shouldTestUserCreationUsingMethodSource(String phoneNo) {
        userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    private static List<String> phoneNoList() {
        return Arrays.asList("0123456789", "025467899", "0321385641654");
    }

    @DisplayName("User creation test using CSV source!")
    @ParameterizedTest
    @CsvSource({"0123454", "04185469", "0478746"})
    public void shouldTestUserCreationUsingCsvSource(String phoneNo) {
        userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    @DisplayName("User creation test using CSV File source!")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestUserCreationUsingCsvFileSource(String email) {
        userService.addUser("Test", "Testko", email, "0123456789");
        Assertions.assertFalse(userService.getAllUsers().isEmpty());
        Assertions.assertEquals(1, userService.getAllUsers().size());
    }

    @Nested
    class RepeatedNestedClass {
        @DisplayName("Repeated nested class - Repeat user creation test 5 times!")
        @RepeatedTest(value = 5, name = "Repetition {currentRepetition}/{totalRepetitions}")
        public void shouldTestUserCreationRepeatedly() {
            userService.addUser("Test", "Testko", "test@testko.hr", "0941243546");
            Assertions.assertFalse(userService.getAllUsers().isEmpty());
            Assertions.assertEquals(1, userService.getAllUsers().size());
        }
    }

    @Nested
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    class ParametrizedNestedClass {
        @DisplayName("Parametrized nested class - User creation test using ValueSource!")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0987654321", "012345687"})
        public void shouldTestUserCreationUsingValueSource(String phoneNo) {
            userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
            Assertions.assertFalse(userService.getAllUsers().isEmpty());
            Assertions.assertEquals(1, userService.getAllUsers().size());
        }

        @DisplayName("Parametrized nested class - User creation test using MethodSource!")
        @ParameterizedTest
        @MethodSource(value = "phoneNoList")
        public void shouldTestUserCreationUsingMethodSource(String phoneNo) {
            userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
            Assertions.assertFalse(userService.getAllUsers().isEmpty());
            Assertions.assertEquals(1, userService.getAllUsers().size());
        }

        private List<String> phoneNoList() {
            return Arrays.asList("0123456789", "025467899", "0321385641654");
        }

        @DisplayName("Parametrized nested class - User creation test using CSV source!")
        @ParameterizedTest
        @CsvSource({"0123454", "04185469", "0478746"})
        public void shouldTestUserCreationUsingCsvSource(String phoneNo) {
            userService.addUser("Test", "Testko", "test@testko.hr", phoneNo);
            Assertions.assertFalse(userService.getAllUsers().isEmpty());
            Assertions.assertEquals(1, userService.getAllUsers().size());
        }

        @DisplayName("Parametrized nested class - User creation test using CSV File source!")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestUserCreationUsingCsvFileSource(String email) {
            userService.addUser("Test", "Testko", email, "0123456789");
            Assertions.assertFalse(userService.getAllUsers().isEmpty());
            Assertions.assertEquals(1, userService.getAllUsers().size());
        }
    }

}