package com.demo.service;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame implements ActionListener, Runnable {
	Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	String clientMessage;
	String sends;
	String sendsboard = "����";
	Socket client;
	String str_canzhuohao;
    TextField textfield;
	Server() {
		super("Server");
		getContentPane().setLayout(b1);
		jb1.setText("�鿴�˵�");
		jb1.addActionListener(this);
		jb_sure.setText("ȷ��");
		jb_sure.addActionListener(this);
		jb2.setText("����");
		jb3.setText("�رշ�����");
		jb3.addActionListener(this);
		jb2.addActionListener(this);
		this.getContentPane().add(j1, java.awt.BorderLayout.NORTH);
		j1.add(jb1);
		j1.add(text);
		j1.add(jb_sure);
		j1.add(jb2);
		j1.add(jb3);
		jtext.setText("�͵�");
		jtext1.setText("����");
		j2.setLayout(b2);
		j2.add(jsp, java.awt.BorderLayout.CENTER);
		jsp.getViewport().add(jtext);

		
		
		this.getContentPane().add(j2, java.awt.BorderLayout.CENTER);
		this.setSize(400, 400);
		this.setVisible(true);
		while (true) {
			try {
				// ����ServerSocket
				ServerSocket serverSocket = new ServerSocket(8885);
				System.out.println("wait");
				while (true) {
					// ���ܿͻ�������
					client = serverSocket.accept();
					System.out.println("accept");
					try {
						dataInputStream = new DataInputStream(
								client.getInputStream());
						clientMessage = dataInputStream.readUTF();
						System.out.println("read:" + clientMessage);
						dataInputStream.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					} finally {
						// �ر�
//						client.close();
						System.out.println("close");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	BorderLayout b1 = new BorderLayout();
	BorderLayout b2 = new BorderLayout();
	JPanel j1 = new JPanel();
	JPanel j2 = new JPanel();
	JButton jb1 = new JButton();
	JButton jb2 = new JButton();
	JButton jb_sure = new JButton();
	JButton jb3 = new JButton();
	JTextField text=new JTextField("",5);
	
	JScrollPane jsp = new JScrollPane();
	static JTextArea jtext = new JTextArea();
	static JTextArea jtext1 = new JTextArea();
	boolean bool = false, start = false;
	Thread thread;

	// main����������������
	public static void main(String a[]) {
		Thread desktopServerThread = new Thread(new Server());
		desktopServerThread.start();
		Server server = new Server();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ʹ�� System exit �����˳�Ӧ�ó��򡣽���Ӧ�ó�����ʹ�á�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			jtext.setText(clientMessage);
			
		}
		if (e.getSource() == jb2) {
			System.out.println(str_canzhuohao);
			try {
				dataOutputStream = new DataOutputStream(
				client.getOutputStream());
				dataOutputStream.writeUTF(str_canzhuohao);
				System.out.println("�����ѳɹ����ͳ�ȥ");
				dataOutputStream.close();
			} catch (Exception e2) {
				System.out.print("����ʧ��");// TODO: handle exception
			}
			finally
			{try {
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print("����δ֪�Ĵ���");
			}}
			
		}
		if (e.getSource() == jb3) {
			System.exit(0);
			
		}
		if (e.getSource() == jb_sure) {
		  str_canzhuohao=text.getText().toString();
			System.out.print(str_canzhuohao);
			
		}
	}

	public void run() {
		// TODO Auto-generated method stub

	}
}
