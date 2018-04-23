import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends ProjectDAO {
    private Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);

    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO \"User\" (login, password) VALUES (?, ?)";
        jdbcConnection = super.connect(jdbcConnection);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getLogin().trim());
        statement.setString(2, user.getPassword().trim());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        super.disconnect(jdbcConnection);
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();

        String sql = "SELECT * FROM \"User\"";

        jdbcConnection = super.connect(jdbcConnection);

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            User user = new User(id, login, password);
            listUser.add(user);
        }

        resultSet.close();
        statement.close();

        super.disconnect(jdbcConnection);

        return listUser;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM \"User\" WHERE user_id = ?";

        jdbcConnection = super.connect(jdbcConnection);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            user = new User(id, login, password);
        }

        resultSet.close();
        statement.close();
        super.disconnect(jdbcConnection);

        return user;
    }

    public User getUser(String login, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM \"User\" WHERE login = ? AND password = ?";

        jdbcConnection = super.connect(jdbcConnection);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("user_id");

            user = new User(id, login, password);
        }

        resultSet.close();
        statement.close();
        super.disconnect(jdbcConnection);

        return user;
    }


}
