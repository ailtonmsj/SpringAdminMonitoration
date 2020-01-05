package br.com.amsj.adminmonitoration.controller;

//@Controller
public class LogoutController {
	
	//@GetMapping("/logout")
	//@PostMapping("/logout")
	public String logout() {
		
		System.out.println("logout");
		
		return "logout";
	}

}
