package core;

import junit.framework.TestCase;

public class ComputerTest extends TestCase{
	
	public void testAi() {
		Computer computer = new Computer(new OneAi());
		System.out.println(computer.printAiType());
	}

}
