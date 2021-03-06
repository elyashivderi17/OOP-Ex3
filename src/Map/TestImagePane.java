package Map;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;

public class TestImagePane {
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	private ImageIcon packmanimage;
	private ImageIcon cherryimage;
	private int counter=0;
	private int count=0;
	public static void main(String[] args) {
		new TestImagePane();
	}

	public TestImagePane() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}

				try {
					Image img = null;
					img = ImageIO.read(new File("Ariel1.png"));
					packmanimage=new ImageIcon("pacman.jpg");
					cherryimage=new ImageIcon("cherry.png");
					JFrame frame = new JFrame("Testing");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new BorderLayout());
					frame.add(new ImagePanel(img));
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (IOException | HeadlessException exp) {
					exp.printStackTrace();
				}
			}
		});
	}

	class ImagePanel extends JPanel implements MouseListener {

		private static final long serialVersionUID = 1L;
		double lat=0;
		double lon=0;
		private Image img;
		private Image scaled;
		double x=-1;
		double y=-1;
		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
			this.addMouseListener(this); 
		}

		public ImagePanel(Image img) {
			this.img = img;
			this.addMouseListener(this); 
		}

		@Override
		public void invalidate() {
			super.invalidate();
			int width = getWidth();
			int height = getHeight();

			if (width > 0 && height > 0) {
				scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(scaled, 0, 0, null);
			double x = getWidth();
			double y = getHeight();
			lat = ((y/y/180)-90)/-1;
			lon=x/(x/360)-180;
			//System.out.println("[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);
			for(int i=0;i<Packmanarr.size();i++) {
				g.drawImage(packmanimage.getImage(), Packmanarr.get(i).getOrinet().ix(), Packmanarr.get(i).getOrinet().iy(),50,50,null);
			}
			for(int i=0;i<Fruitarr.size();i++) {
				g.drawImage(cherryimage.getImage(), Fruitarr.get(i).getOrient().ix(), Fruitarr.get(i).getOrient().iy(),50,50,null);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				x = e.getX();
				y = e.getY();
				System.out.println("left click you create new packman X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input packman speed : ");
				String test2= JOptionPane.showInputDialog("Please input packman radius : ");
				double speed=-1,radius=-1;
				try {
				speed=Double.parseDouble(test1);
				}catch (Exception ex) {}
				try {
				radius=Double.parseDouble(test2);
				}catch(Exception ex) {}
				while(speed<=0) {
					test1= JOptionPane.showInputDialog("Please input packman speed(larger than 0) : ");
					try {
					speed=Double.parseDouble(test1);
					}catch(Exception ex) {}
				}
				while(radius<=0) {
					test2= JOptionPane.showInputDialog("Please input packman radius(larger than 0) : ");
					try {
					radius=Double.parseDouble(test2);
					}catch(Exception ex) {}
				}
				Packmanarr.add(new Packman(count,x,y,0,speed,radius));
				count++;
				repaint();	
			}
			else {
				x = e.getX();
				y = e.getY();
				System.out.println("right click you create new fruit X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input fruit weight : ");
				double weight=-1;
				try {
				weight=Double.parseDouble(test1);
				}catch(Exception ex) {}
				while(weight<=0) {
					test1= JOptionPane.showInputDialog("Please input fruit weight(larger than 0) : ");
					weight=Double.parseDouble(test1);
				}
				Fruitarr.add(new Fruit(counter,x,y,0,weight));
				counter++;
				repaint();			
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
