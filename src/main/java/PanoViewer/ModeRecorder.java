package PanoViewer;

import PanoViewer.gui.Menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModeRecorder {
  private static PropertyChangeSupport support;
  private Mode currentMode;
  private static ModeRecorder instance;
  private ModeRecorder()
  {
    currentMode = Mode.Panoramic;
    support = new PropertyChangeSupport(this);
    addPropertyChangeListener("mode",Menu.getInstance());
    addPropertyChangeListener("mode",SwitchModes.getInstance());
  }
  public static ModeRecorder getInstance() {
    if (instance == null) {
      instance = new ModeRecorder();
    }
    return instance;
   }

  public Mode getCurrentMode()
  {
    return currentMode;
  }
  public void addPropertyChangeListener(String mode,PropertyChangeListener ls) {
    support.addPropertyChangeListener(mode,ls);
  }

  public void removePropertyChangeListener(PropertyChangeListener ls) {
  support.removePropertyChangeListener(ls);
  }
  /**
  * @var-if mode md is Panoramic  then flag is false  else true hence a property change is propagated
  * @param  newMode -mode to be set
  *
  *
  *
  */
  public void setCurrentMode(Mode newMode) {
    Mode oldMode = getCurrentMode();
    support.firePropertyChange("mode", newMode,oldMode);
    currentMode = newMode;
    System.out.println(oldMode + "  " + newMode);
  }
}
