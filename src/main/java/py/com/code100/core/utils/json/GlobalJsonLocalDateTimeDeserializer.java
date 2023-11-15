package py.com.code100.core.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalJsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentToken().id() == JsonTokenId.ID_STRING) {
            try {
                return LocalDateTime.parse(jp.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            }
            catch (Exception ex) {
                return LocalDateTime.parse(jp.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            }
        }
        return null;
    }
}

