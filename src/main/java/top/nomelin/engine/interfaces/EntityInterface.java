package top.nomelin.engine.interfaces;

public interface EntityInterface {
    void init();
    void start();
    void fixedUpdate();
    void Update();
    void lateUpdate();
    void stop();
    void destroy();
}
