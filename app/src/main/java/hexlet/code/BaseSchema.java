package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected boolean required;
    Map<String, Predicate<T>> predicates = new LinkedHashMap();
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
