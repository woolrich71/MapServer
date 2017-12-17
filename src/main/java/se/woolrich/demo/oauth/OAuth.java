package se.woolrich.demo.oauth;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.woolrich.demo.model.AccessToken;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class OAuth {


    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String GRANT_TYPE = "grant_type";
    public static final String SCOPE = "scope";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    public static final int HTTP_OK = 200;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_UNAUTHORIZED = 401;



    @Value("${authentication_server_url}")
    private String authenticationServerUrl;
    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${scope}")
    private String scope;
    @Value("${grant_type}")
    private String grantType;
    @Value("${access_token}")
    private String accessToken;
    @Value("${scheme}")
    private String url;
    @Value("${api_host}")
    private String apiHost;
    @Value("${scheme}")
    private String scheme;


    public void getAccessToken() {

        HttpPost post = new HttpPost(authenticationServerUrl);
        List<BasicNameValuePair> parametersBody = new ArrayList<>();
        parametersBody.add(new BasicNameValuePair(GRANT_TYPE, grantType));
        parametersBody.add(new BasicNameValuePair(CLIENT_ID, clientId));
        parametersBody.add(new BasicNameValuePair(CLIENT_SECRET, clientSecret));

        if (isValid(scope)) {
            parametersBody.add(new BasicNameValuePair(SCOPE, scope));
        }

        HttpClient client = HttpClientBuilder.create().build();  // the http-client, that will send the request
        try {
            post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.ISO_8859_1));

            HttpResponse response = client.execute(post);
            switch(response.getStatusLine().getStatusCode()) {
                case HTTP_UNAUTHORIZED:
                    System.out.println(HTTP_UNAUTHORIZED);
                    System.out.println(EntityUtils.toString(response.getEntity()));
                    break;
                case HTTP_FORBIDDEN:
                    System.out.println(HTTP_FORBIDDEN);
                    System.out.println(EntityUtils.toString(response.getEntity()));
                    break;

                case 404:
                    System.out.println(404);
                    System.out.println(EntityUtils.toString(response.getEntity()));
                    break;
                default:
//                    System.out.println(EntityUtils.toString(response.getEntity()));
//                    response = client.execute(post);
                    AccessToken accessToken = getObject(response, AccessToken.class, false);
                    this.accessToken = accessToken.getAccessToken();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public <T> T get(String path, Map<String, String> parameters, Class<T> valueType )  throws Exception {

        HttpResponse response = getHttpResponse(path, parameters);

        int statusCode = response.getStatusLine().getStatusCode();
        switch(statusCode) {
            case HTTP_UNAUTHORIZED:
            case HTTP_FORBIDDEN:
                getAccessToken();
                response = getHttpResponse(path, parameters);
                break;
            case 200:
                //response = getHttpResponse(path, parameters);
                break;
            default:
                System.out.println("getStatusCode:"+statusCode);
                return null;


        }

        return  getObject(response, valueType, true);
    }


    private <T extends Object> T getObject(HttpResponse response, Class<T> valueType, boolean unwrap_root) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, unwrap_root);

        return  mapper.readValue(response.getEntity().getContent(), valueType);
    }


    private HttpResponse getHttpResponse(String path, Map<String, String> parameters) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();  // the http-client, that will send the request
        URIBuilder builder = new URIBuilder();
        builder.setScheme(scheme)
                .setHost(apiHost)
                .setPath(path)
                .setParameter("format", "json");
        parameters.entrySet().stream().forEach(e -> builder.setParameter(e.getKey(), e.getValue()));
        URI uri = builder.build();
        HttpGet httpget = new HttpGet(uri);
   //     System.out.println(httpget.getURI());


        HttpGet getRequest = new HttpGet(uri);   // the http GET request
        getRequest.addHeader(AUTHORIZATION, BEARER +" "+ accessToken);


        return httpClient.execute(getRequest);
    }


    public static boolean isValid(String str) {
        return (str != null && str.trim().length() > 0);
    }

}
