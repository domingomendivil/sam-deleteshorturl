package deleteshorturl.config;


import deleteshorturl.apigateway.DeleteShortURL;
import deleteshorturl.dao.DeleterDao;
import deleteshorturl.services.Service;
import deleteshorturl.services.ServiceImpl;
import shorturl.cache.Cache;
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
			String cacheEnabledStr = System.getenv("CACHE_ENABLED");
			deleter = new shorturls.dao.config.Factory<Deleter>().getInstance(deleteFactory);
			boolean cacheEnabled = Boolean.valueOf(cacheEnabledStr) ;
			if (cacheEnabled){
				String cacheFactory = System.getenv("CACHE_FACTORY");
				Cache cache = new shorturls.dao.config.Factory<Cache>().getInstance(cacheFactory);
				deleter = new DeleterDao(deleter, cache);
			}
			BaseURL baseURL = new BaseURL(System.getenv("BASE_URL"));
			IdValidator idValidator = new IdValidatorImpl(baseURL);
			Service service = new ServiceImpl(deleter, idValidator);
			return new DeleteShortURL(service);
		} catch (DAOConfigurationException e) {
			throw new ConfigurationException("Error configuring DAO layer",e);
		}
		
    }
	
}
