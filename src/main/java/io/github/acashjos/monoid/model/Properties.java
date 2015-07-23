
package io.github.acashjos.monoid.model;
 
public class Properties {

    /**
     *
     *          id  |   path    | record_id |   depth   | children  | isDeeper  |   key |   value   |   numerical
     *          ____|___________|___________|___________|___________|___________|_______|___________|____________
     *              |           |           |           |           |           |       |           |
     *           int|   string  |   int     |   int     |  string   |  boolean  |string |   string  |   double
     */


    int id;
    String path;
    int parent;
    int depth;
    String children;
    boolean reference;
    String key;

    public String getValue() {
        return value;
    }

    public Properties setValue(String value) {
        this.value = value;
        return this;
    }

    public int getId() {
        return id;
    }

    public Properties setId(int id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    //setters

    public Properties setPath(String path) {
        this.path = path;
        return this;
    }

    public int getParent() {
        return parent;
    }

    public Properties setParent(int parent) {
        this.parent = parent;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public Properties setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public String getChildren() {
        return children;
    }

    public Properties setChildren(String children) {
        this.children = children;
        return this;
    }

    public boolean isReference() {
        return reference;
    }

    public Properties setReference(boolean reference) {
        this.reference = reference;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Properties setKey(String key) {
        this.key = key;
        return this;
    }

    public Double getNum() {
        return num;
    }

    public Properties setNum(Double num) {
        this.num = num;
        return this;
    }

    String value;
    Double num;

 
    // constructors
    public Properties() {
    }

    
 

}