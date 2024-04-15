package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema<T> {

    protected boolean required;
    Map<String, Predicate<T>> predicates = new LinkedHashMap();


    public StringSchema<T> required() {
        addPredicate("required", s -> s instanceof String && !s.equals("") && s != null);
        required = true;
        return this;
    }

    public StringSchema<T> contains(String subString) {
        addPredicate("contains", s -> s.toString().contains(subString));
        return this;
    }

    public StringSchema<T> minLength(int number) {
        addPredicate("minLength", n -> n.toString().length() >= number);
        return this;
    }
    public boolean isValid(T value) {
        if (value == null || value == "") {
            return !required;
        }
        for (var predicate : predicates.entrySet()) {
            if (!predicate.getValue().test(value)) {
                return false;
            }
        }
        return true;
    }
    public void addPredicate(String name, Predicate<T> predicate) {
        predicates.put(name, predicate);
    }
}
