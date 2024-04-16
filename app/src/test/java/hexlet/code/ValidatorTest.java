package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    @Test
    public void stringSchemaTest() {
        var v = new Validator();
        var schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();

        var schema1 = v.string().required().minLength(10).minLength(4);
        assertThat(schema1.isValid("hexlet")).isTrue();
    }
    @Test
    public void numberSchemaTest() {
        var v = new Validator();

        var schema = v.number();

        assertThat(schema.isValid(5)).isTrue(); // true

// Пока не вызван метод required(), null считается валидным
        assertThat(schema.isValid(null)).isTrue(); // true
        assertThat(schema.positive().isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(10)).isTrue(); // true

// Потому что ранее мы вызвали метод positive()
        assertThat(schema.isValid(-10)).isFalse(); // false
//  Ноль — не положительное число
        assertThat(schema.isValid(0)).isFalse(); // false

        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue(); // true
        assertThat(schema.isValid(10)).isTrue(); // true
        assertThat(schema.isValid(4)).isFalse(); // false
        assertThat(schema.isValid(11)).isFalse(); // false
    }
    @Test
    public void mapSchemaTest() {
        var v = new Validator();

        var schema = v.map();

        assertThat(schema.isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(new HashMap<>())).isTrue(); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue(); // true
    }
    @Test
    public void shapeTest() {
        var v = new Validator();

        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema.isValid(human1)).isTrue(); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertThat(schema.isValid(human2)).isFalse(); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertThat(schema.isValid(human3)).isFalse(); // falsez
    }
    @Test
    public void shapeTest2() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", 100);
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isTrue();

        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertThat(schema.isValid(actual4)).isFalse();

        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", -5);
        assertThat(schema.isValid(actual5)).isFalse();

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", 15);
        assertThat(schema.isValid(actual6)).isFalse();
    }
}
