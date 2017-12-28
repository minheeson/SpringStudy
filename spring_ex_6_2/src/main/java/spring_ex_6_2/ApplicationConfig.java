package spring_ex_6_2;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	// 컨테이너 역할

	@Bean
	public Student student1() {
		ArrayList<String> hobby = new ArrayList<String>();
		hobby.add("수영");
		hobby.add("요리");

		Student student = new Student("한아름");
		student.setAge(10);
		student.setHobby(hobby);
		return student;
	}

	@Bean
	public Student student2() {
		ArrayList<String> hobby = new ArrayList<String>();
		hobby.add("독서");
		hobby.add("음악감상");

		Student student = new Student("손민희");
		student.setAge(11);
		student.setHobby(hobby);
		return student;
	}

}
