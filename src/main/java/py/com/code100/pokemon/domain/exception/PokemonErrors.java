package py.com.code100.pokemon.domain.exception;

import py.com.code100.core.config.errors.ErrorCode;

public enum PokemonErrors implements ErrorCode {

    POKEMON_100("POKEMON_100", "Los pokemones con 1 año no pueden tirar poderes");


    private final String code;
    private final String message;

    PokemonErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
