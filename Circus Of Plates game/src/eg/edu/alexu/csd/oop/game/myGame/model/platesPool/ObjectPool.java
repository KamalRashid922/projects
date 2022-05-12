package eg.edu.alexu.csd.oop.game.myGame.model.platesPool;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Logger;


public abstract class ObjectPool<T> {
	private static final Logger log = Logger.getLogger(ObjectPool.class);
	private Hashtable<T, Long> locked, unlocked;
	private long expirationTime;
	public ObjectPool() {
		expirationTime = 50000;
		locked = new Hashtable<T, Long>();
		unlocked = new Hashtable<T, Long>();
	}
	
	protected abstract T create();
	public abstract boolean validate(T object);
	public abstract void expire(T object);
	
	public synchronized T acquire() {
		log.info("Acquiring from pool");
		long now = System.currentTimeMillis();
		T t;
		if (unlocked.size() > 0) {
			Enumeration<T> e = unlocked.keys();
			while(e.hasMoreElements()) {
				t = e.nextElement();
				if (now - unlocked.get(t) > expirationTime) {
					unlocked.remove(t);
					expire(t);
					t = null;
				} else {
					if (validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return t;
					} else {
						unlocked.remove(t);
						expire(t);
						t = null;
					}
				}
			}
		}
		t = create();
		locked.put(t, now);
		return t;
	}
	
	public synchronized void release(T object) {
		log.info("releasing from pool");
		locked.remove(object);
		unlocked.put(object, System.currentTimeMillis());
	}
}
