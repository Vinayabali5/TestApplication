package uk.ac.reigate.onlineapplications.api.lookup

import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.when
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc

import uk.ac.reigate.onlineapplications.domain.lookup.Course
import uk.ac.reigate.onlineapplications.service.lookup.CourseService

@WebMvcTest(CoursesApi.class)
@AutoConfigureRestDocs
class CoursesApiTests extends GeneralApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService service;

	// Sample data
	Course one = new Course(id: 1, title: 'A Level Maths', entryRequirements: 'To be good at Maths')
	Course two = new Course(id: 1, title: 'A Level English', entryRequirements: 'To be good at English')

	@BeforeEach
	public void setupService() {
		when(service.findAll()).thenReturn([one, two])
	}

	@WithMockUser(value = "user@reigate.ac.uk", password = "test")
    @Test
	public void shouldReturnAnArrayOfJson() throws Exception {
		this.mockMvc.perform(get("/courses"))
				.andExpect(status().isOk())
				.andExpect(jsonPath('$', hasSize(2)))
				.andExpect(jsonPath('$[0].id', is(one.id)))
				.andExpect(jsonPath('$[0].title', is(one.title)))
				.andExpect(jsonPath('$[0].entryRequirements', is(one.entryRequirements)))
				.andDo(print())
				.andDo(document("courses-api-get-all"));
	}

}
