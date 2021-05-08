package se2.hanu_hospital.record;

import javax.persistence.Lob;

public class RecordPayload {
    private Long id;
    private String description;
    private String diagnosis;
    private Long patientId;
    private Long doctorId;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }


    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}
