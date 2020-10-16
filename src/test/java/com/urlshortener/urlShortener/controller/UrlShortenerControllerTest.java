package com.urlshortener.urlShortener.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortener.urlShortener.model.Url;
import com.urlshortener.urlShortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.util.StringUtils;


@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest
@ComponentScan("com.urlshortener.urlShortener.service")
class UrlShortenerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UrlShortenerService urlShortenerService;

    @Test
    void shorten() throws Exception {
        Url url = getValidUrl();
        String urlJson = objectMapper.writeValueAsString(url); // Generate Json object from the given url

        ConstrainedFields fields = new ConstrainedFields(Url.class);

        mockMvc.perform(
                post("/api/v1/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(urlJson)).andExpect(status().isOk())
                .andDo(document("v1/shorten",
                        requestFields(
                                fields.withPath("url").description("The long URL")
                        )
                ));
    }

    private Url getValidUrl(){
        Url url = new Url();
        url.setUrl("https://docs.oracle.com/javase/tutorial/");
        return url;
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input){
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path){
            return fieldWithPath(path).attributes(key("constraints").value(
                    StringUtils.collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ", ")
            ));
        }
    }
}