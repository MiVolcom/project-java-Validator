package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSchema<T> extends BaseSchema {
    public MapSchema<T> required() {
        addPredicate("required", n -> n instanceof Map<?,?> && n != null);
        required = true;
        return this;
    }
    public MapSchema<T> sizeof(int size) {
        addPredicate("sizeof", n -> n instanceof Map<?,?> map && map.size() == size);
        return this;
    }
}
