package mypackage;

import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl implements MyService {

	@Override
	public void goodOp1() {
		System.out.println("This is goodOp1()");
	}

	@Override
	public void goodOp2() {
		System.out.println("This is goodOp2()");
	}

	@Override
	public void badOp() throws Exception {
		System.out.println("This is badOp()");
		throw new Exception("Oops");
	}
}
