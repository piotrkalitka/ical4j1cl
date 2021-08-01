/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.fortuna.ical4j.filter.predicate;

import net.fortuna.ical4j.model.PropertyContainer;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Test for a property matching any values in the provided list.
 *
 * @param <T>
 */
public class PropertyInRule<T extends PropertyContainer> implements Predicate<T> {

    private final String propertyName;

    private final Collection<?> value;

    public PropertyInRule(String propertyName, Collection<?> value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    @Override
    public boolean test(T t) {
        return value.stream().anyMatch(value -> new PropertyEqualToRule<>(propertyName, value).test(t));
    }
}
