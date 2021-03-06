import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    Map<String, User> userList = new ConcurrentHashMap<String, User>();

    public void addUser(String firstName, String lastName, String email, String phoneNo) {
        User user = new User(firstName, lastName, email, phoneNo);

        validateUser(user);
        checkIfUserAlreadyExists(user);
        userList.put(generateKey(user), user);
    }

    public Collection<User> getAllUsers() {
        return userList.values();
    }

    private void checkIfUserAlreadyExists(User user) {
        if (userList.containsKey(generateKey(user))) throw new RuntimeException("User already exists!");
    }

    private String generateKey(User user) {
        return String.format("%s-%s", user.getFirstName(), user.getLastName());
    }

    private void validateUser(User user) {
        user.validateFirstName();
        user.validateLastName();
        user.validateEmail();
        user.validatePhoneNo();
    }
}
