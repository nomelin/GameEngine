package top.nomelin.engine.interfaces;

import top.nomelin.engine.game.State;

import java.util.function.Function;

public interface StateMachine {

    // 初始化状态机
    //void init(State... states);

    // 获取当前状态
    State getCurrentState();

    // 处理转换
    void handleTransition(Input input);

    // 添加一个状态
    void addState(State state);

    // 删除一个状态
    void removeState(State state);

    //根据名字查找状态
    State searchState(String stateName);

    //转换到这个名字所指的状态。
    void transitionState(String stateName);

    // 返回可能的转换关系
    //Map<State, List<State>> getStateTransitions();

    // 添加一个转换关系
    void addTransition(State fromState, State toState, Function<Input, Boolean> transitionFunction);

    // 删除一个转换关系
    void removeTransition(State fromState, State toState);
}
