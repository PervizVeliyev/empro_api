package com.example.EmproApi;

import com.example.EmproApi.entity.Student;
import com.example.EmproApi.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@SpringBootApplication
public class EmproApiApplication implements CommandLineRunner {
	static final Logger log =
			LoggerFactory.getLogger(EmproApiApplication.class);

	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmproApiApplication.class, args);
	}

	@Override
	public void run(String... args){
		final String postUrl = "https://empro.naa.edu.az/AuthRest/api/integration/moodle/exam";

		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				try {
					RestTemplate restTemplate = new RestTemplate();
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.set("ACCESS-KEY", "Moodle deea5cf5-bb04-4394-a2a4-d3d0fb3e1f1d-abcd14b1-133b-4121-88b1-e779a2f2f106");

					List<Student> studentsList = studentRepository.findByExamDate("10/04/2023");
					log.info(studentsList.size() + ":" + studentsList);

					String json = "{\"students\":" + new ObjectMapper().writeValueAsString(studentsList) + "}";
					HttpEntity<String> entity = new HttpEntity<>(json, headers);

					ResponseEntity<Object> res = restTemplate.exchange(postUrl, HttpMethod.POST, entity, Object.class);
					log.info(res.toString());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					log.error(e.getMessage());
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L * 60L * 60L * 24L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
}
