
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Classes.*;

public class PredictorTest {

	
	@Test
	public void Test1() throws FileNotFoundException {
		Database x= new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		ToDoubleBiFunction<MP5Db<Restaurant>, String> y =x.getPredictorFunction("Y5j_2YR42UHvWZOtALgjCw");
		Double z= y.applyAsDouble((Database) x, "6QZR4ToHKlse0yhqpU5ijg");
		System.out.println(z);
	}
}
