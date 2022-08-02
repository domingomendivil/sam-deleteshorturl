package deleteshorturl.apigateway;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import deleteshorturl.services.InvalidArgumentsException;
import deleteshorturl.services.Service;

public class DeleteShortURL {

    private Service service;

    public DeleteShortURL(Service service){
        this.service=service;
    }
    
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String shortURL = input.getBody();
        boolean isValid = UrlValidator.getInstance().isValid(shortURL);
        if (isValid) {
            try {
                var shortPath = new URL(shortURL);
                service.deleteURL(shortPath);
                return ResponseCreator.getOKResponse("URL deleted");
            } catch (MalformedURLException e) {
                return ResponseCreator.getInternalErrorResponse();
            } catch (InvalidArgumentsException e) {
                return ResponseCreator.getBadRequestResponse();
            }

        } else {
            return ResponseCreator.getBadRequestResponse();
        }
    }
}
