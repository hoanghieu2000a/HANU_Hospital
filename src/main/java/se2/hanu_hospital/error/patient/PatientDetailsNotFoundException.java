package se2.hanu_hospital.error.patient;

public class PatientDetailsNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PatientDetailsNotFoundException(Long id) {
        super("Could not find this patient: " + id);
      }
}
