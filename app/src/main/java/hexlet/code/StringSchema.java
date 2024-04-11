package hexlet.code;

import java.util.function.Predicate;

public class StringSchema {
    String str;
    int number;
    String subString;

    Predicate<String> required = s -> s.equals("") && s.equals(null);
    Predicate<String> contains = s -> s.contains(subString);
    Predicate<Integer> minLength = n -> str.length() >= number;


    public boolean required() {
    return required.test(str);
    }

    public boolean contains(String subString) {
    return contains.test(subString);
    }

    public boolean minLength(int number) {
        return minLength.test(number);
    }
    public boolean isValid(String str) {
        if (required.test(str)) {
            return true;
        } else if (contains.test(str)) {
            return true;
        } else if (minLength.test(number)) {
            return true;
        } return false;
    }
}
