import java.util.HashMap;

public class UserStorageHashMap implements UserStorage {
    private final HashMap<String, String> storage = new HashMap();

    @Override
    public void register(String name, String password) {

    }

    @Override
    public boolean chech(String name, String password) {
        return false;
    }
}
