package bg.elkabel.calculator.controllers;

import bg.elkabel.calculator.models.bind.CoreBindModel;
import bg.elkabel.calculator.service.CoreService;
import com.google.gson.Gson;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kalin
 */
@Controller
@RequestMapping("/core")
public class CoreController {

	private final CoreService coreService;
	private final Gson gsonService;

	@Autowired
	public CoreController(CoreService coreService, Gson gsonService) {
		this.coreService = coreService;
		this.gsonService = gsonService;
	}

	@GetMapping("")
	public String getCorePage(@ModelAttribute("coreModel") CoreBindModel coreModel) {
		return "view/core";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity getCore(@Validated @RequestBody CoreBindModel coreModel) {

		this.coreService.create(coreModel);

		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/getCores")
	@ResponseBody
	public String getAllCores() throws IOException {

		return this.gsonService.toJson(this.coreService.findAll());
	}

}
