package PanoViewer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModeRecorder {
    private PropertyChangeSupport support;
    private Mode currentMode;

    public ModeRecorder() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener ls) {
        support.addPropertyChangeListener(ls);

    }

    public void removePropertyChangeListener(PropertyChangeListener ls) {
        support.removePropertyChangeListener(ls);
    }

    public void setMode(Mode md) {
        boolean flag = md != Mode.Panoramic;
        support.firePropertyChange("mode", flag, !flag);
    }
}
