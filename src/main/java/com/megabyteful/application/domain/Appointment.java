package com.megabyteful.application.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
    private int id;
    private int scheduleId;
    private int customerId;
    private int serviceProviderID;

    public static Appointment createAppointment(
            final int id, int scheduleId, int customerId, final int serviceProviderID) {

        return new Appointment(id, scheduleId, customerId, serviceProviderID);
    }
}
