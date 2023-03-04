package cz.muni.fi.cpstars.rest.controllers;

import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.implementation.initialization.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for CP-Stars database access.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@RequestMapping(Paths.CP_STARS_DATABASE)
@RestController
public class StarsController {

    private final DataInitializer dataInitializer;
    private final StarsBlManager starsBlManager;

    @Autowired
    public StarsController(DataInitializer dataInitializer, StarsBlManagerImpl starsBlManagerImpl) {
        this.dataInitializer = dataInitializer;
        this.starsBlManager = starsBlManagerImpl;
    }

    /**
     * Return basic information about all CP stars.
     *
     * @return list of stars with basic information
     */
    @GetMapping
    public List<StarBasicInfo> getBasicInfoStarsList() {
//        reload();
        // TODO: Fetch data from database
//        return TemporaryStarBasicInfo.loadedData;
        return starsBlManager.getAllStarsBasicInfo();
    }

    // TODO: Change return type
    @GetMapping("/{id}")
    public Star getStarDetails(@PathVariable long id) {
        return starsBlManager.getStar(id);
    }

    private void reload() {
        this.dataInitializer.initializeData();
    }
}
