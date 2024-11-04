package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Feedback;
import com.megabyteful.application.usecase.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/v1/feedback")
public class FeedbackController {

  private final CreateFeedback createFeedback;
  private final GetFeedbackByProfessional getFeedbackByProfessional;

  @PostMapping("/create")
  public ResponseEntity<Feedback> createFeedback(final @RequestBody @Valid Feedback feedback) {

    final var createdFeedback = createFeedback.execute(feedback);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdFeedback.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdFeedback);
  }

  @GetMapping("/professional/{professionalId}/service/{serviceId}")
  public ResponseEntity<List<Feedback>> findByProfessionalIdAndServiceId(
      final @PathVariable int professionalId, final @PathVariable int serviceId) {

    List<Feedback> feedbackList = getFeedbackByProfessional.execute(professionalId, serviceId);
    return ResponseEntity.ok(feedbackList);
  }
}
