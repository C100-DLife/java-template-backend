package py.com.code100.pokemon.domain.exception;

import py.com.code100.core.config.ErrorCode;
import py.com.code100.core.config.exception.GenericException;

public class PokemonNotFoundException extends GenericException {

    public PokemonNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
