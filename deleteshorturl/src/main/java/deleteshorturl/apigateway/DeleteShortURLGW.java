package deleteshorturl.apigateway;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import deleteshorturl.services.InvalidArgumentsException;
import deleteshorturl.services.Service;
import deleteshorturl.config.Factory;

/**
 * AWS Lambda Handler request for deleting a Short URL
 */
public class DeleteShortURLGW implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DeleteShortURL service = Factory.getDeleteShortURL();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
       return service.handleRequest(input, context);
    }

}
