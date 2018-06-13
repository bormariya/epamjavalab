import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import model.ApuBar;
import model.Bar;
import model.UsualPerson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.TestUtils;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = TestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AopAspectJTest {

	Bar bar;
    UsualPerson customer;

    @NonFinal
    String fromSystemOut;

    @BeforeEach
    void beforeEach() throws IOException {
        fromSystemOut = TestUtils.fromSystemOut(() -> bar.sellSquishee(customer));
    }

    @Test
    public void testBeforeAdvice() {
        assertTrue(fromSystemOut.contains("Hello"), "Before advice is not good enough...");
        assertTrue(fromSystemOut.contains("How are you doing?"), "Before advice is not good enough...");
    }

    @Test
    public void testAfterAdvice() {
        assertTrue(fromSystemOut.contains("Good Bye!"), "After advice is not good enough...");
    }

    @Test
    public void testAfterReturningAdvice() {
        System.out.println(fromSystemOut);
        assertTrue(fromSystemOut.contains("Good Enough?"), "Customer is broken");
    }

    @Test
    public void testAroundAdvice() {
        assertTrue(fromSystemOut.contains("Hi!"), "Around advice is not good enough...");
        assertTrue(fromSystemOut.contains("See you!"), "Around advice is not good enough...");
    }

    @Test
    public void testAllAdvices() {
        assertFalse(bar instanceof ApuBar, "barObject instanceof ApuBar");
    }
}