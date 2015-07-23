package io.github.acashjos.monoid;

import io.github.acashjos.monoid.model.Properties;
import io.github.acashjos.monoid.model.Record;

/**
 * Created by acashjos on 22/7/15.
 */
public class MonoObject {

    int id;
    int type;
    String typeName;
    int index;


    public MonoObject(Record record, Properties properties, Monoid monoid)
    {
        typeName=monoid.getTypeName(record.getType());
        this.type= record.getType();
        this.id= record.getId();

    }
}
