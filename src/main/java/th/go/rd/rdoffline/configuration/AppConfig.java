package th.go.rd.rdoffline.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/message");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
	public static MessageSource getMessageSource(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		MessageSource resources = ctx.getBean(MessageSource.class);
		return resources;
	}
}
