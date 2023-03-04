package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Star-DataSource Attribute Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface StarDatasourceAttributeRepository extends CrudRepository<StarDatasourceAttribute, Long> {
    StarDatasourceAttribute findById(long id);
}
