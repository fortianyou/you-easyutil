package cn.ac.ict.time;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class TimeCounter {
//	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private static long startmili = System.currentTimeMillis();
	private static HashMap<Object,Long> timeStamp = new HashMap<Object,Long>();
	private TimeCounter(){}

	/**
	 * ����ת��ʱ�������
	 * @param ms ������
	 * @return xxʱxx��xx��xx����
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
            sb.append(day+"��");
        }
        if(hour > 0) {
            sb.append(hour+"Сʱ");
        }
        if(minute > 0) {
            sb.append(minute+"��");
        }
        if(second > 0) {
            sb.append(second+"��");
        }
        if(milliSecond > 0) {
            sb.append(milliSecond+"����");
        }
        return sb.toString();
    }
	
    /**
     * ��ȡ��ǰʱ��ĺ�����
     * @return ������
     */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * Ϊ����obj��ӻ��߸��µ�ʱ���
	 * @param obj ����ʵ��
	 */
	public static void updateTimeStamp(Object obj){
		timeStamp.put(obj, System.currentTimeMillis());
	}
	
	/**
	 * ���㵱ǰʱ�����ǵ�ʱ���֮���ʱ��
	 * @param obj ����ʵ��
	 * @return [xxʱ][xx��][xx��]xx����
	 */
	public static String getTimeDiff(Object obj){
		Long diff = timeStamp.get(obj);
		if( diff == null ) diff = startmili;
		
		diff = System.currentTimeMillis() - diff;
//		return formatter.format(diff);
		return formatTime(diff);
	}
	
	/**
	 * ɾ��obj�ϵ�ʱ���
	 * @param obj ����ʵ��
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
