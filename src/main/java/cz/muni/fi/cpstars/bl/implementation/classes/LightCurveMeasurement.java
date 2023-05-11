package cz.muni.fi.cpstars.bl.implementation.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing single measurement of star's light curves.
 *
 * @author Ľuboslav Halama
 */
@Data
@AllArgsConstructor
public class LightCurveMeasurement {
	double time;
	double value;
}
