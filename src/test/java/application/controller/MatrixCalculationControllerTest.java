package application.controller;

import application.factory.Matrix;
import application.factory.Operations;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixCalculationController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class MatrixCalculationControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    String request = new String("{ \n" +
            "   \"matrix1\":{ \n" +
            "         \"a\":\"2\",\n" +
            "         \"b\":\"2\",\n" +
            "         \"matrix\":[ \n" +
            "            [ \n" +
            "               { \n" +
            "                  \"value\" : 1\n" +
            "               },\n" +
            "               { \n" +
            "                  \"value\" : 0\n" +
            "               }\n" +
            "            ],\n" +
            "            [ \n" +
            "               { \n" +
            "                  \"value\" : 0\n" +
            "               },\n" +
            "               { \n" +
            "                  \"value\" : 1\n" +
            "               }\n" +
            "            ]\n" +
            "         ]\n" +
            "   },\n" +
            "   \"matrix2\":{ \n" +
            "         \"a\":\"2\",\n" +
            "         \"b\":\"2\",\n" +
            "         \"matrix\":[ \n" +
            "            [ \n" +
            "               { \n" +
            "                  \"value\" : 1\n" +
            "               },\n" +
            "               { \n" +
            "                  \"value\" : 0\n" +
            "               }\n" +
            "            ],\n" +
            "            [ \n" +
            "               { \n" +
            "                  \"value\" : 0\n" +
            "               },\n" +
            "               { \n" +
            "                  \"value\" : 1\n" +
            "               }\n" +
            "            ]\n" +
            "         ]\n" +
            "   }\n" +
            "}");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddMethod() throws Exception {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("src/test/resources/Request.json"));
        String requestBody = new ObjectMapper().writeValueAsString(jsonObject);
        this.mockMvc.perform(post("/Calc/Add")
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSubstractMethod() throws Exception {
        this.mockMvc.perform(post("/Calc/Substract")
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testMultiplyMethod() throws Exception {
        this.mockMvc.perform(post("/Calc/Multiply").accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
