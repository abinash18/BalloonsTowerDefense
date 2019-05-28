package handlers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import org.json.JSONObject;

import other.StateManager;
import other.StateManager.GameState;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class ServerExplorer extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usernameJC;
	private JTextField joinCode;
	private JTextField passwordJC;
	private JLabel server_ipFSLABEL;
	private JTextField unameDC;
	private JTextField ipDC;
	private JTextField passwordDC;
	private JTextField udpPortDC;
	private JTextField tcpPortDC;
	private JButton btnJoinJC;
	private JButton choosenListJoin;
	private JButton directConnectJoin;
	private JProgressBar GetlistProgress;
	private JTextField passwdFS;
	private JList listOfServers;

	private String ip, username;
	private int udp, tcp, jc;
	
	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerExplorer frame = new ServerExplorer();
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
	public ServerExplorer() {
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Server Explorer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Join Using Code", null, panel, null);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_3.setBounds(29, 44, 360, 139);
		panel.add(panel_3);
		panel_3.setLayout(null);

		usernameJC = new JTextField();
		usernameJC.setBounds(185, 16, 169, 20);
		panel_3.add(usernameJC);
		usernameJC.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(6, 22, 71, 14);
		panel_3.add(lblUsername);

		joinCode = new JTextField();
		joinCode.setBounds(185, 47, 169, 20);
		panel_3.add(joinCode);
		joinCode.setColumns(10);

		JLabel lblGameJoinCode = new JLabel("Game Join Code:");
		lblGameJoinCode.setBounds(6, 53, 100, 14);
		panel_3.add(lblGameJoinCode);

		passwordJC = new JTextField();
		passwordJC.setBounds(185, 78, 169, 20);
		panel_3.add(passwordJC);
		passwordJC.setColumns(10);

		JLabel lblPasswordleaveBlank = new JLabel("Password (Leave Blank If None):");
		lblPasswordleaveBlank.setBounds(6, 84, 169, 14);
		panel_3.add(lblPasswordleaveBlank);

		btnJoinJC = new JButton("Join");
		btnJoinJC.setActionCommand("joinUsingJoinCode");
		btnJoinJC.addActionListener(this);
		btnJoinJC.setBounds(265, 109, 89, 23);
		panel_3.add(btnJoinJC);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Find Server", null, panel_1, null);
		panel_1.setLayout(null);

		listOfServers = new JList();
		listOfServers.setBounds(10, 17, 399, 143);
		panel_1.add(listOfServers);

		choosenListJoin = new JButton("Join");
		choosenListJoin.setBounds(320, 196, 89, 23);
		panel_1.add(choosenListJoin);

		JLabel lblServerIp = new JLabel("Server Ip:");
		lblServerIp.setBounds(10, 200, 65, 14);
		panel_1.add(lblServerIp);

		server_ipFSLABEL = new JLabel("--------------------------");
		server_ipFSLABEL.setBounds(63, 200, 118, 14);
		panel_1.add(server_ipFSLABEL);

		GetlistProgress = new JProgressBar();
		GetlistProgress.setBounds(136, 0, 146, 14);
		panel_1.add(GetlistProgress);

		JLabel label_1 = new JLabel("Password (Leave Blank If None):");
		label_1.setBounds(10, 171, 169, 14);
		panel_1.add(label_1);

		passwdFS = new JTextField();
		passwdFS.setColumns(10);
		passwdFS.setBounds(240, 171, 169, 20);
		panel_1.add(passwdFS);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Direct Connect", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_4.setBounds(22, 14, 374, 198);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		unameDC = new JTextField();
		unameDC.setBounds(199, 16, 169, 20);
		panel_4.add(unameDC);
		unameDC.setColumns(10);

		JLabel label = new JLabel("Username:");
		label.setBounds(6, 19, 169, 14);
		panel_4.add(label);

		ipDC = new JTextField();
		ipDC.setBounds(199, 47, 169, 20);
		panel_4.add(ipDC);
		ipDC.setColumns(10);

		JLabel lblServerIp_1 = new JLabel("Server Ip:");
		lblServerIp_1.setBounds(6, 50, 169, 14);
		panel_4.add(lblServerIp_1);

		passwordDC = new JTextField();
		passwordDC.setBounds(199, 134, 169, 20);
		panel_4.add(passwordDC);
		passwordDC.setColumns(10);

		JLabel label_2 = new JLabel("Password (Leave Blank If None):");
		label_2.setBounds(6, 137, 169, 14);
		panel_4.add(label_2);

		directConnectJoin = new JButton("Join");
		directConnectJoin.setBounds(279, 165, 89, 23);
		panel_4.add(directConnectJoin);

		JLabel lblServerUdpPort = new JLabel("Server UDP Port:");
		lblServerUdpPort.setBounds(6, 81, 169, 14);
		panel_4.add(lblServerUdpPort);

		udpPortDC = new JTextField();
		udpPortDC.setBounds(199, 78, 169, 20);
		panel_4.add(udpPortDC);
		udpPortDC.setColumns(10);

		JLabel lblServerTcpPort = new JLabel("Server TCP Port:");
		lblServerTcpPort.setBounds(6, 109, 169, 14);
		panel_4.add(lblServerTcpPort);

		tcpPortDC = new JTextField();
		tcpPortDC.setBounds(199, 106, 169, 20);
		panel_4.add(tcpPortDC);
		tcpPortDC.setColumns(10);
	}

	private void joinServerJC() {
		try {
			String url = "http://bstc2005.com:3030/getServerByCode/" + jc;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("Checking in on Master Server : " + url);
			System.out.println("Status Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print in String
			System.out.println(response.toString());
			// Read JSON response and print
			JSONObject myResponse = new JSONObject(response.toString());
			ip = (String) myResponse.get("ip");
			udp = (int) myResponse.get("udp");
			tcp = (int) myResponse.get("tcp");
			System.out.println("Raw response >> " + myResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "joinUsingJoinCode":

			if (!usernameJC.getText().equals("") && !joinCode.getText().equals("")) {
				jc = Integer.parseInt(joinCode.getText());
				username = usernameJC.getText();
				joinServerJC();
				this.dispose();
				StateManager.ip = ip;
				StateManager.portUDP = udp;
				StateManager.portTCP = tcp;
				StateManager.username = username;
				StateManager.password = passwordJC.getText();
				StateManager.setState(GameState.GAME_ONLINE);
			}
			
			break;
		}
	}

}
