package deleteshorturl.apigateway;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import deleteshorturl.services.InvalidArgumentsException;
import deleteshorturl.services.Service;
import lombok.val;
import shorturls.apigateway.ResponseCreator;



public class DeleteShortURL {

	private static final UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
	
	
    private final Service service;

    public DeleteShortURL(Service service){
        this.service=service;
    }
    
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input) {
        val shortURL = input.getBody();
        val isValid = urlValidator.isValid(shortURL);
        if (isValid) {
            try {
                val shortPath = new URL(shortURL);
                if (service.deleteURL(shortPath)){
                    return ResponseCreator.getOKResponse("URL deleted");
                }else{
                    return ResponseCreator.getNotFoundResponse();
                }
            } catch (InvalidArgumentsException|MalformedURLException e) {
                return ResponseCreator.getBadRequestResponse();
            }

        } else {
            return ResponseCreator.getBadRequestResponse();
        }
    }
}
