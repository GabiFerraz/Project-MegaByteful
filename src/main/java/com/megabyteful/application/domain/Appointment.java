package com.megabyteful.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    private int id;
    private int scheduleId;
    private int customerId;
    private int serviceProviderID;


}
