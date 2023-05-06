package cz.muni.fi.cpstars.bl.interfaces;

import astrosearcher.classes.ResponseData;
import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;

import java.util.List;

/**
 * Interface for AstroSearcher queries.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface AstroSearcherConnector {

    /**
     * Return all aliases (other identifiers) for given object.
     *
     * @param baseIdentifier basic identifier used for query
     *
     * @return list of object's aliases
     */
    List<String> getAliases(String baseIdentifier);

    /**
     * Return all data retrieved from AstroSearcher general search, e.g. Mast, Simbad, Vizier combined.
     *
     * @param baseIdentifier basic identifier used for query
     *
     * @return data from AstroSearcher
     */
    ResponseData getData(String baseIdentifier);

    /**
     * Return data retrieved from AstroSearcher general search, e.g. Mast, Simbad, Vizier combined.
     *
     * @param baseIdentifier basic identifier used for query
     * @param queryMast specifies query type for MAST
     *                  0 -> no query (skip)
     *                  1 -> query for data
     * @param queryVizier specifies query type for Vizier
     *                    0 -> no query (skip)
     *                    1 -> query for data
     *                    2 -> query for metadata
     * @param querySimbad specifies query type for Simbad
     *                    0 -> no query (skip)
     *                    1 -> query for data
     * @return data from AstroSearcher
     */
    ResponseData getData(String baseIdentifier, int queryMast, int queryVizier, int querySimbad);

    /**
     * Return data retrieved from AstroSearcher containing light curve measurements
     * of the specified star.
     *
     * @param baseIdentifier star's identifier
     * @return list of light curve measurements
     */
    List<LightCurveMeasurement> getStarLightCurveMeasurements(String baseIdentifier);
}
