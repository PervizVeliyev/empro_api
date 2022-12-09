package com.example.RestTemplate;

import com.example.RestTemplate.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
					List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
					MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

					converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
					messageConverters.add(converter);
					restTemplate.setMessageConverters(messageConverters);

					students.add(new Student("random@mail.ru", "Eliyev Eli Veli", "11/11/2011", "10","math"));
					students.add(new Student("random@gmail.com", "Eliyev Eli Veli", "11/10/2012", "10","math"));

					String json = new ObjectMapper().writeValueAsString(students);
					HttpEntity<String> entity = new HttpEntity<>(json, headers);

					ResponseEntity<String> res = restTemplate.exchange(postUrl, HttpMethod.POST, entity, String.class);
					System.out.println(res.getStatusCode());
					System.out.println(res);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1L;
		long period = 10L * 6L * 6L * 24L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
}
