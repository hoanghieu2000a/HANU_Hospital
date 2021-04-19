package se2.hanu_hospital.error.patient;

//When an PatientNotFoundException is thrown, this extra tidbit of Spring MVC configuration is used to render an HTTP 404
// PatientNotFoundException is an exception used to indicate when an employee is looked up but not found.
public class PatientNotFoundException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PatientNotFoundException(Long id) {
        super("Could not find this patient: " + id);
      }
}
