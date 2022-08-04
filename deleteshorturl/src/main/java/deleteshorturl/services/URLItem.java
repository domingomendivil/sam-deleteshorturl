package deleteshorturl.services;

import java.net.URL;
import java.time.LocalDate;


/**
 * An URL Item relates the url with the corresponding short URL, indicating
 * also the creation date and time. It can optionally have an expiration time
 * in hours, after which will be deleted automatically, that is, if the
 * expiration is 2 hours, that means that after 2 hours of being created, the
 * short URL will be deleted and cannot be accesed. 
 * 
 */

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
    
	/*
	 * From this part of the class till the end, there exist the corresponding 
	 * getters, setters and the equals() and hashCode() methods.
	 */
    public Long getExpirationHours() {
        return expirationHours;
    }

    public void setExpirationHours(Long expirationHours) {
        this.expirationHours = expirationHours;
    }

    public void setShortPath(String shortPath){
        this.shortPath=shortPath;
    }

    public String getShortPath(){
        return this.shortPath;
    }

    public void setLongURL(URL longURL){
        this.longURL=longURL;
    }

    public URL getLongURL(){
        return this.longURL;
    }

    public void setCreationDate(LocalDate creationDate){
        this.creationDate=creationDate;
    }

    public LocalDate getCreationDate(){
        return this.creationDate;
    }
    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((expirationHours == null) ? 0 : expirationHours.hashCode());
		result = prime * result + ((longURL == null) ? 0 : longURL.hashCode());
		result = prime * result + ((shortPath == null) ? 0 : shortPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URLItem other = (URLItem) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (expirationHours == null) {
			if (other.expirationHours != null)
				return false;
		} else if (!expirationHours.equals(other.expirationHours))
			return false;
		if (longURL == null) {
			if (other.longURL != null)
				return false;
		} else if (!longURL.equals(other.longURL))
			return false;
		if (shortPath == null) {
			if (other.shortPath != null)
				return false;
		} else if (!shortPath.equals(other.shortPath))
			return false;
		return true;
	}

	
}
