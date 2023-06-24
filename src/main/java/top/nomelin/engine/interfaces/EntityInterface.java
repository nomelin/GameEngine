package top.nomelin.engine.interfaces;

@Deprecated(forRemoval = true)
public interface EntityInterface {
    void init();
    void start();
    boolean fixedUpdate();
    boolean update();
    boolean lateUpdate();
    void stop();
    void destroy();
}
