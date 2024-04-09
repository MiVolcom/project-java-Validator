package hexlet.code;

public class StringSchema {
    private String string;

    public boolean required() {
        return string != null || string != "";
    }
    public boolean contains(String subString) {
        return string.contains(subString);
    }
    public boolean minLength(int number) {
        return string.length() >= number;
    }

}
