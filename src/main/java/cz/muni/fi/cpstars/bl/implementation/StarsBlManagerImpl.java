package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.star.StarRepository;
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

    private StarRepository starRepository;

    @Autowired
    public StarsBlManagerImpl(StarRepository starRepository) {
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
}
