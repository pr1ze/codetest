package dk.lunar.codetest.application.web

import dk.lunar.codetest.generated.api.NotFoundException
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.persistence.OptimisticLockException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity(final NoSuchElementException noSuchElementException) {
        new ResponseEntity(noSuchElementException.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity(final DuplicateKeyException duplicateKeyException) {
        new ResponseEntity(duplicateKeyException.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(OptimisticLockException.class)
    ResponseEntity(final OptimisticLockException optimisticLockException) {
        new ResponseEntity(optimisticLockException.message, HttpStatus.PRECONDITION_FAILED)
    }

}
