package shared;

import javax.swing.JComponent;

public interface AppScreen {
    String id();                
    JComponent getComponent();  
    void onShow();              
}