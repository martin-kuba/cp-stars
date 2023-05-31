package cz.muni.fi.cpstars.bl.implementation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown when not supported (invalid) empty value representation
 * is requested (chosen).
 *
 * @author Ľuboslav Halama
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyValueRepresentationNotSupportedException extends RuntimeException {
	public EmptyValueRepresentationNotSupportedException(String message) {
		super(message);
	}
}
