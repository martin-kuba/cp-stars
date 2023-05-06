package cz.muni.fi.cpstars.rest.controllers;


import cz.muni.fi.cpstars.bl.implementation.DataSourcesBlManagerImpl;
import cz.muni.fi.cpstars.bl.interfaces.DataSourcesBlManager;
import cz.muni.fi.cpstars.dal.implementation.classes.DataSourceBasicInfo;
import cz.muni.fi.cpstars.dal.entities.DataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for CP-Stars database access to information about datasources.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@RequestMapping(Paths.CP_STARS_DATASOURCES)
@RestController
public class DatasourcesController {

	private final DataSourcesBlManager datasourcesBlManager;

	@Autowired
	public DatasourcesController(DataSourcesBlManagerImpl datasourcesBlManagerImpl) {
		this.datasourcesBlManager = datasourcesBlManagerImpl;
	}

	/**
	 * Return information about all datasources.
	 *
	 * @return list of datasources
	 */
	@Operation(
			summary = "Return basic information about all datasources.",
			description = "Return list of all datasources in the database with basic information."
	)
	@ApiResponse(
			responseCode = "200",
			description = "List of datasources with basic information returned."
	)
	@GetMapping
	public List<DataSourceBasicInfo> getAllDatasourcesBasicInfo() {
		return datasourcesBlManager.getAllDatasourcesBasicInfo();
	}

	/**
	 * Return information about one datasource.
	 *
	 * @return datasource information
	 */
	@Operation(
			summary = "Return exhaustive information about one datasource.",
			description = "Return all information about specified datasource in the database."
	)
	@ApiResponse(
			responseCode = "200",
			description = "Datasource with all information stored in the database about it."
	)
	@GetMapping("/{id}")
	public DataSource getDatasource(@PathVariable long id) {
		return datasourcesBlManager.getDatasource(id);
	}
}
