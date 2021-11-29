package com.zcc.aditya.ZccAditya.Controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.zcc.aditya.ZccAditya.Model.ZccLinks;
import com.zcc.aditya.ZccAditya.Model.ZccTicket;

@Controller
@RequestMapping("/tickets")
public class ZccController {

	/*
	 * This method gets the values for an individual ticket and returns the view to
	 * the user interface
	 * 
	 */
	@GetMapping("/view/{id}")
	public static String getTicket(@PathVariable(value = "id") int id, Model model) {

		System.out.println("getTicket method");
		try {
			HttpEntity<String> request = headerData(); // calling method to get header related authentication data
			RestTemplate restTemplate = new RestTemplate();
			// calling the Zendesk API
			String uri = "https://zccadityazendesk.zendesk.com/api/v2/tickets/" + Integer.toString(id) + ".json";
			ResponseEntity<String> s = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

			// creating a Json Object to get the required fields from the API
			JSONObject jsonObject = new JSONObject(s.getBody());
			JSONObject obj = jsonObject.getJSONObject("ticket");

			// ZCCTicket object holds the data to display on the user interface
			ZccTicket ticket = new ZccTicket();
			if (!obj.isNull("id") && obj.has("id"))
				ticket.setId((Integer) obj.get("id"));
			if (!obj.isNull("subject") && obj.has("subject"))
				ticket.setSubject((String) obj.get("subject"));
			if (!obj.isNull("status") && obj.has("status"))
				ticket.setStatus((String) obj.getString("status"));
			if (!obj.isNull("requester_id") && obj.has("requester_id"))
				ticket.setRequesterId((Long) obj.get("requester_id"));
			if (!obj.isNull("description") && obj.has("description"))
				ticket.setDescription(obj.get("description").toString());
			if (!obj.isNull("submitter_id") && obj.has("submitter_id"))
				ticket.setSubmitterId((Long) obj.get("submitter_id"));

			System.out.println("Ticket::" + ticket);
			model.addAttribute("ticket", ticket);

			return "viewTicket"; // returning the viewTicket.html file with the populated data
		} catch (Exception e) {
			e.printStackTrace();
			return "apiError"; // returning the apiError.html file in case any error occurs
		}
	}

	/*
	 * This method gets all the tickets from the Zendesk API and returns the view to the user interface
	 * 
	 */
	@GetMapping(value = { "/info", "/info/{page}" })
	public static String ticketInfo(@PathVariable(value = "page", required = false) String page, Model model) {

		System.out.println("ticketInfo method");
		try {
			HttpEntity<String> request = headerData(); // calling method to get header related authentication data
			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://zccadityazendesk.zendesk.com/api/v2/tickets.json?" + page + "&per_page=25";
			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

			JSONObject jsonObject = new JSONObject(responseEntity.getBody());
			System.out.println("jsonObject:::" + jsonObject);
			JSONArray tickets = (JSONArray) jsonObject.getJSONArray("tickets");
			System.out.println("tickets:::" + tickets);

			//The ZccLink object will hold data related to the next and previous page.
			ZccLinks zccLink = new ZccLinks();
			try {
				if (jsonObject.has("next_page") && !jsonObject.isNull("next_page")) {
					String next = (String) jsonObject.get("next_page");
					next = next.substring(next.indexOf("?") + 1);
					next = next.substring(0, next.indexOf("&"));
					System.out.println("next:::" + next);
					zccLink.setNext(next);
				}
				if (jsonObject.has("previous_page") && !jsonObject.isNull("previous_page")) {
					String prev = (String) jsonObject.get("previous_page");
					prev = prev.substring(prev.indexOf("?") + 1);
					prev = prev.substring(0, prev.indexOf("&"));
					System.out.println("prev:::" + prev);
					zccLink.setPrev(prev);
				}

			} catch (Exception e) {
				System.out.println("Links not found!");
				e.printStackTrace();
			}

			List<ZccTicket> listTickets = new ArrayList<>();
			//iterating through the tickets and adding each ticket to the ArrayList
			for (int i = 0; i < tickets.length(); i++) {
				ZccTicket ticket = new ZccTicket();
				JSONObject objects = tickets.getJSONObject(i);
				if (!objects.isNull("id") && objects.has("id"))
					ticket.setId((Integer) objects.get("id"));
				if (!objects.isNull("subject") && objects.has("subject"))
					ticket.setSubject((String) objects.get("subject"));
				if (!objects.isNull("status") && objects.has("status"))
					ticket.setStatus((String) objects.getString("status"));

				listTickets.add(ticket);
			}
			System.out.println("TicketInfo method successful!");
			System.out.println("ZccLink::" + zccLink);
			
			model.addAttribute("tickets", listTickets); 
			model.addAttribute("links", zccLink);
			return "tickets"; // returning the tickets.html file with the populated data
		} catch (Exception e) {
			e.printStackTrace();
			return "apiError"; // returning the apiError.html file in case any error occurs
		}

	}

	/*
	 * Method for basic auth related data
	 * using emailid and token
	 * 
	 */
	private static HttpEntity<String> headerData() {
		HttpHeaders httpHeaders = new HttpHeaders();
		String plainCreds = "adityakulkarni270@gmail.com/token:hq9CjJaTdEhmiau0AX7TPriKFgc3nqqDiHDwuNUV";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		httpHeaders.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
		return request;
	}

}
