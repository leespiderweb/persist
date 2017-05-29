package com.test.main;

 

public class HelloWorldTest {

 
	public void TestSayHello()
	{
		HelloWorld helloworld = new HelloWorldTest();
		
		String result = helloworld.sayHello();
		
		assertEquals("Hello maven",result);
		
	}
	
	public static void main(String[] args) {

	System.out.print(new HelloWorld().sayHello());
	
	}
	
}
