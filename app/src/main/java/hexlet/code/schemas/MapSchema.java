package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema {
    public MapSchema<T> required() {
        addPredicate("required", n -> n != null);
        required = true;
        return this;
    }
    public MapSchema<T> sizeof(int size) {
        addPredicate("sizeof", n -> ((Map<?, ?>) n).size() == size);
        return this;
    }
    public MapSchema<T> shape(Map<String, BaseSchema> schemas) {
        addPredicate("shape", value -> schemas.keySet().stream()
                .allMatch(key -> {
                    Object valueInput = ((Map<?, ?>) value).get(key);
                    return schemas.get(key).isValid(valueInput);
                }));
        return this;
    }
}

