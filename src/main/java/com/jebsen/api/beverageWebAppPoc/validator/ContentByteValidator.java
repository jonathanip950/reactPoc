package com.jebsen.api.beverageWebAppPoc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class ContentByteValidator implements ConstraintValidator<ContentByte, String> {

	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	@Override
	public void initialize(ContentByte constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String content, ConstraintValidatorContext context) {
		content = content==null?"":content;
		int bytes = content.getBytes(StandardCharsets.UTF_8).length;
//		log.info("isValid min:{}, max:{}; byte:{}; length:{}; content:{}", this.min, this.max, content.getBytes(StandardCharsets.UTF_8).length, content.length(), content);
		return bytes >= this.min && bytes <= this.max;
	}

}
