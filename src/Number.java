import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Number {
	ArrayList<Byte> data;
	Boolean sign = true;
	private Number power_byte_buffer[];

	public Number(String path, int line)

	{
		power_byte_buffer=new Number[16];
		data = new ArrayList<Byte>();
		sign = true;

		String strLine = null;
		try {
			
			FileInputStream fstream = new FileInputStream(path);			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			for (int i = 0; i < line; i++)
				strLine = br.readLine();
			in.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
		if (strLine.charAt(0) == '-') {
			sign = false;
			strLine = strLine.substring(1);
		}
		for (int i = strLine.length() - 1; i >= 0; i -= 1) {
			data.add((byte) Byte.parseByte(strLine.substring(i, i + 1), 16));
		}
	}

	public Number() {
		

	}
	
	public Number(Number number)
	{
		power_byte_buffer=new Number[16];
		data = new ArrayList<Byte>();
		for(int i=0;i<number.getLength();i++)
		{
			data.add(number.getData(i));
		}
		sign = number.sign;
	}
	
	public Number(String strLine)
	{
		power_byte_buffer=new Number[16];
		data = new ArrayList<Byte>();
		sign = true;
		if (strLine.charAt(0) == '-') {
			sign = false;
			strLine = strLine.substring(1);
		}
		for (int i = strLine.length() - 1; i >= 0; i -= 1) {
			data.add((byte) Byte.parseByte(strLine.substring(i, i + 1), 16));
		}
	}

	public Number(int length) {
		data = new ArrayList<Byte>();
		for (int i = 0; i < length; i++) {
			data.add((byte) 0);
		}
	}

	public byte getData(int i) {
		if (i < data.size())
			return data.get(i);
		return 0;
	}

	public int getLength() {
		return data.size();
	}

	public void setData(byte byteData, int i) {
		data.set(i, byteData);
	}

	public void setSign(Boolean sign) {
		this.sign = sign;
	}

	public void normalize()// removes first zeros
	{
		int i = this.getLength();

		while (this.getData(i - 1) == 0) {
			if (i <= 1) {
				break;
			}
			this.data.remove(i - 1);
			i--;

		}

	}
	
	public String toString()
	{
		String result = "";
		
		
		this.normalize();
		if (!sign)
			result+="-";

		for (int i = data.size() - 1; i >= 0; i--) {
			String out;
			out = Long.toHexString(data.get(i));
			result+=out;

		}

		
		return result;
	}
	
	public void out() {
		this.normalize();
		if (!sign)
			System.out.print("-");

		for (int i = data.size() - 1; i >= 0; i--) {
			String out;

			out = Long.toHexString(data.get(i));
			System.out.print(out);

		}
		System.out.println();

	}

	public Boolean isGreaterOrEqualThan(Number number) {
		this.normalize();
		number.normalize();
		if (this.getLength() > number.getLength())
			return true;
		if (this.getLength() < number.getLength())
			return false;
		for (int i = this.getLength() - 1; i >= 0; i--) {
			if (this.getData(i) > number.getData(i))
				return true;
			if (this.getData(i) < number.getData(i))
				return false;
		}
		return true;
	}

	public Number add(Number number) {
		Number result = new Number(Math.max(this.getLength(),
				number.getLength()) + 1);

		for (int i = 0; i < result.getLength(); i++) {
			byte sum = (byte) (this.getData(i) + number.getData(i) + result
					.getData(i));
			if (sum >= 16) {
				result.setData((byte) (result.getData(i + 1) + 1), i + 1);
				sum -= 16;
			}
			result.setData((byte) (sum), i);
		}
		result.normalize();

		return result;
	}

	public Number subtract(Number number) {
		Number result = new Number(this.getLength() + 1);
		if (!this.isGreaterOrEqualThan(number)) {
			result = number.subtract(this);
			result.setSign(false);
			return result;
		}
		for (int i = 0; i < this.getLength(); i++) {
			byte sum = (byte) (this.getData(i) - number.getData(i) + result
					.getData(i));
			if (sum < 0) {
				result.setData((byte) (result.getData(i + 1) - 1), i + 1);
				sum += 16;
			}
			result.setData((byte) (sum), i);
		}

		return result;
	}

	public Number moveLeft(int l) {
		Number result=new Number(this);
		for (int i = 0; i < l; i++) {
			result.data.add(0, (byte) 0);
		}
		return result;

	}

	public Number moveRight(int l) {
		Number result=new Number(this);
		if (l < result.getLength()) {
			for (int i = 0; i < l; i++) {
				result.data.remove(0);
			}
		}
		return result;
	}

	public Number multiplyBite(byte b) {
		Number result = new Number(this.getLength() + 2);
		for (int i = 0; i < result.getLength() - 1; i++) {
			int partOfMult = b * this.getData(i) + result.getData(i);
			result.setData((byte) (partOfMult / 16), i + 1);
			partOfMult = partOfMult % 16;
			result.setData((byte) partOfMult, i);
		}
		result.normalize();
		return result;
	}

	public Number Multiply(Number number) {
		Number result = new Number(1);

		for (int i = 0; i < number.getLength(); i++) {
			result = result.add(this.multiplyBite(number.getData(i))
					.moveLeft(i));
		}
		return result;
	}

	public Number Divide(Number number) {
		this.normalize();	
		number.normalize();
		Number divider=this;
		Number divisor=number;
		Number result=new Number("0");
		Number one=new Number("1");
		one.normalize();
		while(divider.isGreaterOrEqualThan(divisor))
		{
			int i=divider.getLength()-divisor.getLength()-1;
			if(i<1)
			{
				i=1;
			}
			while(divider.isGreaterOrEqualThan(divisor.moveLeft(i)))
			{
				i++;
			}
			i--;
			result=result.add(one.moveLeft(i));
			divider=divider.subtract(divisor.Multiply(one.moveLeft(i)));
		}
		return result;
	}
	
	public Number RemainderClassic(Number number)
	{
		Number result = new Number();
		result=this.subtract(number.Multiply(this.Divide(number)));
		return result;
	}
	
	public Number RemainderBarett(Number number)
	{
		this.normalize();
		int k;
		k=number.getLength()+1;
		Number one=new Number("1");
		Number mu=one.moveLeft(2*k).Divide(number);
		if(this.getLength()<=2*k)
		{
			return RemainderBarett(number,mu);
		}else 
		{
			return this.RemainderClassic(number);
		}
	}
	
	public Number RemainderBarett(Number number,Number mu)
	{
		
		this.normalize();
		int k;
		k=number.getLength()+1;
		if(this.getLength()>2*k)
		{
			return this.RemainderClassic(number);
		}
		Number one=new Number("1");
		Number q=((this.Divide(one.moveLeft(k-1)).Multiply(mu))).Divide(one.moveLeft(k+1));
		Number x=new Number(this);
		x=(x.RemainderClassic(one.moveLeft(k+1))).subtract(q.Multiply(number).RemainderClassic(one.moveLeft(k+1)));
		
		if(x.sign==false)
		{
			x.sign=true;
			x=one.moveLeft(k+1).subtract(x);
		}
		while(x.isGreaterOrEqualThan(number))
		{
			x=x.subtract(number);
		}
		return x;
	}
	
	public Number MultiplyModul(Number number,Number modul)
	{
		Number result=new Number();
		result=this.RemainderClassic(modul).Multiply(number.RemainderClassic(modul)).RemainderClassic(modul);

		return result;
	}
	
	public Number SquareModul(Number modul)
	{
		return this.MultiplyModul(this, modul);
	}

	public Number PowerModul(Number power,Number modul)
	{
		int k;
		k=modul.getLength()+1;
		Number one=new Number("1");
		Number mu=one.moveLeft(2*k).Divide(modul);
		Number result=new Number("1");
		for(int i=power.getLength()-1;i>=0;i--)
		{
			for(int j=0;j<4;j++)
			{
				result=result.Multiply(result);
				result=result.RemainderBarett(modul,mu);
			}
			result=result.Multiply(this.PowerByteModul(power.getData(i),modul,mu));
			//result=result.RemainderClassic(modul);
			
			
			result=result.RemainderBarett(modul,mu);
		}
		
		
		return result;
	}
	
	public Number PowerByteModul(byte power,Number number,Number mu)
	{
		if(this.power_byte_buffer[power]==null)
		{
			for(int i=0;i<16;i++)
			{
				this.power_byte_buffer[i]=new Number("0");
			}
		}
		if(this.power_byte_buffer[power].getLength()<2)
		{
			Number result=new Number("1");
			this.power_byte_buffer[0]=result;
			for(byte i=0;i<15;i++)
			{
				result=result.Multiply(this);
				result=result.RemainderBarett(number, mu);
				this.power_byte_buffer[i+1]=result;
			}
			
			
			return this.power_byte_buffer[power];
		}else
		{
			return power_byte_buffer[power];
		}
	}
	
	public Number MultiplyKaratsuba(Number number)
	{
		this.normalize();
		number.normalize();
		Number one=new Number("1");
		if(number.getLength()<200 || this.getLength()<200 || number.getLength()!=this.getLength())
		{
			return this.Multiply(number);
		}		
		//Number this1=new Number(this.RemainderClassic(one.moveLeft(this.getLength()/2)));
		Number this1=new Number(this.getLength()/2);
		for(int i=0;i<this.getLength()/2;i++)
		{
			this1.setData(this.getData(i), i);
		}
		Number this2=new Number(this.moveRight(this.getLength()/2));
		//Number number1=new Number(number.RemainderClassic(one.moveLeft(number.getLength()/2)));
		Number number1=new Number(this.getLength()/2);
		for(int i=0;i<number.getLength()/2;i++)
		{
			number1.setData(number.getData(i), i);
		}
		Number number2=new Number(number.moveRight(number.getLength()/2));
		
		Number A1B1=this1.MultiplyKaratsuba(number1);
		Number A2B2=this2.MultiplyKaratsuba(number2);
		
		Number result= new Number(this1.add(this2).MultiplyKaratsuba(number1.add(number2)));
		result=result.subtract(A1B1.add(A2B2));
		result=result.moveLeft(number.getLength()/2);
		result=result.add(A1B1.add(A2B2.moveLeft(number.getLength()/2*2)));
		
		return result;
	}
	
}
