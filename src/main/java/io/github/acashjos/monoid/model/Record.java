
package io.github.acashjos.monoid.model;
 
public class Record {
    /**
     *
     *          id  |  type_id  |   index
     *          ____|___________|_________
     *              |           |
     *           int|     int   |   int
     */



    int id;
    int type;
    int index;


    // constructors

    public Record() {
    }

 //getters


    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    //setters

    public Record setId(int id) {
        this.id = id;
        return this;
    }

    public Record setType(int type) {
        this.type = type;
        return this;
    }

    public Record setIndex(int index) {
        this.index = index;
        return this;
    }
}