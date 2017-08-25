package package ${packageName};

import com.alibaba.fastjson.JSONObject;
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

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

    protected MockMvc mvc;
    private String token = null;

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
        return MockMvcRequestBuilders.get(url)
                .header("Token",token)
                .content(data==null?"":data);
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

    public String toJSONString(Object object){
        return JSONObject.toJSONString(object);
    }

    public static class FormDataBuilder{
            private JSONObject json = new JSONObject();

            public FormDataBuilder put(String key, Object value){
                json.put(key, value);
                return this;
            }

            public String build(){
                StringBuilder sb = new StringBuilder();
                for (Map.Entry entry : json.entrySet()){
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                String data = sb.toString();
                if (data.length()>0){
                    return data.substring(0,data.lastIndexOf('&'));
                }
                return null;
            }
        }
}
