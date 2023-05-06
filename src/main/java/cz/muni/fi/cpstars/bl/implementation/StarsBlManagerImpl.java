package cz.muni.fi.cpstars.bl.implementation;

import astrosearcher.classes.ResponseData;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.dal.implementation.classes.ExtendedStar;
import cz.muni.fi.cpstars.dal.implementation.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.StarRepository;
import cz.muni.fi.cpstars.utils.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for Stars manager.
 * Provides functionality to access/work with Star and related objects.
 *
 * @author Ľuboslav Halama <lubo.halam@gmail.com>
 */
@Service
public class StarsBlManagerImpl implements StarsBlManager {

    private final AstroSearcherConnector astroSearcherConnector;
    private StarRepository starRepository;

    @Autowired
    public StarsBlManagerImpl(AstroSearcherConnectorImpl astroSearcherConnectorImpl, StarRepository starRepository) {
        this.astroSearcherConnector = astroSearcherConnectorImpl;
        this.starRepository = starRepository;
    }
    @Override
    public List<StarBasicInfo> getAllStarsBasicInfo() {
        return starRepository.findAllStarsBasicInfo();
    }

    @Override
    public Star getStar(long id) {
        Star found = starRepository.findById(id);
        return found == null ? new Star() : found;
    }

    @Override
    public Star getStarByRensonId(String id) {
        Star found = starRepository.findByRenson(id);
        return found == null ? new Star() : found;
    }


    @Override
    public ExtendedStar getExtendedStar(long id) {
        Star found = starRepository.findById(id);

        if (found == null) {
            return new ExtendedStar();
        }

        return getExtendedStar(found, "Renson " + found.getRenson());
    }

    @Override
    public ExtendedStar getExtendedStar(long id, String externalIdentifier) {
        Star found = starRepository.findById(id);

        if (found == null) {
            return new ExtendedStar();
        }

        return getExtendedStar(found, externalIdentifier);
    }

    @Override
    public ExtendedStar getExtendedStar(Star star, String externalIdentifier) {
        // get external data
        ResponseData externalData = astroSearcherConnector.getData(externalIdentifier);

        return new ExtendedStar(star, externalData);
    }

    @Override
    public List<Star> getStars() {
        return IterableUtils.convertToList(starRepository.findAll());
    }
}
