import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryLogin implements UserRepository {


    private Connection connection;
    private static final String SQl_SELECT_ALL_FROM_DRIVER = "SELECT * FROM usuarios WHERE login = ? AND password = ?";
    public UserRepositoryLogin(Connection connection){
        this.connection = connection;
    }

    @Override
    public User findByEmailAndPassword(String login, String password) {
        User result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQl_SELECT_ALL_FROM_DRIVER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("surname"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}

