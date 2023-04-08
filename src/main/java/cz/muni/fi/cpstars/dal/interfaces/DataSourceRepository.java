package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.classes.DataSourceBasicInfo;
import cz.muni.fi.cpstars.dal.entities.DataSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for DataSource Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Repository
public interface DataSourceRepository extends CrudRepository<DataSource, Long> {
//public interface DataSourceRepository extends CrudRepository<DataSource, Long>, DataSourceRepositoryCustom<DataSource, Long> {
//public interface DataSourceRepository extends CrudRepository<DataSource, Long>, CustomDataSourceRepository {

    String DATASOURCE_BASIC_INFO_SELECT = "d.id, d.name, d.year, d.bibcode";

    DataSource findById(long id);

    DataSource findByName(String name);

    @Query(value = "SELECT new cz.muni.fi.cpstars.dal.classes.DataSourceBasicInfo(" + DATASOURCE_BASIC_INFO_SELECT + ") " +
            "FROM DataSource d")
    List<DataSourceBasicInfo> loadBasicInfo();
}
