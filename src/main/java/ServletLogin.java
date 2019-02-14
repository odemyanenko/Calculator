import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServletLogin extends HttpServlet {
    private final UserStorage security;

    private final String COOKIE_LOGIN = "my_login";
    private final String COOKIE_PASSWORD = "my_password";

    public ServletLogin(UserStorage security) {
        this.security = security;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cookie> myCookies = Arrays.stream(req.getCookies())
                .filter(c -> (c.getName().equalsIgnoreCase(COOKIE_LOGIN) || c.getName().equalsIgnoreCase(COOKIE_PASSWORD)))
                .collect(Collectors.toList());

        myCookies.stream()
                .map((Function<Cookie, Cookie>) c -> new Cookie(c.getName(), c.getValue()) {{
                    setMaxAge(0);
                }});

        Files.copy(
                Paths.get("form_login.html"),
                resp.getOutputStream());
    }
//Сюда прийдет запрос, после того как отправим форму
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String login =  pfr.getString("login");
        String password = pfr.getString("password");

        resp.addCookie(new Cookie(COOKIE_LOGIN, login));
        resp.addCookie(new Cookie(COOKIE_PASSWORD, password));

        resp.sendRedirect("/auth");
    }
}
