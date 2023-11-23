package py.com.code100.pokemon.infrastructure.out.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import py.com.code100.pokemon.infrastructure.out.jpa.model.PokemonJPAModel;

import java.util.UUID;

@Repository
public interface PokemonJPARepository extends JpaRepository<PokemonJPAModel, UUID>, JpaSpecificationExecutor<PokemonJPAModel> {
}
