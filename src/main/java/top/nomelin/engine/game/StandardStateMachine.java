package top.nomelin.engine.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.interfaces.Input;
import top.nomelin.engine.interfaces.StateMachine;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class StandardStateMachine implements StateMachine {

    private Set<State> states;

    private State currentState;

    private static final Logger LOGGER=LogManager.getLogger(StandardStateMachine.class);

    @Override
    public void init(State... states) {
        this.states=new HashSet<>();
        if(states!=null){

        }
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void handleTransition(Input input) {

    }

    @Override
    public void addState(State state) {

    }

    @Override
    public void removeState(State state) {

    }

    @Override
    public State searchState(String stateName) {
        return null;
    }

    @Override
    public void transitionState(String stateName) {

    }

    @Override
    public void addTransition(State fromState, State toState, Function<Input, Boolean> transitionFunction) {

    }

    @Override
    public void removeTransition(State fromState, State toState) {

    }
}
