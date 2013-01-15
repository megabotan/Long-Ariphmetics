import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class NumberTestClassicMultiply {

	public String stringOfLength(int length) {
		Random rand = new Random();

		String numbers = "0123456789ABCDEF";
		String result = "";
		for (int i = 0; i < length; i++) {
			result += numbers.charAt(rand.nextInt(15));
		}
		return result;
	}

	@Test
	public void testMultiply1() {
		Number test1 = new Number(stringOfLength(500));
		Number test2 = new Number(stringOfLength(500));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply2() {
		Number test1 = new Number(stringOfLength(500*2));
		Number test2 = new Number(stringOfLength(500*2));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply3() {
		Number test1 = new Number(stringOfLength(500*3));
		Number test2 = new Number(stringOfLength(500*3));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply4() {
		Number test1 = new Number(stringOfLength(500*4));
		Number test2 = new Number(stringOfLength(500*4));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply5() {
		Number test1 = new Number(stringOfLength(500*5));
		Number test2 = new Number(stringOfLength(500*5));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply6() {
		Number test1 = new Number(stringOfLength(500*6));
		Number test2 = new Number(stringOfLength(500*6));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply7() {
		Number test1 = new Number(stringOfLength(500*7));
		Number test2 = new Number(stringOfLength(500*7));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply8() {
		Number test1 = new Number(stringOfLength(500*8));
		Number test2 = new Number(stringOfLength(500*8));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply9() {
		Number test1 = new Number(stringOfLength(500*9));
		Number test2 = new Number(stringOfLength(500*9));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply10() {
		Number test1 = new Number(stringOfLength(500*10));
		Number test2 = new Number(stringOfLength(500*10));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply11() {
		Number test1 = new Number(stringOfLength(500*11));
		Number test2 = new Number(stringOfLength(500*11));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply12() {
		Number test1 = new Number(stringOfLength(500*12));
		Number test2 = new Number(stringOfLength(500*12));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply13() {
		Number test1 = new Number(stringOfLength(500*13));
		Number test2 = new Number(stringOfLength(500*13));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply14() {
		Number test1 = new Number(stringOfLength(500*14));
		Number test2 = new Number(stringOfLength(500*14));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply15() {
		Number test1 = new Number(stringOfLength(500*15));
		Number test2 = new Number(stringOfLength(500*15));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply16() {
		Number test1 = new Number(stringOfLength(500*16));
		Number test2 = new Number(stringOfLength(500*16));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply17() {
		Number test1 = new Number(stringOfLength(500*17));
		Number test2 = new Number(stringOfLength(500*17));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply18() {
		Number test1 = new Number(stringOfLength(500*18));
		Number test2 = new Number(stringOfLength(500*18));
		test1.Multiply(test2);
		assertTrue(true);
	}

	@Test
	public void testMultiply19() {
		Number test1 = new Number(stringOfLength(500*19));
		Number test2 = new Number(stringOfLength(500*19));
		test1.Multiply(test2);
		assertTrue(true);
	}



}
