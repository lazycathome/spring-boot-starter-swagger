package cn.bigdb.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liujt on 2017/5/15.
 */
@Configuration
@EnableSwagger2
@Profile("${springfox.api.env:dev}")
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Value("${springfox.api.group:you are api group name}")
    private String groupName;

    @Value("${springfox.api.title:set a you title}")
    private String title;

    @Value("${springfox.api.desc:[set a api desc]}")
    private String desc;

    @Value("${springfox.api.version:[set a api version]}")
    private String version;

    @Value("${springfox.api.termsOfServiceUrl:https://www.github.com/lazycathome}")
    private String termsOfServiceUrl;

    @Value("${springfox.api.contact.author:liujiangtao}")
    private String name;

    @Value("${springfox.api.contact.url:https://www.github.com/lazycathome}")
    private String url;

    @Value("${springfox.api.contact.email:liujt2009@gmail.com}")
    private String email;

    @Value("${springfox.api.license:[set a api license]}")
    private String license;

    @Value("${springfox.api.licenseUrl:[set a api licenseUrl]}")
    private String licenseUrl;

    /**
     * Create rest api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(Date.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        new ArrayList(){
                            {new ResponseMessageBuilder().
                                    code(500).message("服务器出错啦!!!").
                                    responseModel(new ModelRef("Error")).build();};
                        })
                .forCodeGeneration(true);
    }

    /**
     * generate basic api info
     *
     * @return api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .license(license)
                .licenseUrl(licenseUrl)
                .contact(new Contact(name,
                        url, email))
                .build();
    }
}
