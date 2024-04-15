package hexlet.code;

public class Validator {
    Validator validator;
    public StringSchema string() {
        return new StringSchema();
    }
    public NumberSchema number() {
        return new NumberSchema();
    }
}