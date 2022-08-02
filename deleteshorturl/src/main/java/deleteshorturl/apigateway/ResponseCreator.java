package deleteshorturl.apigateway;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import software.amazon.awssdk.http.HttpStatusCode;

public class ResponseCreator {

	
	private static  APIGatewayProxyResponseEvent getTextResponse() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "text/html");
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);
		return response;
	}
	
	static  APIGatewayProxyResponseEvent getNotFoundResponse() {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.NOT_FOUND).withBody("URL not found");
	}
	

	static  APIGatewayProxyResponseEvent getBadRequestResponse() {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.BAD_REQUEST).withBody("Invalid URL");
	}

	static APIGatewayProxyResponseEvent getMovedResponse(String url) {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.MOVED_PERMANENTLY).withBody(url);
		
	}
	
	
	static APIGatewayProxyResponseEvent getOKResponse(String body) {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.ACCEPTED).withBody(body);
		
	}
	static APIGatewayProxyResponseEvent getInternalErrorResponse() {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR).withBody("An Internal Error has ocurred");
		
	}
	
	static APIGatewayProxyResponseEvent getURLCreatedResponse(String url) {
		 var response  = getTextResponse();
		 return response.withStatusCode(HttpStatusCode.CREATED).withBody(url);
		
	}
	
	
	
	
	
}
