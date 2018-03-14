package org.uva.sea.ql.interpreter.staticAnalysis.helpers;

import java.util.*;

public class Relation<T> {

    private Set<AbstractMap.SimpleEntry<T,T>> relations = new HashSet<>();

    public Set<AbstractMap.SimpleEntry<T, T>> getRelations() {
        return relations;
    }

    /**
     * Add relation from elementA to elementB
     * @param elementA
     * @param elementB
     * @return
     */
    public boolean addRelation(T elementA, T elementB) {
        AbstractMap.SimpleEntry<T, T> element = new AbstractMap.SimpleEntry<>(elementA, elementB);
        return this.relations.add(element);
    }

    /**
     * Checks if there are circular relations
     * @return List of relations that are circular
     */
    public List<T> getCircularRelations() {
        Relation<T> transitiveClosure = this.getTransitiveClosure();
        List<T> circular = new ArrayList<>();
        for(AbstractMap.SimpleEntry<T,T> entry : transitiveClosure.getRelations()) {
            if(entry.getKey().equals(entry.getValue())) {
                circular.add(entry.getKey());
            }
        }

        return circular;
    }

    /**
     * Get transitive closure of the current relation
     * @return
     */
    private Relation<T> getTransitiveClosure() {
        Relation<T> transitiveClosure = new Relation<>();
        transitiveClosure.addAll(this.relations);

        boolean newElementsAdded;
        do {
            newElementsAdded = false;
            for(AbstractMap.SimpleEntry<T,T> entry : transitiveClosure.getRelations()) {
                T source = entry.getKey();
                T target = entry.getValue();
                Set<T> relationTo = transitiveClosure.getRelationTo(target);
                for( T element : relationTo) {
                    if(transitiveClosure.addRelation(source, element)) {
                        newElementsAdded = true;
                    }
                }
            }
        } while(newElementsAdded);

        return transitiveClosure;
    }

    /**
     * Add a set of relations
     * @param relations
     */
    private void addAll(Set<AbstractMap.SimpleEntry<T, T>> relations) {
        this.relations.addAll(relations);
    }

    /**
     * Check if key is related to value
     * @param key
     * @param value
     * @return If it this relation
     */
    public boolean contains(T key, T value) {
        return relations.contains(new AbstractMap.SimpleEntry<>(key, value));
    }

    /**
     * Find what elements have a relation with element
     * @param element The element
     * @return Set of items that have a relation to element
     */
    public Set<T> getRelationTo(T element) {
        Set<T> result = new HashSet<>();
        for( AbstractMap.SimpleEntry<T,T> entry : this.relations) {
            if(entry.getKey().equals(element)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }
}
