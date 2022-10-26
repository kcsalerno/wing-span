package learn.wing_span.domain;

public class Validations {
    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }
}
