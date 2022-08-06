package deleteshorturl.config;


import deleteshorturl.apigateway.DeleteShortURL;
import deleteshorturl.events.Events;
import deleteshorturl.events.EventsImpl;
import deleteshorturl.services.Service;
import deleteshorturl.services.ServiceImpl;
import shorturls.dao.Deleter;
import shorturls.dao.config.DAOConfigurationException;
import urlutils.idvalidator.BaseURL;
import urlutils.idvalidator.IdValidator;
import urlutils.idvalidator.IdValidatorImpl;

public class Factory {
	
	private Factory() {
		//cannot instantiate this class
	}

	public static final DeleteShortURL deleteShortURL = init();
	
    private static DeleteShortURL init() {
		Deleter deleter;
		try {
			String deleteFactory = System.getenv("DELETER_FACTORY");
			deleter = new shorturls.dao.config.Factory<Deleter>().getInstance(deleteFactory);
			Events events =  new EventsImpl(deleter);
			BaseURL baseURL = new BaseURL(System.getenv("BASE_URL"));
			IdValidator idValidator = new IdValidatorImpl(baseURL);
			Service service = new ServiceImpl(events, idValidator);
			return new DeleteShortURL(service);
		} catch (DAOConfigurationException e) {
			throw new ConfigurationException("Error configuring DAO layer",e);
		}
		
    }
	
}
