package bg.elkabel.calculator.controllers;

import bg.elkabel.calculator.models.bind.CableBindModel;
import bg.elkabel.calculator.service.CableService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/cable")
public class CableController {
	
	
	private final CableService cableService;
	private final Gson gsonService;

	@Autowired
	public CableController(CableService cableService, Gson gsonService) {
		this.cableService = cableService;
                this.gsonService = gsonService;
	}
	
	
	
	@GetMapping("")
	public String getCorePage(@ModelAttribute("cable") CableBindModel cable) {
		return "view/cable";
	}
	
	@PostMapping("")
	public String getCore(@ModelAttribute("cable") CableBindModel cable) {

		this.cableService.createCable(cable);

		return "redirect:/";
	}
	
      
	
	@RequestMapping(value = "/getCables", method = RequestMethod.GET)
	ResponseEntity findAll() {

		return new ResponseEntity(
				this.cableService.allCables()
				, HttpStatus.OK);
	}
}
