package deleteshorturl.config;

import java.net.URI;

import deleteshorturl.apigateway.DeleteShortURL;
import deleteshorturl.dao.DynamoDAO;
import deleteshorturl.events.Events;
import deleteshorturl.events.EventsImpl;
import deleteshorturl.services.Service;
import deleteshorturl.services.ServiceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import urlutils.idvalidator.BaseURL;
import urlutils.idvalidator.IdValidator;
import urlutils.idvalidator.IdValidatorImpl;

public class Factory {
	
	private Factory() {
		//cannot instantiate this class
	}

	public static final DeleteShortURL deleteShortURL = init();
	
    private static DeleteShortURL init() {
		String dynamoURL = System.getenv("DYNAMO_URL");
		DynamoDbClient client = getDynamoDBClient(dynamoURL);
		DynamoDAO dao = new DynamoDAO(client);
		Events events =  new EventsImpl(dao);
		BaseURL baseURL = new BaseURL(System.getenv("BASE_URL"));
		IdValidator idValidator = new IdValidatorImpl(baseURL);
		Service service = new ServiceImpl(events, idValidator);
		return new DeleteShortURL(service);
    }
    
	
    private static synchronized DynamoDbClient getDynamoDBClient(String dynamoURL) {
        if ((dynamoURL==null) || (dynamoURL.equals(""))){
            return  DynamoDbClient.create();
        }else {
            return DynamoDbClient
            .builder().
            endpointOverride(URI.create(dynamoURL)).build();
        }
    }
}
