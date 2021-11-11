package prueba.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import prueba.dtos.User;

public class ProcessRequestGetUsers implements Processor {

	@SuppressWarnings("deprecation")
	@Override
	public void process(Exchange exchange) throws Exception {
		User user = new User();
		user.setName("Sebastian Ortiz");
		user.setAge(24);
		
		exchange.getOut().setBody(user);

	}

}
