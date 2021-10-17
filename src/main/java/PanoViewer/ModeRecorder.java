package PanoViewer;

public class ModeRecorder implements ModeListner {
    Mode currentMode;

    @Override
    public void update() {
        SwitchModes.getInstance().setCurrentMode(currentMode);

    }
    public void setMode(Mode mode)
    {
        currentMode = mode;
        update();
    }

}