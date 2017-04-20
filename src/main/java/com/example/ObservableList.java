package com.example;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amarendra on 20/04/17.
 */
public class ObservableList<T,R> implements Serializable{

    protected final List<T> list;
    protected final SerializedSubject<Object, Object> onAdd;

    public ObservableList() {
        this.list = new ArrayList<T>();
        this.onAdd = new SerializedSubject<Object, Object>(PublishSubject.create());
    }
    public void add(T value) {
        list.add(value);
        onAdd.onNext(value);
    }
    public SerializedSubject<Object, Object> getObservable() {
        return onAdd;
    }
}
