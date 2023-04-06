package NewSales.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import java.util.Locale;

//@Configuration
public class InternacionalizacaoConfig {

    @Bean
    public MessageSource msg() {
        ReloadableResourceBundleMessageSource msg = new ReloadableResourceBundleMessageSource();
        msg.setBasename("classpath:message");
        msg.setDefaultEncoding("ISO-8859-1");
        msg.setDefaultLocale(Locale.getDefault());
        return msg;}


    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(msg());
        return bean;
    }
}
