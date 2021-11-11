package prueba.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ReplaceQuery implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String params = "";

		String name = (String) exchange.getIn().getHeader("name");
		String age = String.valueOf(exchange.getIn().getHeader("age"));
		String id = String.valueOf(exchange.getIn().getHeader("id"));
		String query = (String) exchange.getIn().getHeader("CamelSqlQuery");

		if (name != null && !name.isEmpty()) {
			params = "name='" + name + "'";

		} else {

			params = !params.isEmpty() ? params + " and " : params;

		if (age != null && !age.isEmpty()) {
			params = "age=" + age;
		}


		}
		exchange.getIn().setHeader("CamelSqlQuery", query.replace("params", params).replace(":#id", id));
}

}