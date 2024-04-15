package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {

    protected boolean required;
    Map<String, Predicate> predicates = new LinkedHashMap();


    public StringSchema required() {
        addPredicate("required", s -> s instanceof String && !s.equals("") && s != null);
        required = true;
        return this;
    }

    public StringSchema contains(String subString) {
        addPredicate("contains", s -> s.toString().contains(subString));
        return this;
    }

    public StringSchema minLength(int number) {
        addPredicate("minLength", n -> n.toString().length() >= number);
        return this;
    }
    public boolean isValid(Object obj) {
        if (obj == null || obj == "") {
            return !required;
        }
        for (var predicate : predicates.entrySet()) {
            if (!predicate.getValue().test(obj)) {
                return false;
            }
        }
        return true;
    }
    public void addPredicate(String name, Predicate predicate) {
        predicates.put(name, predicate);
    }
}
