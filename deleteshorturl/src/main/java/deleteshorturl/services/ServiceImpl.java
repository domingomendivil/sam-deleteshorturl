package deleteshorturl.services;

import java.net.URL;

import deleteshorturl.events.Events;
import urlutils.idvalidator.IdValidator;
import urlutils.idvalidator.ValidationException;

/**
 * Implementation of the Service Layer. It validates the inputs and communicates
 * with data layer and the unique id generator.
 *
 */
public class ServiceImpl implements Service {

	private final Events events;


	private final IdValidator idValidator;

	public ServiceImpl(Events events, IdValidator idValidator) {
		this.events = events;
		this.idValidator = idValidator;
	}

	@Override
	public void deleteURL(URL shortURL) throws InvalidArgumentsException {
		String shortPath;
		try {
			shortPath = idValidator.getCode(shortURL);
			events.delete(shortPath);
		} catch (ValidationException e) {
			throw new InvalidArgumentsException(e);
		}
		
	}

}
