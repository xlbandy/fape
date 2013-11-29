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
package fape.core.planning.states;

import fape.core.execution.model.ActionRef;
import fape.core.planning.bindings.BindingManager;
import fape.core.planning.causalities.CausalNetworkManager;
import fape.core.planning.constraints.ConstraintNetworkManager;
import fape.core.planning.stn.STNManager;
import fape.core.planning.tasknetworks.TaskNetworkManager;
import fape.core.planning.temporaldatabases.TemporalDatabaseManager;

/**
 *
 * @author FD
 */
public class State {

    public TemporalDatabaseManager tdb;
    public STNManager tempoNet;
    public TaskNetworkManager taskNet;
    //public ConstraintNetworkManager conNet;
    public BindingManager bindings;
    //public CausalNetworkManager causalNet;

    /**
     * this constructor is only for the initial state!! other states are constructed
     * from from the existing states
     */
    public State(){
        tdb = new TemporalDatabaseManager();
        tempoNet = new STNManager();
        taskNet = new TaskNetworkManager();
        //conNet = new ConstraintNetworkManager();
        bindings = new BindingManager();
        //causalNet = new CausalNetworkManager();
    }
}
