package ru.skypro.homework.springboot.weblibrary_hw.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    Logger logger= LoggerFactory.getLogger(EmployeeService.class);
    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        String message = "Значение не найдено";
        logger.error(message);
        // Возвращает статус 404 (Not Found) при возникновении IOException.
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        String message = "Ошибка сервера во время выполнения запроса";
        logger.error(message);
        // Возвращает статус 500 (Internal Server Error)
        // при возникновении SQLException.
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIncorrectIdException(IncorrectIdException incorrectIdException) {
        // Возвращает статус 400 (Bad Request) при возникновении Exception.
        String message = "Ошибка ввода. Некорректный ID";
        logger.error(message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
