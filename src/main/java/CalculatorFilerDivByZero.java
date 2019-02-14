import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CalculatorFilerDivByZero implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            int b = pfr.getInt("p");
            String op = pfr.getString("op");
            if ("DIV".equalsIgnoreCase(op) && b == 0) {
                servletResponse.getWriter().println("You can't devide zero");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
