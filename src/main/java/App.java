import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {
        UserStorage security = new UserStorageHashMap();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new ServletLogin(security)), "/login/*");
        handler.addServlet(new ServletHolder(new ServletLogout(security)), "/logout/*");
        handler.addServlet(new ServletHolder(new ServletHistory(security)), "/history/*");
        handler.addServlet(new ServletHolder(new ServletAuth(security)), "/auth/*");
        handler.addServlet(new ServletHolder(new ServletCalculator(security)), "/calc/*");

       // handler.addFilter(CalculatorFilter.class, "/login/*", EnumSet.of(DispatcherType.INCLUDE));
//        handler.addFilter(new FilterHolder(new CalculatorFilter(security)), "/calc/", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST))
        handler.addFilter(CalculatorFilter.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilerDivByZero.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
