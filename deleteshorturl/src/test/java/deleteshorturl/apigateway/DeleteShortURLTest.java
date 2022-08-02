package deleteshorturl.apigateway;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import deleteshorturl.services.Service;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;

public class DeleteShortURLTest {

    @Mock
    private Service svc;

    @InjectMocks
    private DeleteShortURL deleteShortURL;

    @Test
    public void test1(){
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
        deleteShortURL.handleRequest(input, null);
    }
}
