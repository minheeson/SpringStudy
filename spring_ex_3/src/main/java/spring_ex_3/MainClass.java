package spring_ex_3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//bean을 설정한 xml 파일이 있는 위치 지정  
		String configLoc = "classpath:applicationCTX.xml";
		
		//위치를 참고해서 설정파일 얻어옴   
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLoc);
		
		//설정파일에서 bean을 가져옴  
		//"myCalculator"를 얻어와서 객체를 생성 (주입)  
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);

		
		myCalculator.add();
		myCalculator.div();
		myCalculator.mul();
		myCalculator.sub();
		

	}

}
