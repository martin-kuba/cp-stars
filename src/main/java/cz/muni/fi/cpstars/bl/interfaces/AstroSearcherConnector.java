package cz.muni.fi.cpstars.bl.interfaces;

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
}
