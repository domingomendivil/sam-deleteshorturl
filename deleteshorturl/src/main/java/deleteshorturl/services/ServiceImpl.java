package deleteshorturl.services;

import java.net.URL;

import deleteshorturl.events.Events;
import deleteshorturl.idvalidator.IdValidator;
/**
 * Implementation of the Service Layer. 
 * It validates the inputs and communicates with data layer
 * and the unique id generator. 
 *
 */
public class ServiceImpl implements Service {

	/*
	 * 
	 */
	private final Events events;
	
	private final BaseURL baseURL;
	
	private final IdValidator idValidator;
	

	public ServiceImpl(Events events,IdValidator idValidator,BaseURL baseURL){
		this.events=events;
		this.idValidator=idValidator;
		this.baseURL=baseURL;
	}

	

	@Override
	public void deleteURL(URL shortURL) throws InvalidArgumentsException{
		String shortPath = getCode(shortURL);
		if (idValidator.isValid(shortPath)){
			events.delete(shortPath);
		}else{
			throw new InvalidArgumentsException();
		}
		
	}
	
	private String getCode(URL shortURL){
		String path = shortURL.getPath();
		if (path.equals("")){
			return "";
		}else{
			int i = path.length()-1;
			String shortCode="";
			while (path.charAt(i)!='/'){
				shortCode = path.charAt(i)+shortCode;
				i--;	
			}
			String base= shortURL.toString().replace(shortCode,"");
			if (base.equals(baseURL.toString())){
				return shortCode;
			}else{
				return "";
			}
			
		}

	}	


}
