package ir.farbod.humanresource.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ExceptionModel implements Serializable {
    private String message;
    private LocalDate timestamp;
    private HttpStatus status;
}
