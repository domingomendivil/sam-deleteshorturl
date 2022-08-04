package deleteshorturl.apigateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import deleteshorturl.services.Service;
@RunWith(MockitoJUnitRunner.class)
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
