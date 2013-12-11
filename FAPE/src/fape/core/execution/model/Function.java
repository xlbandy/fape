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
package fape.core.execution.model;

/**
 *
 * @author FD
 */
public class Function {

    /**
     *
     */
    public enum EOperator {

        /**
         *
         */
        DIVIDE,

        /**
         *
         */
        MULTIPLY,

        /**
         *
         */
        PLUS,

        /**
         *
         */
        MINUS
    }

    /**
     *
     */
    public EOperator mOperator;

    /**
     *
     */
    public Reference left,

    /**
     *
     */
    right;

    @Override
    public String toString() {
        return left + " " + mOperator + " " + right;
    }
}
