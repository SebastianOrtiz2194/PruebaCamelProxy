package prueba.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import prueba.dtos.User;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {
	

    @Override
    public void configure() {
    	
     JacksonDataFormat jsonUser = new JacksonDataFormat(User.class);
    
     
     ObjectMapper mapper = new ObjectMapper();
     mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
     
     jsonUser.setObjectMapper(mapper);
     

    	

    	
    	restConfiguration()
    	.component("servlet")
        .enableCORS(true)
        .port(8080)
        .contextPath("/api")
        .bindingMode(RestBindingMode.auto)
        .clientRequestValidation(true)
    	.corsHeaderProperty("Access-Control-Allow-Origin", "*")
    	.corsHeaderProperty("Access-Control-Allow-Headers","*");
        
    	rest("users-proxy")
    		.get("/")
    		.consumes(MediaType.APPLICATION_JSON_VALUE.toString())
    		.produces(MediaType.APPLICATION_JSON_VALUE.toString())
    		.route()
    		.setHeader(Exchange.HTTP_METHOD, constant("GET"))
    		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
    		.setHeader("Accept", constant("application/json"))
    		.to("http://pruebacamel-test-jortiz-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/api/users/listar?bridgeEndpoint=true")	
    		//.log("${body}")
    		//.setBody(constant("{\"id\":1, \"name\":\"andres\"}"))
    		/*.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					exchange.getIn().setBody(new User());
					
				}
			})*/
    		//.marshal(jsonUser)
    		.log("${body}")	
    		.unmarshal(jsonUser)
    		.log("${body}")
	    	.marshal(jsonUser)
			.log("${body}");
    	
    		
    		
    	
    	
    	rest("users-proxy")
			.post("/").type(User.class)
			.consumes(MediaType.APPLICATION_JSON_VALUE.toString())
			.produces(MediaType.APPLICATION_JSON_VALUE.toString())
			.route()
			.setHeader("id",simple("${body.id}")).setHeader("name",simple("${body.name}")).setHeader("age",simple("${body.age}"))
			.setHeader("HttpMethod", constant("POST"))
			.marshal(jsonUser)
			.to("http://pruebacamel-test-jortiz-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/api/users/guardar?bridgeEndpoint=true")
	    	.log("${body}");
	  

    		
    	
    		
	
    }

}
