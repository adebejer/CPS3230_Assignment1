package um.edu.cps3230;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class restClient {

    //http post API call
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url); //http post request
        httppost.setEntity(new StringEntity(entityString)); //for payload

        //for headers:
        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httppost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
        return closeableHttpResponse;
    }

    //http delete API call
    public CloseableHttpResponse delete() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDel = new HttpDelete("https://api.marketalertum.com/Alert?userId=2f7894be-442b-41cc-aad0-a3b04a7a6891"); //http delete request
        CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpDel); //hit the GET URL
        return closeableHttpResponse;


    }


}
