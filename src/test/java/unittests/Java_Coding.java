package unittests;

import static utils.Constant.KEY_APP_PATH;
import static utils.Constant.LOCAL_PROFILE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

import utils.ReadConfig;

public class Java_Coding {

	//Find a first uniq char from a string sentence
	@Test
	public void getFirstUniqChar() {
		String string = "I love alaskain people";
		char firstUniq = 0;
		char [] arr = string.toLowerCase().toCharArray();
		
		for(char c : arr) {
			int counter = 0;
			for(char i : arr) {
				if(c == i && counter < 2) {
					counter++;
				}else if(counter >= 2) {
					break;
				}
			}
			if(counter == 1) {
				firstUniq = c;
				break;
			}
		}
		System.out.println(firstUniq);
	}
	
	//File multiple threads
	@Test
	public void multiThreadOps() {
		Calculte calculte = new Calculte();
		System.out.println(calculte.cal(2, 3));
	}
	
	@Test
	public void lambda() {
		int x = 5;
		int y = 3;
		
		Java_Coding jCoding = new Java_Coding();
		Functional1 add = new Functional1() {
			@Override
			public int calculate(int a, int b) {
				return a+b;
			}
		};
		
		Functional1 add1 = (a,b) -> a+b;
		Functional1 multiply = (a, b) -> a*b;
		Functional1 sub = (a,b) -> a-b;
		System.out.println(jCoding.ops(x, y, add));
		System.out.println(jCoding.ops(x, y, add1));
		System.out.println(jCoding.ops(x, y, multiply));
		System.out.println(jCoding.ops(3, 2, sub));
	}
	
	public int ops(int a, int b, Functional1 functional1) {
		return functional1.calculate(a, b);
	}
	
	class Calculte {
		int cal(int a, int b) {
			return a - b;
		}
	}
	
	interface Functional1{  //Interface has 1 abstract method
		int calculate(int a, int b);
	}
	
	@Test
	public void lambdaOnList() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(31);
		list.add(32);
		list.add(33);
		list.add(34);
		
		list.forEach(i -> System.out.println(i));
		list.forEach(System.out::println);
	}
	
	@Test
	public void getFile() {
		ReadConfig config = new ReadConfig(LOCAL_PROFILE);
		File file = new File(config.getProp(KEY_APP_PATH));
		System.out.println(config.getProp(KEY_APP_PATH));
		System.out.println(file.getAbsolutePath());
	}
}
