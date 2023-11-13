package py.com.code100.core.config;

public enum ErrorCode {

    // POKEMON ERROR
    POKEMON_NOT_FOUND(100, "El pokemon no existe"),
    POKEMON_DELETED(101, "El pokemon ya se encuentra eliminado"),

    // GENERIC ERROR
    INTERNAL_ERROR(900, "Ha ocurrido un error interno en la aplicación, por favor contecte con los administradores"),
    VALID_DATA_ERROR(901, "Error de validación de datos");

    private final int value;
    private final String reasonPhrase;

    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
