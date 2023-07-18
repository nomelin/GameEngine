package top.nomelin.engine.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.controller.IdManager;
import top.nomelin.engine.interfaces.Input;
import top.nomelin.engine.interfaces.StateMachine;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class StandardStateMachine implements StateMachine {

    private boolean onStart;

    private final Set<State> states;

    /**
     * 当前状态
     */
    private State currentState;

    /**
     * 记录这个状态运行了多少帧。在自动处理转换时会自动更新此字段。
     */
    private int flamer;

    /**
     * 记录这个状态切入的时间戳，以便获取此状态运行的时间（单位毫秒）。
     * 使用transitionState方法时会自动更新此字段。
     */
    private long timer;

    private static final Logger LOGGER = LogManager.getLogger(StandardStateMachine.class);

    private final int id;

    public StandardStateMachine(State... states) {
        id = IdManager.getSystemId();
        this.states = new HashSet<>();
        if (states != null) {
            Collections.addAll(this.states, states);
            transitionState(states[0]);
        }
        LOGGER.info("状态机:" + id + ":初始化完成");
    }

    public StandardStateMachine(String... stateNames) {
        id = IdManager.getSystemId();
        this.states = new HashSet<>();
        if (stateNames != null) {
            boolean temp = true;
            for (String sn : stateNames) {
                State s = new State(sn);
                states.add(s);
                if (temp) {
                    transitionState(s);
                    temp = false;
                }
            }
        }
        LOGGER.info("状态机:" + id + ":初始化完成");
    }

    public StandardStateMachine() {
        this.states = new HashSet<>();
        id = IdManager.getSystemId();
        LOGGER.info("状态机:" + id + ":初始化完成");
    }

    public void start(){
        onStart=true;
    }

    @Override
    public State getCurrentState() {
        if (currentState == null) {
            LOGGER.warn("状态机:" + id + ":没有当前状态");
            throw new NullPointerException();
        }
        return currentState;
    }

    /**
     * 根据转换关系的函数，自动处理转换状态，如果函数成立则转换。
     *
     * @param input 输入接口
     */
    @Override
    public void handleTransition(Input input) {
        if(!onStart){
            return;
        }
        transitionState(currentState.handleTransition(input));
        updateFlamer();
    }

    @Override
    public void addState(State state) {
        states.add(state);
    }

    public void addState(String stateName) {
        states.add(new State(stateName));
    }

    @Override
    public void removeState(State state) {
        if (!containsState(state)) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + state.getName());
        }
        states.remove(state);
    }

    public void removeState(String stateName) {
        if (!containsState(stateName)) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + stateName);
        }
        State state = null;
        for (State s : states) {
            if (s.getName().equals(stateName)) {
                state = s;
                break;
            }
        }
        states.remove(state);
    }

    @Override
    public State searchState(String stateName) {
        for (State s : states) {
            if (s.getName().equals(stateName)) {
                return s;
            }
        }
        LOGGER.warn("状态机:" + id + ":状态不存在：" + stateName);
        return null;
    }

    /**
     * 直接转换状态
     *
     * @param stateName 要转换的状态名字
     */
    @Override
    public void transitionState(String stateName) {
        if(currentState!=null&&stateName.equals(currentState.getName())){
            return;
        }
        for (State s : states) {
            if (s.getName().equals(stateName)) {
                currentState = s;
                //获取进入状态的时间戳
                timer = System.currentTimeMillis();
                LOGGER.info("状态机:" + id + ":状态转换到：" + stateName);
                return;
            }
        }
        LOGGER.warn("状态机:" + id + ":未找到此状态，无法转换：" + stateName);
    }

    /**
     * 直接转换状态
     *
     * @param state 要转换的状态引用
     */
    public void transitionState(State state) {
        if(currentState!=null&&state.getName().equals(currentState.getName())){
            return;
        }
        if (!containsState(state)) {
            LOGGER.warn("状态机:" + id + ":未找到此状态，无法转换：" + state.getName());
        }
        currentState = state;
        //获取进入状态的时间戳
        timer = System.currentTimeMillis();
        LOGGER.info("状态机:" + id + ":状态转换到：" + state.getName());
    }

    @Override
    public void addTransition(State fromState, State toState, Function<Input, Boolean> transitionFunction) {
        if (!containsState(fromState.getName()) || !containsState(toState.getName())) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + fromState.getName() + "-----" + toState.getName());
        }
        fromState.addTransition(toState, transitionFunction);
        LOGGER.info("状态机:" + id + ":添加转换：" + fromState.getName() + "---到---" + toState.getName());
    }

    public void addTransition(String fromStateName, String toStateName, Function<Input, Boolean> transitionFunction) {
        if (!containsState(fromStateName) || !containsState(toStateName)) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + fromStateName + "-----" + toStateName);
        }
        for (State s : states) {
            if (s.getName().equals(fromStateName)) {
                s.addTransition(toStateName, transitionFunction);
                LOGGER.info("状态机:" + id + ":添加转换：" + fromStateName + "---到---" + toStateName);

            }
        }
    }

    @Override
    public void removeTransition(State fromState, State toState) {
        if (!containsState(fromState.getName()) || !containsState(toState.getName())) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + fromState.getName() + "-----" + toState.getName());
        }
        fromState.removeTransition(toState);
        LOGGER.info("状态机:" + id + ":删除转换：" + fromState.getName() + "---到---" + toState.getName());

    }

    public void removeTransition(String fromStateName, String toStateName) {
        if (!containsState(fromStateName) || !containsState(toStateName)) {
            LOGGER.warn("状态机:" + id + ":状态不存在：" + fromStateName + "-----" + toStateName);
        }
        for (State s : states) {
            if (s.getName().equals(fromStateName)) {
                s.removeTransition(toStateName);
                LOGGER.info("状态机:" + id + ":删除转换：" + fromStateName + "---到---" + toStateName);
            }
        }
    }

    public boolean containsState(State state) {
        return states.contains(state);
    }

    public boolean containsState(String stateName) {
        for (State s : states) {
            if (s.getName().equals(stateName)) {
                return true;
            }
        }
        return false;
    }

    public void updateFlamer() {
        flamer++;
    }

    public int getFlamer() {
        return flamer;
    }

    public int getId() {
        return id;
    }

    public long getStateRunTime() {
        return System.currentTimeMillis() - timer;
    }
    public void stop(){
        onStart=false;
    }
    public boolean onStart(){
        return onStart;
    }
}
