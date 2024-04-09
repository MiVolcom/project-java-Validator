package hexlet.code;

public class App {
    public static void main(String[] args) {
        var v = new Validator();
        var schema = v.string();
        System.out.println(schema.isValid(""));
        System.out.println(schema.isValid(null));

        schema.required();
        System.out.println(schema.isValid(""));
        System.out.println(schema.isValid(null));
        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true

        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        schema.isValid("what does the fox say"); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

// Если один валидатор вызывался несколько раз
// то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
    }
}
