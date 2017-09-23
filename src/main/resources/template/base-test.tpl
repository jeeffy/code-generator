package package ${packageName};

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

    protected MockMvc mvc;
    private String token = "";

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public void assertNotNull(Object object){
        Assert.assertNotNull(object);
    }

    public RequestBuilder get(String url, String data){
        data =(data==null ? "" : data);
        return MockMvcRequestBuilders.get(url + "?" + data)
                .header("Token",token);
    }

    public RequestBuilder post(String url, String data){
        return MockMvcRequestBuilders.post(url)
                .header("Token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(data);
    }

    public RequestBuilder post(String url, String data, MediaType contentType){
        return MockMvcRequestBuilders.post(url)
                .header("Token",token)
                .contentType(contentType)
                .content(data);
    }

    public RequestBuilder put(String url, String data){
        return MockMvcRequestBuilders.put(url)
                .header("Token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(data);
    }

    public RequestBuilder put(String url, String data, MediaType contentType){
        return MockMvcRequestBuilders.put(url)
                .header("Token",token)
                .contentType(contentType)
                .content(data);
    }

    public RequestBuilder delete(String url, String data){
        return MockMvcRequestBuilders.delete(url)
                .header("Token",token)
                .content(data==null?"":data);
    }

    public String toJsonString(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
