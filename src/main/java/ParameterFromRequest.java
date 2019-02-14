import javax.servlet.http.HttpServletRequest;

public class ParameterFromRequest {
    private final HttpServletRequest req;

    public int getInt(String name) {
        if (req.getParameter(name) == null) {
            throw new IllegalStateException(String.format("Parameter %s is missing", name));
        }
        return Integer.parseInt(req.getParameter(name));
    }

    public String getString(String name) {
        if (req.getParameter(name) == null) {
            throw new IllegalStateException(String.format("Parameter %s is missing", name));
        }
        return req.getParameter(name);
    }

    public ParameterFromRequest(HttpServletRequest req) {
        this.req = req;
    }
}
