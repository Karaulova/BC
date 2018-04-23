import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO extends ProjectDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public RatingDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;

    }

    public boolean insertRating(Rating rating) throws SQLException {
        String sql = "INSERT INTO \"Rating\" (user_id, steps) VALUES (?, ?)";
        jdbcConnection = super.connect(jdbcConnection);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, rating.getUser().getId());
        statement.setInt(2, rating.getSteps());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        super.disconnect(jdbcConnection);
        return rowInserted;
    }

    public List<Rating> listAllRatings() throws SQLException {
        List<Rating> listRating = new ArrayList<>();

        String sql = "SELECT * FROM \"Rating\"";

        jdbcConnection = super.connect(jdbcConnection);

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("rating_id");
            int steps = resultSet.getInt("steps");

            User user = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword).getUserById(resultSet.getInt("user_id"));
            Rating rating = new Rating(id, steps, user);
            listRating.add(rating);

        }

        resultSet.close();
        statement.close();

        super.disconnect(jdbcConnection);

        return listRating;
    }

    public Rating getRatingById(int id) throws SQLException {
        Rating rating = null;
        String sql = "SELECT * FROM \"Rating\" WHERE rating_id = ?";

        jdbcConnection = super.connect(jdbcConnection);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int steps = resultSet.getInt("steps");
            User user = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword).getUserById(resultSet.getInt("user_id"));
            rating = new Rating(id, steps, user);
        }

        resultSet.close();
        statement.close();
        super.disconnect(jdbcConnection);

        return rating;
    }

    public Rating getRatingByUser(User user) throws SQLException {
        Rating rating = null;
        String sql = "SELECT * FROM \"Rating\" WHERE user_id = ?";


        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int steps = resultSet.getInt("steps");
            int id = resultSet.getInt("rating_id");
            rating = new Rating(id, steps, user);
        }

        resultSet.close();
        statement.close();

        return rating;
    }

    public boolean updateRating(Rating rating2) throws SQLException {
        String sql = "UPDATE \"Rating\" SET steps = ?";
        sql += " WHERE user_id = ?";
        jdbcConnection = super.connect(jdbcConnection);
        Rating rating1 = getRatingByUser(rating2.getUser());
        int steps = Math.round((float) (rating2.getSteps() + rating1.getSteps()) / 2);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, steps);
        statement.setInt(2, rating2.getUser().getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        super.disconnect(jdbcConnection);
        return rowUpdated;
    }
}
