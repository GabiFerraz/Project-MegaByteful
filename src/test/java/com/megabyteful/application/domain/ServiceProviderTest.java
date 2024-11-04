package com.megabyteful.application.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ServiceProviderTest {

  @Test
  public void testValidServiceProvider() {
    ServiceProvider serviceProvider =
        new ServiceProvider(
            1,
            "Maria Silva",
            "12345678901",
            "+5511998765432",
            "Haircut",
            "123 Main St",
            "maria.silva@example.com");
    assertTrue(isValidServiceProvider(serviceProvider));
  }

  @Test
  public void testInvalidName() {
    ServiceProvider serviceProvider =
        new ServiceProvider(
            1,
            "", // Nome inválido
            "12345678901",
            "+5511998765432",
            "Haircut",
            "123 Main St",
            "maria.silva@example.com");
    assertFalse(isValidServiceProvider(serviceProvider));
  }

  @Test
  public void testInvalidDocument() {
    ServiceProvider serviceProvider =
        new ServiceProvider(
            1,
            "Maria Silva",
            "123456", // CPF inválido
            "+5511998765432",
            "Haircut",
            "123 Main St",
            "maria.silva@example.com");
    assertFalse(isValidServiceProvider(serviceProvider));
  }

  @Test
  public void testInvalidPhone() {
    ServiceProvider serviceProvider =
        new ServiceProvider(
            1,
            "Maria Silva",
            "12345678901",
            "1234", // Telefone inválido
            "Haircut",
            "123 Main St",
            "maria.silva@example.com");
    assertFalse(isValidServiceProvider(serviceProvider));
  }

  @Test
  public void testInvalidEmail() {
    ServiceProvider serviceProvider =
        new ServiceProvider(
            1,
            "Maria Silva",
            "12345678901",
            "+5511998765432",
            "Haircut",
            "123 Main St",
            "invalid-email" // Email inválido
            );
    assertFalse(isValidServiceProvider(serviceProvider));
  }

  private boolean isValidServiceProvider(ServiceProvider serviceProvider) {
    if (serviceProvider.getName() == null
        || serviceProvider.getName().isBlank()
        || serviceProvider.getName().length() > 100
        || !serviceProvider.getName().matches("[a-zA-Z\\s]+")) {
      return false;
    }
    if (serviceProvider.getDocument() == null
        || !serviceProvider.getDocument().matches("\\d{11}")) {
      return false;
    }
    if (serviceProvider.getPhone() == null
        || !serviceProvider.getPhone().matches("\\+?\\d{10,15}")) {
      return false;
    }
    if (serviceProvider.getBeautyServices() == null
        || serviceProvider.getBeautyServices().isBlank()
        || serviceProvider.getBeautyServices().length() > 30) {
      return false;
    }
    if (serviceProvider.getAddress() == null
        || serviceProvider.getAddress().isBlank()
        || serviceProvider.getAddress().length() > 255) {
      return false;
    }
    if (serviceProvider.getEmail() == null
        || serviceProvider.getEmail().isBlank()
        || serviceProvider.getEmail().length() > 255
        || !serviceProvider.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
      return false;
    }
    return true;
  }
}
