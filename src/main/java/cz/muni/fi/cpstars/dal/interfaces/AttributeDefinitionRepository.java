package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.AttributeDefinition;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for AttributeDefinition Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface AttributeDefinitionRepository extends CrudRepository<AttributeDefinition, Long> {

    AttributeDefinition findById(long id);

    AttributeDefinition findByName(long name);
}
