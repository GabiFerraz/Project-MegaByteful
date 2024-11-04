package com.megabyteful.infrastructure.api;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class ServiceProviderControllerTest {
//
//	private static final String BASE_URL = "/megabyteful/servicesProviders";
//
//	  @MockBean
//	  private CreateServiceProvider createServiceProvider;
//	  @MockBean
//	  private GetServiceProvider getServiceProvider;
//	  @MockBean
//	  private UpdateServiceProvider updateServiceProvider;
//	  @MockBean
//	  private DeleteServiceProvider deleteServiceProvider;
//
//	  @Autowired
//	  private MockMvc mockMvc;
//	  @Autowired
//	  private ObjectMapper objectMapper;
//
//	  @Nested
//	  class CreateCustomerTests {
//
//	    @Test
//	    void shouldCreateServiceProviderSuccessfully() throws Exception {
//	      final var request = validServiceProviderRequest();
//	      final var createResponse = validServiceProviderRequest();
//
//	      when(createServiceProvider.execute(any())).thenReturn(createResponse);
//
//	      mockMvc
//	          .perform(
//	              post(BASE_URL)
//	                  .contentType(MediaType.APPLICATION_JSON)
//	                  .content(objectMapper.writeValueAsString(request)))
//	          .andExpect(status().isCreated())
//	          .andExpect(jsonPath("$.name", equalTo("Maria Silva")))
//	          .andExpect(jsonPath("$.document", equalTo("12345678900")))
//	          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
//	          .andExpect(jsonPath("$.beautyServices", equalTo("Hair cut")))
//	          .andExpect(jsonPath("$.address", equalTo("Rua Hum, 500")))
//	          .andExpect(jsonPath("$.email", equalTo("test@example.com")));
//
//	      verify(createServiceProvider).execute(any());
//	    }
//	  }
//
//	  @Nested
//	  class FindByDocumentTests {
//
//	    @Test
//	    void shouldFindServiceProviderByDocumentSuccessfully() throws Exception {
//	      final var request = "12345678900";
//	      final var response = validServiceProviderRequest();
//
//	      when(getServiceProvider.execute(any())).thenReturn(response);
//
//	      mockMvc
//	          .perform(
//	              get(BASE_URL + "/" + request)
//	                  .contentType(MediaType.APPLICATION_JSON)
//	                  .content(objectMapper.writeValueAsString(request)))
//	          .andExpect(status().isOk())
//	          .andExpect(jsonPath("$.name", equalTo("Maria Silva")))
//	          .andExpect(jsonPath("$.document", equalTo("12345678900")))
//	          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
//	          .andExpect(jsonPath("$.beautyServices", equalTo("Hair cut")))
//	          .andExpect(jsonPath("$.address", equalTo("Rua Hum, 500")))
//	          .andExpect(jsonPath("$.email", equalTo("test@example.com")));
//
//	      verify(getServiceProvider).execute(any());
//	    }
//	  }
//
//	  @Nested
//	  class UpdateServiceProviderTests {
//
//	    @Test
//	    void shouldUpdateServiceProviderSuccessfully() throws Exception {
//	      final var document = "12345678900";
//	      final var request = validUpdateServiceProviderRequest();
//	      final var updateResponse = validServiceProviderResponse();
//
//	      when(updateServiceProvider.execute(any(), any())).thenReturn(updateResponse);
//
//	      mockMvc
//	          .perform(
//	              put(BASE_URL + "/" + document)
//	                  .contentType(MediaType.APPLICATION_JSON)
//	                  .content(objectMapper.writeValueAsString(request)))
//	          .andExpect(status().isOk())
//	          .andExpect(jsonPath("$.name", equalTo("Maria Silva")))
//	          .andExpect(jsonPath("$.document", equalTo("12345678900")))
//	          .andExpect(jsonPath("$.phone", equalTo("12345678969")))
//	          .andExpect(jsonPath("$.beautyServices", equalTo("Hair cut")))
//	          .andExpect(jsonPath("$.address", equalTo("Rua Hum, 500")))
//	          .andExpect(jsonPath("$.email", equalTo("test@example.com")));
//
//	      verify(updateServiceProvider).execute(any(), any());
//	    }
//	  }
//
//	  @Nested
//	  class DeleteServiceProviderTests {
//
//	    @Test
//	    void shouldDeleteServiceProviderSuccessfully() throws Exception {
//	      final var document = "12345678900";
//
//	      mockMvc.perform(delete(BASE_URL + "/" + document)).andExpect(status().isNoContent());
//
//	      verify(deleteServiceProvider).execute(any());
//	    }
//	  }
//
//
// }
