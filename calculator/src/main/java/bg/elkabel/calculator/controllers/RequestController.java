package bg.elkabel.calculator.controllers;

import bg.elkabel.calculator.models.bind.RequestBindModel;
import bg.elkabel.calculator.models.view.RequestViewModel;
import bg.elkabel.calculator.service.RequestService;
import bg.elkabel.calculator.utils.PDFCreator;
import bg.elkabel.calculator.utils.RequestProperties;
import bg.elkabel.calculator.utils.RequestPropertiesBuilder;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/request")
public class RequestController {

	private final RequestService requestService;
	private final PDFCreator pdfCreator;
	private final RequestPropertiesBuilder requestPropertiesBuilder;

	@Autowired
	public RequestController(RequestPropertiesBuilder requestPropertiesBuilder, RequestService requestService, PDFCreator pdfCreator) {
		this.requestService = requestService;
		this.pdfCreator = pdfCreator;
		this.requestPropertiesBuilder = requestPropertiesBuilder;
	}

	@GetMapping("")
	public String getRequestPage(@ModelAttribute("requestModel") RequestBindModel requestModel) {
		return "view/request";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity getRequest(@Validated @RequestBody RequestBindModel requestModel) {
		this.requestService.createRequest(requestModel);

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestViewModel> findOneById(@PathVariable("id") Long id) throws IOException {

		RequestViewModel result = this.requestService.findById(id);

		return ResponseEntity.accepted().body(result);
	}

	@RequestMapping(value = "/createPdf/{id}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<byte[]> downloadPDFFile(@PathVariable("id") long id)
			throws IOException {
		RequestViewModel result = this.requestService.findById(id);
		RequestProperties properti = this.requestPropertiesBuilder.createRequestProperties(result);
		byte[] data = this.pdfCreator.createRequestDocument(properti);
		return ResponseEntity.ok().body(data);
	}

}
