package cz.muni.fi.cpstars.bl.implementation.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing single measurement of star's spectrum.
 *
 * @author Ľuboslav Halama
 */
@Data
@AllArgsConstructor
public class SpectrumMeasurement {
	double wavelength;
	double flux;
}
