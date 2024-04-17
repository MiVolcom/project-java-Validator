package hexlet.code.schemas;

public final class StringSchema<T> extends BaseSchema {

    public StringSchema<T> required() {
        addPredicate("required", s -> !s.equals("") && s != null);
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
}
