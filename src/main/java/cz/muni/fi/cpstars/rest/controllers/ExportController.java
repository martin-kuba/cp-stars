package cz.muni.fi.cpstars.rest.controllers;

import cz.muni.fi.cpstars.bl.implementation.export.ExportBlManagers;
import cz.muni.fi.cpstars.rest.forms.export.ExportCsvForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
	}


	@Operation(
			summary = "Export specified stars into CSV file.",
			description = """
                    Selected stars are exported into CSV file together with exhaustive information gathered from database."""
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "CSV file containing data about specified stars."
			),
			@ApiResponse(
					responseCode = "400",
					description = "Provided form (input data) are not valid, probably invalid CSV empty value representation."
			)
	})
	@PostMapping(value = Paths.FORMAT_CSV)
	public ResponseEntity<byte[]> downloadStarsCSV(@RequestBody ExportCsvForm exportCsvForm) {
		byte[] csvData = exportBlManagers.getCsvBlManager().getStarsCsv(exportCsvForm);

		// set HTTP headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setContentLength(csvData.length);
		headers.setContentDispositionFormData("attachment", "stars.zip");

		// create HTTP response entity
		return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
	}

	@Operation(
			summary = "Export all stars into CSV file.",
			description = """
                    All stars are exported into CSV file together with exhaustive information gathered from database."""
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "CSV file containing data about all stars from database."
			),
			@ApiResponse(
					responseCode = "400",
					description = "Provided form (input data) are not valid, probably invalid CSV empty value representation."
			)
	})
	@GetMapping(value = Paths.FORMAT_CSV + Paths.ALL)
	public ResponseEntity<byte[]> downloadStarsCSV() {
		return downloadStarsCSV(new ExportCsvForm());
	}

	@Operation(
			summary = "Return list of all supported (possible) empty value representations.",
			description = """
                    Return list of all supported (possible) empty value representations. Only these representations can be used for export."""
	)
	@ApiResponse(
			responseCode = "200",
			description = "List of supported (possible) empty value representations."
	)
	@GetMapping(value = Paths.FORMAT_CSV + Paths.EMPTY_VALUE_REPRESENTATIONS)
	public List<String> getSupportedEmptyValueRepresentations() {
		return exportBlManagers.getCsvBlManager().getSupportedEmptyValueRepresentations();
	}
}
