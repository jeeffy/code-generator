package ${packageName}.controller;

import ${packageName}.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ${classType}ControllerTest extends BaseTest {

    private String baseUrl = "/${map.format(className, "-")}s/";
    private ${idType} id = null;

    @Test
        public void testList() throws Exception {
            String data = "";
            mvc.perform(get(baseUrl, data))
                    .andDo(print())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        public void testGet() throws Exception {
            mvc.perform(get(baseUrl + id, null))
                    .andDo(print())
                    .andExpect(jsonPath("$").exists());
        }

        @Test
        public void testSave() throws Exception {
            ${classType} ${className} = new ${classType}();
            <#list fields as field>
            ${className}.set${field.fieldName?cap_first}(null);
            </#list>
            String data = toJSONString(${className});
            mvc.perform(post(baseUrl, data))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void testDelete() throws Exception {
            mvc.perform(delete(baseUrl + id, null))
                    .andDo(print())
                    .andExpect(status().isOk());
        }


}