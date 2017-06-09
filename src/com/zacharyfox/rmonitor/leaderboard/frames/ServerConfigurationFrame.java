package com.zacharyfox.rmonitor.leaderboard.frames;

import com.zacharyfox.rmonitor.utils.Settings;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerConfigurationFrame extends JFrame
{
	public JButton saveButton;
	public JTextField ip;
	public JTextField port;
	private final JLabel ipLabel;
	private final JLabel portLabel;
	private static ServerConfigurationFrame instance;
	private static final long serialVersionUID = 3848021032174790659L;

	private ServerConfigurationFrame(MainFrame mainFrame)
	{
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][]"));
		setTitle("Remote Server Configuration");

		ipLabel = new JLabel("Remote Server IP:");
		ipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(ipLabel, "cell 0 0,alignx trailing");
		setBounds(100, 100, 400, 150);

		ip = new JTextField();
		ip.setText(Settings.getInstance().getRemoteServerIp());
		getContentPane().add(ip, "cell 1 0,growx");
		ip.setColumns(10);

		portLabel = new JLabel("Remove Server Port:");
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(portLabel, "cell 0 1,alignx trailing");

		port = new JTextField();
		port.setText(Settings.getInstance().getRemoteServerPort());
		getContentPane().add(port, "cell 1 1,growx");
		port.setColumns(10);

		saveButton = new JButton("Save");
		saveButton.setHorizontalAlignment(SwingConstants.RIGHT);
//		connectButton.addActionListener(mainFrame);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.getInstance().setRemoteServerIp(ip.getText());
				Settings.getInstance().setRemoteServerPort(port.getText());
				String message;
				String title;
				if (Settings.getInstance().save()) {
					message = "Successfully save the configurations.";
					title = "Saved";
					JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
				} else {
					message = "It was not possible to save the configurations.";
					title = "Not Saved";
					JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		getContentPane().add(saveButton, "cell 1 2,alignx right");
	}

	public String getRemoteIPServer()
	{
		return ip.getText();
	}

	public Integer getRemteIPPort()
	{
		return Integer.parseInt(port.getText());
	}

	public static ServerConfigurationFrame getInstance(MainFrame mainFrame)
	{
		if (instance == null) {
			instance = new ServerConfigurationFrame(mainFrame);
		}

		return instance;
	}
}