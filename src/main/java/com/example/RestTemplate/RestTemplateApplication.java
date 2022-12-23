package com.example.RestTemplate;

import com.example.RestTemplate.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.*;

@SpringBootApplication
public class RestTemplateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

	@Override
	public void run(String... args){
		String postUrl = "https://empro.naa.edu.az/AuthRest/api/integration/moodle/exam";

		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				try {
					RestTemplate restTemplate = new RestTemplate();
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.set("ACCESS-KEY", "Moodle deea5cf5-bb04-4394-a2a4-d3d0fb3e1f1d-abcd14b1-133b-4121-88b1-e779a2f2f106");

					List<Student> students = new ArrayList<>();
//					students.add(new Student("test1@gmail.com", "TestSoyad Test1 TestAta", "21/12/2022", "50","Xarici iqtisadi siyasət"));
//					students.add(new Student("test2@gmail.com", "testSoyad2 test2 testAta2", "21/12/2022", "40","Xarici iqtisadi siyasət"));
//					students.add(new Student("test3@gmail.com", "testSoyad3 test3 testAta3", "21/12/2022", "30","Xarici iqtisadi siyasət"));
//					students.add(new Student("test4@gmail.com", "testSoyad4 test4 testAta4", "21/12/2022", "20","Xarici iqtisadi siyasət"));
//					students.add(new Student("test5@gmail.com", "testSoyad5 test5 testAta5", "21/12/2022", "10","Xarici iqtisadi siyasət"));

					String json = "{\"students\":" + new ObjectMapper().writeValueAsString(students) + "}";
					HttpEntity<String> entity = new HttpEntity<>(json, headers);

					ResponseEntity<Object> res = restTemplate.exchange(postUrl, HttpMethod.POST, entity, Object.class);
					System.out.println(res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L * 6L * 6L * 24L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
}
