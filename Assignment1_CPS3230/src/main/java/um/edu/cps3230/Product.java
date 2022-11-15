package um.edu.cps3230;

public class Product {

    //list of attributes
    int alertType = 6;
    String heading = "";
    String description;
    String url;
    String imageUrl;
    String postedBy = "2f7894be-442b-41cc-aad0-a3b04a7a6891";
    int priceInCents;

    //constructor
    public Product(){

    }

    //constructor with one param
    public Product(int alertType){
        this.alertType = alertType;
        heading = "Test";
        description = "Test";
        url = "Test";
        imageUrl = "Test";
        priceInCents = 0;
    }

    //constructor with 4 params
    public Product(String description, String url, String imageUrl, int priceInCents){
        heading = "Apple iPhone 14";
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.priceInCents = priceInCents;
    }



    //getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public String getHeading() {
        return heading;
    }

    public String getPostedBy() {
        return postedBy;
    }
}
