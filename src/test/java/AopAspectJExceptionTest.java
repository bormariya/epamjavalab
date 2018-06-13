import static org.junit.jupiter.api.Assertions.assertTrue;

import aop.AopLog;
import model.Bar;
import model.UsualPerson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private UsualPerson customer;

    @Test
    public void testAfterThrowingAdvice() {
 
    	bar.sellSquishee(customer);
    	
        assertTrue(AopLog.getStringValue().contains("Hmmm..."),"Customer is not broken ");
        System.out.println(AopLog.getStringValue());
    }
}