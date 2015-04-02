package fape.core.planning.planner;

import fape.core.planning.search.flaws.finders.*;
import planstack.constraints.stnu.Controllability;

public class PlanningOptions {

    public PlanningOptions(String[] planSelStrategies, String[] flawSelStrategies) {
        this.planSelStrategies = planSelStrategies;
        this.flawSelStrategies = flawSelStrategies;
    }

    /**
     * Those are used to extract all flaws from a state.
     * The GetFlaws method will typically use all of those
     * to generate the flaws that need to be solved in a given state.
     */
    public FlawFinder[] flawFinders = {
            new OpenGoalFinder(),
            new UndecomposedActionFinder(),
            new UnsupportedTaskConditionFinder(),
            new UnmotivatedActionFinder(),
            new AllThreatFinder()
    };

    /**
     * Used to build comparators for flaws. Default to a least commiting first.
     */
    public String[] flawSelStrategies;

    /**
     * Used to build comparators for partial plans.
     */
    
    public String[] planSelStrategies;

    /**
     * If true, the planner will solve trivial flaws (with one resolver) before adding the plan
     * to the queue
     */
    public boolean useFastForward = false;

    /**
     * If set to true, the choice of the flaw to solve next will be done on the command line.
     */
    public boolean chooseFlawManually = false;

}
