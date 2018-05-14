package com.company.webapp.customer.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static String BASEURL = "http://localhost:8080/jerseytest/webapi/userservice";

	@RequestMapping(value = "/")
	public ModelAndView home(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/loginUser")
	public ModelAndView loginUser(HttpServletResponse response) throws IOException {
		return new ModelAndView("loginUser");
	}

	@RequestMapping(value = "/newUser")
	public ModelAndView newUser(HttpServletResponse response) throws IOException {
		return new ModelAndView("newUser");
	}

	@RequestMapping(value = "/updateUser")
	public ModelAndView updateUser(HttpServletResponse response) throws IOException {
		return new ModelAndView("updateUser");
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(HttpServletResponse response) throws IOException {
		return new ModelAndView("deleteUser");
	}

	@RequestMapping(value = "/getAllUserDetails")
	public ModelAndView getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("HomeController.getAllUsers() called ******* ");
		String accessURI = getBaseURI() + "/allUserDetails";
		RestTemplate restTemplate = createRestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		String base64Creds = authenticationPrepare();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> req = new HttpEntity<String>(headers);
		ResponseEntity<Details[]> users = restTemplate.exchange(accessURI, HttpMethod.GET, req, Details[].class);
		// Details[] users = restTemplate.getForObject(accessURI, Details[].class);
		Details[] detailList = users.getBody();
		ArrayList<Details> list = new ArrayList<>();
		list.addAll(Arrays.asList(detailList));
		for (Details details : list) {
			System.out.println(details.getUsername());
		}
		return new ModelAndView("home", "users", list);
	}

	private String authenticationPrepare() {
		String name = "rambabu";
		String password = "southafrica";
		String authString = name + ":" + password;
		String authStringEnc = "";
		try {
			authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes("utf-8"));
			System.out.println("Base64 encoded auth string: " + authStringEnc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authStringEnc;
	}

	@RequestMapping(value = "/login")
	public ModelAndView loginUser(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String redirectPage = "loginUser";
		String status = "failure";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String accessURI = getBaseURI() + "/findDetailsByUsername/{username}";
		RestTemplate restTemplate = createRestTemplate();
		Details user = restTemplate.getForObject(accessURI, Details.class, username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				redirectPage = "successfulLogin";
				status = "success";
			} else {
				redirectPage = "loginUser";
				status = "failure";
			}
		}
		model.addAttribute("user", username);
		model.addAttribute("status", status);
		return new ModelAndView(redirectPage);
	}

	@RequestMapping(value = "/getUserDetailsByUsername")
	public ModelAndView getUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String username = request.getParameter("username");
		String accessURI = getBaseURI() + "/findDetailsByUsername/{username}";
		RestTemplate restTemplate = createRestTemplate();
		Details user = restTemplate.getForObject(accessURI, Details.class, username);

		return new ModelAndView("home", "user", user);
	}

	@RequestMapping(value = "/addUserDetails")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String status = null;
		String addstatus = "Record added :";
		System.out.println("HomeController.addUser() <<username>>" + username + "<<password>>" + password);
		if (!(username.isEmpty()) && (!(password.isEmpty()))) {
			String accessURI = getBaseURI() + "/addDetails/{username}/{password}";
			LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.set("username", username);
			map.set("password", password);
			RestTemplate restTemplate = createRestTemplate();
			status = restTemplate.postForObject(accessURI, map, String.class, username, password);
			System.out.println(status + "<<<< status - addUserDetails>>>");
			addstatus = username + " profile has been create successfully";
		}
		return new ModelAndView("successfulLogin", "addstatus", addstatus);
	}

	@RequestMapping(value = "/updateUserDetailsByUsername")
	public ModelAndView updateUserByUsername(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String updateStatus = "unable to change";
		System.out.println("HomeController.updateUserByUsername()" + username + "||||" + password);

		String accessURI = getBaseURI() + "/findDetailsByUsername/{username}";
		RestTemplate restTemplate = createRestTemplate();
		Details user = restTemplate.getForObject(accessURI, Details.class, username);
		if (user.getPassword().contentEquals(password)) {
			updateStatus = "supplied password is same as old password / please use new password";
		} else {
			LinkedMultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.set("username", username);
			formData.set("password", password);
			accessURI = getBaseURI() + "/updateDetails/{username}/{password}";
			restTemplate = new RestTemplate();
			restTemplate.put(accessURI, formData, username, password);
			updateStatus = "password successfully changed";
		}
		model.addAttribute("updateStatus", updateStatus);

		return new ModelAndView("updateUser");
	}

	@RequestMapping(value = "/deleteUserDetailsByUsername")
	public ModelAndView deleteUserByUsername(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String username = request.getParameter("username");
		LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.set("username", username);
		String accessURI = getBaseURI() + "/deleteDetails/{username}";
		RestTemplate restTemplate = createRestTemplate();
		restTemplate.delete(accessURI, username);
		return new ModelAndView("home");
	}

	private RestTemplate createRestTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		// factory.setReadTimeout(2000);
		// factory.setConnectTimeout(2000);
		return factory;
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(BASEURL).build();
	}
}
