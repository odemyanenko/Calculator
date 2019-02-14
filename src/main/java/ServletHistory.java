import javax.servlet.http.HttpServlet;

public class ServletHistory extends HttpServlet {
    private final UserStorage security;

    public ServletHistory(UserStorage security) {
        this.security = security;
    }
}
