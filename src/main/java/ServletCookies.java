import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ServletCookies extends HttpServlet {
    private final String COOKIE_NAME = "My_cookie";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getPathInfo();
        List<Cookie> myCookies = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(COOKIE_NAME))
                .collect(Collectors.toList());

        PrintWriter w = resp.getWriter();
        switch (cmd) {
            case "/set":
                resp.addCookie(new Cookie(COOKIE_NAME, "Hello:" + (int)(Math.random()*100)));
                break;
            case "/get":
                myCookies.forEach(c -> w.printf("Cookie_name: %s, value: %s", c.getName(), c.getValue()));
                break;
            case "/rm":
                myCookies.stream()
                        .map((Function<Cookie, Cookie>) c -> new Cookie(c.getName(), c.getValue()) {{
                            setMaxAge(0);
                        }});
                break;
        }

        //getting values from cookies
//        Cookie[] cookies = req.getCookies();
//        if (cookies.length > 0) {
//            StringBuilder s = new StringBuilder();
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equalsIgnoreCase("A-User")) {
//                    s
//                            .append("Cookie_name:")
//                            .append(cookie.getName())
//                            .append(", cookie_value")
//                            .append(cookie.getValue());
//                }
//            }
//            resp.getWriter().printf(s.toString());
//        }
        //adding
//        resp.addCookie(new Cookie("A-User", "1"));

        //cookie removing
//        Cookie cookie = new Cookie("A-User", "1");
//        cookie.setMaxAge(0); //deleted 0 time exits
//        resp.addCookie(cookie);

        //resp.getWriter().println("CookieSet");
    }
}
