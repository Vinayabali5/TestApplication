package uk.ac.reigate.onlineapplications.service

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.mockito.Mockito.doAnswer
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.Mockito.any

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

import uk.ac.reigate.onlineapplications.domain.Address
import uk.ac.reigate.onlineapplications.domain.Application
import uk.ac.reigate.onlineapplications.domain.lookup.Gender
import uk.ac.reigate.onlineapplications.domain.lookup.LegalSex
import uk.ac.reigate.onlineapplications.domain.lookup.School
import uk.ac.reigate.onlineapplications.dto.application.AddressDTO
import uk.ac.reigate.onlineapplications.dto.application.ApplicationDTO
import uk.ac.reigate.onlineapplications.repository.ApplicationRepository
import uk.ac.reigate.onlineapplications.service.lookup.CourseService
import uk.ac.reigate.onlineapplications.service.lookup.GenderService
import uk.ac.reigate.onlineapplications.service.lookup.LegalSexService
import uk.ac.reigate.onlineapplications.service.lookup.SchoolService
import uk.ac.reigate.onlineapplications.service.lookup.TitleService

@ActiveProfiles("test")
@SpringBootTest
class ApplicationServiceTests {

	@MockBean
	ApplicationRepository applicationRepository

	@MockBean
	TitleService titleService

	@MockBean
	LegalSexService legalSexService

	@MockBean
	GenderService genderService

	@MockBean
	AddressService addressService

	@MockBean
	ContactService contactService

	@MockBean
	SchoolService schoolService

	@MockBean
	CourseService courseService

	@MockBean
	CourseRequestService courseRequestService

	@Autowired
	ApplicationService applicationService

	// Sample Data
	Address sampleAddress = new Address(line1: 'Some Line', town: 'Some Town', postcode: 'RH20SD')
	Application sampleApplication = new Application(id: 1, )

	@BeforeEach
	public void setup() {
		when(applicationRepository.findById(1)).thenReturn(new Optional(sampleApplication))
		when(legalSexService.findById(1)).thenReturn(new LegalSex(id:1))
		when(genderService.findById(1)).thenReturn(new Gender(id: 1))
		when(schoolService.findById(1)).thenReturn(new School(id: 1))
		when(addressService.convertToEntity(any(AddressDTO.class))).thenCallRealMethod()
	}

	@Test
	public void testFindById() {
		Application entity = applicationService.findById(sampleApplication.id)

		verify(applicationRepository, times(1)).findById(sampleApplication.id)

		assertEquals(entity.id, sampleApplication.id)
	}

	@Test
	public void testFindById_invalidId() {
		Application entity = applicationService.findById(999)

		verify(applicationRepository, times(1)).findById(999)

		assertEquals(entity, null)
	}

	@Test
	public void testCreateApplication_validApplicationNoId() {
		doAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock invocation) {
						Object[] args = invocation.getArguments()
						Application app = (Application)args[0]
						app.id = 1
						return app
					}
				}).when(applicationRepository).save(Mockito.any(Application.class))
		ApplicationDTO appDto = new ApplicationDTO(
				legalFirstName: 'John',
				middleNames: null,
				legalSurname: 'Doe',
				dateOfBirth: new Date(),
				legalSexId: 1,
				genderId: 1,
				home: '01234567890',
				email: 'john.doe@test.com',
				resident: true,
				sibling: false,
				address: new AddressDTO(this.sampleAddress),
				schoolId: 1,

				)
		Application app = applicationService.createApplication(appDto)

		verify(legalSexService, times(1)).findById(appDto.legalSexId)
		verify(genderService, times(1)).findById(appDto.genderId)
		verify(schoolService, times(1)).findById(appDto.schoolId)
		
		assertEquals(app.person.legalFirstName, appDto.legalFirstName)
		assertEquals(app.person.middleNames, appDto.middleNames)
		assertEquals(app.person.legalSurname, appDto.legalSurname)
		assertEquals(app.person.preferredName, appDto.preferredName)
		assertEquals(app.person.previousSurname, appDto.previousSurname)
		assertEquals(app.person.dateOfBirth, appDto.dateOfBirth)
		assertEquals(app.person.legalSex.id, appDto.legalSexId)
		assertEquals(app.person.gender.id, appDto.genderId)
		assertEquals(app.person.home, appDto.home)
		assertEquals(app.person.mobile, appDto.mobile)
		assertEquals(app.person.email, appDto.email)
		assertNotNull(app.person.address)
		assertEquals(app.resident, appDto.resident)
		assertEquals(app.sibling, appDto.sibling)
		assertEquals(app.school.id, appDto.schoolId)
		
	}
}
