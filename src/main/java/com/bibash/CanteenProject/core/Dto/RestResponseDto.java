package com.bibash.CanteenProject.core.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;




@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto {

    private String message;
    private Object detail;

    @JsonIgnore
    private int code;

  /*  public ResponseEntity successModel(Object o) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(o);
        r.setMessage("SUCCESS");
        return new ResponseEntity(r, HttpStatus.OK);
    }

    public ResponseEntity validationFailed(List<ObjectError> errors) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(errors);

        return new ResponseEntity(r, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity failureModel(String message) {
        RestResponseDto r = new RestResponseDto();
        r.setMessage(message);
        return new ResponseEntity(r, HttpStatus.BAD_REQUEST);
    }*/

    public ResponseEntity successModel(Object o) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(o);
        r.setMessage("SUCCESS");
        return new ResponseEntity(r, HttpStatus.OK);
    }

    public ResponseEntity failureModel(String message) {
        RestResponseDto r = new RestResponseDto();
        r.setMessage(message);
        return new ResponseEntity(r, HttpStatus.BAD_REQUEST);
    }
}
