package deleteshorturl.config;

import static java.lang.System.getenv;
import static shorturls.constants.Constants.BASE_URL;
import static shorturls.constants.Constants.CACHE_ENABLED;
import static shorturls.constants.Constants.CACHE_FACTORY;
import static shorturls.constants.Constants.DELETER_FACTORY;
import static shorturls.constants.Constants.RANDOM_ALPHABET;

import com.meli.factory.Factory;

import deleteshorturl.apigateway.DeleteShortURL;
import deleteshorturl.dao.DeleterDao;
import deleteshorturl.services.ServiceImpl;
import shorturls.cache.Cache;
import shorturls.dao.Deleter;
import urlutils.idvalidator.BaseURL;
import urlutils.idvalidator.IdValidatorImpl;

public class DeleteFactory {

	private DeleteFactory() {
		// cannot instantiate this class
	}

	private static DeleteShortURL deleteShortURL;

	public static synchronized DeleteShortURL getInstance() {
		if (deleteShortURL==null){
			var deleteFactory = getenv(DELETER_FACTORY);
			var cacheEnabledStr = getenv(CACHE_ENABLED);
			var deleter = new Factory<Deleter>().getInstance(deleteFactory);
			var cacheEnabled = Boolean.valueOf(cacheEnabledStr);
			if (cacheEnabled) {
				var cacheFactory = getenv(CACHE_FACTORY);
				var cache = new Factory<Cache>().getInstance(cacheFactory);
				deleter = new DeleterDao(deleter, cache);
			}
			var baseURL = new BaseURL(getenv(BASE_URL));
			var alphabet = getenv(RANDOM_ALPHABET);
			var idValidator = new IdValidatorImpl(baseURL, alphabet);
			var service = new ServiceImpl(deleter, idValidator);
			deleteShortURL = new DeleteShortURL(service);
		}
		return deleteShortURL;

	}

}
