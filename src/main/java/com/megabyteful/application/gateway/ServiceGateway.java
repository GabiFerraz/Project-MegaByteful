package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Service;
import java.util.Optional;

public interface ServiceGateway {

  Optional<Service> findById(final int id);
}
