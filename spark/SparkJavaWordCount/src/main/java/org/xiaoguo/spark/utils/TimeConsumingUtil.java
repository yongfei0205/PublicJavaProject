package org.xiaoguo.spark.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeConsumingUtil {
	public static TimeConsumingUtil create() {
		return new TimeConsumingUtil();
	}

	private Map<String, Long> timeMap = new HashMap<String, Long>();

	private TimeConsumingUtil() {

	}

	public TimeConsumingUtil start(String key) {
		if (!timeMap.containsKey(key)) {
			timeMap.put(key, System.nanoTime());
		} else {
			System.out.println("Key[" + key + "] is exist!");
		}
		return this;
	}
	
	public TimeConsumingUtil end(String key){
		return	end(key,TimeUnit.MILLISECONDS);
	}
	
	public TimeConsumingUtil end(String key,TimeUnit timeUnit){
		Long startTime = timeMap.get(key);
		long timeSpan=System.nanoTime()-startTime;
		String span="纳秒";
		switch (timeUnit) {
		case MICROSECONDS:
			span="微秒";
			timeSpan/=1000;
			break;
		case MILLISECONDS:
			span="毫秒";
			timeSpan/=1000*1000;
			break;
		case SECONDS:
			span="秒";
			timeSpan/=1000*1000*1000;
			break;
		default:
			break;
		}
		System.out.println(String.format("%20s : 耗时 %-50d %s", key,timeSpan,span));
		timeMap.remove(key);
		return this;
	}
}
