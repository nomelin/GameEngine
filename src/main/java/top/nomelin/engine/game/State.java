package top.nomelin.engine.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.interfaces.Input;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class State {
    /**
     * 状态名字，作为key
     */
    private final String name;

    protected static final Logger LOGGER = LogManager.getLogger(State.class);

    /**
     * 从这个节点出发的边，箭头，转换。
     * name为下一个节点的名字。
     * function作为转换函数，为true则转换状态.
     */
    private final Map<String, Function<Input, Boolean>> transitions;

    public State(String name) {
        this.name = name;
        this.transitions = new HashMap<>();
        LOGGER.info("创建状态："+name);
    }

    public void addTransition(State nextState, Function<Input, Boolean> transitionFunction) {
        transitions.put(nextState.getName(), transitionFunction);
        LOGGER.info("创建转换边："+name+"--到--"+nextState.getName());
    }

    public void addTransition(String nextStateName, Function<Input, Boolean> transitionFunction) {
        transitions.put(nextStateName, transitionFunction);
        LOGGER.info("创建转换边："+name+"--到--"+nextStateName);

    }

    public void removeTransition(State nextState){
        transitions.remove(nextState.getName());
    }

    public void removeTransition(String nextStateName){
        transitions.remove(nextStateName);
    }

    /**
     * @param input 输入接口
     * @return 切换后的状态的名字（没切换就是自己的名字）
     */
    public String handleTransition(Input input) {
        for (Map.Entry<String, Function<Input, Boolean>> entry : transitions.entrySet()) {
            if (entry.getValue().apply(input)) {
                LOGGER.info("切换状态:由"+name+"到"+entry.getKey());
                return entry.getKey();
            }
        }
        return name;
    }

    public String getName() {
        return name;
    }

    /**
     * 若本状态包含指向输入状态的转换，则返回true。
     * @param state 输入状态的引用
     */
    public boolean containsNextState(State state){
        String sn=state.getName();
        return transitions.containsKey(sn);
    }

    /**
     * 若本状态包含指向输入状态的转换，则返回true。
     * @param stateName 输入状态的名字
     */
    public boolean containsNextState(String stateName){
        return transitions.containsKey(stateName);
    }
}
