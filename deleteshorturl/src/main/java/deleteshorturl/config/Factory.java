package deleteshorturl.config;


import static java.lang.System.getenv;
import static  shorturls.constants.Constants.BASE_URL;
import static  shorturls.constants.Constants.CACHE_ENABLED;
import static  shorturls.constants.Constants.CACHE_FACTORY;
import static  shorturls.constants.Constants.RANDOM_ALPHABET;
import static  shorturls.constants.Constants.DELETER_FACTORY;
import deleteshorturl.apigateway.DeleteShortURL;
import deleteshorturl.dao.DeleterDao;
import deleteshorturl.services.Service;
import deleteshorturl.services.ServiceImpl;
import shorturls.cache.Cache;
import shorturls.dao.Deleter;
import shorturls.dao.config.DAOConfigurationException;
import urlutils.idvalidator.BaseURL;
import urlutils.idvalidator.IdValidatorImpl;

public class Factory {
	
	private Factory() {
		//cannot instantiate this class
	}

	public static final DeleteShortURL deleteShortURL = init();
	
    private static DeleteShortURL init() {
		
		try {
			String deleteFactory = getenv(DELETER_FACTORY);
			String cacheEnabledStr = getenv(CACHE_ENABLED);
			var deleter = new shorturls.dao.config.Factory<Deleter>().getInstance(deleteFactory);
			boolean cacheEnabled = Boolean.valueOf(cacheEnabledStr) ;
			if (cacheEnabled){
				var cacheFactory = getenv(CACHE_FACTORY);
				var cache = new shorturls.dao.config.Factory<Cache>().getInstance(cacheFactory);
				deleter = new DeleterDao(deleter, cache);
			}
			var baseURL = new BaseURL(getenv(BASE_URL));
			var alphabet = getenv(RANDOM_ALPHABET);
			var idValidator = new IdValidatorImpl(baseURL,alphabet);
			var service = new ServiceImpl(deleter, idValidator);
			return new DeleteShortURL(service);
		} catch (DAOConfigurationException e) {
			throw new ConfigurationException("Error configuring DAO layer",e);
		}
		
    }
	
}
