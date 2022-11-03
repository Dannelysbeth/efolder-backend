package com.example.efolder.api.handlers;

import com.example.efolder.api.handlers.DTO.ErrorResponse;
import com.example.efolder.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse businessExceptionHandler(BusinessException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse addressNotFoundExceptionHandler(AddressNotFoundException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse documentCreationFailureExceptionHandler(DocumentCreationFailureException exception){
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse documentNotFoundExceptionHandler(DocumentNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse emailExistsExceptionHandler(EmailExistsException expireDateEarlierThanStartDateException) {
        return new ErrorResponse(expireDateEarlierThanStartDateException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse employmentNotFoundExceptionHandler(EmploymentNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse noSuchFileCategoryExceptionHandler(NoSuchFileCategoryException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse notMatchingPasswordExceptionHandler(NotMatchingPasswordException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse notPersonFromHrPeoplePullExceptionHandler(NotPersonFromHrPeoplePullException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse notValidDocumentExtensionExceptionHandler(NotValidDocumentExtensionException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse notValidPictureExceptionHandler(NotValidPictureException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse profilePictureNotFoundExceptionHandler(ProfilePictureNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse roleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse teamNotFoundExceptionHandler(TeamNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse userHasRoleExceptionHandler(UserHasRoleException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ErrorResponse(exception);
    }

//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorResponse CardInactiveExceptionHandler(CardInactiveException cardInactiveException) {
//        return new ErrorResponse(cardInactiveException);
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorResponse UnsupportedSkiLiftScannerExceptionHandler(UnsupportedSkiliftScannerException unsupportedSkiliftScannerException) {
//        return new ErrorResponse(unsupportedSkiliftScannerException);
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorResponse CardNotFoundExceptionHandler(CardNotFoundException cardNotFoundException) {
//        return new ErrorResponse(cardNotFoundException);
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorResponse CardUnpaidExceptionHandler(CardUnpaidException cardUnpaidException) {
//        return new ErrorResponse(cardUnpaidException);
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ErrorResponse CardUnpaidExceptionHandler(ScanNotFoundException scanNotFoundException) {
//        return new ErrorResponse(scanNotFoundException);
//    }
}
