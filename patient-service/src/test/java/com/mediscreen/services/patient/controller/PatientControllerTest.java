package com.mediscreen.services.patient.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;


    @DisplayName("get patient displays patient.html")
    @Test
    public void testGetPatient() throws Exception {
        //GIVEN
        final String url = "/patient/get";

        // WHEN
        final var response = mockMvc.perform(get(url)
                .param("patientId","1"))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/patient"))
                .andExpect(model().attributeExists("patient"));
    }

    @DisplayName("get patients displays patient.html")
    @Test
    public void testGetPatients() throws Exception {
        //GIVEN
        final String url = "/patients";

        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/patient"))
                .andExpect(model().attributeExists("patients"));
    }

    @DisplayName("get patient displays patient.html")
    @Test
    public void testGetPatientUpdateForm() throws Exception {
        //GIVEN
        final String url = "/patient/update/{patientId}";
        Long patientId = 1L;
        // WHEN
        final var response = mockMvc.perform(get(url,patientId))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/update"))
                .andExpect(model().attributeExists("patient"));
    }
    @Test
    @DisplayName("Test Update patient")
    public void updatePatientTest() throws Exception {
        //GIVEN
        final String url = "/patient/update/{patientId}";
        Patient expectedResult = new Patient(1L,"family","given", LocalDate.of(2002,01,01),F,"address","phone" );
        assertThat(patientService.getPatient(expectedResult.getId())).isNotEqualTo(expectedResult);
        //WHEN
        final var result = mockMvc.perform(post(url,expectedResult.getId())
                .param("id",expectedResult.getId().toString())
                .param("family",expectedResult.getFamily())
                .param("given",expectedResult.getGiven())
                .param("dob",expectedResult.getDob().toString())
                .param("sex",expectedResult.getSex().toString())
                .param("address",expectedResult.getAddress())
                .param("phone",expectedResult.getPhone())
                .content(asJsonString(expectedResult))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patient/get?patientId=" + expectedResult.getId()));

        Patient updatedPatient = patientService.getPatient(expectedResult.getId());

        assertThat(updatedPatient)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(expectedResult));

    }

    @DisplayName("display patient form")
    @Test
    public void testCreatePatientForm() throws Exception {
        //GIVEN
        final String url = "/patient/add";

        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/add"))
                .andExpect(model().attributeExists("patient"));
    }


    @DisplayName("get patients displays patient.html")
    @Test
    public void testCreatePatient() throws Exception {
        //GIVEN
        final String url = "/patient/add";
        Patient patient = new Patient("family","given3", LocalDate.of(2002,01,01), Patient.Gender.M,"address","phone");

        // WHEN
        final var response = mockMvc.perform(post(url)
                .param("family",patient.getFamily())
                .param("given",patient.getGiven())
                .param("dob",patient.getDob().toString())
                .param("sex",patient.getSex().toString())
                .param("address",patient.getAddress())
                .param("phone",patient.getPhone())
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        // THEN
        Long patientId = patientService.getPatientByFamilyAndGiven(patient.getFamily(),patient.getGiven()).getId();
        response.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patient/get?patientId=" + patientId));

    }
    @SneakyThrows
    protected String asJsonString(final Object obj) {
        return objectMapper.writeValueAsString(obj);
    }*/
}
