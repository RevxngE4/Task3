import java.util.List;

public interface UserRepository <T>{
    User findByEmailAndPassword(String login, String password);
}
