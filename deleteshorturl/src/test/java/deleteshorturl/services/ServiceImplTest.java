package deleteshorturl.services;

import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import deleteshorturl.events.Events;
import deleteshorturl.idvalidator.IdValidator;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {

    @InjectMocks
    private ServiceImpl svc;

    @Mock
    private Events events;

    @Mock
    private BaseURL baseURL;

    @Mock
    private IdValidator idValidator;

    
    @Test(expected = InvalidArgumentsException.class)
    public void testDeleteShortURL2() throws MalformedURLException, InvalidArgumentsException{
        svc.deleteURL(new URL("http://www.montevideo.com.uy"));    
    }

    @Test
    public void testDeleteShortURL3() throws MalformedURLException, InvalidArgumentsException{
        String shortPath="FKSLC5S";
        when(idValidator.isValid(shortPath)).thenReturn(true);
        when(baseURL.toString()).thenReturn("http://localhost:8000/");
        svc.deleteURL(new URL("http://localhost:8000/FKSLC5S"));    
    }


    @Test(expected = InvalidArgumentsException.class)
    public void testGetDeleteShortURL4() throws MalformedURLException, InvalidArgumentsException{
        svc.deleteURL(new URL("http://www.montevideo.com.uy"));    

    }

    @Test(expected = InvalidArgumentsException.class)
    public void testGetDeleteShortUR5() throws MalformedURLException, InvalidArgumentsException{
        svc.deleteURL(new URL("http://www.montevideo.com.uy/"));    

    }

    @Test(expected = InvalidArgumentsException.class)
    public void testDeleteShortURL6() throws MalformedURLException, InvalidArgumentsException{
          svc.deleteURL(new URL("http://localhost:8999/asdfed"));    

    }

    @Test(expected =  InvalidArgumentsException.class)
    public void testDeleteShortURL7() throws MalformedURLException, InvalidArgumentsException{
        String shortPath="FKSLC5S";
        when(idValidator.isValid(shortPath)).thenReturn(false);
        svc.deleteURL(new URL("http://localhost:8000/FKSLC5S"));    
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testDeleteShortURL8() throws MalformedURLException, InvalidArgumentsException{
       svc.deleteURL(new URL("http://www.montevideo.com.uy/a"));    
    }


}
