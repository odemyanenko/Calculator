import javax.servlet.http.HttpServlet;

public class ServletLogout extends HttpServlet {
    private final UserStorage security;

    public ServletLogout(UserStorage security) {
        this.security = security;
    }
}
