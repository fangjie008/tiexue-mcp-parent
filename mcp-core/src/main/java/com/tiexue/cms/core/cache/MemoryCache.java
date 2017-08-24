package com.tiexue.cms.core.cache;


import java.util.Iterator;
import java.util.Map;


import org.apache.log4j.Logger;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * 内存缓存实现
 * @author zhangxiaowei
 *
 */
public class MemoryCache {

	private final static Logger logger=Logger.getLogger(MemoryCache.class);
	
	/**
	 * 缓存实例对象
	 */
	private static MemoryCache memoryCache;
	/**
	 * 是否正在回收超时的缓存数据
	 */
	private static boolean isGc;
	/**
	 * 缓存容器
	 */
	private static Map<String,Object> cacheMap=new ConcurrentHashMap<String,Object>();
	
	/**
	 * 私有构造方法
	 */
	private MemoryCache(){
		logger.info("开始执行私有构造方法");
		Thread newThread = new Thread(myRunnable,"clearCache");
		newThread.setDaemon(true);
		newThread.start();
	}

	/**
	 * 采用单例模式获取缓存对象
	 * 双重检查锁
	 * 
	 * @return
	 */
	public static MemoryCache getInstance(){
		if(memoryCache==null){
			synchronized(MemoryCache.class) {
				if(memoryCache==null){
				memoryCache=new MemoryCache();
				}
			}
		}
		return memoryCache;
	}
	
	/**
	 * 获取缓存对象
	 * @param cacheKey
	 * @return
	 * @throws InterruptedException
	 */
	public  Object get(String key) throws InterruptedException{
		//如果正在回收超时的缓存数据则需要等待
		while(isGc) Thread.sleep(5);
		try {
			if(!cacheMap.containsKey(key)){
				 return null;
			}
				  
			CacheUnit cacheUnit=(CacheUnit) cacheMap.get(key);
			if(cacheUnit!=null&&cacheUnit.isEnabled()){
				return cacheUnit.getData();
			}
			cacheMap.remove(key);
			return null;
		} catch (Exception e) {
			logger.error("get cache exception key:"+key);
			logger.error("exception:"+e);
		}
		return null;
	}
	/**
	 * 保存缓存值
	 * @param cacheKey 
	 * @param secondDelay 时间单位为秒
	 * @param data 缓存数据
	 * @throws InterruptedException 
	 */
	public  void set(String key,double secondDelay,Object data) throws InterruptedException{
		//如果正在回收超时的缓存数据则需要等待
		while(isGc) Thread.sleep(5);
		try {
			if(cacheMap.containsKey(key)){
				CacheUnit cacheUnit=(CacheUnit) cacheMap.get(key);
				if(cacheUnit==null)
					cacheUnit=new CacheUnit();
				cacheUnit.setData(data);
				long time=(long) (System.currentTimeMillis()+secondDelay*1000);
				cacheUnit.setExpireTime(time);
			}else {
				CacheUnit cacheUnit=new CacheUnit();
				cacheUnit.setData(data);
				long time=(long) (System.currentTimeMillis()+secondDelay*1000);
				cacheUnit.setExpireTime(time);
				cacheMap.put(key,cacheUnit);
			}
		} catch (Exception e) {
			logger.error("set cache exception key:"+key);
			logger.error("exception:"+e);
		}
		
	}
	/**
	 * 删除缓存
	 * @param cacheKey
	 * @throws InterruptedException
	 */
	public void remove(String key) throws InterruptedException{
		//如果正在回收超时的缓存数据则需要等待
		while(isGc) Thread.sleep(5);
		try {
			if(cacheMap.containsKey(key))
				cacheMap.remove(key);
		} catch (Exception e) {
			logger.error("remve cache key:"+key+".exception:"+e);
		}
		
	}
	
	/**
	 * 判断缓存是否存在
	 * @param cacheKey
	 * @return
	 * @throws InterruptedException
	 */
	public  boolean exist(String cacheKey) throws InterruptedException{
		//如果正在回收超时的缓存数据则需要等待
		while(isGc) Thread.sleep(5);
		return cacheMap.containsKey(cacheKey);
	}
	
	/**
	 * 获取缓存数量
	 * @return
	 * @throws InterruptedException
	 */
	public  int size() throws InterruptedException{
		//如果正在回收超时的缓存数据则需要等待
		while(isGc) Thread.sleep(5);
		return cacheMap.size();
	}
	
	
	/**
	 * 线程匿名类,用于启动gc
	 */
	Runnable myRunnable = new Runnable(){
		   public void run(){
			   gc();
		   }
    };
	
	/**
	 * 回收超时的缓存数据
	 */
	private  void gc(){
		while(true){
			//回收数据
			try {
				Thread.sleep(60*60*1000);
				logger.info("开始回收缓存数据");
				isGc=true;
				Iterator<Map.Entry<String, Object>> it=cacheMap.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<String, Object> entry=it.next(); 
					CacheUnit cacheUnit=(CacheUnit)entry.getValue();
					if(cacheUnit!=null){
						if(!cacheUnit.isEnabled()){
							it.remove();
							logger.info("回收一条数据 ");
						}
					}
					
				}
			} catch (Exception e) {
				logger.error("回收缓存数据异常："+e);
			}
			finally {
				isGc=false;
			}
			
		}
	}
	
	
}

/**
 * 缓存的数据对象
 * @author zhangxiaowei
 *
 */
class CacheUnit{
	private Object data;
	
	private long  expireTime;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	/**
	 * 判断缓存是否还能用
	 * @return
	 */
	public boolean isEnabled(){
		long nowTime= System.currentTimeMillis();
		return  nowTime<expireTime;
	}
	
}


