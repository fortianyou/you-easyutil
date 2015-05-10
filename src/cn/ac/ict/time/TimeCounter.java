package cn.ac.ict.time;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class TimeCounter {
//	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private static long startmili = System.currentTimeMillis();
	private static HashMap<Object,Long> timeStamp = new HashMap<Object,Long>();
	private TimeCounter(){}

	/**
	 * 毫秒转化时分秒毫秒
	 * @param ms 毫秒数
	 * @return xx时xx分xx秒xx毫秒
	 */
    private static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        
        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分");
        }
        if(second > 0) {
            sb.append(second+"秒");
        }
        if(milliSecond > 0) {
            sb.append(milliSecond+"毫秒");
        }
        return sb.toString();
    }
	
    /**
     * 获取当前时间的毫秒数
     * @return 毫秒数
     */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 为对象obj添加或者更新的时间戳
	 * @param obj 对象实例
	 */
	public static void updateTimeStamp(Object obj){
		timeStamp.put(obj, System.currentTimeMillis());
	}
	
	/**
	 * 计算当前时间与标记的时间戳之间的时差
	 * @param obj 对象实例
	 * @return [xx时][xx分][xx秒]xx毫秒
	 */
	public static String getTimeDiff(Object obj){
		Long diff = timeStamp.get(obj);
		if( diff == null ) diff = startmili;
		
		diff = System.currentTimeMillis() - diff;
//		return formatter.format(diff);
		return formatTime(diff);
	}
	
	/**
	 * 删除obj上的时间戳
	 * @param obj 对象实例
	 */
	public static void removeTimeStamp(Object obj){
		timeStamp.remove(obj);
	}
	
	public static void main(String args[]) throws InterruptedException{
		Object obj = new Object();
		TimeCounter.updateTimeStamp(obj);
		for( int i = 0; i < 10000; ++ i ){
			Thread.currentThread().sleep(1000);
			System.out.println( TimeCounter.getTimeDiff(obj) );
		}
		
		TimeCounter.removeTimeStamp(obj);
	}
}
