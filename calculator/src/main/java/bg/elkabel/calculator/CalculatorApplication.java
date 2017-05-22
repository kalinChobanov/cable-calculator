package bg.elkabel.calculator;

import bg.elkabel.calculator.utils.PDFCreator;
import bg.elkabel.calculator.utils.RequestPropertiesBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}
	
	@Bean
	ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	
	@Bean
	Gson getGson(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson;
	}
	@Bean
	PDFCreator getPDFCreator(){
		return new PDFCreator();
	}
	@Bean
	RequestPropertiesBuilder getRequestPropertiesBuilder(){
		return new RequestPropertiesBuilder();
	}
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages/messages");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}
}
