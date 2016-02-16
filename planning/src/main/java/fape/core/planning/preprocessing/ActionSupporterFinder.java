package fape.core.planning.preprocessing;

import fape.core.planning.states.State;
import fape.core.planning.timelines.Timeline;

import java.util.Collection;

public interface ActionSupporterFinder {

    /**
     * Finds which actions containing a statement that can be used as an enabler for the temporal database.
     *
     * @param db DB that needs enablers
     * @return Actions containing at least one statement that might enable the database.
     */
    Collection<SupportingAction> getActionsSupporting(State st, Timeline db);
}
