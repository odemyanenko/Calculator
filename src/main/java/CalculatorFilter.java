import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CalculatorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req;
        if (servletRequest instanceof HttpServletRequest) {
            req = (HttpServletRequest) servletRequest;
        } else {
            throw new IllegalArgumentException("Servlet request be instance of HttpServletRequest");
        }

        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {

            try {
                ParameterFromRequest pfr = new ParameterFromRequest(req);
                int a = pfr.getInt("a");
                int b = pfr.getInt("b");

                String command = req.getParameter("op").toUpperCase();

                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e) {
                servletResponse.getWriter().println(e.getMessage());
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

//        final Set<String> allowedCommands = new HashSet<String>(Arrays.asList("ADD", "MIN", "MULT", "DIV"));
//
//        String responseMessage = "";
//
//        try {
//
//
//            int result = 0;
//            String operation = "";
//
//            switch (command) {
//                case "SUM":
//                    operation = "+";
//                    result = a + b;
//                    break;
//                case "MIN":
//                    operation = "-";
//                    result = a - b;
//                    break;
//                case "MULT":
//                    operation = "*";
//                    result = a * b;
//                    break;
//                case "DIV":
//                    operation = "/";
//                    result = a / b;
//                    break;
//            }
//            responseMessage = String.format("%d %s %d = %d\n", a, operation, b, result);
//        } catch (ArithmeticException e) {
//            responseMessage = "You can't devide zero!";
//        }
//        PrintWriter writer = resp.getWriter();
//        writer.println(responseMessage);
    }

    @Override
    public void destroy() {

    }
}
