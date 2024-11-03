package com.megabyteful.infrastructure.api;

import static com.megabyteful.infrastructure.api.fixture.CustomerControllerTestFixture.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megabyteful.application.usecase.CreateCustomer;
import com.megabyteful.application.usecase.DeleteCustomer;
import com.megabyteful.application.usecase.GetCustomer;
import com.megabyteful.application.usecase.UpdateCustomer;
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
class CustomerControllerTest {

  private static final String BASE_URL = "/megabyteful/customers";

  @MockBean private CreateCustomer createCustomer;
  @MockBean private GetCustomer getCustomer;
  @MockBean private UpdateCustomer updateCustomer;
  @MockBean private DeleteCustomer deleteCustomer;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Nested
  class CreateCustomerTests {

    @Test
    void shouldCreateCustomerSuccessfully() throws Exception {
      final var request = validCustomerRequest();
      final var createResponse = validCustomerRequest();

      when(createCustomer.execute(any())).thenReturn(createResponse);

      mockMvc
          .perform(
              post(BASE_URL)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.name", equalTo("John Doe")))
          .andExpect(jsonPath("$.cpf", equalTo("12345678901")))
          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
          .andExpect(jsonPath("$.email", equalTo("test@example.com")));

      verify(createCustomer).execute(any());
    }
  }

  @Nested
  class FindByCpfTests {

    @Test
    void shouldFindCustomerByCpfSuccessfully() throws Exception {
      final var request = "12345678901";
      final var response = validCustomerRequest();

      when(getCustomer.execute(any())).thenReturn(response);

      mockMvc
          .perform(
              get(BASE_URL + "/" + request)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name", equalTo("John Doe")))
          .andExpect(jsonPath("$.cpf", equalTo("12345678901")))
          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
          .andExpect(jsonPath("$.email", equalTo("test@example.com")));

      verify(getCustomer).execute(any());
    }
  }

  @Nested
  class UpdateCustomerTests {

    @Test
    void shouldUpdateCustomerSuccessfully() throws Exception {
      final var cpf = "12345678901";
      final var request = validUpdateCustomerRequest();
      final var updateResponse = validCustomerResponse();

      when(updateCustomer.execute(any(), any())).thenReturn(updateResponse);

      mockMvc
          .perform(
              put(BASE_URL + "/" + cpf)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name", equalTo("John Doe")))
          .andExpect(jsonPath("$.cpf", equalTo("12345678901")))
          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
          .andExpect(jsonPath("$.email", equalTo("test@example.com")));

      verify(updateCustomer).execute(any(), any());
    }
  }

  @Nested
  class DeleteCustomerTests {

    @Test
    void shouldDeleteCustomerSuccessfully() throws Exception {
      final var cpf = "12345678901";

      mockMvc.perform(delete(BASE_URL + "/" + cpf)).andExpect(status().isNoContent());

      verify(deleteCustomer).execute(any());
    }
  }
}
