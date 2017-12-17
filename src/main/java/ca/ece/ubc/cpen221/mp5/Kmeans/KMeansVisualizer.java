package ca.ece.ubc.cpen221.mp5.Kmeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/*
 * Pseudocode example:
 * 
 * KMeansVisualizer v = new Visualizer();
 * v.setDelay(500);
 * 
 *  while ([K means iteration loop]) {
 * 	//update points and centroids
 * 	
 * 	for (centroid : centroids) {
 * 		v.beginCluster(centroid.x, centroid.y);
 * 		for (point : clusters.get(centroid)) {
 * 			v.addPoint(point.x, point.y);
 * 		}
 * 	}
 * 	v.show();
 *  }
 */

public class KMeansVisualizer {
	
	public static void main(String[] args) {
		KMeansVisualizer vis = new KMeansVisualizer();
		vis.setDelay(100);
		for (int g = 0; g < 50; g++) {
			for (int h = 0; h < 3; h++) {
				double x = Math.random() * 3.;
				double y = Math.random() * 3.;
				vis.beginCluster(x, y);
				for (int i = 0; i < 10; i++) {
					vis.addPoint(Math.random() - 0.5 + x, Math.random() - 0.5 + y);
				}
			}
			vis.show();
		}
		vis.close();
	}
	
	private JFrame window;
	private KMeansVisualizerPanel panel;
	
	private List<List<KMeansVisualizerPoint>> currentData = null;
	private int delay = 0;
	
	public void setDelay(int milliseconds) {
		this.delay = milliseconds;
	}
	
	public void beginCluster(double x, double y) {
		if (currentData == null) {
			currentData = new ArrayList<List<KMeansVisualizerPoint>>();
		}
		List<KMeansVisualizerPoint> cluster = new ArrayList<KMeansVisualizerPoint>();
		cluster.add(new KMeansVisualizerPoint(x, y));
		currentData.add(cluster);
	}
	
	public void addPoint(double x, double y) {
		if (currentData.size() == 0) throw new IllegalStateException("addPoint called without starting any clusters.");
		currentData.get(currentData.size() - 1).add(new KMeansVisualizerPoint(x, y));
	}
	
	public void show() {
		panel.update(currentData);
		currentData = null;
		try {
			Thread.sleep(delay);
		}
		catch (InterruptedException e) {
			
		}
	}
	
	public void close() {
		window.dispose();
	}
	
	public KMeansVisualizer() {
		window = new JFrame("KMeansVisualizer");
		Container container = window.getContentPane();
		window.setVisible(true);
		panel = new KMeansVisualizerPanel();
		container.add(panel, BorderLayout.CENTER);
		
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.pack();
	}

}

class KMeansVisualizerPanel extends JPanel {
	private static final long serialVersionUID = 3157204862883774476L;
	
	KMeansVisualizerPanel() {
		super();
		setPreferredSize(new Dimension(640, 480));
		setBackground(Color.WHITE);
	}
	
	List<List<KMeansVisualizerPoint>> currentData = new ArrayList<List<KMeansVisualizerPoint>>();
	double xMin, xMax, yMin, yMax, xRange, yRange;
	double borderRatio = 0.1;
	
	public void update(List<List<KMeansVisualizerPoint>> data) {
		currentData = data;
		
		xMin = data.stream().flatMap(l -> l.stream()).mapToDouble(p -> p.x).min().orElse(0);
		xMax = data.stream().flatMap(l -> l.stream()).mapToDouble(p -> p.x).max().orElse(0);
		yMin = data.stream().flatMap(l -> l.stream()).mapToDouble(p -> p.y).min().orElse(0);
		yMax = data.stream().flatMap(l -> l.stream()).mapToDouble(p -> p.y).max().orElse(0);
		xRange = xMax - xMin;
		yRange = yMax - yMin;
		if (xRange == 0.) xRange = 1.;
		if (yRange == 0.) yRange = 1.;
		
		repaint();
	}
	
	private KMeansVisualizerPoint scalePoint(KMeansVisualizerPoint point) {
		double viewXMin = xMin - borderRatio * xRange;
		double viewYMin = yMin - borderRatio * yRange;
		
		double normalizedX = (point.x - viewXMin) / (xRange * (1 + 2. * borderRatio));
		//Want +y axis to be upwards
		double normalizedY = 1.0 - (point.y - viewYMin) / (yRange * (1 + 2. * borderRatio));
		
		return new KMeansVisualizerPoint(normalizedX * getWidth(), normalizedY * getHeight());
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if (currentData.size() == 0) {
			graphics.setColor(Color.BLACK);
			graphics.drawString("No data", 0, 12);
			return;
		}
		graphics.drawString("visualizer", 0, 12);
		//System.out.println(currentData);
		
		for (List<KMeansVisualizerPoint> cluster : currentData) {
			KMeansVisualizerPoint centroid = scalePoint(cluster.get(0));
			graphics.fillOval((int)centroid.x - 5, (int)centroid.y - 5, 10, 10);
			for (int i = 1; i < cluster.size(); i++) {
				KMeansVisualizerPoint point = scalePoint(cluster.get(i));
				graphics.drawLine((int)centroid.x, (int)centroid.y, (int)point.x, (int)point.y);
			}
		}
	}
}

class KMeansVisualizerPoint {
	final double x, y;
	KMeansVisualizerPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}