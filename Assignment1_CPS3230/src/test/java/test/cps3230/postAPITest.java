package test.cps3230;

import um.edu.cps3230.Product;
import um.edu.cps3230.restClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.testng.Assert;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;


public class postAPITest{
    restClient restclient;
    CloseableHttpResponse closeableHttpResponse;


    //testing post API call
    @Test
    public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
        restclient = new restClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        //jackson apa to convert java to json
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product("Apple iPhone 14 Plus 512GB Midnight", "https://www.scanmalta.com/shop/apple-iphone-14-plus-512gb-midnight.html", "https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/1/8/1812786.jpg", 162900);

        //object to json file
        mapper.writeValue(new File("C://Users//adria//IdeaProjects//Assignment1_CPS3230//src//test//java//data//product.json"), product);

        //object to json in string
        String productJsonString = mapper.writeValueAsString(product);
        System.out.println(productJsonString);

        closeableHttpResponse = restclient.post("https://api.marketalertum.com/Alert", productJsonString, headerMap);

        //check status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);

        //check jsonString
        String respString = EntityUtils.toString(closeableHttpResponse.getEntity(), UTF_8);
        JSONObject respJson = new JSONObject(respString);
        System.out.println("Response from API: "+ respJson);



    }
}
