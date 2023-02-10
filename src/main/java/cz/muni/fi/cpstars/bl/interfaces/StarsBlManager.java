package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;

import java.util.List;

/**
 * Unified interface to access all information about CP stars.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface StarsBlManager {

    /**
     * Retrieve basic information about all stars.
     *
     * @return List of stars with basic information
     */
    List<StarBasicInfo> getAllStarsBasicInfo();

    /**
     * Retrieve detailed information about specific star.
     *
     * @param id star's id
     * @return information about star with provided ID
     */
    Star getStar(long id);
}
