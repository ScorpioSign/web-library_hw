package ru.skypro.homework.springboot.weblibrary_hw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        String message = "Ошибка ввода. Некорректный ID";
        // Возвращает статус 404 (Not Found) при возникновении IOException.
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        String message = "Ошибка сервера во время выполнения запроса";
        // Возвращает статус 500 (Internal Server Error)
        // при возникновении SQLException.
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        // Возвращает статус 400 (Bad Request) при возникновении Exception.
        String message = "Неверные параметры запроса";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
