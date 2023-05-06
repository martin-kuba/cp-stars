package cz.muni.fi.cpstars.rest.controllers;


import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;
import cz.muni.fi.cpstars.bl.implementation.AstroSearcherConnectorImpl;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
import cz.muni.fi.cpstars.dal.implementation.classes.ExternalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller to query external services.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@RestController
@RequestMapping(Paths.EXTERNAL_SERVICES)
public class ExternalServicesController {

    private final AstroSearcherConnector astroSearcherConnector;

    @Autowired
    public ExternalServicesController(AstroSearcherConnectorImpl astroSearcherConnectorImpl) {
        this.astroSearcherConnector = astroSearcherConnectorImpl;
    }

    @Operation(
            summary = "Get identifiers from external sources (AstroSearcher).",
            description = """
                    Response contains list of identifiers obtained from AstroSearcher application.
                    IMPORTANT: Querying external sources may take some time."""
    )
    @ApiResponse(
            responseCode = "200",
            description = "Identifiers obtained from external sources."
    )
    @GetMapping(Paths.IDENTIFIERS + "/{name}")
    public List<String> getIdentifiers(@PathVariable String name) {
        return astroSearcherConnector.getAliases(name);
    }

    @Operation(
            summary = "Get data about specified object from Simbad using AstroSearcher.",
            description = """
                    Response contains Simbad data obtained from external sources (AstroSearcher).
                    IMPORTANT: Querying external sources may take some time."""
    )
    @ApiResponse(
            responseCode = "200",
            description = "External data for specified object."
    )
    @GetMapping(Paths.ASTROSEARCHER_SIMBAD + "/{name}")
    public ExternalDetails getSimbadExternalDetails(@PathVariable String name) {
        return new ExternalDetails(astroSearcherConnector.getData(name, 0, 0, 1));
    }

    @Operation(
            summary = "Get Vizier metadata about specified object using AstroSearcher.",
            description = """
                    Response contains Vizier metadata obtained from external sources (AstroSearcher).
                    IMPORTANT: Querying external sources may take some time."""
    )
    @ApiResponse(
            responseCode = "200",
            description = "External data for specified object."
    )
    @GetMapping(Paths.ASTROSEARCHER_VIZIER_METADATA + "/{name}")
    public ExternalDetails getVizierMetadata(@PathVariable String name) {
        return new ExternalDetails(astroSearcherConnector.getData(name, 0, 2, 0));
    }
}
