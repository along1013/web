package com.demo.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

public class TaskTest {

	static ScheduledThreadPoolExecutor stpe = null;
	static int index;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		int num = Runtime.getRuntime().availableProcessors();
		System.out.println(num);
		// 构造一个ScheduledThreadPoolExecutor对象，并且设置它的容量为5个
		stpe = new ScheduledThreadPoolExecutor(2);
		MyTask task = new MyTask();
		// 隔2秒后开始执行任务，并且在上一次任务开始后隔一秒再执行一次；
		 stpe.scheduleWithFixedDelay(task, 2, 1, TimeUnit.SECONDS);
		// 隔6秒后执行一次，但只会执行一次。
//		stpe.schedule(task, 6, TimeUnit.SECONDS);
	}

	public void init(){
		
	}
	private static String getTimes() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		return format.format(date);
	}

	private static class MyTask implements Runnable {

		@Override
		public void run() {
//			index++;
			System.out.println("2= " + getTimes() + " start" + index);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("2= " + getTimes() + " end" + index);
			if (index >= 10) {
				stpe.shutdown();
				if (stpe.isShutdown()) {
					System.out.println("停止了？？？？");
				}
			}
		}
	}
}
