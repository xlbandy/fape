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

package fape.core.planning.temporaldatabases.events.resources;

import fape.core.planning.temporaldatabases.events.TemporalEvent;

/**
 *
 * @author FD
 */
public class ConditionEvent extends TemporalEvent {

    /**
     *
     */
    public String operator;

    /**
     *
     */
    public float value;

    /**
     *
     * @return
     */
    @Override
    public TemporalEvent cc() {
        ConditionEvent ret = new ConditionEvent();
        ret.operator = operator;
        ret.value = value;
        return ret;
    }

}
