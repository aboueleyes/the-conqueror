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
public class M2PrivateTests {
	
	@Test(timeout = 3000)
	public void testArcherAttackLevel2() throws Exception {
		
		Archer archerUnit2 = new Archer(2, 60, 0.4, 0.5, 0.6);
		

		
		unitAttack(archerUnit2, 0.4, 0.3, 0.1);
		

	}
	@Test(timeout = 3000)
	public void testArcherAttackLevel3() throws Exception {
		Archer archerUnit3 = new Archer(3, 70, 0.5, 0.6, 0.7);

		unitAttack(archerUnit3, 0.5, 0.4, 0.2);

	}
	@Test(timeout = 3000)
	public void testCavalryAttackLevel2() throws Exception {
		
		Cavalry cavalryUnit2 = new Cavalry(2, 40, 0.6, 0.7, 0.75);
		unitAttack(cavalryUnit2, 0.6, 0.4, 0.2);
		
	}
	@Test(timeout = 3000)
	public void testCavalryAttackLevel3() throws Exception {
		Cavalry cavalryUnit3 = new Cavalry(3, 60, 0.7, 0.8, 0.9);

		unitAttack(cavalryUnit3, 0.7, 0.5, 0.3);
	}
	@Test(timeout = 3000)
	public void testInfantryAttackLevel2() throws Exception {
		
		Infantry infantryUnit2 = new Infantry(2, 50, 0.5, 0.6, 0.7);
		

		
		unitAttack(infantryUnit2, 0.4, 0.2, 0.2);
		
	}
	@Test(timeout = 3000)
	public void testInfantryAttackLevel3() throws Exception {
		Infantry infantryUnit3 = new Infantry(3, 60, 0.6, 0.7, 0.8);

		unitAttack(infantryUnit3, 0.5, 0.3, 0.25);
	}
	@Test(timeout = 3000, expected = FriendlyFireException.class)
	public void testCavalryFriendlyFire() throws Throwable{
		Cavalry cavalryUnit1 = new Cavalry(1, 40, 0.6, 0.7, 0.75);
		Infantry infantryUnit1 = new Infantry(1, 50, 0.5, 0.6, 0.7);
		Army army = new Army("Cairo");
		army.getUnits().add(infantryUnit1);
		army.getUnits().add(cavalryUnit1);
		cavalryUnit1.setParentArmy(army);
		infantryUnit1.setParentArmy(army);
		try{
			cavalryUnit1.attack(infantryUnit1);
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
	@Test(timeout = 1000)
	public void testFarmUpgradeLogic() throws Exception {
	
	Farm farm = new Farm();
	farm.setCoolDown(false);
	try {
	
	Method upgrade = Farm.class.getDeclaredMethod("upgrade");
	
	upgrade.invoke(farm);
	
	assertTrue("Value of level is wrong, expected " + 2 + " but was " + farm.getLevel() + ".",
		2 == farm.getLevel());
	assertTrue("Value of upgrade cost is wrong, expected " + 700 + " but was " + farm.getUpgradeCost() + ".",
		700 == farm.getUpgradeCost());
	assertTrue("Value of coolDown is wrong,expected True but was false",farm.isCoolDown());
	farm.setCoolDown(false);
	upgrade.invoke(farm);
	assertTrue("Value of level is wrong, expected " + 3 + " but was " + farm.getLevel() + ".",
		3 == farm.getLevel());
	
	} catch (NoSuchMethodException e) {
	fail("Farm class should have upgrade method");
	}
	
	}
	@Test(timeout = 1000)
	public void testStableUpgradeLogic() throws Exception {
	
	Stable stable = new Stable();
	stable.setCoolDown(false);
	
	try {
	
	Method upgrade = Stable.class.getDeclaredMethod("upgrade");
	
	upgrade.invoke(stable);
	
	assertTrue("Value of level is wrong, expected " + 2 + " but was " + stable.getLevel() + ".",
		2 == stable.getLevel());
	assertTrue("Value of upgrade cost is wrong, expected " + 2000 + " but was " + stable.getUpgradeCost() + ".",
		2000 == stable.getUpgradeCost());
	
	assertTrue("Value of recruitment cost is wrong, expected " + 650 + " but was " + stable.getRecruitmentCost()
		+ ".", 650 == stable.getRecruitmentCost());
	assertTrue("Value of coolDown is wrong,expected True but was false",stable.isCoolDown());
	stable.setCoolDown(false);
	upgrade.invoke(stable);
	
	assertTrue("Value of level is wrong, expected " + 3 + " but was " + stable.getLevel() + ".",
		3 == stable.getLevel());
	assertTrue("Value of recruitment cost is wrong, expected " + 700 + " but was " + stable.getRecruitmentCost()
		+ ".", 700 == stable.getRecruitmentCost());
	
	} catch (NoSuchMethodException e) {
	fail("Stable class should have upgrade method");
	}
	
	}
	@Test(timeout = 100)
	public void testTargetCityLogic1() throws Exception {
		Game g = new Game("Mabrouk", "Sparta");
		City city = new City("Paris");
		Army army = new Army("Paris");
		Distance d = new Distance("Paris", "Berlin", 12);
		g.getDistances().add(d);
		g.targetCity(army, "Berlin");
		assertEquals("The Army target should be Rome","Berlin", army.getTarget());
		assertEquals("The Distance to target should be 12",12, army.getDistancetoTarget());
		}
	@Test(timeout = 3000)
	public void testArcheryRangeUpgrade() throws Exception {
	testExistsInClass(ArcheryRange.class, "upgrade", true, void.class);
	}
	
	@Test(timeout = 3000)
	public void testBarracksUpgrade() throws Exception {
	testExistsInClass(Barracks.class, "upgrade", true, void.class);
	}
	@Test(timeout = 1000)
	public void testFarmHarvestLogic() throws Exception {
	
	Farm f = new Farm();
	
	f.setCoolDown(false);
	
	try {
	
	Method harvest = Farm.class.getDeclaredMethod("harvest");
	
	
	int harvestLevel1 = (Integer) harvest.invoke(f);
	
	assertTrue("Value of harvest is wrong, expected " + 500 + " but was " + harvestLevel1 + ".",
		500 == harvestLevel1);
	
	f.setLevel(2);
	int harvestLevel2 = (Integer) harvest.invoke(f);
	
	assertTrue("Value of harvest is wrong, expected " + 700 + " but was " + harvestLevel2 + ".",
		700 == harvestLevel2);
	
	f.setLevel(3);
	int harvestLevel3 = (Integer) harvest.invoke(f);
	
	assertTrue("Value of harvest is wrong, expected " + 1000 + " but was " + harvestLevel3 + ".",
		1000 == harvestLevel3);
	
	} catch (NoSuchMethodException e) {
	fail("Market class should have harvest method");
	}
	
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
	@Test(timeout = 3000, expected = BuildingInCoolDownException.class)
	public void testRecruitStableInCooldownException() throws Throwable {
		Stable stable = new Stable();
		stable.setCoolDown(true);
		Method recruit = Stable.class.getDeclaredMethod("recruit");
		try {
		recruit.invoke(stable);
		
		} catch (InvocationTargetException ite) {
		if (ite.getCause() instanceof BuildingInCoolDownException) {
		throw ite.getCause();
		} else {
		throw ite;
		}
		}
	
	}
	
	@Test(timeout = 3000, expected = MaxRecruitedException.class)
	public void testRecruitStableMaxRecruitedException() throws Throwable {
		Stable stable = new Stable();
		stable.setCoolDown(false);
		stable.setCurrentRecruit(3);
		Method recruit = Stable.class.getDeclaredMethod("recruit");
		try {
		recruit.invoke(stable);
		
		} catch (InvocationTargetException ite) {
		if (ite.getCause() instanceof MaxRecruitedException) {
		throw ite.getCause();
		} else {
		throw ite;
		}
		}
	
	}
	@Test(timeout = 3000)
	public void testRecruitUnitInfantryPlayerOriginalCityWithoutEnoughGoldException() throws Exception {
		try {
			Game game = createGameHelper("Cairo");
			game.getPlayer().setTreasury(0);
			playerRecruitUnitExceptionHelper(game, "Infantry", "Cairo", false, 0);
			fail("NotEnoughGoldException should be thrown.");
		} catch (NotEnoughGoldException e) {

		}

	}

	@Test(timeout = 3000)
	public void testRecruitUnitCavalryPlayerOriginalCityWithoutEnoughGoldException() throws Exception {
		try {
			Game game = createGameHelper("Cairo");
			game.getPlayer().setTreasury(0);
			playerRecruitUnitExceptionHelper(game, "Cavalry", "Cairo", false, 0);
			fail("NotEnoughGoldException should be thrown.");
		} catch (NotEnoughGoldException e) {

		}
	}
	@Test(timeout = 3000, expected = FriendlyFireException.class)
	public void testInfantrtFriendlyFire() throws Throwable{
		Archer archerUnit1 = new Archer(1, 60, 0.4, 0.5, 0.6);
		Infantry infantryUnit1 = new Infantry(1, 50, 0.5, 0.6, 0.7);
		Army army = new Army("Cairo");
		army.getUnits().add(infantryUnit1);
		army.getUnits().add(archerUnit1);
		archerUnit1.setParentArmy(army);
		infantryUnit1.setParentArmy(army);
		try{
			infantryUnit1.attack(archerUnit1);
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
	


	@Test(timeout = 3000)
	public void testPlayerBuildStable() throws Exception {

		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerBuildHelper(game, "Stable", "Cairo", 5000);
		game.getPlayer().setTreasury(0);
		playerBuildWithoutEnoughGoldLogicHelper(game, "Stable", "Cairo", 0);
	}



	@Test(timeout = 3000)
	public void testPlayerBuildFarm() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerBuildHelper(game, "Farm", "Cairo", 5000);
		game.getPlayer().setTreasury(0);
		playerBuildWithoutEnoughGoldLogicHelper(game, "Farm", "Cairo", 0);

	}
	@Test(timeout = 3000)
	public void testPlayerLaySiegeLogic2() throws Exception{
		City city = new City("Rome");
		Army army = new Army("Cairo");
		Player player =  new Player("player1");
		army.setCurrentLocation("Rome");
		player.laySiege(army, city);
		
		assertEquals("The city should be under siege",true,city.isUnderSiege());
		
	}
	@Test(timeout = 3000)
	public void testFarmUpgrade() throws Exception {
	testExistsInClass(Farm.class, "upgrade", true, void.class);
	}
	
	@Test(timeout = 3000)
	public void testStableUpgrade() throws Exception {
	testExistsInClass(Stable.class, "upgrade", true, void.class);
	}
	@Test(timeout = 1000)
	public void testBarracksUpgradeLogic() throws Exception {
	
	Barracks barracks = new Barracks();
	barracks.setCoolDown(false);
	
	try {
	
	Method upgrade = Barracks.class.getDeclaredMethod("upgrade");
	
	upgrade.invoke(barracks);
	
	assertTrue("Value of level is wrong, expected " + 2 + " but was " + barracks.getLevel() + ".",
		2 == barracks.getLevel());
	assertTrue(
		"Value of upgrade cost is wrong, expected " + 1500 + " but was " + barracks.getUpgradeCost() + ".",
		1500 == barracks.getUpgradeCost());
	
	assertTrue("Value of recruitment cost is wrong, expected " + 550 + " but was "
		+ barracks.getRecruitmentCost() + ".", 550 == barracks.getRecruitmentCost());
	assertTrue("Value of coolDown is wrong,expected True but was false",barracks.isCoolDown());
	barracks.setCoolDown(false);
	upgrade.invoke(barracks);
	
	assertTrue("Value of level is wrong, expected " + 3 + " but was " + barracks.getLevel() + ".",
		3 == barracks.getLevel());
	assertTrue("Value of recruitment cost is wrong, expected " + 600 + " but was "
		+ barracks.getRecruitmentCost() + ".", 600 == barracks.getRecruitmentCost());
	
	} catch (NoSuchMethodException e) {
	fail("Barracks class should have upgrade method");
	}
	
	}
	@Test(timeout = 3000)
	public void testEconomicalBuildingHarvest() throws Exception {
	testExistsInClass(EconomicBuilding.class, "harvest", true, int.class);
	}
	@Test(timeout = 3000)
	public void testMarketHarvest() throws Exception {
	testExistsInClass(Market.class, "harvest", true, int.class);
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
