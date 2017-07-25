package ${packageName}.controller;

import ${packageName}.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ${ClassName}ControllerTest extends BaseTest {

    private String baseUrl = "/${className}s/";
    private ${idType} id = null;

    @Test
    public void testList() throws Exception {
        mvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testDetail() throws Exception {
        mvc.perform(get(baseUrl + id))
                .andDo(print())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreate() throws Exception {
        String data = "";
        mvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(data)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        String data = "";
        mvc.perform(put(baseUrl + id)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(data)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete(baseUrl + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

}