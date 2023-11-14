package py.com.code100.core.data;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import py.com.code100.core.annotations.DoIgnore;
import py.com.code100.core.models.CycleAvoidingMappingContext;

/**
 * An interface defining base mapping methods between request models (DTOs) and domain entities.
 *
 * <p>Implementations of this interface provide methods for converting between request models (DTOs) and domain entities.
 * It uses MapStruct annotations to define mappings.</p>
 *
 * @param <R> The type of the request model (DTO).
 * @param <DOMAIN> The type of the domain entity.
 */
public interface BaseDomainMapper<R, DOMAIN> {

    /**
     * Converts a domain entity to a request model (DTO) with the provided mapping context.
     *
     * @param domain The domain entity to be converted.
     * @param mappingContext The mapping context to avoid cycles in mappings.
     * @return The converted request model.
     */
    R toRequestDomain(DOMAIN domain, @Context CycleAvoidingMappingContext mappingContext);

    /**
     * Converts a domain entity to a request model (DTO) using a default mapping context.
     *
     * <p>This method serves as a default implementation, using a new instance of {@link CycleAvoidingMappingContext}
     * as the mapping context to avoid cycles in mappings.</p>
     *
     * @param domain The domain entity to be converted.
     * @return The converted request model.
     */
    @DoIgnore
    default R toRequestDomain(DOMAIN domain) {
        return toRequestDomain(domain, new CycleAvoidingMappingContext());
    }

    /**
     * Converts a request model (DTO) to a domain entity with the provided mapping context.
     *
     * @param requestModel The request model (DTO) to be converted.
     * @param mappingContext The mapping context to avoid cycles in mappings.
     * @return The converted domain entity.
     */
    @InheritInverseConfiguration
    DOMAIN toDomainRequest(R requestModel, @Context CycleAvoidingMappingContext mappingContext);
}
