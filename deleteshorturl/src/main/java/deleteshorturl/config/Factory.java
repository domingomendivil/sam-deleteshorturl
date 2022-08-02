package deleteshorturl.config;

import java.net.URI;

import deleteshorturl.dao.DAO;
import deleteshorturl.dao.DynamoDAO;
import deleteshorturl.events.Events;
import deleteshorturl.events.EventsImpl;
import deleteshorturl.idvalidator.IdValidator;
import deleteshorturl.idvalidator.IdValidatorImpl;
import deleteshorturl.services.Service;
import deleteshorturl.services.ServiceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class Factory {

    private static final String baseURL =System.getenv("BASE_URL");

    private static final String dynamoURL=System.getenv("DYNAMO_URL");


    private static Service service;
    
    private static EventsImpl events;

    private static IdValidator idValidator;

    public static DAO dao;
    
    private static DynamoDbClient client;
 

    public static synchronized Service getService() {
        if (service==null){
            IdValidator idValidator = getIdValidator();
            Events events = getEventsImpl();
            service = new ServiceImpl(events, idValidator, baseURL);
        }
        return service;
    }


    private static synchronized Events getEventsImpl() {
    	if (events==null) {    
            dao =getDAO();
    		events =  new EventsImpl(dao);
    	}
		return events;
	}


	private static IdValidator getIdValidator() {
		if (idValidator==null) {
			idValidator =  new IdValidatorImpl();
    	}
		return idValidator;
    }


    public static synchronized DAO getDAO(){
        if (dao==null ){
            DynamoDbClient client = getDynamoDBClient();
            dao =  new DynamoDAO(client);
        }
        return dao;
    }

    private static synchronized DynamoDbClient getDynamoDBClient() {
        if (client==null){
            if ((dynamoURL==null) || (dynamoURL.equals(""))){
                client = DynamoDbClient.create();
            }else {
                client = DynamoDbClient
                .builder().
                endpointOverride(URI.create(dynamoURL)).build();
            }
        }
        return client;
    }

}
