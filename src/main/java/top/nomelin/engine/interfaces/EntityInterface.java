package top.nomelin.engine.interfaces;

public interface EntityInterface {
    void init();
    void start();
    boolean fixedUpdate();
    boolean update();
    boolean lateUpdate();
    void stop();
    void destroy();
}
