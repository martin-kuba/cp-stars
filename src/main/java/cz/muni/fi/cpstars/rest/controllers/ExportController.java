package cz.muni.fi.cpstars.rest.controllers;

import cz.muni.fi.cpstars.bl.implementation.export.ExportBlManagers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class to handle all data export throughout the application.
 */
@RestController
@RequestMapping(Paths.EXPORT)
public class ExportController {

	private final ExportBlManagers exportBlManagers;

	@Autowired
	public ExportController(ExportBlManagers exportBlManagers) {
		this.exportBlManagers = exportBlManagers;
	}

	@Operation(
			summary = "Get all information (extended version) about specified star.",
			description = """
                    Response contains exhaustive information in plain text form about the star both from the database and external services.
                    IMPORTANT: Querying external sources may take some time.
                    Information obtained include:
                    - external data
                    - coordinates
                    - identifier
                    - photometry
                    - proper motions and parallaxes"""
	)
	@ApiResponse(
			responseCode = "200",
			description = "Plain-text extended star representation (application database and external sources info)."
	)
	@GetMapping(value = Paths.FORMAT_TXT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String getExtendedStarTxt(@RequestParam long id) {
		return exportBlManagers.getTxtBlManager().getExtendedStarTxt(id);
	};

}
