package py.com.code100.core.data;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import py.com.code100.core.annotations.DoIgnore;
import py.com.code100.core.configurations.CycleAvoidingMappingContext;

public interface BaseDomainMapper<R, DOMAIN> {

    R toRequestDomain(DOMAIN domain, @Context CycleAvoidingMappingContext mappingContext);

    @DoIgnore
    default R toRequestDomain(DOMAIN domain) {
        return toRequestDomain(domain, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    DOMAIN toDomainRequest(R requestModel, @Context CycleAvoidingMappingContext mappingContext);
}
