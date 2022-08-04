package deleteshorturl.services;

import java.net.URL;
import java.time.LocalDate;

import lombok.Data;


/**
 * An URL Item relates the url with the corresponding short URL, indicating
 * also the creation date and time. It can optionally have an expiration time
 * in hours, after which will be deleted automatically, that is, if the
 * expiration is 2 hours, that means that after 2 hours of being created, the
 * short URL will be deleted and cannot be accesed. 
 * 
 */
@Data
public class URLItem {
	
	/*
	 * The short path after the base URL. Only the short path is stored, because
	 * for all the short URLs generated, the first part is always the same. 
	 * That is, for the following short URLS: "http://meli.com/FDKJFS" and
	 * "http://meli.com/GEDFED", the first part "http://meli.com" is the same.
	 * So only "GEDFED" and "FDKJFS" are stored. 
	 */
	private String shortPath;
    
	/*
	 * 
	 */
	private URL longURL;
    
	/*
	 * 
	 */
	private LocalDate creationDate;
    
	/*
	 * 
	 */
	private Long expirationHours;
    
	
	
}
