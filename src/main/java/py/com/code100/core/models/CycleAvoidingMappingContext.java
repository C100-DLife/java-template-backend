package py.com.code100.core.models;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Context class used to avoid cycles during object mapping.
 *
 * <p>This class is typically used with mapping frameworks like MapStruct to prevent infinite recursion and stack
 * overflow errors when mapping objects that have circular references.</p>
 *
 * <p>The context maintains a map of known instances to avoid re-mapping the same instance, ensuring that cycles are
 * properly handled.</p>
 */
public class CycleAvoidingMappingContext {

    /**
     * Map to store known instances during the mapping process.
     */
    private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

    /**
     * Retrieves a mapped instance for a given source object and target type.
     *
     * @param source     The source object being mapped.
     * @param targetType The target type to cast the mapped instance.
     * @param <T>        The generic type of the target.
     * @return The mapped instance for the given source and target type.
     */
    @BeforeMapping
    public <T> T getMappedInstance(
            Object source,
            @TargetType Class<T> targetType
    ) {
        return targetType.cast(knownInstances.get(source));
    }

    /**
     * Stores a mapped instance in the context for a given source and target.
     *
     * @param source The source object being mapped.
     * @param target The target object being mapped.
     */
    @BeforeMapping
    public void storeMappedInstance(
            Object source,
            @MappingTarget Object target
    ) {
        knownInstances.put(source, target);
    }
}
