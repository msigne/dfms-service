package com.ia.dfms.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Step {
    private Request request;
    private String observation;
    private PhysicalResource division;
    private Resource manager;
    private StepStatus status;
    private RequestStatus requestStatus;
}
