package com.javalec.spring_ex_15;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Student.class.isAssignableFrom(arg0); // 검증할 객체의 클래스 타입 정보
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Student student = (Student) obj;

		String studentName = student.getName();
		if (studentName == null || studentName.trim().isEmpty()) {
			errors.rejectValue("name", "trouble");
		}

		int studentId = student.getId();
		if (studentId == 0) {
			errors.rejectValue("id", "trouble");
		}

	}

}
