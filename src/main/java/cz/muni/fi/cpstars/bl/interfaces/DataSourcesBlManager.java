package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.classes.DataSourceBasicInfo;
import cz.muni.fi.cpstars.dal.entities.DataSource;

import java.util.List;

/**
 * Unified interface to access project datasources information.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface DataSourcesBlManager {

	/**
	 * Get all datasources used in the database with basic information.
	 *
	 * @return list of datasources with basic information
	 */
	List<DataSourceBasicInfo> getAllDatasourcesBasicInfo();

	/**
	 * Get datasource used in the database.
	 *
	 * @return specified datasource
	 */
	DataSource getDatasource(long id);
}
