package deleteshorturl.services;

public class BaseURL {
    private final String url;
    public BaseURL(String url){
        this.url=url;
    }

    @Override
    public String toString(){
        return url;
    }
}
