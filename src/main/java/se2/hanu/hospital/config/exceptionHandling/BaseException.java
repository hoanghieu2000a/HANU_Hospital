package se2.hanu.hospital.config.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException {

    private HttpStatus status;

    private String message;

    @Singular
    private List<String> errors;
}
