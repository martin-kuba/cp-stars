package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.DataSource;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for DataSource Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface DataSourceRepository  extends CrudRepository<DataSource, Long> {
    DataSource findById(long id);

    DataSource findByName(String name);
}
