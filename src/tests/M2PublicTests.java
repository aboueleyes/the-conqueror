package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;
import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Distance;
import engine.Game;
import engine.Player;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.BuildingInCoolDownException;
import exceptions.EmpireException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.MaxLevelException;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class M2PublicTests {
	String armyPath = "units.Army";
	String unitPath = "units.Unit";
	String archerPath = "units.Archer";
	String infantryPath = "units.Infantry";
	String cavalryPath = "units.Cavalry";
	String cityPath = "engine.City";
	
	@Test(timeout = 100)
	public void testIsGameOverMethodExists() {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Class Game should contain a method named isGameOver.", containsMethodName(methods, "isGameOver"));
	}

	@Test(timeout = 100)
	public void testIsGameOverMethodReturnsTrue() throws Exception {
		Game g = new Game("Mabrouk", "Cairo");
		Field ctc = Game.class.getDeclaredField("currentTurnCount");
		ctc.setAccessible(true);
		ctc.set(g, 31);
		Class[] argsDeclared = {};
		Method m = Game.class.getDeclaredMethod("isGameOver", argsDeclared);
		Object[] argsCalled = {};
		assertEquals("method isGameOver should return True when currentTurnCount is 31", m.invoke(g, argsCalled), true);
	}

	@Test(timeout = 100)
	public void testIsGameOverMethodReturnsFalse() throws Exception {
		Game g = new Game("Mabrouk", "Cairo");
		Field ctc = Game.class.getDeclaredField("currentTurnCount");
		Class[] argsDeclared = {};
		Method m = Game.class.getDeclaredMethod("isGameOver", argsDeclared);
		Object[] argsCalled = {};
		ctc.setAccessible(true);
		ctc.set(g, 1);
		assertEquals("method isGameOver should return False when currentTurnCount is 30 or less",
				m.invoke(g, argsCalled), false);
		ctc.set(g, 30);
		assertEquals("method isGameOver should return False when currentTurnCount is 30 or less",
				m.invoke(g, argsCalled), false);
	}
	
	@Test(timeout = 100)
	public void testOccupyMethodExists() {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Class Game should contain a method named occupy.", containsMethodName(methods, "occupy"));
	}

	@Test(timeout = 100)
	public void testOccupyMethodBehaviour() throws Exception {
		Game g = new Game("Mabrouk", "Rome");

		Field ac = Game.class.getDeclaredField("availableCities");
		ac.setAccessible(true);
		ArrayList<City> availableCities = (ArrayList<City>) ac.get(g);
		Army occupingArmy = null;
		for (City c : availableCities) {
			if (c.getName().equals("Cairo")) {
				occupingArmy = c.getDefendingArmy();
			}
		}
		City occupiedCity = null;
		for (City c : availableCities) {
			if (c.getName().equals("Sparta")) {
				occupiedCity = c;
			}
		}
		occupiedCity.setUnderSiege(true);

		Class[] argsDeclared = { Army.class, String.class };
		Method m = Game.class.getDeclaredMethod("occupy", argsDeclared);
		Object[] argsCalled = { occupingArmy, "Sparta" };
		m.invoke(g, argsCalled);

		Field pp = Game.class.getDeclaredField("player");
		pp.setAccessible(true);
		Player p = (Player) pp.get(g);
		boolean cityOccupied = false;
		for (City c : p.getControlledCities()) {
			if (c.getName().equals("Sparta")) {
				cityOccupied = true;
			}
		}
		assertTrue("An occupied City should be added to the Player's controlledCities", cityOccupied);

		}
	@Test(timeout = 100)
	public void testOccupyMethodBehaviour1() throws Exception {
		Game g = new Game("Mabrouk", "Rome");

		Field ac = Game.class.getDeclaredField("availableCities");
		ac.setAccessible(true);
		ArrayList<City> availableCities = (ArrayList<City>) ac.get(g);
		Army occupingArmy = null;
		for (City c : availableCities) {
			if (c.getName().equals("Cairo")) {
				occupingArmy = c.getDefendingArmy();
			}
		}
		City occupiedCity = null;
		for (City c : availableCities) {
			if (c.getName().equals("Sparta")) {
				occupiedCity = c;
			}
		}
		occupiedCity.setUnderSiege(true);

		Class[] argsDeclared = { Army.class, String.class };
		Method m = Game.class.getDeclaredMethod("occupy", argsDeclared);
		Object[] argsCalled = { occupingArmy, "Sparta" };
		m.invoke(g, argsCalled);

		Field pp = Game.class.getDeclaredField("player");
		pp.setAccessible(true);
		Player p = (Player) pp.get(g);
		boolean cityOccupied = false;
		for (City c : p.getControlledCities()) {
			if (c.getName().equals("Sparta")) {
				cityOccupied = true;
			}
		}
		
		assertTrue("An occupied City should be have the occupying Army as their new defendingArmy",
				occupiedCity.getDefendingArmy().equals(occupingArmy));

			}
	@Test(timeout = 3000)
	public void testPlayerUpgradeBuildingMaxLevelException() throws Exception {
		try {
			Player player = new Player("Player");
			Barracks b = new Barracks();
			playerUpgradeBuildingExceptionHelper(player, b, 5000, 3, false);
			fail("MaxLevelReachedException should be thrown");
		} catch (MaxLevelException e) {

		}
	}
	@Test(timeout = 100)
	public void testOccupyMethodBehaviour2() throws Exception {
		Game g = new Game("Mabrouk", "Rome");

		Field ac = Game.class.getDeclaredField("availableCities");
		ac.setAccessible(true);
		ArrayList<City> availableCities = (ArrayList<City>) ac.get(g);
		Army occupingArmy = null;
		for (City c : availableCities) {
			if (c.getName().equals("Cairo")) {
				occupingArmy = c.getDefendingArmy();
			}
		}
		City occupiedCity = null;
		for (City c : availableCities) {
			if (c.getName().equals("Sparta")) {
				occupiedCity = c;
			}
		}
		occupiedCity.setUnderSiege(true);
		occupiedCity.setTurnsUnderSiege(3);
		Class[] argsDeclared = { Army.class, String.class };
		Method m = Game.class.getDeclaredMethod("occupy", argsDeclared);
		Object[] argsCalled = { occupingArmy, "Sparta" };
		m.invoke(g, argsCalled);

		Field pp = Game.class.getDeclaredField("player");
		pp.setAccessible(true);
		Player p = (Player) pp.get(g);
		boolean cityOccupied = false;
		for (City c : p.getControlledCities()) {
			if (c.getName().equals("Sparta")) {
				cityOccupied = true;
			}
		}
		
		assertFalse("A recently occupied City shouldn't be under siege", occupiedCity.isUnderSiege());
		
		assertEquals("A recently occupied City's under siege value should be -1",-1, occupiedCity.getTurnsUnderSiege());
	}
	
	@Test(timeout = 100)
	public void testAutoResolveMethodExists() {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Class Game should contain a method named autoResolve.", containsMethodName(methods, "autoResolve"));
	}

	@Test(timeout = 1000)
	public void testAutoResolveMethodBehaviour() throws Exception {
		Game g = new Game("Mabrouk", "Rome");
		Field ac = Game.class.getDeclaredField("availableCities");
		ac.setAccessible(true);
		ArrayList<City> availableCities = (ArrayList<City>) ac.get(g);
		Army army1 = null;
		for (City c : availableCities) {
			if (c.getName().equals("Cairo")) {
				army1 = c.getDefendingArmy();
			}
		}
		City city2 = null;
		Army army2 = null;
		for (City c : availableCities) {
			if (c.getName().equals("Sparta")) {
				city2 = c;
				army2 = c.getDefendingArmy();
			}
		}
		
		Class[] argsDeclared = { Army.class, Army.class };
		Method m = Game.class.getDeclaredMethod("autoResolve", argsDeclared);
		Object[] argsCalled = { army1, army2 };
		m.invoke(g, argsCalled);

		if (army1.getUnits().size() == 0) {
			
		} else if (army2.getUnits().size() == 0) {
			Field pp = Game.class.getDeclaredField("player");
			pp.setAccessible(true);
			Player p = (Player) pp.get(g);
			boolean cityOccupied = false;
			for (City c : p.getControlledCities()) {
				if (c.getName().equals(city2.getName())) {
					cityOccupied = true;
				}
			}
			assertTrue("The defending Army lost so the defending City must be occupied",
					cityOccupied && city2.getDefendingArmy().equals(army1));

		} else {
			fail("autoResolve Shouldn't terminate until the Units of one Army are all dead.");
		}
	}

	
	@Test(timeout = 100)
	public void testTargetCityMethodExists() {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Class Game should contain a method named targetCity.", containsMethodName(methods, "targetCity"));
	}
	
	
	@Test(timeout = 100)
	public void testTargetCityLogic2() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city = new City("Paris");
		Army army = new Army("Paris");
		Distance d = new Distance("Berlin", "Paris", 14);
		g.getDistances().add(d);
		g.targetCity(army, "Berlin");
		assertEquals("The Army target should be Rome","Berlin", army.getTarget());
		assertEquals("The Distance to target should be 14",14, army.getDistancetoTarget());
		}
	
	
	@Test(timeout = 100)
	public void testEndTurnMethodExists() {
		Method[] methods = Game.class.getDeclaredMethods();
		assertTrue("Class Game should contain a method named endTurn.", containsMethodName(methods, "endTurn"));
	}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic1() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		g.setCurrentTurnCount(3);
		g.endTurn();
		assertEquals("The current turn count should be incremented", 4,g.getCurrentTurnCount() );
	}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic2() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city1 = new City("Cairo");
		city1.getEconomicalBuildings().add(new Market());
		city1.getEconomicalBuildings().add(new Farm());
		city1.getMilitaryBuildings().add(new Barracks());
		city1.getMilitaryBuildings().add(new Stable());
		city1.getMilitaryBuildings().add(new ArcheryRange());
		City city2 = new City("Cairo");
		city2.getEconomicalBuildings().add(new Market());
		city2.getMilitaryBuildings().add(new Barracks());
		city2.getMilitaryBuildings().add(new ArcheryRange());
		g.getPlayer().getControlledCities().add(city1);
		g.getPlayer().getControlledCities().add(city2);
		g.endTurn();
		for(City c:g.getPlayer().getControlledCities()){
			for(MilitaryBuilding b:c.getMilitaryBuildings()){
				assertEquals("The cooldown of all buildings should be false",false, b.isCoolDown());
			}
			for(EconomicBuilding b:c.getEconomicalBuildings()){
				assertEquals("The cooldown of all buildings should be false",false, b.isCoolDown());
			}
		}
		}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic3() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city1 = new City("Cairo");
		city1.getMilitaryBuildings().add(new Barracks());
		city1.getMilitaryBuildings().add(new Stable());
		city1.getMilitaryBuildings().add(new ArcheryRange());
		City city2 = new City("Cairo");
		city2.getMilitaryBuildings().add(new Barracks());
		city2.getMilitaryBuildings().add(new ArcheryRange());
		g.getPlayer().getControlledCities().add(city1);
		g.getPlayer().getControlledCities().add(city2);
		for(City c:g.getPlayer().getControlledCities()){
			for(MilitaryBuilding b:c.getMilitaryBuildings()){
				b.setCurrentRecruit(2);
			}
			
		}
		g.endTurn();
		for(City c:g.getPlayer().getControlledCities()){
			for(MilitaryBuilding b:c.getMilitaryBuildings()){
				assertEquals("The current recruit value  of all buildings should be returned to 0",0, b.getCurrentRecruit());
			}
			
		}
		}
	
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic4() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city1 = new City("Cairo");
		city1.getEconomicalBuildings().add(new Market());
		city1.getEconomicalBuildings().add(new Farm());
		City city2 = new City("Cairo");
		city2.getEconomicalBuildings().add(new Market());
		g.getPlayer().getControlledCities().add(city1);
		g.getPlayer().getControlledCities().add(city2);
		g.getPlayer().setFood(1000);
		g.getPlayer().setTreasury(1000);
		
		g.endTurn();
		assertTrue("The Food value should be incremented correctly after end turn method expected 1500 but was " +g.getPlayer().getFood() +"",1500 == g.getPlayer().getFood());
		assertTrue("The Gold value should be incremented correctly after end turn method expected 3000 but was " +g.getPlayer().getTreasury() +"",3000 == g.getPlayer().getTreasury());
		city1.getEconomicalBuildings().get(0).setLevel(2);
		city1.getEconomicalBuildings().get(1).setLevel(2);
		city2.getEconomicalBuildings().get(0).setLevel(3);
		g.endTurn();
		assertTrue("The Food value should be incremented correctly after end turn method expected 2200 but was " +g.getPlayer().getFood() +"",2200 == g.getPlayer().getFood());
		assertTrue("The Gold value should be incremented correctly after end turn method expected 6500 but was " +g.getPlayer().getTreasury() +"",6500 == g.getPlayer().getTreasury());
		
		}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic5() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		Army army1 = new Army("Cairo");
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		army1.getUnits().add(archerUnit1); 
		army1.getUnits().add(archerUnit1); 
		g.getPlayer().getControlledArmies().add(army1); 
		g.getPlayer().setFood(50);
		g.endTurn(); 
		assertTrue("The player's food value should be updated after calling end turn expected 2 but was "+ g.getPlayer().getFood()+"",g.getPlayer().getFood() == 2);
		Army army2 = new Army("Cairo");
		Archer archerUnit2 = new Archer(1, 60, 0.4, 0.5, 0.6);
		army2.getUnits().add(archerUnit2); 
		army2.getUnits().add(archerUnit2); 
		army2.setCurrentStatus(Status.MARCHING);
		g.getPlayer().getControlledArmies().add(army2); 
		g.getPlayer().setFood(120);
		g.endTurn();
		assertTrue("The player's food value should be updated after calling end turn expected 12 but was "+ g.getPlayer().getFood()+"",g.getPlayer().getFood() == 12);
		}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic6() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		Army army1 = new Army("Cairo");
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit2 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit3 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit4 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit5 = new Archer(1, 60, 0.4, 0.5, 0.6);
		army1.getUnits().add(archerUnit1); 
		army1.getUnits().add(archerUnit2); 
		army1.getUnits().add(archerUnit3); 
		army1.getUnits().add(archerUnit4); 
		army1.getUnits().add(archerUnit5); 
		g.getPlayer().getControlledArmies().add(army1); 
		g.getPlayer().setFood(20);
		g.endTurn(); 
		assertTrue("The player's food value should be updated after calling end turn expected 0 but was "+ g.getPlayer().getFood()+"",g.getPlayer().getFood() == 0);
		for(Army a:g.getPlayer().getControlledArmies())
		{
			for(Unit u:a.getUnits())
			{
				assertEquals("If the player's food reaches zero, all units should lose 10% ",54,u.getCurrentSoldierCount());
			}
		}
	}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic7() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		Army army1 = new Army("Cairo");
		army1.setCurrentLocation("onRoad");
		army1.setDistancetoTarget(10);
		g.getPlayer().getControlledArmies().add(army1); 
		g.endTurn(); 
		assertEquals("The Distance to target of all armies shouldn't be change if the army doesn't have a target",10,army1.getDistancetoTarget());
		army1.setTarget("Rome");
		army1.setCurrentLocation("onRoad");
		army1.setCurrentStatus(Status.MARCHING);
		army1.setDistancetoTarget(10);
		g.endTurn(); 
		
		assertEquals("The Distance to target of all armies should be updated correctly ",9,army1.getDistancetoTarget());
		army1.setDistancetoTarget(1); 
		g.endTurn();
		assertEquals("The current location should be updated if the army reaches its target ","Rome",army1.getCurrentLocation());
		assertEquals("The status should be updated if the army reaches its target ",Status.IDLE,army1.getCurrentStatus());
		assertEquals("The targe value should be empty if the army reaches its target ","",army1.getTarget());
		
	}
	@Test(timeout = 1000)
	public void testEndTurnMethodLogic8() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city1 = new City("Cairo");
		City city2 = new City("Cairo");
		Army army1 = new Army("Cairo");
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit2 = new Archer(1, 60, 0.4, 0.5, 0.6);
		army1.getUnits().add(archerUnit1);
		army1.getUnits().add(archerUnit2);
		Archer archerUnit3 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Archer archerUnit4 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Army army2 = new Army("Cairo");
		army2.getUnits().add(archerUnit3);
		army2.getUnits().add(archerUnit4);
		g.getAvailableCities().add(city1);
		g.getAvailableCities().add(city2);
		city1.setUnderSiege(true);
		city1.setTurnsUnderSiege(1);
		city1.setDefendingArmy(army1);
		g.endTurn();
		assertEquals("If a city is under siege, you should increment the underSiege value",2,city1.getTurnsUnderSiege());
		for(Unit u:army1.getUnits())
		{
			assertEquals("If the city is under siege, all army units should lose 10% ",54,u.getCurrentSoldierCount());
		}
		city2.setTurnsUnderSiege(3);
		g.endTurn();
		assertEquals("The city under siege value cannot exceed 3",3,city2.getTurnsUnderSiege());
		assertEquals("If a city under siege count reaches 3, you should set the underSiege value to false",false,city2.isUnderSiege());
		for(Unit u:army2.getUnits())
		{
			assertEquals("If the city under siege value reaches 3, you shouldn't decrement from it's unit ",60,u.getCurrentSoldierCount());
		}
		}

	

	@Test(timeout = 100)
	public void testInstanceVariableUnitParentArmy() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "parentArmy",
				true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "parentArmy");
		testInstanceVariableOfType(Class.forName(unitPath),
				"defendingArmy", Class.forName(armyPath));
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitParentArmyGetter()
			throws Exception {
		testGetterMethodExistsInClass(Class.forName(unitPath),
				"getParentArmy", Class.forName(armyPath), true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitParentArmySetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setParentArmy",
				 Class.forName(armyPath), true);
	}
	@Test(timeout = 1000)
	public void testConstructorInfantryConstructorInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(infantryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		Object myObj = constructor.newInstance(random1, random2, random3, random4, random5);
		String[] names = { "level", "maxSoldierCount", "currentSoldierCount", "idleUpkeep", "marchingUpkeep", "siegeUpkeep" };
		Object[] values = { random1, random2, random2, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testConstructorArcherConstructorInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		Object myObj = constructor.newInstance(random1, random2, random3, random4, random5);
		String[] names = { "level", "maxSoldierCount", "currentSoldierCount", "idleUpkeep", "marchingUpkeep", "siegeUpkeep" };
		Object[] values = { random1, random2, random2, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testConstructorCavalryConstructorInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(cavalryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		Object myObj = constructor.newInstance(random1, random2, random3, random4, random5);
		String[] names = { "level", "maxSoldierCount", "currentSoldierCount", "idleUpkeep", "marchingUpkeep", "siegeUpkeep" };
		Object[] values = { random1, random2, random2, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 3000)
	public void testRelocateUnitArmy() throws Exception {
		testExistsInClass(Army.class, "relocateUnit", true, void.class, Unit.class);

	}

	@Test(timeout = 3000)
	public void testRelocateUnitArmyLogic() throws Exception {
		int random = (int) (Math.random() * 10);
		Army army = new Army("Cairo" + random);

		Infantry infantry = new Infantry(1, 50, 0.5, 0.6, 0.7);
		infantry.setParentArmy(new Army("Test"));
		Army old_army = infantry.getParentArmy();
		army.relocateUnit(infantry);
		assertTrue("Relocate unit should add the unit to the arraylist correctly",army.getUnits().contains(infantry));
		Army new_army = infantry.getParentArmy();
		assertTrue("The Parent army of the relocated unit should be changed to the corresponding army",new_army.equals(army));
		
	
				
	}

	@Test(timeout = 3000, expected = MaxCapacityException.class)
	public void testRelocateUnitArmyMaxCapacityReachedException() throws Throwable {

		int random = (int) (Math.random() * 10);
		Army army = new Army("Cairo" + random);

		for (int i = 0; i < army.getMaxToHold(); i++) {
			Archer archer = new Archer(1, 60, 0.4, 0.5, 0.6);
			army.getUnits().add(archer);

		}

		Method relocateUnit = Army.class.getDeclaredMethod("relocateUnit", Unit.class);

		try {
			
			Archer archerunit1 = new Archer(2, 60, 0.4, 0.5, 0.6);
			archerunit1.setParentArmy(army);
			relocateUnit.invoke(army,archerunit1 );
			

		} catch (InvocationTargetException ite) {
			if (ite.getCause() instanceof MaxCapacityException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}
	}
	
	@Test(timeout = 3000)
	public void testHandleAttackedUnit() throws Exception {
		testExistsInClass(Army.class, "handleAttackedUnit", true, void.class, Unit.class);

	}
	@Test(timeout = 3000)
	public void testHandleAttackedUnitLogic() throws Exception {
		int random = (int) (Math.random() * 10);
		Army army = new Army("Cairo" + random);
		Archer archer = new Archer(2, 60, 0.4, 0.5, 0.6);
		army.getUnits().add(archer);
		army.handleAttackedUnit(archer);
		assertTrue("Handle attacked unit shouldn't remove the unit from the army if its soldier's count greater than 0",army.getUnits().contains(archer));
		archer.setCurrentSoldierCount(0);
		army.handleAttackedUnit(archer);
		assertFalse("Handle attacked unit should remove the unit from the army if its soldier's count reaches 0",army.getUnits().contains(archer));
		
			
		
				
	}
	@Test(timeout = 100)
	public void testConstructorCityConstructorInitialization()
			throws Exception {
		City city = new City("Test_City");
		
		if(city.getDefendingArmy().equals(null))
			fail("The defending army shouldn't be null");
		
		assertEquals("The defending army location should be inbside the city",city.getName(),city.getDefendingArmy().getCurrentLocation());
		
	}
	
	@Test(timeout = 3000)
	public void testArcherAttackLevel1() throws Exception {
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		unitAttack(archerUnit1, 0.3, 0.2, 0.1);
		
	}
	
	@Test(timeout = 3000)
	public void testCavalryAttackLevel1() throws Exception {
		Cavalry cavalryUnit1 = new Cavalry(1, 40, 0.6, 0.7, 0.75);
		
		unitAttack(cavalryUnit1, 0.5, 0.3, 0.2);
		}
	
	@Test(timeout = 3000)
	public void testInfantryAttackLevel1() throws Exception {
		Infantry infantryUnit1 = new Infantry(1, 50, 0.5, 0.6, 0.7);
		
		unitAttack(infantryUnit1, 0.3, 0.1, 0.1);
		
	}
	
	
	@Test(timeout = 3000, expected = FriendlyFireException.class)
	public void testArcherFriendlyFire() throws Throwable{
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Infantry infantryUnit1 = new Infantry(1, 50, 0.5, 0.6, 0.7);
		Army army = new Army("Cairo");
		army.getUnits().add(infantryUnit1);
		army.getUnits().add(archerUnit1);
		archerUnit1.setParentArmy(army);
		infantryUnit1.setParentArmy(army);
		
		try{
		archerUnit1.attack(infantryUnit1);
		fail("Attack should throw friendly fire exception if a unit is attacking a friendly unit");
		}
		catch (Exception ite) {
			if (ite.getCause() instanceof FriendlyFireException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}
	}
	
	
	
	@Test(timeout = 3000, expected = FriendlyFireException.class)
	public void testFriendlyFireExceptionInSameUnit() throws Throwable {
		int random = (int) (Math.random() * 10);
		Player player = new Player("Player_" + random);

		random = (int) (Math.random() * 10);
		Army army1 = new Army("City_" + random);
		Archer archerUnit = new Archer(1, 60, 0.4, 0.5, 0.6);
		Cavalry cavalryUnit = new Cavalry(1, 40, 0.6, 0.7, 0.75);
		Infantry infantryUnit = new Infantry(1, 50, 0.5, 0.6, 0.7);
		archerUnit.setParentArmy(army1);
		infantryUnit.setParentArmy(army1);
		cavalryUnit.setParentArmy(army1);
		army1.getUnits().add(archerUnit);
		army1.getUnits().add(cavalryUnit);
		army1.getUnits().add(infantryUnit);
		player.getControlledArmies().add(army1);
		Unit[] units = { archerUnit, cavalryUnit, infantryUnit };
		random = (int) (Math.random() * 3);

		try {
			Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
			m.invoke(units[random], units[random]);
		} catch (InvocationTargetException ite) {
			if (ite.getCause() instanceof FriendlyFireException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}

	}

	@Test(timeout = 3000, expected = FriendlyFireException.class)
	public void testFriendlyFireExceptionInSameArmy() throws Throwable {
		int random = (int) (Math.random() * 10);
		Player player = new Player("Player_" + random);

		random = (int) (Math.random() * 10);
		Army army1 = new Army("City_" + random);
		Archer archerUnit = new Archer(1, 60, 0.4, 0.5, 0.6);
		Cavalry cavalryUnit = new Cavalry(1, 40, 0.6, 0.7, 0.75);
		Infantry infantryUnit = new Infantry(1, 50, 0.5, 0.6, 0.7);
		archerUnit.setParentArmy(army1);
		infantryUnit.setParentArmy(army1);
		cavalryUnit.setParentArmy(army1);
		army1.getUnits().add(archerUnit);
		army1.getUnits().add(cavalryUnit);
		army1.getUnits().add(infantryUnit);
		player.getControlledArmies().add(army1);
		Unit[] units = { archerUnit, cavalryUnit, infantryUnit };
		int random1 = (int) (Math.random() * 3);
		int random2 = (int) (Math.random() * 3);
		while (random1 == random2)
			random2 = (int) (Math.random() * 3);

		try {
			Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
			m.invoke(units[random1], units[random2]);
		} catch (InvocationTargetException ite) {
			if (ite.getCause() instanceof FriendlyFireException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}

	}

	@Test(timeout = 3000)
	public void testArmyFoodNeeded() throws Exception {
		testExistsInClass(Army.class, "foodNeeded", true, double.class);

	}
	
	@Test(timeout = 3000)
	public void testArmyFoodNeededLogic() throws Exception{
		Army army = new Army("Cairo");
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		army.getUnits().add(archerUnit1);
		army.setCurrentStatus(Status.IDLE);
		assertTrue("The foodNeeded logic is incorrect excpected 24 but was "+army.foodNeeded(),24==army.foodNeeded());
		army.getUnits().add(archerUnit1);
		assertTrue("The foodNeeded logic is incorrect excpected 48 but was "+army.foodNeeded(),48==army.foodNeeded());
		army.setCurrentStatus(Status.MARCHING);
		assertTrue("The foodNeeded logic is incorrect excpected 60 but was "+army.foodNeeded(),60==army.foodNeeded());
		army.getUnits().add(archerUnit1);
		assertTrue("The foodNeeded logic is incorrect excpected 90 but was "+army.foodNeeded(),90==army.foodNeeded());
		
		army.setCurrentStatus(Status.BESIEGING);
		assertTrue("The foodNeeded logic is incorrect excpected 108 but was "+army.foodNeeded(),108==army.foodNeeded());
		army.getUnits().add(archerUnit1);
		assertTrue("The foodNeeded logic is incorrect excpected 144 but was "+army.foodNeeded(),144==army.foodNeeded());
		
	}
	@Test(timeout = 3000)
	public void testBuildingUpgrade() throws Exception {
	testExistsInClass(Building.class, "upgrade", true, void.class);
	}
	
	@Test(timeout = 3000)
	public void testMarketUpgrade() throws Exception {
	testExistsInClass(Market.class, "upgrade", true, void.class);
	}
	
	
	
	
	@Test(timeout = 1000)
	public void testMarketUpgradeLogic() throws Exception {
	
	Market m = new Market();
	m.setCoolDown(false);
	try {
	
	Method upgrade = Market.class.getDeclaredMethod("upgrade");
	
	upgrade.invoke(m);
	
	assertTrue("Value of level is wrong, expected " + 2 + " but was " + m.getLevel() + ".", 2 == m.getLevel());
	assertTrue("Value of upgrade cost is wrong, expected " + 1000 + " but was " + m.getUpgradeCost() + ".",
		1000 == m.getUpgradeCost());
	assertTrue("Value of coolDown is wrong,expected True but was false",m.isCoolDown());
	m.setCoolDown(false);
	upgrade.invoke(m);
	assertTrue("Value of level is wrong, expected " + 3 + " but was " + m.getLevel() + ".", 3 == m.getLevel());
	
	} catch (NoSuchMethodException e) {
	fail("Market class should have upgrade method");
	}
	
	}
	
	
	
	@Test(timeout = 3000, expected = BuildingInCoolDownException.class)
	public void testUpgradeBuildingInCoolDownException() throws Throwable {
	
	Market m = new Market();
	Method upgrade = Market.class.getDeclaredMethod("upgrade");
	
	try {
	upgrade.invoke(m);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof BuildingInCoolDownException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	
	}
	
	
	
	@Test(timeout = 3000, expected = MaxLevelException.class)
	public void testUpgradeBuildingMaxLevelException() throws Throwable {
	
	Market m = new Market();
	m.setLevel(3);
	m.setCoolDown(false);
	
	Method upgrade = Market.class.getDeclaredMethod("upgrade");
	
	try {
	upgrade.invoke(m);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof MaxLevelException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	
	}
	
	@Test(timeout = 1000)
	public void testArcheryRangeUpgradeLogic() throws Exception {
	
	ArcheryRange a = new ArcheryRange();
	a.setCoolDown(false);
	
	try {
	
	Method upgrade = ArcheryRange.class.getDeclaredMethod("upgrade");
	
	upgrade.invoke(a);
	
	assertTrue("Value of level is wrong, expected " + 2 + " but was " + a.getLevel() + ".", 2 == a.getLevel());
	assertTrue("Value of upgrade cost is wrong, expected " + 700 + " but was " + a.getUpgradeCost() + ".",
		700 == a.getUpgradeCost());
	
	assertTrue(
		"Value of recruitment cost is wrong, expected " + 450 + " but was " + a.getRecruitmentCost() + ".",
		450 == a.getRecruitmentCost());
	assertTrue("Value of coolDown is wrong,expected True but was false",a.isCoolDown());
	a.setCoolDown(false);
	upgrade.invoke(a);
	
	assertTrue("Value of level is wrong, expected " + 3 + " but was " + a.getLevel() + ".", 3 == a.getLevel());
	assertTrue(
		"Value of recruitment cost is wrong, expected " + 500 + " but was " + a.getRecruitmentCost() + ".",
		500 == a.getRecruitmentCost());
	
	} catch (NoSuchMethodException e) {
	fail("ArcheryRange class should have upgrade method");
	}
	
	}
	
	
	
	
	
	
	@Test(timeout = 3000)
	public void testEconomicalBuildingHarvestIsAbstract() throws Exception {
	Method harvest = EconomicBuilding.class.getDeclaredMethod("harvest");
	assertTrue("harvest method should be abstract in the class EconomicalBuilding",
	Modifier.toString(harvest.getModifiers()).contains("abstract"));
	
	}
	
	
	
	@Test(timeout = 3000)
	public void testFarmHarvest() throws Exception {
	testExistsInClass(Farm.class, "harvest", true, int.class);
	}
	
	@Test(timeout = 1000)
	public void testMarketHarvestLogic() throws Exception {
	
	Market m = new Market();
	m.setCoolDown(false);
	
	try {
	
	Method harvest = Market.class.getDeclaredMethod("harvest");
	
	
	int harvestLevel1 = (Integer) harvest.invoke(m);
	
	assertTrue("Value of harvest is wrong, expected " + 1000 + " but was " + harvestLevel1 + ".",
		1000 == harvestLevel1);
	
	
	m.setLevel(2);
	int harvestLevel2 = (Integer) harvest.invoke(m);
	
	assertTrue("Value of harvest is wrong, expected " + 1500 + " but was " + harvestLevel2 + ".",
		1500 == harvestLevel2);
	
	
	m.setLevel(3);
	int harvestLevel3 = (Integer) harvest.invoke(m);
	
	assertTrue("Value of harvest is wrong, expected " + 2000 + " but was " + harvestLevel3 + ".",
		2000 == harvestLevel3);
	
	} catch (NoSuchMethodException e) {
	fail("Market class should have harvest method");
	}
	
	}
	
	
	
	@Test(timeout = 3000)
	public void testRecruitMilitaryBuilding() throws Exception {
	testExistsInClass(MilitaryBuilding.class, "recruit", true, Unit.class);
	
	}
	
	@Test(timeout = 3000)
	public void testRecruitMilitaryBuildingIsAbstract() throws Exception {
	Method recruit = MilitaryBuilding.class.getDeclaredMethod("recruit");
	assertTrue("recruit method should be abstract in the class MilitaryBuilding",
	Modifier.toString(recruit.getModifiers()).contains("abstract"));
	
	}
	
	@Test(timeout = 3000)
	public void testRecruitArcheryRange() throws Exception {
	testExistsInClass(ArcheryRange.class, "recruit", true, Unit.class);
	}
	
	@Test(timeout = 3000)
	public void testRecruitBarracks() throws Exception {
	testExistsInClass(Barracks.class, "recruit", true, Unit.class);
	}
	
	@Test(timeout = 3000)
	public void testRecruitStable() throws Exception {
	testExistsInClass(Stable.class, "recruit", true, Unit.class);
	}
	
	@Test(timeout = 3000)
	public void testRecruitArcheryRangeLogic() throws Exception {
	
	ArcheryRange archeryRange = new ArcheryRange();
	archeryRange.setCoolDown(false);
	
	Method recruit = ArcheryRange.class.getDeclaredMethod("recruit");
	
	
	Unit archerLevel1 = (Unit) recruit.invoke(archeryRange);
	
	
	assertTrue("Type of unit is wrong, expected " + "Archer" + " but was " + archerLevel1.getClass() + ".",
	archerLevel1 instanceof Archer);
	
	assertTrue("level of unit is wrong, expected " + 1 + " but was " + archerLevel1.getLevel() + ".",
	1 == archerLevel1.getLevel());
	
	assertTrue("maxSoldierCount of archer is wrong, expected " + 60 + " but was "
	+ archerLevel1.getMaxSoldierCount() + ".", 60 == archerLevel1.getMaxSoldierCount());
	
	assertTrue("idleUpKeep of archer is wrong, expected " + 0.4 + " but was " + archerLevel1.getIdleUpkeep() + ".",
	0.4 == archerLevel1.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of archer is wrong, expected " + 0.5 + " but was " + archerLevel1.getMarchingUpkeep()
	+ ".", 0.5 == archerLevel1.getMarchingUpkeep());
	
	assertTrue(
	"siegeUpkeep of archer is wrong, expected " + 0.6 + " but was " + archerLevel1.getSiegeUpkeep() + ".",
	0.6 == archerLevel1.getSiegeUpkeep());
	
	assertFalse("Recruit unit shouldn't set the cool down value to true",archeryRange.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 1, archeryRange.getCurrentRecruit());
	archeryRange.setLevel(2);
	archeryRange.setCoolDown(false);
	Unit archerLevel2 = (Unit) recruit.invoke(archeryRange);
	
	assertTrue("Type of unit is wrong, expected " + "Archer" + " but was " + archerLevel2.getClass() + ".",
	archerLevel2 instanceof Archer);
	
	assertTrue("level of unit is wrong, expected " + 2 + " but was " + archerLevel2.getLevel() + ".",
	2 == archerLevel2.getLevel());
	
	assertTrue("maxSoldierCount of archer is wrong, expected " + 60 + " but was "
	+ archerLevel1.getMaxSoldierCount() + ".", 60 == archerLevel2.getMaxSoldierCount());
	
	assertTrue("idleUpKeep of archer is wrong, expected " + 0.4 + " but was " + archerLevel1.getIdleUpkeep() + ".",
	0.4 == archerLevel2.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of archer is wrong, expected " + 0.5 + " but was " + archerLevel1.getMarchingUpkeep()
	+ ".", 0.5 == archerLevel2.getMarchingUpkeep());
	
	assertTrue(
	"siegeUpkeep of archer is wrong, expected " + 0.6 + " but was " + archerLevel1.getSiegeUpkeep() + ".",
	0.6 == archerLevel2.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",archeryRange.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 2, archeryRange.getCurrentRecruit());
	
	archeryRange.setLevel(3);
	archeryRange.setCoolDown(false);
	
	Unit archerLevel3 = (Unit) recruit.invoke(archeryRange);
	
	assertTrue("Type of unit is wrong, expected " + "Archer" + " but was " + archerLevel3.getClass() + ".",
	archerLevel3 instanceof Archer);
	
	assertTrue("level of unit is wrong, expected " + 3 + " but was " + archerLevel3.getLevel() + ".",
	3 == archerLevel3.getLevel());
	
	assertTrue("maxSoldierCount of archer is wrong, expected " + 70 + " but was "
	+ archerLevel1.getMaxSoldierCount() + ".", 70 == archerLevel3.getMaxSoldierCount());
	
	assertTrue("idleUpKeep of archer is wrong, expected " + 0.5 + " but was " + archerLevel1.getIdleUpkeep() + ".",
	0.5 == archerLevel3.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of archer is wrong, expected " + 0.6 + " but was " + archerLevel3.getMarchingUpkeep()
	+ ".", 0.6 == archerLevel3.getMarchingUpkeep());
	
	assertTrue(
	"siegeUpkeep of archer is wrong, expected " + 0.7 + " but was " + archerLevel1.getSiegeUpkeep() + ".",
	0.7 == archerLevel3.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",archeryRange.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 3, archeryRange.getCurrentRecruit());
	
	}
	
	@Test(timeout = 3000)
	public void testRecruitBarracksLogic() throws Exception {
	
	Barracks barracks = new Barracks();
	barracks.setCoolDown(false);
	
	Method recruit = Barracks.class.getDeclaredMethod("recruit");
	
	Unit infantryLevel1 = (Unit) recruit.invoke(barracks);
	
	assertTrue("Type of unit is wrong, expected " + "Infantry" + " but was " + infantryLevel1.getClass() + ".",
	infantryLevel1 instanceof Infantry);
	
	assertTrue("level of unit is wrong, expected " + 1 + " but was " + infantryLevel1.getLevel() + ".",
	1 == infantryLevel1.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 50 + " but was "
	+ infantryLevel1.getMaxSoldierCount() + ".", 50 == infantryLevel1.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.5 + " but was " + infantryLevel1.getIdleUpkeep() + ".",
	0.5 == infantryLevel1.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.6 + " but was "
	+ infantryLevel1.getMarchingUpkeep() + ".", 0.6 == infantryLevel1.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.7 + " but was " + infantryLevel1.getSiegeUpkeep()
	+ ".", 0.7 == infantryLevel1.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",barracks.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 1, barracks.getCurrentRecruit());
	
	
	
	barracks.setLevel(2);
	barracks.setCoolDown(false);
	Unit infantryLevel2 = (Unit) recruit.invoke(barracks);
	
	assertTrue("Type of unit is wrong, expected " + "Infantry" + " but was " + infantryLevel2.getClass() + ".",
	infantryLevel2 instanceof Infantry);
	
	assertTrue("level of unit is wrong, expected " + 2 + " but was " + infantryLevel2.getLevel() + ".",
	2 == infantryLevel2.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 50 + " but was "
	+ infantryLevel2.getMaxSoldierCount() + ".", 50 == infantryLevel2.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.5 + " but was " + infantryLevel2.getIdleUpkeep() + ".",
	0.5 == infantryLevel2.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.6 + " but was "
	+ infantryLevel2.getMarchingUpkeep() + ".", 0.6 == infantryLevel2.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.7 + " but was " + infantryLevel2.getSiegeUpkeep()
	+ ".", 0.7 == infantryLevel2.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",barracks.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 2, barracks.getCurrentRecruit());
	
	
	
	barracks.setLevel(3);
	barracks.setCoolDown(false);
	Unit infantryLevel3 = (Unit) recruit.invoke(barracks);
	
	assertTrue("Type of unit is wrong, expected " + "Infantry" + " but was " + infantryLevel3.getClass() + ".",
	infantryLevel3 instanceof Infantry);
	
	assertTrue("level of unit is wrong, expected " + 3 + " but was " + infantryLevel3.getLevel() + ".",
	3 == infantryLevel3.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 60 + " but was "
	+ infantryLevel3.getMaxSoldierCount() + ".", 60 == infantryLevel3.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.6 + " but was " + infantryLevel3.getIdleUpkeep() + ".",
	0.6 == infantryLevel3.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.7 + " but was "
	+ infantryLevel3.getMarchingUpkeep() + ".", 0.7 == infantryLevel3.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.8 + " but was " + infantryLevel3.getSiegeUpkeep()
	+ ".", 0.8 == infantryLevel3.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",barracks.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 3, barracks.getCurrentRecruit());
	
	
	
	}
	
	@Test(timeout = 3000)
	public void testRecruitStableLogic() throws Exception {
	
	Stable stable = new Stable();
	stable.setCoolDown(false);
	
	Method recruit = Stable.class.getDeclaredMethod("recruit");
	
	Unit cavalryLevel1 = (Unit) recruit.invoke(stable);
	
	assertTrue("Type of unit is wrong, expected " + "Cavalry" + " but was " + cavalryLevel1.getClass() + ".",
	cavalryLevel1 instanceof Cavalry);
	
	assertTrue("level of unit is wrong, expected " + 1 + " but was " + cavalryLevel1.getLevel() + ".",
	1 == cavalryLevel1.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 40 + " but was "
	+ cavalryLevel1.getMaxSoldierCount() + ".", 40 == cavalryLevel1.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.6 + " but was " + cavalryLevel1.getIdleUpkeep() + ".",
	0.6 == cavalryLevel1.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.7 + " but was "
	+ cavalryLevel1.getMarchingUpkeep() + ".", 0.7 == cavalryLevel1.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.75 + " but was " + cavalryLevel1.getSiegeUpkeep()
	+ ".", 0.75 == cavalryLevel1.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",stable.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 1, stable.getCurrentRecruit());
	
	
	
	stable.setLevel(2);
	stable.setCoolDown(false);
	Unit cavalryLevel2 = (Unit) recruit.invoke(stable);
	
	assertTrue("Type of unit is wrong, expected " + "Cavalry" + " but was " + cavalryLevel2.getClass() + ".",
	cavalryLevel2 instanceof Cavalry);
	
	assertTrue("level of unit is wrong, expected " + 2 + " but was " + cavalryLevel2.getLevel() + ".",
	2 == cavalryLevel2.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 40 + " but was "
	+ cavalryLevel2.getMaxSoldierCount() + ".", 40 == cavalryLevel2.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.6 + " but was " + cavalryLevel2.getIdleUpkeep() + ".",
	0.6 == cavalryLevel2.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.7 + " but was "
	+ cavalryLevel2.getMarchingUpkeep() + ".", 0.7 == cavalryLevel2.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.75 + " but was " + cavalryLevel2.getSiegeUpkeep()
	+ ".", 0.75 == cavalryLevel2.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",stable.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 2, stable.getCurrentRecruit());
	
	
	stable.setLevel(3);
	stable.setCoolDown(false);
	Unit cavalryLevel3 = (Unit) recruit.invoke(stable);
	
	assertTrue("Type of unit is wrong, expected " + "Cavalry" + " but was " + cavalryLevel3.getClass() + ".",
	cavalryLevel3 instanceof Cavalry);
	
	assertTrue("level of unit is wrong, expected " + 3 + " but was " + cavalryLevel3.getLevel() + ".",
	3 == cavalryLevel3.getLevel());
	
	assertTrue("maxSoldierCount of infantry is wrong, expected " + 60 + " but was "
	+ cavalryLevel3.getMaxSoldierCount() + ".", 60 == cavalryLevel3.getMaxSoldierCount());
	
	assertTrue(
	"idleUpKeep of infantry is wrong, expected " + 0.7 + " but was " + cavalryLevel3.getIdleUpkeep() + ".",
	0.7 == cavalryLevel3.getIdleUpkeep());
	
	assertTrue("marchingUpKeep of infantry is wrong, expected " + 0.8 + " but was "
	+ cavalryLevel3.getMarchingUpkeep() + ".", 0.8 == cavalryLevel3.getMarchingUpkeep());
	
	assertTrue("siegeUpkeep of infantry is wrong, expected " + 0.9 + " but was " + cavalryLevel3.getSiegeUpkeep()
	+ ".", 0.9 == cavalryLevel3.getSiegeUpkeep());
	assertFalse("Recruit unit should set the cool down value to true",stable.isCoolDown());
	assertEquals("Recruit unit should increment the current recruit variable correctly", 3, stable.getCurrentRecruit());
	
	
	}
	
	@Test(timeout = 3000, expected = BuildingInCoolDownException.class)
	public void testRecruitArcheryRangeInCooldownException() throws Throwable {
	ArcheryRange archeryRange = new ArcheryRange();
	archeryRange.setCoolDown(true);
	Method recruit = ArcheryRange.class.getDeclaredMethod("recruit");
	try {
	recruit.invoke(archeryRange);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof BuildingInCoolDownException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	
	}
	
	@Test(timeout = 3000, expected = MaxRecruitedException.class)
	public void testRecruitArcheryRangeMaxRecruitedException() throws Throwable {
	ArcheryRange archeryRange = new ArcheryRange();
	archeryRange.setCoolDown(false);
	archeryRange.setCurrentRecruit(3);
	
	Method recruit = ArcheryRange.class.getDeclaredMethod("recruit");
	try {
	recruit.invoke(archeryRange);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof MaxRecruitedException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	
	}
	
	@Test(timeout = 3000, expected = BuildingInCoolDownException.class)
	public void testRecruitBarracksInCooldownException() throws Throwable {
	Barracks barracks = new Barracks();
	barracks.setCoolDown(true);
	
	Method recruit = Barracks.class.getDeclaredMethod("recruit");
	try {
	recruit.invoke(barracks);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof BuildingInCoolDownException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	
	}
	
	@Test(timeout = 3000, expected = MaxRecruitedException.class)
	public void testRecruitBarracksMaxRecruitedException() throws Throwable {
	Barracks barracks = new Barracks();
	barracks.setCoolDown(false);
	barracks.setCurrentRecruit(3);
	
	Method recruit = Barracks.class.getDeclaredMethod("recruit");
	try {
	recruit.invoke(barracks);
	
	} catch (InvocationTargetException ite) {
	if (ite.getCause() instanceof MaxRecruitedException) {
	throw ite.getCause();
	} else {
	throw ite;
	}
	}
	}
	
	



@Test(timeout = 3000)
public void testPlayerRecruitUnit() throws Exception {
	testExistsInClass(Player.class, "recruitUnit", true, void.class, String.class, String.class);
}


@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOriginalCityWithEnoughGold() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	playerRecruitUnitWithEnoughGoldHelper(game, "Archer", "Cairo");
	game.getPlayer().setTreasury(5000);
	playerRecruitUnitWithEnoughGoldHelper(game, "Infantry", "Cairo");
	game.getPlayer().setTreasury(5000);
	playerRecruitUnitWithEnoughGoldHelper(game, "Cavalry", "Cairo");
}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOriginalCityWithoutEnoughGoldException() throws Exception {
	try {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(0);
		playerRecruitUnitExceptionHelper(game, "Archer", "Cairo", false, 0);
		fail("NotEnoughGoldException should be thrown.");
	} catch (NotEnoughGoldException e) {

	}

}


@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOriginalCityWithoutEnoughGoldLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(0);
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Archer", "Cairo", 0);
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Infantry", "Cairo", 0);
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Cavalry", "Cairo", 0);
}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOriginalCityInCoolDownLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	City c = getCityWithName(game, "Cairo");
	playerRecruitUnitInCoolDownLogicHelper(game, "Archer", "Cairo", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitInCoolDownLogicHelper(game, "Infantry", "Cairo", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitInCoolDownLogicHelper(game, "Cavalry", "Cairo", c.getDefendingArmy().getUnits().size());
}




@Test(timeout = 3000)
public void testRecruitUnitInfantryPlayerOriginalCityInCoolDownException() throws Exception {
	try {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitExceptionHelper(game, "Infantry", "Cairo", true, 0);
		fail("BuildingInCoolDownException should be thrown.");
	} catch (BuildingInCoolDownException e) {

	}

}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOriginalCityMaxRecruitedLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	City c = getCityWithName(game, "Cairo");
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Archer", "Cairo", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Infantry", "Cairo", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Cavalry", "Cairo", c.getDefendingArmy().getUnits().size());
}





@Test(timeout = 3000)
public void testRecruitUnitCavalryPlayerOriginalCityMaxRecruitedException() throws Exception {
	try {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitExceptionHelper(game, "Cavalry", "Cairo", false, 3);
		fail("MaxRecruitedException should be thrown.");
	} catch (MaxRecruitedException e) {

	}
}

@Test(timeout = 3000)
public void testRecruitUnitArcherFromUnControlledCity() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	recruitUnitFromUnControlledCityHelper(game, "Archer", "Sparta", 5000);
	recruitUnitFromUnControlledCityHelper(game, "Infantry", "Rome", 5000);
	game = createGameHelper("Rome");
	game.getPlayer().setTreasury(5000);
	recruitUnitFromUnControlledCityHelper(game, "Cavalry", "Cairo", 5000);

}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOtherControlledCityWithEnoughGold() throws Exception {
	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().setTreasury(5000);
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitWithEnoughGoldHelper(game, "Archer", "Sparta");
	game.getPlayer().setTreasury(5000);
	playerRecruitUnitWithEnoughGoldHelper(game, "Cavalry", "Sparta");
	c = getCityWithName(game, "Rome");
	game.getPlayer().getControlledCities().add(c);
	game.getPlayer().setTreasury(5000);
	playerRecruitUnitWithEnoughGoldHelper(game, "Infantry", "Rome");
	
}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOtherControlledCityWithoutEnoughGoldLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().setTreasury(0);
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Archer", "Sparta", c.getDefendingArmy().getUnits().size());
	c = getCityWithName(game, "Rome");
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Infantry", "Rome", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitWithoutEnoughGoldLogicHelper(game, "Cavalry", "Rome", c.getDefendingArmy().getUnits().size());

}


@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOtherControlledCityInCoolDownLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().setTreasury(5000);
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitInCoolDownLogicHelper(game, "Archer", "Sparta", c.getDefendingArmy().getUnits().size());
	c = getCityWithName(game, "Rome");
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitInCoolDownLogicHelper(game, "Infantry", "Rome", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitInCoolDownLogicHelper(game, "Cavalry", "Rome", c.getDefendingArmy().getUnits().size());
}



@Test(timeout = 3000)
public void testRecruitUnitArcherPlayerOtherControlledCityMaxRecruitedLogic() throws Exception {
	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().setTreasury(5000);
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Archer", "Sparta", c.getDefendingArmy().getUnits().size());
	c = getCityWithName(game, "Rome");
	game.getPlayer().getControlledCities().add(c);
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Infantry", "Rome", c.getDefendingArmy().getUnits().size());
	playerRecruitUnitMaxRecruitedLogicHelper(game, "Cavalry", "Rome", c.getDefendingArmy().getUnits().size());
}


@Test(timeout = 3000)
public void testPlayerUpgradeBuilding() throws Exception {
	testExistsInClass(Player.class, "upgradeBuilding", true, void.class, Building.class);
}



@Test(timeout = 3000)
public void testPlayerUpgradeBuildingWithEnoughGold() throws Exception {

	Player player = new Player("Player");
	ArcheryRange b = new ArcheryRange();
	playerUpgradeBuildingHelper(player, b, 5000, 1, false);
}


@Test(timeout = 3000)
public void testPlayerUpgradeBuildingWithoutEnoughGoldLogic() throws Exception {

	Player player = new Player("Player");
	ArcheryRange b = new ArcheryRange();
	playerUpgradeBuildingWithoutEnoughGoldLogicHelper(player, b, 0, 1, false);
}





@Test(timeout = 3000)
public void testPlayerUpgradeBuildingWithoutEnoughGoldException() throws Exception {
	try {
		Player player = new Player("Player");
		Stable b = new Stable();
		playerUpgradeBuildingExceptionHelper(player, b, 0, 1, false);
		fail("NotEnoughGoldException should be thrown");
	} catch (NotEnoughGoldException e) {

	}
}


@Test(timeout = 3000)
public void testPlayerUpgradeBuildingInCoolDownLogic() throws Exception {

	Player player = new Player("Player");
	Market b = new Market();
	playerUpgradeBuildingInCoolDownLogicHelper(player, b, 5000, 1, true);
}




@Test(timeout = 3000)
public void testPlayerUpgradeBuildingInCoolDownException() throws Exception {
	try {
		Player player = new Player("Player");
		Market b = new Market();
		playerUpgradeBuildingExceptionHelper(player, b, 5000, 1, true);
		fail("BuildingInCoolDownException should be thrown");
	} catch (BuildingInCoolDownException e) {

	}
}



@Test(timeout = 3000)
public void testPlayerUpgradeBuildingMaxLevelReachedLogic() throws Exception {
	Player player = new Player("Player");
	Barracks b = new Barracks();
	playerUpgradeBuildingMaxLevelReachedLogicHelper(player, b, 5000, 3, false);

}





@Test(timeout = 3000)
public void testPlayerBuild() throws Exception {
	testExistsInClass(Player.class, "build", true, void.class, String.class, String.class);
}

@Test(timeout = 3000)
public void testPlayerBuildArcheryRange() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	playerBuildHelper(game, "ArcheryRange", "Cairo", 5000);
	game.getPlayer().setTreasury(0);
	playerBuildWithoutEnoughGoldLogicHelper(game, "ArcheryRange", "Cairo", 0);


}

@Test(timeout = 3000)
public void testPlayerBuildBarracks() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	playerBuildHelper(game, "Barracks", "Cairo", 5000);
	game.getPlayer().setTreasury(0);
	playerBuildWithoutEnoughGoldLogicHelper(game, "Barracks", "Cairo", 0);

}


@Test(timeout = 3000)
public void testPlayerBuildMarket() throws Exception {
	Game game = createGameHelper("Cairo");
	game.getPlayer().setTreasury(5000);
	playerBuildHelper(game, "Market", "Cairo", 5000);
	game.getPlayer().setTreasury(0);
	playerBuildWithoutEnoughGoldLogicHelper(game, "Market", "Cairo", 0);

}
@Test(timeout = 3000)
public void testPlayerBuildDuplicatesBuilding() throws Exception {
	Game game = createGameHelper("Paris");
	game.getPlayer().setTreasury(5000);
	City city = new City("Paris");
	game.getPlayer().getControlledCities().add(city);
	
	city.getEconomicalBuildings().add(new Farm());
	city.getEconomicalBuildings().add(new Market());
	city.getMilitaryBuildings().add(new Barracks());
	game.getPlayer().build("Farm", "Paris");
	game.getPlayer().build("Barracks", "Paris");
	int farmCount = 0;
	int marketCount = 0;
	int barracksCount = 0;
	
	for(EconomicBuilding b:city.getEconomicalBuildings()){
		if(b instanceof Farm)
			farmCount++;
		if(b instanceof Market)
			marketCount++;
		
		
	}
	for(MilitaryBuilding b:city.getMilitaryBuildings()){
		if(b instanceof Barracks)
			barracksCount++;
		
		
	}
	assertEquals("City can contain only one instance of Farm", 1,farmCount);
	assertEquals("City can contain only one instance of Market", 1,marketCount);
	assertEquals("City can contain only one instance of Barracks", 1,barracksCount);
}






@Test(timeout = 3000)
public void testPlayerBuildWithoutEnoughGoldException() throws Exception {
	try {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(0);
		game.getPlayer().build("ArcheryRange", "Cairo");
		fail("NotEnoughGoldException  should be thrown");
	} catch (NotEnoughGoldException e) {

	}
}



@Test(timeout = 3000)
public void testPlayerOtherCitiesBuildArcheryRangeWithEnoughGold() throws Exception {
	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().getControlledCities().add(c);
	game.getPlayer().setTreasury(5000);
	playerBuildHelper(game, "ArcheryRange", "Sparta", 5000);

}

@Test(timeout = 3000)
public void testPlayerOtherCitiesBuildBarracksWithoutEnoughGoldLogic() throws Exception {

	Game game = createGameHelper("Cairo");
	City c = getCityWithName(game, "Sparta");
	game.getPlayer().getControlledCities().add(c);
	game.getPlayer().setTreasury(0);
	playerBuildWithoutEnoughGoldLogicHelper(game, "Barracks", "Sparta", 0);
}



@Test(timeout = 3000)
public void testPlayerInitiateArmy() throws Exception{
	testExistsInClass(Player.class, "initiateArmy", true, void.class, City.class, Unit.class);
}
@Test(timeout = 3000)
public void testPlayerInitiateArmyLogic1() throws Exception{
	City city = new City("Cairo");
	Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
	city.setDefendingArmy(new Army("Cairo"));
	city.getDefendingArmy().getUnits().add(archerUnit1);
	Player player = new Player("player1");
	player.initiateArmy(city, archerUnit1);
	assertFalse("The Given unit should be removed from the defending army of the given city", city.getDefendingArmy().getUnits().contains(archerUnit1));
	}
@Test(timeout = 3000)
public void testPlayerInitiateArmyLogic2() throws Exception{
	City city = new City("Cairo");
	Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
	city.setDefendingArmy(new Army("Cairo"));
	city.getDefendingArmy().getUnits().add(archerUnit1);
	Player player = new Player("player1");
	player.initiateArmy(city, archerUnit1);
	
	assertTrue("The new Army should be added to the controlled armies of the player",player.getControlledArmies().size()==1);
	}
@Test(timeout = 3000)
public void testPlayerInitiateArmyLogic3() throws Exception{
	City city = new City("Cairo");
	Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
	city.setDefendingArmy(new Army("Cairo"));
	city.getDefendingArmy().getUnits().add(archerUnit1);
	Player player = new Player("player1");
	player.initiateArmy(city, archerUnit1);
	if(player.getControlledArmies().size() == 0)
		fail("Player controlled Armies should have the new army");
	assertTrue("The new Army should contain the given units",player.getControlledArmies().get(0).getUnits().contains(archerUnit1));
	
}
@Test(timeout = 3000)
public void testPlayerInitiateArmyLogic4() throws Exception{
	City city = new City("Cairo");
	Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
	city.setDefendingArmy(new Army("Cairo"));
	city.getDefendingArmy().getUnits().add(archerUnit1);
	Player player = new Player("player1");
	player.initiateArmy(city, archerUnit1);
	if(player.getControlledArmies().size() == 0)
		fail("Player controlled Armies should have the new army");
	assertTrue("The parent army of the given unit should be updated",player.getControlledArmies().get(0).equals(archerUnit1.getParentArmy()));
}

@Test(timeout = 3000)
public void testPlayerLaySiege() throws Exception{
	testExistsInClass(Player.class, "laySiege", true, void.class, Army.class, City.class);
}
@Test(timeout = 3000)
public void testPlayerLaySiegeLogic1() throws Exception{
	City city = new City("Rome");
	Army army = new Army("Cairo");
	Player player =  new Player("player1");
	army.setCurrentLocation("Rome");
	army.setDistancetoTarget(0);
	player.laySiege(army, city);
	assertEquals("The current status of the given army should be changed to BESIEGING", Status.BESIEGING,army.getCurrentStatus());
	}

@Test(timeout = 3000)
public void testPlayerLaySiegeLogic3() throws Exception{
	City city = new City("Rome");
	Army army = new Army("Cairo");
	Player player =  new Player("player1");
	army.setCurrentLocation("Rome");
	army.setDistancetoTarget(0);
	player.laySiege(army, city);
	assertEquals("The turn under siege should be 0",0,city.getTurnsUnderSiege());
}
@Test(timeout = 3000)
public void testPlayerLaySiegeThrowsTargetNotReachedException() throws Exception{
	City city = new City("Rome");
	Army army = new Army("Cairo");
	Player player =  new Player("player1");
	army.setCurrentLocation("onRoad");
	try{
	player.laySiege(army, city);
	fail("Target not reached exception should be thrown");
	}catch(EmpireException e){
		
	}
	
	}
@Test(timeout = 3000)
public void testPlayerLaySiegeThrowsFriendlyFireException() throws Exception{
	City city1 = new City("Rome");
	City city2 = new City("Cairo");
	Army army = new Army("Cairo");
	Player player =  new Player("player1");
	player.getControlledCities().add(city1);
	player.getControlledCities().add(city2);
	army.setCurrentLocation("Rome");
	try{
	player.laySiege(army, city1);
	fail("Friendly city exception should be thrown");
	}catch(EmpireException e){
		
	}
	
	}
	// ###################################### Player Helpers ###############################################//

	private void testExistsInClass(Class aClass, String methodName, boolean implementedMethod, Class returnType,
			Class... inputTypes) {
		
		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue("The " + methodName + " method in class " + aClass.getSimpleName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The " + methodName + " method in class " + aClass.getSimpleName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(
					aClass.getSimpleName() + " class should have " + methodName + " method that takes no parameters.",
					found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes " + inputsList
					+ " parameter(s).", found);
		}

		assertTrue("incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(returnType));

	}
		private Game createGameHelper(String cityName) throws Exception {
			Game g = new Game("Player", cityName);
			return g;
		}

		private City getCityWithName(Game game, String cityName) {
			for (City c : game.getAvailableCities()) {
				if (c.getName().equals(cityName))
					return c;
			}
			return null;
		}

		private City insertMilitaryBuildingHelper(Game game, String cityName, MilitaryBuilding m) throws Exception {
			for (City c : game.getAvailableCities()) {
				if (c.getName().equals(cityName)) {
					c.getMilitaryBuildings().add(m);
					return c;
				}
			}
			return null;

		}

		private void playerRecruitUnitWithEnoughGoldHelper(Game game, String type, String cityName) throws Exception {
			
			MilitaryBuilding m = null;
			City c = null;
			int initialArmySize = 0;
			switch (type) {
			case "Archer":
				m = new ArcheryRange();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Archer", cityName);
				unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
				assertTrue("A unit of type Archer should be added to the defending army.",
						c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Archer);

				break;
			case "Cavalry":
				m = new Stable();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Cavalry", cityName);
				unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
				assertTrue("A unit of type Cavalry should be added to the defending army.",
						c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Cavalry);
				break;
			case "Infantry":
				m = new Barracks();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Infantry", cityName);
				unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
				assertTrue("A unit of type Cavalry should be added to the defending army.",
						c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Infantry);
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void unitAddedAndTreasuryDecrementedHelper(Game game, City c, int count, int treasury, MilitaryBuilding m) {
			assertEquals("A unit should be added to the defending army.", count, c.getDefendingArmy().getUnits().size());
			assertEquals("The recruited unit should have the defending army of the city as its parent army.", c.getDefendingArmy(),c.getDefendingArmy().getUnits().get(count-1).getParentArmy());
			assertEquals("Recruiting Cost was not deceremented correctly.", (int) (treasury - m.getRecruitmentCost()),
					(int) game.getPlayer().getTreasury());
		}

		private void playerRecruitUnitWithoutEnoughGoldLogicHelper(Game game, String type, String cityName,
				int expectedArmyCount) throws Exception {

			MilitaryBuilding m = null;
			City c = null;
			switch (type) {
			case "Archer":
				try {
					m = new ArcheryRange();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Archer", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				} catch (NotEnoughGoldException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				}
				break;
			case "Cavalry":
				try {
					m = new Stable();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Cavalry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				} catch (NotEnoughGoldException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				}
				break;
			case "Infantry":
				try {
					m = new Barracks();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Infantry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				} catch (NotEnoughGoldException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 0, c);

				}
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void playerRecruitUnitInCoolDownLogicHelper(Game game, String type, String cityName, int expectedArmyCount)
				throws Exception {

			MilitaryBuilding m = null;
			City c = null;
			switch (type) {
			case "Archer":
				try {
					m = new ArcheryRange();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(true);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Archer", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (BuildingInCoolDownException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			case "Cavalry":
				try {
					m = new Stable();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(true);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Cavalry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (BuildingInCoolDownException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			case "Infantry":
				try {
					m = new Barracks();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(true);
					m.setCurrentRecruit(0);
					game.getPlayer().recruitUnit("Infantry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (BuildingInCoolDownException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void playerRecruitUnitMaxRecruitedLogicHelper(Game game, String type, String cityName,
				int expectedArmyCount) throws Exception {

			MilitaryBuilding m = null;
			City c = null;
			switch (type) {
			case "Archer":
				try {
					m = new ArcheryRange();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(3);
					game.getPlayer().recruitUnit("Archer", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (MaxRecruitedException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			case "Cavalry":
				try {
					m = new Stable();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(3);
					game.getPlayer().recruitUnit("Cavalry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (MaxRecruitedException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			case "Infantry":
				try {
					m = new Barracks();
					c = insertMilitaryBuildingHelper(game, cityName, m);
					m.setCoolDown(false);
					m.setCurrentRecruit(3);
					game.getPlayer().recruitUnit("Infantry", cityName);
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				} catch (MaxRecruitedException e) {
					unitNotAddedAndTreasuryNotDecrementedHelper(game, expectedArmyCount, 5000, c);

				}
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void unitNotAddedAndTreasuryNotDecrementedHelper(Game game, int expectedArmyCount, int treasury, City c) {
			assertEquals("A unit should not be added to the defending army.", expectedArmyCount,
					c.getDefendingArmy().getUnits().size());
			assertEquals("Player's Treasury should not be updated for unsuccessfull recruiting attempt.", treasury,
					(int) game.getPlayer().getTreasury());

		}

		private void playerRecruitUnitExceptionHelper(Game game, String type, String cityName, boolean coolDown,
				int currentRecruit) throws Exception {
			MilitaryBuilding m = null;
			switch (type) {
			case "Archer":
				m = new ArcheryRange();
				insertMilitaryBuildingHelper(game, cityName, m);
				m.setCoolDown(coolDown);
				m.setCurrentRecruit(currentRecruit);
				game.getPlayer().recruitUnit("Archer", cityName);
				break;
			case "Cavalry":
				m = new Stable();
				insertMilitaryBuildingHelper(game, cityName, m);
				m.setCoolDown(coolDown);
				m.setCurrentRecruit(currentRecruit);
				game.getPlayer().recruitUnit("Cavalry", cityName);
				break;
			case "Infantry":
				m = new Barracks();
				insertMilitaryBuildingHelper(game, cityName, m);
				m.setCoolDown(coolDown);
				m.setCurrentRecruit(currentRecruit);
				game.getPlayer().recruitUnit("Infantry", cityName);

				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void recruitUnitFromUnControlledCityHelper(Game game, String type, String cityName, int treasury)
				throws Exception {

			MilitaryBuilding m = null;
			City c = null;
			int initialArmySize = 0;
			switch (type) {
			case "Archer":
				m = new ArcheryRange();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Archer", cityName);
				assertEquals("A unit should not be recruited from an uncontrolled city.", initialArmySize,
						c.getDefendingArmy().getUnits().size());
				assertEquals("Player's Treasury should not be updated for unsuccessfull recruiting attempt.", treasury,
						(int) game.getPlayer().getTreasury());

				break;
			case "Cavalry":
				m = new Stable();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Cavalry", cityName);
				assertEquals("A unit should not be recruited from an uncontrolled city.", initialArmySize,
						c.getDefendingArmy().getUnits().size());
				assertEquals("Player's Treasury should not be updated for unsuccessfull recruiting attempt.", treasury,
						(int) game.getPlayer().getTreasury());
				break;
			case "Infantry":
				m = new Barracks();
				c = insertMilitaryBuildingHelper(game, cityName, m);
				initialArmySize = c.getDefendingArmy().getUnits().size();
				m.setCoolDown(false);
				m.setCurrentRecruit(0);
				game.getPlayer().recruitUnit("Infantry", cityName);
				assertEquals("A unit should not be recruited from an uncontrolled city.", initialArmySize,
						c.getDefendingArmy().getUnits().size());
				assertEquals("Player's Treasury should not be updated for unsuccessfull recruiting attempt.", treasury,
						(int) game.getPlayer().getTreasury());
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
			}

		}

		private void playerUpgradeBuildingHelper(Player player, Building b, int treasury, int level, boolean coolDown)
				throws Exception {
			player.setTreasury(treasury);
			b.setCoolDown(coolDown);
			b.setLevel(level);
			int initialUpgradeCost = b.getUpgradeCost();
			player.upgradeBuilding(b);
			assertEquals("Player treasury should be updated when a building is successfully upgraded",
					treasury - initialUpgradeCost, (int) player.getTreasury());
			assertEquals("Building level should be incremented when successfully upgraded.", level + 1, b.getLevel());
		}

		private void playerUpgradeBuildingWithoutEnoughGoldLogicHelper(Player player, Building b, int treasury, int level,
				boolean inCoolDown) throws Exception {
			player.setTreasury(treasury);
			b.setCoolDown(inCoolDown);
			b.setLevel(level);
			try {
				player.upgradeBuilding(b);
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);
			} catch (NotEnoughGoldException e) {
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);

			}

		}

		private void treasuryNotUpdatedAndLevelNotUpdated(Player player, Building b, int treasury, int level) {
			assertEquals("Player's Treasury should not be updated for unsuccessfull upgrading attempt.", treasury,
					(int) player.getTreasury());
			assertEquals("Building level should not be incremented when unsuccessfully upgraded.", level, b.getLevel());

		}

		private void playerUpgradeBuildingExceptionHelper(Player player, Building b, int treasury, int level,
				boolean inCoolDown) throws Exception {
			player.setTreasury(treasury);
			b.setLevel(level);
			b.setCoolDown(inCoolDown);
			player.upgradeBuilding(b);
		}

		private void playerUpgradeBuildingInCoolDownLogicHelper(Player player, Building b, int treasury, int level,
				boolean inCoolDown) throws Exception {
			try {
				player.setTreasury(treasury);
				b.setLevel(level);
				b.setCoolDown(inCoolDown);
				player.upgradeBuilding(b);
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);
			} catch (BuildingInCoolDownException e) {
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);

			}
		}

		private void playerUpgradeBuildingMaxLevelReachedLogicHelper(Player player, Building b, int treasury, int level,
				boolean inCoolDown) throws Exception {
			try {
				player.setTreasury(treasury);
				b.setLevel(level);
				b.setCoolDown(inCoolDown);
				player.upgradeBuilding(b);
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);

			} catch (MaxLevelException e) {
				treasuryNotUpdatedAndLevelNotUpdated(player, b, treasury, level);

			}
		}

		private void playerBuildHelper(Game game, String type, String cityName, int treasury) throws Exception {
			MilitaryBuilding m = null;
			EconomicBuilding e = null;
			City c = null;
			int initialBuildingCount = 0;
			switch (type) {
			case "ArcheryRange":
				m = new ArcheryRange();
				c = getCityWithName(game, cityName);
				initialBuildingCount = c.getMilitaryBuildings().size();
				game.getPlayer().build("ArcheryRange", cityName);
				militaryBuildingAddedAndTreasuryDecrementedHelper(game, c, initialBuildingCount + 1, treasury, m);
				assertTrue("A Building of type ArcheryRange should be added to the military buildings.",
						c.getMilitaryBuildings().get(initialBuildingCount) instanceof ArcheryRange);

				break;
			case "Stable":
				m = new Stable();
				c = getCityWithName(game, cityName);
				initialBuildingCount = c.getMilitaryBuildings().size();
				game.getPlayer().build("Stable", cityName);
				militaryBuildingAddedAndTreasuryDecrementedHelper(game, c, initialBuildingCount + 1, treasury, m);
				assertTrue("A Building of type Stable should be added to the military buildings.",
						c.getMilitaryBuildings().get(initialBuildingCount) instanceof Stable);
				break;
			case "Barracks":
				m = new Barracks();
				c = getCityWithName(game, cityName);
				initialBuildingCount = c.getMilitaryBuildings().size();
				game.getPlayer().build("Barracks", cityName);
				militaryBuildingAddedAndTreasuryDecrementedHelper(game, c, initialBuildingCount + 1, treasury, m);
				assertTrue("A Building of type Barracks should be added to the military buildings.",
						c.getMilitaryBuildings().get(initialBuildingCount) instanceof Barracks);
				break;

			case "Farm":
				e = new Farm();
				c = getCityWithName(game, cityName);
				initialBuildingCount = c.getEconomicalBuildings().size();
				game.getPlayer().build("Farm", cityName);
				economicalBuildingAddedAndTreasuryDecrementedHelper(game, c, initialBuildingCount + 1, treasury, e);
				assertTrue("A Building of type Farm should be added to the economical buildings.",
						c.getEconomicalBuildings().get(initialBuildingCount) instanceof Farm);
				break;
			case "Market":
				e = new Market();
				c = getCityWithName(game, cityName);
				initialBuildingCount = c.getEconomicalBuildings().size();
				game.getPlayer().build("Market", cityName);
				economicalBuildingAddedAndTreasuryDecrementedHelper(game, c, initialBuildingCount + 1, treasury, e);
				assertTrue("A Building of type Market should be added to the economical buildings.",
						c.getEconomicalBuildings().get(initialBuildingCount) instanceof Market);
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry.");
			}
		}

		private void militaryBuildingAddedAndTreasuryDecrementedHelper(Game game, City c, int expectedBuildingCount,
				int treasury, MilitaryBuilding m) {
			assertEquals("A building should be added to the military buildings.", expectedBuildingCount,
					c.getMilitaryBuildings().size());
			assertEquals("Building Cost was not deceremented correctly.", (int) (treasury - m.getCost()),
					(int) game.getPlayer().getTreasury());
		}

		private void economicalBuildingAddedAndTreasuryDecrementedHelper(Game game, City c, int expectedBuildingCount,
				int treasury, EconomicBuilding m) {
			assertEquals("A building should be added to the economical buildings.", expectedBuildingCount,
					c.getEconomicalBuildings().size());
			assertEquals("Building Cost was not deceremented correctly.", (int) (treasury - m.getCost()),
					(int) game.getPlayer().getTreasury());
		}
		
		private void playerBuildWithoutEnoughGoldLogicHelper(Game game, String type, String cityName, int treasury)
				throws Exception {

			MilitaryBuilding m = null;
			EconomicBuilding e = null;
			City c = null;
			int initialBuildingCount = 0;
			switch (type) {
			case "ArcheryRange":
				try {
					m = new ArcheryRange();
					c = getCityWithName(game, cityName);
					initialBuildingCount = c.getMilitaryBuildings().size();
					game.getPlayer().build("ArcheryRange", cityName);
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				} catch (NotEnoughGoldException exp) {
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				}
				break;
			case "Stable":
				try {
					m = new Stable();
					c = getCityWithName(game, cityName);
					initialBuildingCount = c.getMilitaryBuildings().size();
					game.getPlayer().build("Stable", cityName);
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				} catch (NotEnoughGoldException exp) {
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				}
				break;
			case "Barracks":
				try {
					m = new Barracks();
					c = getCityWithName(game, cityName);
					initialBuildingCount = c.getMilitaryBuildings().size();
					game.getPlayer().build("Barracks", cityName);
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				} catch (NotEnoughGoldException exp) {
					militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, m);
				}
				break;

			case "Farm":
				try {
					e = new Farm();
					c = getCityWithName(game, cityName);
					initialBuildingCount = c.getEconomicalBuildings().size();
					game.getPlayer().build("Farm", cityName);
					economicalBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, e);
				} catch (NotEnoughGoldException exp) {
					economicalBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, e);
				}
				break;
			case "Market":
				try {
					e = new Market();
					c = getCityWithName(game, cityName);
					initialBuildingCount = c.getEconomicalBuildings().size();
					game.getPlayer().build("Market", cityName);
					economicalBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, e);
				} catch (NotEnoughGoldException exp) {
					economicalBuildingNotAddedAndTreasuryNotDecrementedHelper(game, c, initialBuildingCount, treasury, e);
				}
				break;
			default:
				fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry.");
			}

		}

		private void militaryBuildingNotAddedAndTreasuryNotDecrementedHelper(Game game, City c, int expectedBuildingCount,
				int treasury, MilitaryBuilding m) {
			assertEquals("A building should not be added to the military buildings.", expectedBuildingCount,
					c.getMilitaryBuildings().size());
			assertEquals("Treasury shouldn't be decermented when the Build is unsuccessful.", (int) treasury,
					(int) game.getPlayer().getTreasury());
		}

		private void economicalBuildingNotAddedAndTreasuryNotDecrementedHelper(Game game, City c, int expectedBuildingCount,
				int treasury, EconomicBuilding m) {
			assertEquals("A building should not be added to the economical buildings.", expectedBuildingCount,
					c.getEconomicalBuildings().size());
			assertEquals("Treasury shouldn't be decermented when the Build is unsuccessful.", (int) treasury,
					(int) game.getPlayer().getTreasury());
		}
		private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
				throws SecurityException {

			boolean thrown = false;
			try {
				aClass.getDeclaredField(varName);
			} catch (NoSuchFieldException e) {
				thrown = true;
			}
			if (implementedVar) {
				assertFalse(
						"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
						thrown);
			} else {
				assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
						+ aClass.getSimpleName() + ".", thrown);
			}
		}

		private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
			Field f = null;
			try {
				f = aClass.getDeclaredField(varName);
			} catch (NoSuchFieldException e) {
				return;
			}
			Class varType = f.getType();
			assertEquals(
					"the attribute " + varType.getSimpleName() + " should be of the type " + expectedType.getSimpleName(),
					expectedType, varType);
		}

		private void testInstanceVariableIsPrivate(Class aClass, String varName)
				throws NoSuchFieldException, SecurityException {
			Field f = aClass.getDeclaredField(varName);
			assertEquals("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " should not be accessed outside that class.", 2, f.getModifiers());
		}
		private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
				boolean readvariable) {
			Method m = null;
			boolean found = true;
			try {
				m = aClass.getDeclaredMethod(methodName);
			} catch (NoSuchMethodException e) {
				found = false;
			}

			String varName = "";
			if (returnedType == boolean.class)
				varName = methodName.substring(2,3).toLowerCase() + methodName.substring(3);
			else
				varName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
			if (readvariable) {
				assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
						+ " is a READ variable.", found);
				assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
						m.getReturnType().isAssignableFrom(returnedType));
			} else {
				assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
						+ " is not a READ variable.", found);
			}

		}

		private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
				boolean writeVariable) {

			Method[] methods = aClass.getDeclaredMethods();
			String varName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
			if (writeVariable) {
				assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
						+ " is a WRITE variable.", containsMethodName(methods, methodName));
			} else {
				assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
						+ " is not a WRITE variable.", containsMethodName(methods, methodName));
				return;
			}
			Method m = null;
			boolean found = true;
			try {
				m = aClass.getDeclaredMethod(methodName, inputType);
			} catch (NoSuchMethodException e) {
				found = false;
			}

			assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
					+ inputType.getSimpleName() + " parameter.", found);

			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
					m.getReturnType().equals(Void.TYPE));

		}
		private static boolean containsMethodName(Method[] methods, String name) {
			for (Method method : methods) {
				if (method.getName().equals(name))
					return true;
			}
			return false;
		}
		private void testSetterLogic(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
				throws Exception {

			Field f = null;
			Class curr = createdObject.getClass();

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ name + "\".");
				try {
					f = curr.getDeclaredField(name);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}

			}

			f.setAccessible(true);

			Character c = name.charAt(0);
			String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
			Method m = createdObject.getClass().getMethod(methodName, type);
			m.invoke(createdObject, setValue);

			assertEquals(
					"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
							+ " should set the correct value of variable \"" + name + "\".",
					expectedValue, f.get(createdObject));

		}
		private void testConstructorInitialization(Object createdObject, String[] names, Object[] values)
				throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

			for (int i = 0; i < names.length; i++) {

				Field f = null;
				Class curr = createdObject.getClass();
				String currName = names[i];
				Object currValue = values[i];

				while (f == null) {

					if (curr == Object.class)
						fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
								+ currName + "\".");
					try {
						f = curr.getDeclaredField(currName);
					} catch (NoSuchFieldException e) {
						curr = curr.getSuperclass();
					}

				}

				f.setAccessible(true);

				assertEquals(
						"The constructor of the " + createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName + "\" correctly.",
						currValue, f.get(createdObject));

			}

		}


		private void unitAttack(Unit attackUnit, double archerFactor, double infantryFactor, double cavalryFactor ) {

			Archer archerTarget = null;
			Cavalry cavalryTarget = null;
			Infantry infantryTraget = null;
			Army  test_Army = new Army("Cairo");
			attackUnit.setParentArmy(test_Army);
			Army target_Army=  new Army("Rome");
			int level = (int) (Math.random() * 3) + 1;
			switch (level) {
			case 1:
				archerTarget = new Archer(1, 60, 0.4, 0.5, 0.6);
				break;
			case 2:
				archerTarget = new Archer(2, 60, 0.4, 0.5, 0.6);
				break;
			case 3:
				archerTarget = new Archer(3, 70, 0.5, 0.6, 0.7);
				break;
			default:
				archerTarget = new Archer(1, 60, 0.4, 0.5, 0.6);
			}

			level = (int) (Math.random() * 3) + 1;
			switch (level) {
			case 1:
				cavalryTarget = new Cavalry(1, 40, 0.6, 0.7, 0.75);
				break;
			case 2:
				cavalryTarget = new Cavalry(2, 40, 0.6, 0.7, 0.75);
				break;
			case 3:
				cavalryTarget = new Cavalry(3, 60, 0.7, 0.8, 0.9);
				break;
			default:
				cavalryTarget = new Cavalry(1, 40, 0.6, 0.7, 0.75);
			}
			

			level = (int) (Math.random() * 3) + 1;
			switch (level) {
			case 1:
				infantryTraget = new Infantry(1, 50, 0.5, 0.6, 0.7);
				break;
			case 2:
				infantryTraget = new Infantry(2, 50, 0.5, 0.6, 0.7);
				break;
			case 3:
				infantryTraget = new Infantry(3, 60, 0.6, 0.7, 0.8);
				break;
			default:
				infantryTraget = new Infantry(1, 50, 0.5, 0.6, 0.7);
			}
			archerTarget.setParentArmy(target_Army);
			cavalryTarget.setParentArmy(target_Army);
			infantryTraget.setParentArmy(target_Army);

			int archerCurrentSoldierCountBefore = archerTarget.getCurrentSoldierCount();
			int cavalryCurrentSoldierCountBefore = cavalryTarget.getCurrentSoldierCount();
			int infantryCurrentSoldierCountBefore = infantryTraget.getCurrentSoldierCount();
			
			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				 m.invoke(attackUnit, archerTarget);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (attackUnit.getCurrentSoldierCount() * archerFactor >= archerCurrentSoldierCountBefore){
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + archerTarget.getCurrentSoldierCount(),
						archerTarget.getCurrentSoldierCount(), 0);
				}
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (archerCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * archerFactor))
								+ " but was " + archerTarget.getCurrentSoldierCount(),
						
						archerCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * archerFactor),archerTarget.getCurrentSoldierCount());

			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				m.invoke(attackUnit, cavalryTarget);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (attackUnit.getCurrentSoldierCount() * cavalryFactor >= cavalryCurrentSoldierCountBefore)
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + cavalryTarget.getCurrentSoldierCount(),
						cavalryTarget.getCurrentSoldierCount(), 0);
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (cavalryCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * cavalryFactor))
								+ " but was " + cavalryTarget.getCurrentSoldierCount(),
						cavalryTarget.getCurrentSoldierCount(),
						cavalryCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * cavalryFactor));

			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				m.invoke(attackUnit, infantryTraget);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (attackUnit.getCurrentSoldierCount() * infantryFactor >= infantryCurrentSoldierCountBefore)
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + infantryTraget.getCurrentSoldierCount(),
						infantryTraget.getCurrentSoldierCount(), 0);
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (infantryCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * infantryFactor))
								+ " but was " + infantryTraget.getCurrentSoldierCount(),
						infantryTraget.getCurrentSoldierCount(),
						infantryCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * infantryFactor));

			archerTarget = new Archer(1, 5, 0.4, 0.5, 0.6);
			cavalryTarget = new Cavalry(1, 5, 0.6, 0.7, 0.75);
			infantryTraget = new Infantry(3, 5, 0.6, 0.7, 0.8);
			archerTarget.setParentArmy(target_Army);
			cavalryTarget.setParentArmy(target_Army);
			infantryTraget.setParentArmy(target_Army);
			target_Army.getUnits().add(archerTarget);
			target_Army.getUnits().add(cavalryTarget);
			target_Army.getUnits().add(infantryTraget);

			archerCurrentSoldierCountBefore = archerTarget.getCurrentSoldierCount();
			cavalryCurrentSoldierCountBefore = archerTarget.getCurrentSoldierCount();
			infantryCurrentSoldierCountBefore = archerTarget.getCurrentSoldierCount();
			
			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				m.invoke(attackUnit, archerTarget);
				
			} catch (Exception e) {
				e.printStackTrace();	}
			
			if (attackUnit.getCurrentSoldierCount() * archerFactor >= archerCurrentSoldierCountBefore){
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + archerTarget.getCurrentSoldierCount(),
						 0,archerTarget.getCurrentSoldierCount());
				
				assertFalse("The Target should be removed from its parent army's unit by calling handle attacked units method",target_Army.getUnits().contains(archerTarget));
			
			}
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (archerCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * archerFactor))
								+ " but was " + archerTarget.getCurrentSoldierCount(),
						archerTarget.getCurrentSoldierCount(),
						archerCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * archerFactor));

			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				m.invoke(attackUnit, cavalryTarget);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (attackUnit.getCurrentSoldierCount() * cavalryFactor >= cavalryCurrentSoldierCountBefore){
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + cavalryTarget.getCurrentSoldierCount(),
						cavalryTarget.getCurrentSoldierCount(), 0);
				
				assertFalse("The Target should be removed from its parent army's unit by calling handle attacked units method",target_Army.getUnits().contains(cavalryTarget));
			
			}
			
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (cavalryCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * cavalryFactor))
								+ " but was " + cavalryTarget.getCurrentSoldierCount(),
						cavalryTarget.getCurrentSoldierCount(),
						cavalryCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * cavalryFactor));

			try {
				Method m = Unit.class.getDeclaredMethod("attack", Unit.class);
				m.invoke(attackUnit, infantryTraget);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (attackUnit.getCurrentSoldierCount() * infantryFactor >= infantryCurrentSoldierCountBefore){
				assertEquals(
						"The Target Current Soldier Count should be 0 but was " + infantryTraget.getCurrentSoldierCount(),
						infantryTraget.getCurrentSoldierCount(), 0);
				assertFalse("The Target should be removed from its parent army's unit by calling handle attacked units method",target_Army.getUnits().contains(infantryTraget));
			}
			else
				assertEquals(
						"The Target Current Soldier Count should be "
								+ (infantryCurrentSoldierCountBefore
										- (int) (attackUnit.getCurrentSoldierCount() * infantryFactor))
								+ " but was " + infantryTraget.getCurrentSoldierCount(),
						infantryTraget.getCurrentSoldierCount(),
						infantryCurrentSoldierCountBefore - (int) (attackUnit.getCurrentSoldierCount() * infantryFactor));
		}

	
////////////////////////////////////////Helper Methods //////////////////////////////////////////

	
}
