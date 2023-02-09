package cz.muni.fi.cpstars.dal.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for storing basic (general) information about CP Star.
 * Information are to be used in database overview table.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StarBasicInfo {
    public long id;
    public String name;
    public Double icrsRightAscension;
    public Double icrsDeclination;
    public Double galacticLongitude;
    public Double galacticLatitude;
}
