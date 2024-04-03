package restaurant.models;

import br.com.restaurante.model.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RestaurantTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNameReturnsCorrectName() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		assertEquals("Fish Witch", testRestaurant.getName());
	}

	@Test
	public void getAddressReturnsCorrectAddress() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		assertEquals(1, testRestaurant.getAddressId());
	}

	@Test
	public void getPhoneReturnsCorrectPhone() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		assertEquals("503-402-9874", testRestaurant.getPhone());
	}

	@Test
	public void setNameSetsCorrectName() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		testRestaurant.setName("Steak House");
		assertNotEquals("Witch", testRestaurant.getName());
	}

	@Test
	public void setAddressSetsCorrectAddress() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		testRestaurant.setAddressId(1);
		assertNotEquals(1, testRestaurant.getAddressId());
	}

	@Test
	public void setPhoneSetsCorrectPhone() throws Exception {
		Restaurant testRestaurant = setupRestaurant();
		testRestaurant.setPhone("971-898-7878");
		assertNotEquals("503-402-9874", testRestaurant.getPhone());
	}

	public Restaurant setupRestaurant() {
		return new Restaurant("Witch", 1, "97232", 1);

	}

	public Restaurant setupAltRestaurant() {
		return new Restaurant("Witch", 1, "97232", 1);
	}
}
