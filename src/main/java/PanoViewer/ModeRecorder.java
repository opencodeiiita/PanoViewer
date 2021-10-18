package PanoViewer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModeRecorder {
    private static PropertyChangeSupport support;
    private Mode currentMode;
    private ModeRecorder instance;
    private ModeRecorder()
    {
    }
    public ModeRecorder getInstance()
    {
        if(instance==null)
        {
            instance=new ModeRecorder();
            support=new PropertyChangeSupport(this);
        }
        return instance;
    }



    //  public ModeRecorder() {
//    support = new PropertyChangeSupport(this);
//  }
    public Mode getCurrentMode()
    {
        return currentMode;
    }
    public  void addPropertyChangeListener(PropertyChangeListener ls) {
        support.addPropertyChangeListener(ls);

    }

    public void removePropertyChangeListener(PropertyChangeListener ls) {
        support.removePropertyChangeListener(ls);
    }
    /**
     * @var-if mode md is Panoramic  then flag is false  else true hence a property change is propagated
     * @param  md -mode to be set
     *
     *
     *
     */
    public void setMode(Mode md) {
        boolean flag = md != Mode.Panoramic;
        support.firePropertyChange("mode", flag, !flag);
        currentMode=md;
    }
}
