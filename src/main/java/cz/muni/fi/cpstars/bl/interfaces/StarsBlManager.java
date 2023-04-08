package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.classes.ExtendedStar;
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
     * Retrieve detailed information about specific star including fetched external data.
     *
     * @param id star's id
     * @return information about star with provided ID
     */
    Star getStar(long id);

    /**
     * Retrieve detailed information (only from the application database) about specific star.
     * IMPORTANT: Fetching external data may take some time, so you this method wisely
     *            and only for specific use-cases.
     *
     * @param id star's id
     * @return all information about star with provided ID
     */
    ExtendedStar getExtendedStar(long id);

    /**
     * Retrieve detailed information (only from the application database) about specific star.
     * IMPORTANT: Fetching external data may take some time, so you this method wisely
     *            and only for specific use-cases.
     *
     * @param id star's id
     * @param externalIdentifier identifier to replace default identifier used for queries in external data sources.
     * @return all information about star with provided ID
     */
    ExtendedStar getExtendedStar(long id, String externalIdentifier);

    /**
     * Retrieve detailed information (only from the application database) about specific star.
     * IMPORTANT: Fetching external data may take some time, so you this method wisely
     *            and only for specific use-cases.
     *
     * @param star star (from internal database)
     * @param externalIdentifier identifier to replace default identifier used for queries in external data sources.
     * @return all information about star with provided ID
     */
    ExtendedStar getExtendedStar(Star star, String externalIdentifier);
}
