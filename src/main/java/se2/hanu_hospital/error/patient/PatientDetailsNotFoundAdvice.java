package se2.hanu_hospital.error.patient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PatientDetailsNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(PatientDetailsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String patientDetailsNotFoundHandler(PatientDetailsNotFoundException ex) {
        return ex.getMessage();
    }
}
