

public class Rating {
    private int id;
    private int steps;
    private User user;

    public Rating(int id, int steps, User user) {
        this(steps,user);
        this.id = id;
    }

    public Rating(int steps, User user) {
        this.steps = steps;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
