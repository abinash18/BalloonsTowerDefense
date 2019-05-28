package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.io.PrintStream;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.Choice;
import javax.swing.JScrollBar;

public class test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setBackground(Color.RED);
		setTitle("test");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\abinash\\Desktop\\java\\GameRepo\\Versions\\v4\\BalloonsTowerDefense\\BalloonsTowerDefence\\src\\resources\\IMG_20180806_160656_458.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setIconifiable(true);
		internalFrame.setResizable(true);
		internalFrame.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		internalFrame.setBounds(81, 82, 929, 432);
		desktopPane.add(internalFrame);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		internalFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JScrollBar scrollBar = new JScrollBar();
		internalFrame.getContentPane().add(scrollBar, BorderLayout.EAST);
	}
}
