package com.alexchurs.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    /**
     * @param exception исключение если пользователь не найден
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(EmployeeNotFoundException exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * @param exception обработка всех исключений
     * @return status 400
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
