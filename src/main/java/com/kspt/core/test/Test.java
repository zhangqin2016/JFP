package com.kspt.core.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		long time=new Date().getTime();
		List<String> ls=new ArrayList<String>();
		int i=0;
		while (i<20){
			ls.add(i+"");
			i++;
		}
		List<String> ls2=new ArrayList<String>();
		List<String> ls3=new ArrayList<String>();
		List<String> ls5=new ArrayList<String>();
		List<String> ls4=new ArrayList<String>();
		for(int j=0;j<ls.size();j++){
			if(j<5){
				ls2.add(ls.get(j));
			}else if(j>=5&&j<10){
				ls3.add(ls.get(j));
			}else if(j>=10&&j<15){
				ls4.add(ls.get(j));
			}else if(j>=15){
				ls5.add(ls.get(j));
			}
		}

		Thread2 t2=new Thread2(ls3);
		Thread2 t3=new Thread2(ls2);
		Thread2 t4=new Thread2(ls5);
		Thread2 t5=new Thread2(ls4);
		
		Thread tt=new Thread(t2);
		Thread tt2=new Thread(t3);
		Thread tt3=new Thread(t4);
		Thread tt5=new Thread(t5);
		tt.start();
		tt2.start();
		tt3.start();
		tt5.start();
	}

}
class Thread2 implements Runnable{
	
	List<String > ls;
	public Thread2(List<String > ls) {
		this.ls=ls;
	}

	@Override
	public  void run() {
	
		for (String iterable_element : ls) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"---正在输出:"+iterable_element);
		}
	}
	
}