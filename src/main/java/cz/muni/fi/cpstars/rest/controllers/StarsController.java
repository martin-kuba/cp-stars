package cz.muni.fi.cpstars.rest.controllers;

import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.temporaryData.TemporaryStarBasicInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * REST Controller for CP-Stars database access.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@RequestMapping(Paths.CP_STARS_DATABASE)
@RestController
public class StarsController {

    /**
     * Return basic information about all CP stars.
     *
     * @return list of stars with basic information
     */
    @GetMapping
    public List<StarBasicInfo> getBasicInfoStarsList() {
        // TODO: Fetch data from database
        return TemporaryStarBasicInfo.loadedData;
    }

    // TODO: Change return type
    @GetMapping("/{id}")
    public StarBasicInfo getStarDetails(@PathVariable int id) {
        List<StarBasicInfo> found = TemporaryStarBasicInfo.loadedData.stream().filter((star) -> star.Id == id).toList();

        return found.size() > 0 ? found.get(0) : new StarBasicInfo();
    }
}
