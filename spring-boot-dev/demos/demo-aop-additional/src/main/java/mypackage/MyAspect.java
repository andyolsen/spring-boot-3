package mypackage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before("execution(* BankService.do*(int, double))")
	public void logBizOp(JoinPoint jp) {
		System.out.println("***Method: "   + jp.getSignature().getName() +
				           " account id: " + jp.getArgs()[0] + 
				           " amount: "     + jp.getArgs()[1]);
	}


	@Before("execution(* BankService.do*(int, double)) && " +
			"target(service) && args(id, amt)")
	public void logBizOp2(JoinPoint jp, BankService service, int id, double amt) {
	
		System.out.println("***Method: "   + jp.getSignature().getName() +
				           " account id: " + jp.getArgs()[0] + 
				           " amount: "     + jp.getArgs()[1]);
	
		// Levy 10% punitive fee on poor customer. Mwa, mwa, mwa...
		service.levyFee(id, amt * 0.10);
	}
}
