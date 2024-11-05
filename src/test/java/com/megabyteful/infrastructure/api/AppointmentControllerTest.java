package com.megabyteful.infrastructure.api;

import static com.megabyteful.infrastructure.api.fixture.AppointmentControllerTestFixture.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megabyteful.application.usecase.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

  private static final String BASE_URL = "/megabyteful/appointments";

  @MockBean private CreateAppointment createAppointment;
  @MockBean private GetAppointment getAppointment;
  @MockBean private DeleteAppointment deleteAppointment;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Nested
  class CreateAppointmentTests {

    @Test
    void shouldCreateAppointmentSuccessfully() throws Exception {
      final var request = validAppointmentRequest();
      final var createResponse = validAppointmentRequest();

      when(createAppointment.execute(any(), any())).thenReturn(createResponse);

      mockMvc
          .perform(
              post(BASE_URL + "/createAppointment?cpf=87987987912")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.scheduleId", equalTo(1)))
          .andExpect(jsonPath("$.customerId", equalTo(1)));

      verify(createAppointment).execute(any(), any());
    }
  }

  @Nested
  class DeleteAppointmentTests {

    @Test
    void shouldDeleteAppointmentSuccessfully() throws Exception {
      final var id = 1;

      mockMvc.perform(delete(BASE_URL + "/" + id)).andExpect(status().isNoContent());
    }
  }
}
