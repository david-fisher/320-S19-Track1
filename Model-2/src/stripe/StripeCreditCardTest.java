package stripe;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

public class StripeCreditCardTest {
	
	@Test
	public void testCreateCustomer() {
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
		Map<String, Object> customerParameters = new HashMap<String, Object>();
    	customerParameters.put("email", "test@gmail.com");
    	try {
    		Customer newCustomer = Customer.create(customerParameters);
    		//assert newCustomer == Customer.retrieve(newCustomer.getId());
    	} catch(StripeException e) {
    		//assert e == null;
    	}
	}
}
