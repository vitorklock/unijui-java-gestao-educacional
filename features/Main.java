import javax.swing.SwingUtilities;

import main.bootstrap.AppBootstrap;
import main.bootstrap.AppContext;
import views.MainWindow;

public class Main {

	 public static void main(String[] args) {
	        AppContext ctx = AppBootstrap.init();
	        SwingUtilities.invokeLater(() -> new MainWindow(ctx).setVisible(true));
	    }

}
