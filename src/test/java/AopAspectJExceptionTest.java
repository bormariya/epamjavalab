import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Bar;
import model.CustomerBrokenException;
import model.UsualPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.TestConfiguration;
import utils.TestUtils;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE)
@ContextConfiguration(classes = TestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AopAspectJExceptionTest {

    @NonNull
	private Bar bar;
    @NonNull
    private UsualPerson customer;

    String fromSystemOut;

    @BeforeEach
    void beforeEach(){
       customer = customer.withBroke(true);

    }

    @Test
    public void testAfterThrowingAdvice() throws IOException {
        fromSystemOut = TestUtils.fromSystemOut(
                () -> assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(customer)));
        assertTrue(fromSystemOut.contains("Hmmm..."),"Customer is not broken ");
        System.out.println(fromSystemOut);
    }
}