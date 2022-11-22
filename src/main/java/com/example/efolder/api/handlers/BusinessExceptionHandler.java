package com.example.efolder.api.handlers;

import com.example.efolder.api.handlers.DTO.ErrorResponse;
import com.example.efolder.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse businessExceptionHandler(BusinessException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse addressNotFoundExceptionHandler(AddressNotFoundException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse authExceptionHandler(AuthException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(DocumentCreationFailureException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse documentCreationFailureExceptionHandler(DocumentCreationFailureException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(DocumentIsTooBigException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse documentIsTooBigExceptionExceptionHandler(DocumentIsTooBigException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse documentNotFoundExceptionHandler(DocumentNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse emailExistsExceptionHandler(EmailExistsException expireDateEarlierThanStartDateException) {
        return new ErrorResponse(expireDateEarlierThanStartDateException);
    }

    @ExceptionHandler(EmploymentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse employmentNotFoundExceptionHandler(EmploymentNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(EmptyFieldException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse emptyFieldExceptionHandler(EmptyFieldException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(JwtExpireException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse jwtExpireExceptionHandler(JwtExpireException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(JwtValidationException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse jwtValidationExceptionHandler(JwtValidationException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(NoSuchFileCategoryException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse noSuchFileCategoryExceptionHandler(NoSuchFileCategoryException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(NotMatchingPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse notMatchingPasswordExceptionHandler(NotMatchingPasswordException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(NotPersonFromHrPeoplePullException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse notPersonFromHrPeoplePullExceptionHandler(NotPersonFromHrPeoplePullException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(NotValidDocumentExtensionException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse notValidDocumentExtensionExceptionHandler(NotValidDocumentExtensionException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(NotValidPictureException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse notValidPictureExceptionHandler(NotValidPictureException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(ProfilePictureNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse profilePictureNotFoundExceptionHandler(ProfilePictureNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse roleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse teamNotFoundExceptionHandler(TeamNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler(UserHasRoleException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse userHasRoleExceptionHandler(UserHasRoleException exception) {
        return new ErrorResponse(exception);
    }
    @ExceptionHandler(UsernameIsTakenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse usernameIsTakenExceptionHandler(UsernameIsTakenException exception) {
        return new ErrorResponse(exception);
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ErrorResponse(exception);
    }
}
