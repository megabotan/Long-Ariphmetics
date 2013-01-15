public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Number firstNumber = new Number("./input", 1);
		Number secondNumber = new Number("./input", 2);
		firstNumber.out();
		secondNumber.out();
		//firstNumber.RemainderBarett(secondNumber).out();
		//firstNumber.RemainderClassic(secondNumber).out();
		System.out.println("End");
	}

}
