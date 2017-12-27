package spring_ex_4;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String configLocation = "classpath:applicationCTX.xml";
		
		// IOC 컨테이너 생성 (부품들 생성) 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		// 스프링 컨테이너에서 컴포넌트 가져옴 
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		
		myInfo.getInfo();
		ctx.close();

	}

}
