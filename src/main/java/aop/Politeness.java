package aop;


import model.Squishee;
import model.UsualPerson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Politeness {

    @Pointcut("bean(bar) && execution(model.Squishee sellSquishee(model.Person))")
    public void sellSquishee() {
    }

    @Before("sellSquishee() && args(person)")
    public void sayHello(UsualPerson person) {
        System.out.println("Hello " + person.getFirstName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "squishee", argNames = "squishee")
    public void askOpinion(Squishee squishee) {
        System.out.println("Is " + squishee.getName() + " Good Enough? \n");
    }

    @AfterThrowing("sellSquishee()")
    public void sayYouAreNotAllowed() {
        System.out.println("Hmmm... \n");
    }

    @After("sellSquishee()")
    public void sayGoodBye() {
        System.out.println("Good Bye! \n");
    }

    @Around("sellSquishee()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hi! \n");
        Object retVal = pjp.proceed();
        System.out.println("See you! \n");
        return retVal;
    }

}