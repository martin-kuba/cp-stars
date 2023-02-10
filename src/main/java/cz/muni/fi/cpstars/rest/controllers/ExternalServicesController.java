package cz.muni.fi.cpstars.rest.controllers;


import cz.muni.fi.cpstars.bl.implementation.AstroSearcherConnectorImpl;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
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

    @GetMapping(Paths.IDENTIFIERS + "/{name}")
    public List<String> getIdentifiers(@PathVariable String name) {
        return astroSearcherConnector.getAliases(name);
    }
}
