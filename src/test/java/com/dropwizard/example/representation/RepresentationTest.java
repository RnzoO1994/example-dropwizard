package com.dropwizard.example.representation;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import com.dropwizard.example.model.Part;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import static org.assertj.core.api.Assertions.assertThat;
import static io.dropwizard.testing.FixtureHelpers.fixture;
public class RepresentationTest {
	
 private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
 private static final String PART_JSON = "fixtures/part.json";
 private static final String TEST_PART_NAME = "testPartName";
 private static final String TEST_PART_CODE = "testPartCode";

 @Test
 public void serializesToJSON() throws Exception {
   final Part part = new Part(1, TEST_PART_NAME, TEST_PART_CODE);

   final String expected =
       MAPPER.writeValueAsString(MAPPER.readValue(fixture(PART_JSON), Part.class));

   assertThat(MAPPER.writeValueAsString(part)).isEqualTo(expected);
 }

 @Test
 public void deserializesFromJSON() throws Exception {
   final Part part = new Part(1, TEST_PART_NAME, TEST_PART_CODE);

   assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getId()).isEqualTo(part.getId());
   assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getName())
       .isEqualTo(part.getName());
   assertThat(MAPPER.readValue(fixture(PART_JSON), Part.class).getCode())
       .isEqualTo(part.getCode());
 }
}