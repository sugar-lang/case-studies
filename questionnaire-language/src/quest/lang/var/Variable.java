package quest.lang.var;

import java.util.List;

/**
 * @author seba
 */
public class Variable<T> {
  public static interface ChangeListener<T> {
    public void changed(T old, T now);
  }
  
  private T t;
  private List<ChangeListener<T>> listeners;
  
  public Variable(T t) {
    this.set(t);
  }

  public T get() {
    return t;
  }

  public void set(T now) {
    T old = t;
    this.t = now;
    for (ChangeListener<T> l : listeners)
      l.changed(old, now);
  }
  
  public void addChangeListener(ChangeListener<T> l) {
    listeners.add(l);
  }
}
