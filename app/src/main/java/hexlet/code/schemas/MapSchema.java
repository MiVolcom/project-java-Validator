package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema {
    public MapSchema<T> required() {
        addPredicate("required", n -> n instanceof Map<?, ?> && n != null);
        required = true;
        return this;
    }
    public MapSchema<T> sizeof(int size) {
        addPredicate("sizeof", n -> n instanceof Map<?, ?> map && map.size() == size);
        return this;
    }
    public MapSchema<T> shape(Map<String, BaseSchema> schemas) {
        addPredicate("shape", value -> value instanceof Map<?, ?> map && schemas.keySet().stream()
                .allMatch(key -> {
                    Object valueInput = map.get(key);
                    return schemas.get(key).isValid(valueInput);
                })
        );
        return this;
    }
//    public MapSchema<T> shape(Map<String, BaseSchema<T>> schemas) {
//        addPredicate("shape", value -> value instanceof Map<?, ?> map && schemas.entrySet().stream()
//                .allMatch(schema -> {
//                    return map.entrySet().stream()
//                            .allMatch(m -> schema.getValue().isValid((T) m.getValue()));
//                }));
//        return this;
//    }
}

