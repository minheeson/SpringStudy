package spring_ex_3;

public class Calculator {

	public void add(int f, int s) {
		System.out.println("add");
		int result = f + s;
		System.out.println("+ " + result);
	}

	public void sub(int f, int s) {
		System.out.println("sub");
		int result = f - s;
		System.out.println("+- " + result);
	}

	public void mul(int f, int s) {
		System.out.println("mul");
		int result = f * s;
		System.out.println("* " + result);
	}

	public void div(int f, int s) {
		System.out.println("div");
		int result = f / s;
		System.out.println("/ " +  result);
	}

}
