public interface UserStorage {
    void register(String name, String password);
    public boolean chech(String name, String password);
}
