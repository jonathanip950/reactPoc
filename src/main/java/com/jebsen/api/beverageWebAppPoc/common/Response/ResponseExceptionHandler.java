//package com.jebsen.api.beverageWebAppPoc.common.Response;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonMappingException.Reference;
//import com.fasterxml.jackson.databind.exc.MismatchedInputException;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.ConversionNotSupportedException;
//import org.springframework.beans.TypeMismatchException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingPathVariableException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
//import org.springframework.web.multipart.support.MissingServletRequestPartException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import javax.validation.UnexpectedTypeException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//@Slf4j
//@ControllerAdvice
//public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
//
//	@Getter
//	@Setter
//	@ToString
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Builder(toBuilder=true)
//	public static class ErrorResponse {
//		@JsonProperty("result_code")
//		private String result_code;
//		@JsonProperty("result_message")
//		private String result_message;
//		@JsonProperty("result_error")
//		private List<ErrorField> result_error;
//		@JsonProperty("result_content")
//		private Map<String, Object> result_content;
//	}
//
//	@Getter
//	@Setter
//	@ToString
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Builder(toBuilder=true)
//	public static class ErrorField {
//		private String field;
//		private String message;
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleHttpRequestMethodNotSupported "+status);
//
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(status.value()+" "+status.name())
//				.result_error(new ArrayList<ErrorField>())
//				.build();
//
//		return new ResponseEntity<>(errorBody, headers, HttpStatus.OK);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleHttpMediaTypeNotSupported "+status);
//
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(status.value()+" "+status.name())
//				.result_error(new ArrayList<ErrorField>())
//				.build();
//		return new ResponseEntity<>(errorBody, headers, HttpStatus.OK);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleHttpMediaTypeNotAcceptable "+status);
//		return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		log.info("handleMissingPathVariable "+status);
//		return super.handleMissingPathVariable(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleMissingServletRequestParameter "+status);
//		List<ErrorField> resultErrors = new ArrayList<ErrorField>();
//		resultErrors.add(new ErrorField(ex.getParameterName(), ex.getMessage()));
//
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(status.value()+" "+status.name())
//				.result_error(resultErrors)
//				.build();
//	//
//		return new ResponseEntity<>(errorBody, headers, HttpStatus.OK);
////		return super.handleMissingServletRequestParameter(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleServletRequestBindingException "+status);
//		return super.handleServletRequestBindingException(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleConversionNotSupported "+status);
//		return super.handleConversionNotSupported(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		log.info("handleTypeMismatch "+status);
//		return super.handleTypeMismatch(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleHttpMessageNotReadable "+status, ex);
//		Throwable cause = ex.getCause();
//		String message = status.value()+" Invalid Request Content";
//		ArrayList<ErrorField> errorFields = new ArrayList<ErrorField>();
//		if (cause instanceof JsonParseException) {
//			message += ":"+((JsonParseException) cause).getOriginalMessage();
//		}
//		else if (cause instanceof MismatchedInputException) {
//			MismatchedInputException mie = (MismatchedInputException) cause;
//			String errorFieldName = "";
//			if (mie.getPath() != null && mie.getPath().size() > 0) {
//				for(Reference reference: mie.getPath()) {
//					if(reference.getFieldName() != null && !reference.getFieldName().isBlank()) {
//						errorFieldName += reference.getFieldName();
//					}
//					if(reference.getIndex() >= 0) {
//						errorFieldName += "["+reference.getIndex()+"].";
//					}
//				}
//            }
//			ErrorField errorField = new ErrorField();
//			errorField.setField(errorFieldName);
//			errorField.setMessage(mie.getOriginalMessage());
//			errorFields.add(errorField);
//		}
//		else if (cause instanceof JsonMappingException) {
//			message += ":"+((JsonMappingException) cause).getOriginalMessage();
//		}
//
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(message)
//				.result_error(errorFields)
//				.build();
//		return new ResponseEntity<>(errorBody, headers, status);
////		return super.handleHttpMessageNotReadable(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleHttpMessageNotWritable "+status);
//		return super.handleHttpMessageNotWritable(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleMethodArgumentNotValid "+status);
//
//		List<ErrorField> resultErrors = new ArrayList<ErrorField>();
//		for(FieldError error: ex.getFieldErrors()) {
//			resultErrors.add(new ErrorField(error.getField(), error.getDefaultMessage()));
//		}
//
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(status.value()+" "+status.name())
//				.result_error(resultErrors)
//				.build();
//
//		return new ResponseEntity<>(errorBody, headers, HttpStatus.OK);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		log.info("handleMissingServletRequestPart "+status);
//		return super.handleMissingServletRequestPart(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
//			WebRequest request) {
//		log.info("handleBindException "+status);
//		return super.handleBindException(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		log.info("handleNoHandlerFoundException "+status);
//		return super.handleNoHandlerFoundException(ex, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
//		log.info("handleAsyncRequestTimeoutException "+status);
//		return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		log.info("handleExceptionInternal "+status);
//		return super.handleExceptionInternal(ex, body, headers, status, request);
//	}
//
//	@ExceptionHandler(ConstraintViolationException.class)
//	protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
//		log.info("handleConstraintViolationException "+ex.getMessage());
//		List<ErrorField> resultErrors = new ArrayList<ErrorField>();
//		resultErrors.add(new ErrorField(request.getRequestURI()+request.getQueryString(), ex.getLocalizedMessage()));
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(HttpStatus.BAD_REQUEST.value()+" "+ex.getLocalizedMessage())
//				.result_error(resultErrors)
//				.build();
//
//		return new ResponseEntity<>(errorBody, HttpStatus.OK);
//	}
//
//	@ExceptionHandler(UnexpectedTypeException.class)
//	protected ResponseEntity<?> handleUnexpectedTypeException(UnexpectedTypeException ex, HttpServletRequest request) {
//		log.info("handleUnexpectedTypeException "+ex.getMessage());
//		List<ErrorField> resultErrors = new ArrayList<ErrorField>();
//		resultErrors.add(new ErrorField(request.getRequestURI()+request.getQueryString(), ex.getLocalizedMessage()));
//		ErrorResponse errorBody = ErrorResponse.builder()
//				.result_code("99")
//				.result_message(HttpStatus.BAD_REQUEST.value()+" "+ex.getLocalizedMessage())
//				.result_error(resultErrors)
//				.build();
//
//		return new ResponseEntity<>(errorBody, HttpStatus.OK);
//	}
//}
