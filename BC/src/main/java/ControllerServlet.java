
import org.postgresql.util.PSQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private Handing handing;
    private RatingDAO ratingDAO;
    List<CheckValue> checkValues;
    User newUser;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        ratingDAO = new RatingDAO(jdbcURL, jdbcUsername, jdbcPassword);
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
        handing = new Handing();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/logIn":
                    logIn(request, response);
                    break;
                case "/signUp":
                    signUp(request, response);
                    break;
                case "/loged":
                    loged(request, response);
                    break;
                case "/sign":
                    sign(request, response);
                    break;
                case "/logOut":
                    logOut(request, response);
                    break;
                case "/checkGame":
                    checkGame(request, response);
                    break;
                case "/rating":
                    getRating(request, response);
                    break;
                case "/newGame":
                    newGame(request, response);
                    break;
                default:
                    getRating(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void checkGame(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {

            User user = (User) session.getAttribute("user");
            String enterValue = request.getParameter("enterValue");
            enterValue = enterValue.substring(0, 4);
            Boolean flag = false;
            int entValue = Integer.parseInt(enterValue);
            int forParseInt = 1000;
            List<Integer> enterValueList = new ArrayList<>();
            if ((entValue / forParseInt) < 1) {
                forParseInt /= 10;
                flag = true;
            }
            for (int i = 0; i < 4; i++) {
                if (flag && i == 0) {
                    enterValueList.add(0);
                } else {
                    enterValueList.add(entValue / forParseInt);
                    entValue %= forParseInt;
                    forParseInt /= 10;
                }
            }
            int bulls = handing.getBulls(enterValueList);
            int cows = handing.getCows(enterValueList);
            checkValues.add(new CheckValue(bulls, cows, enterValue));
            RequestDispatcher dispatcher;
            if (bulls != 4) {
                session.setAttribute("listCheckValue", checkValues);
                session.setAttribute("countTry", checkValues.size());
                dispatcher = request.getRequestDispatcher("game.jsp");

            } else {
                User userForRating = userDAO.getUser(user.getLogin(), user.getPassword());
                Rating rating = new Rating(checkValues.size(), userForRating);
                session.setAttribute("win", "ok");
                if (newUser != null) {
                    ratingDAO.insertRating(rating);
                    newUser = null;
                } else ratingDAO.updateRating(rating);
                List<Rating> listRating = ratingDAO.listAllRatings();
                request.setAttribute("listRatingValue", listRating);
                checkValues = new ArrayList<>();
                dispatcher = request.getRequestDispatcher("rating.jsp");
            }
            dispatcher.forward(request, response);

        }
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("logIn.jsp");
        request.setAttribute("user", "user");
        dispatcher.forward(request, response);
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("logIn.jsp");
        dispatcher.forward(request, response);
    }

    private void sign(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        newUser = new User(login, password);
        userDAO.insertUser(newUser);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", newUser);
        restartGame();
        session.setAttribute("listCheckValue", checkValues);
        RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
        dispatcher.forward(request, response);
    }

    private void loged(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userDAO.getUser(login, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        if (session != null && session.getAttribute("user") != null) {
            restartGame();
            session.setAttribute("listCheckValue", checkValues);
            RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        session.setAttribute("user", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void getRating(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {

            List<Rating> listRating = ratingDAO.listAllRatings();
            request.setAttribute("listRatingValue", listRating);
            RequestDispatcher dispatcher = request.getRequestDispatcher("rating.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void newGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            restartGame();
            session.setAttribute("listCheckValue", checkValues);
            RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void restartGame() {
        handing.getValue();
        checkValues = new ArrayList<>();
    }


}
