package fape.core.planning.search.strategies.plans;

import fape.core.planning.states.State;


/**
 * Selects the plans with the least number of flaws with respect to the number of action.
 *
 * Evaluation function: (num open leaves + num consumers) / numActions.
 */
public class LeastFlawRatio implements PartialPlanComparator {
    @Override
    public String shortName() {
        return "lfr";
    }

    @Override
    public String reportOnState(State st) {
        return "LFR:\tflaw-ratio (%): "+eval(st);
    }

    float eval(State st) {
        return ((float) st.tdb.getConsumers().size()) / ((float) st.getNumActions())*100;
    }

    @Override
    public int compare(State state, State state2) {
        return (int) (eval(state) - eval(state2));
    }
}
