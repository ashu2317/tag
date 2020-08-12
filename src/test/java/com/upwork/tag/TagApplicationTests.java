package com.upwork.tag;


import com.upwork.tag.config.TagApplication;
import com.upwork.tag.entity.Tag;
import com.upwork.tag.model.TagRequest;
import com.upwork.tag.model.TagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TagApplication.class)
@TestPropertySource(properties = "server.port=8080")
@Sql(scripts = "/clean-up.sql", executionPhase = AFTER_TEST_METHOD)
class TagApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DBUtils utils;

    private final String baseURL = "http://localhost:8080" + "/api";

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Test Add API")
    public void add_a_new_tag() throws URISyntaxException {
        URI url = new URI(baseURL + "/tags");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TagRequest tagRequest = new TagRequest();
        tagRequest.setTag("Test Tag -1");
        tagRequest.setValue("This tag is to test creation");

        HttpEntity<TagRequest> requestEntity = new HttpEntity(tagRequest, headers);

        ResponseEntity<TagResponse> response = restTemplate.postForEntity(url, requestEntity, TagResponse.class);

        assertNotNull("Once created ID should not be null", response.getBody().getId());
    }

    @Test
    @DisplayName("Test Listing and Delete API")
    public void list_tags_then_delete() throws URISyntaxException {
        URI url = new URI(baseURL + "/tags");

        Tag tag = new Tag();
        tag.setTag("Tag - 1");
        tag.setValue("Value for tag 1");

        //Fetch list from rest end point
        TagResponse[] responses = restTemplate.getForObject(url, TagResponse[].class);
        //At this time no entry in DB
        assertEquals(0, responses.length);

        utils.insertTagInDB(tag);

        //This should return 1 record now
        responses = restTemplate.getForObject(url, TagResponse[].class);
        assertEquals(1, responses.length);

        //Now delete record using rest endpoint
        restTemplate.delete(url + "/" + responses[0].getId());

        //At this time no entry in DB again because record is deleted
        responses = restTemplate.getForObject(url, TagResponse[].class);
        assertEquals(0, responses.length);
    }

    @Test
    @DisplayName("Test Update API")
    public void update_tag() throws URISyntaxException {
        URI url = new URI(baseURL + "/tags");

        Tag tag = new Tag();
        tag.setTag("Tag - 1");
        tag.setValue("Value for tag 1");
        utils.insertTagInDB(tag);
        //Verify that record is in DB
        //Fetch list from rest end point
        TagResponse[] responses = restTemplate.getForObject(url, TagResponse[].class);
        //At this time no entry in DB
        assertEquals(1, responses.length);
		assertEquals(tag.getTag(), responses[0].getTag());

		//Now modify the record
		TagRequest tagRequest = new TagRequest();
		tagRequest.setId(responses[0].getId());
		tagRequest.setTag("Tag - 1 MODIFIED");
		tagRequest.setValue("Value for tag 1 MODIFIED");
		restTemplate.put(url, tagRequest);

		// Now fetch record and verify the change
		responses = restTemplate.getForObject(url, TagResponse[].class);
		assertEquals("Tag - 1 MODIFIED", responses[0].getTag());
		assertEquals("Value for tag 1 MODIFIED", responses[0].getValue());


    }


}
