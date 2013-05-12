package by.bsu.kazmerchuk.wicket;

import org.apache.wicket.model.PropertyModel;

public class BufferedPropertyModel<T>
    extends PropertyModel<T> {

    T object;

    public BufferedPropertyModel(Object modelObject, String expression) {
        super(modelObject, expression);

        object = getObject();
    }

    @Override
    public void setObject(T object) {
        this.object = object;

        super.setObject(object);
    }

    @Override
    public T getObject() {
        return object == null ? object = super.getObject() : object;
    }

    @Override
    public void detach() {
        super.detach();
        object = null;
    }
}
