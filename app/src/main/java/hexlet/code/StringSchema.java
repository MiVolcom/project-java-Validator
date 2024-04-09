package hexlet.code;

import java.util.function.Predicate;

public class StringSchema {

    String string;
    String subString;
    int number;
    public StringSchema(String string) {
        this.string = string;
    }

    public String required() {
        Predicate<String> req = s -> s.equals("") && s.equals(null);
        return string;
    }
    public String contains(String subString) {
        Predicate<String> con = s -> s.contains(subString);
        return subString;
    }
    public int minLength(int number) {
        Predicate<Integer> min = n -> string.length() >= number;
        return number;
    }
    public boolean isValid(String str) {


    }
}
