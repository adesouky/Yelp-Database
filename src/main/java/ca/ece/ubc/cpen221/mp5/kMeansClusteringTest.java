package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class kMeansClusteringTest {

	@Test
	public void test() {
		
		KMeansVisualizer v = new KMeansVisualizer(); //part of testing needs to be removed after
		 v.setDelay(500); //part of testing needs to be removed after
		 
		 for(Centroid cent : centroidList) {
				v.beginCluster(cent.getLatitude(), cent.getLongitude());
				for (Restaurant point : groupMap.get(cent)){
								v.addPoint(point.getLatitude(), point.getLongitude());
					  		}
			}
			v.show();
			v.setDelay(100000);
			
		fail("Not yet implemented");
	}

	
	@Test
	public void test02() {
		//create centroids and resturants 
	}
}
