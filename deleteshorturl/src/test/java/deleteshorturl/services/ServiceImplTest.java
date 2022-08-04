package deleteshorturl.services;

import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.Test.None;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import deleteshorturl.events.Events;
import urlutils.idvalidator.IdValidator;
import urlutils.idvalidator.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {

    @InjectMocks
    private ServiceImpl svc;

    @Mock
    private Events events;

    @Mock
    private IdValidator idValidator;

    
    @Test(expected = InvalidArgumentsException.class)
    public void testDeleteShortURL2() throws MalformedURLException, InvalidArgumentsException, ValidationException{
    	URL url = new URL("http://www.montevideo.com.uy");
    	ValidationException e = new ValidationException();
    	when(idValidator.getCode(url)).thenThrow(e);
    	svc.deleteURL(new URL("http://www.montevideo.com.uy"));    
    }

    @Test(expected = None.class)
    public void testDeleteShortURL3() throws MalformedURLException, InvalidArgumentsException, ValidationException{
        String shortPath="FKSLC5S";
        URL url  = new URL("http://localhost:8000/FKSLC5S");
        when(idValidator.getCode(url)).thenReturn(shortPath);
        svc.deleteURL(new URL("http://localhost:8000/FKSLC5S"));    
    }


}
