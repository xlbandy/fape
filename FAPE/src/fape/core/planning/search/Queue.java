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

package fape.core.planning.search;

import fape.core.planning.states.State;
import java.util.LinkedList;

/**
 * more of a dummy implementation of the queue
 * @author FD
 */
public class Queue {
    LinkedList<State> list = new LinkedList<>();

    /**
     *
     * @param st
     */
    public void Add(State st){
        list.add(st);
    }

    /**
     *
     * @return
     */
    public State Pop(){
        return list.pollFirst();
    }

    /**
     *
     * @return
     */
    public boolean Empty() {
        return list.isEmpty();
    }

    /**
     *
     * @return
     */
    public State Peek() {
        return list.getFirst();
    }
}
