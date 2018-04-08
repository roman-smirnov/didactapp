package com.didactapp.didact.models;

public abstract class ComponentModel {

    protected final int componentId;
    protected final int componentNum;

    protected ComponentModel(int componentId, int componentNum) {
        this.componentId = componentId;
        this.componentNum = componentNum;
    }

    public final int getComponentId() {
        return componentId;
    }

    public final int getComponentNum() {
        return componentNum;
    }
}
