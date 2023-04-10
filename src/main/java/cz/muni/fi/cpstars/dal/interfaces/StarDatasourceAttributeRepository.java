package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Star-DataSource Attribute Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface StarDatasourceAttributeRepository extends CrudRepository<StarDatasourceAttribute, Long> {
    StarDatasourceAttribute findById(long id);

    List<StarDatasourceAttribute> findAllByStar(Star star);

    @Query(value = "SELECT s " +
            "FROM StarDatasourceAttribute s " +
            "WHERE s.star.id=?1")
    List<StarDatasourceAttribute> findAllByStarId(long starId);

    @Query(value = "SELECT DISTINCT a.attributeDefinition.name " +
            "FROM StarDatasourceAttribute a")
    List<String> findAllAttributeNames();
}
