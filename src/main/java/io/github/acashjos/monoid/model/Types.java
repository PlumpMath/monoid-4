package io.github.acashjos.monoid.model;

/**
 * Created by acashjos on 23/7/15.
 */

public class Types {

    /**
     *
     *          id  |   name   |   count
     *          ____|__________|__________
     *              |          |
     *           int|   string |  int
     */


    int id,count;
    String name;

    //constructor

    public Types()
    {}

    //getters

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }


    //setters

    public Types setId(int id) {
        this.id = id;
        return this;
    }

    public Types setCount(int count) {
        this.count = count;
        return this;
    }

    public Types setName(String name) {
        this.name = name;
        return this;
    }
}
