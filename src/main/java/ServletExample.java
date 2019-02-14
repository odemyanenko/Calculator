import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;


public class ServletExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap());

        Cookie[] cookies = req.getCookies();
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String sn = names.nextElement();
            String sv = req.getHeader(sn);
            System.out.printf("%s: %s\n", sn, sv);
        }
        System.out.println();



        BufferedReader br = new BufferedReader(new FileReader(new File("1.txt")));
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        PrintWriter writer = resp.getWriter();
//        writer.println(br.);
        writer.println("Hello");
        //resp.setStatus(404);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("POST");
    }
}
