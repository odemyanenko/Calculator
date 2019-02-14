import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletCalculator extends HttpServlet {
    private final UserStorage security;

    public ServletCalculator(UserStorage security) {
        this.security = security;
    }

    private int convertParameter(String name, HttpServletRequest req) {
        if (req.getParameter(name) == null) {
            throw new IllegalStateException(String.format("Parameter %s is missing", name));
        }
        return Integer.parseInt(req.getParameter(name));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean checked =

        ParameterFromRequest pfr = new ParameterFromRequest(req);

        int a = pfr.getInt("a");
        int b = pfr.getInt("b");
        Operation command = Operation.valueOf(req.getParameter("op").toUpperCase());

        int result = 0;

        switch (command) {
            case ADD:
                result = a + b;
                break;
            case SUB:
                result = a - b;
                break;
            case MULT:
                result = a * b;
                break;
            case DIV:
                result = a / b;
                break;
        }
        PrintWriter writer = resp.getWriter();
        writer.printf("%d %s %d = %d\n", a, command.getOperSymbol(), b, result);
    }
}
