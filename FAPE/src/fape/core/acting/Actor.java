/*
 * Author:  Filip Dvořák <filip.dvorak@runbox.com>
 *
 * Copyright (c) 2013 Filip Dvořák <filip.dvorak@runbox.com>, all rights reserved
 *
 * Publishing, providing further or using this program is prohibited
 * without previous written permission of the author. Publishing or providing
 * further the contents of this file is prohibited without previous written
 * permission of the author.
 */
package fape.core.acting;

import fape.core.execution.Executor;
import fape.core.execution.model.ANMLBlock;
import fape.core.execution.model.AtomicAction;
import fape.core.planning.Planner;
import fape.util.Pair;
import fape.util.TimeAmount;
import fape.util.TimePoint;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author FD
 */
public class Actor {

    /**
     *
     * @param b
     */
    public void PushEvent(ANMLBlock b) {
        newEventBuffer.add(b);
    }

    /**
     *
     * @param e
     * @param p
     */
    public void bind(Executor e, Planner p) {
        mExecutor = e;
        mPlanner = p;
    }
    long sleepTime = 100;
    long repairTime = 300;
    long progressTime = 100;
    long progressStep = 100;
    Executor mExecutor;
    Planner mPlanner;

    /**
     *
     */
    public LinkedList<ANMLBlock> newEventBuffer = new LinkedList<>();

    /**
     *
     */
    public enum EActorState {

        /**
         *
         */
        STOPPED,

        /**
         *
         */
        ACTING,

        /**
         *
         */
        ENDING
    }
    EActorState mState = EActorState.ACTING;

    /**
     * runs the acting agent
     * @throws java.lang.InterruptedException
     */
    public void run() throws InterruptedException {
        boolean end = false;
        while (!end) {
            switch (mState) {
                case ENDING:
                    end = true;
                case STOPPED:
                    Thread.sleep(sleepTime);
                    mState = EActorState.ACTING;
                    break;
                case ACTING:
                    while (!newEventBuffer.isEmpty()) {
                        mPlanner.ForceFact(newEventBuffer.pop());
                    }
                    //performing repair and progress here
                    mPlanner.Repair(new TimeAmount(repairTime));
                    List<Pair<AtomicAction, TimePoint>> scheduledActions = mPlanner.Progress(new TimeAmount(progressStep), new TimeAmount(repairTime));
                    mExecutor.executeAtomicActions(scheduledActions);
                    mState = EActorState.STOPPED;
                    break;
            }
        }
    }
}
