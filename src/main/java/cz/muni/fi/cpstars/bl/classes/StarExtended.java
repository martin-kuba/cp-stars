package cz.muni.fi.cpstars.bl.classes;

import cz.muni.fi.cpstars.dal.entities.Star;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chemically peculiar star class that combines information from database and information
 * from external services (e.g. Simbad).
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Data
@NoArgsConstructor
public class StarExtended extends Star {
}
