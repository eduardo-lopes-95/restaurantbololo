package restaurant.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import br.com.restaurante.dao.impl.Sql2oRestaurantDao;
import br.com.restaurante.model.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class Sql2oRestaurantDaoTest {

	private Connection conn;
	private Sql2oRestaurantDao restaurantDao;

	@Before
	public void setUp() throws Exception {
		String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
		Sql2o sql2o = new Sql2o(connectionString, "", "");
		restaurantDao = new Sql2oRestaurantDao(sql2o);
		conn = sql2o.open();
	}

	@After
	public void tearDown() throws Exception {
		conn.close();
	}

	@Test
	public void addedRestaurantsAreReturnedFromGetAll() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		restaurantDao.add(testRestaurant);
		assertEquals(1, restaurantDao.getAll().size());
	}

	@Test
	public void noRestaurantsReturnsEmptyList() throws Exception {
		assertEquals(0, restaurantDao.getAll().size());
	}

	@Test
	public void deleteByIdDeletesCorrectRestaurant() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		restaurantDao.add(testRestaurant);
		restaurantDao.deleteById(testRestaurant.getId());
		assertEquals(0, restaurantDao.getAll().size());
	}

	public Restaurant setupRestaurant() {
		return new Restaurant("Witch", 1, "97232", 1);
	}

	public Restaurant setupAltRestaurant() {
		return new Restaurant("Witch", 1, "97232", 1);
	}
}
