package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.lang.reflect.Method;

import java.util.ArrayList;



import org.junit.Test;



@SuppressWarnings({ "rawtypes", "unchecked" })
public class M1PrivateTests {

// ############################################# Classes' Paths  ############################################# //

	String buildingPath = "buildings.Building";
	String economicBuildingPath = "buildings.EconomicBuilding";
	String farmPath = "buildings.Farm";
	String marketPath = "buildings.Market";
	String militaryBuildingPath = "buildings.MilitaryBuilding";
	String archeryRangePath = "buildings.ArcheryRange";
	String barracksPath = "buildings.Barracks";
	String stablePath = "buildings.Stable";
	String distancePath = "engine.Distance";
	String gamePath = "engine.Game";
	String statusPath = "units.Status";
	String playerPath = "engine.Player";
	String cityPath = "engine.City";

	String mlePath = "exceptions.MaxLevelException";
	String bePath = "exceptions.BuildingException";
	String aePath = "exceptions.ArmyException";
	String ffePath = "exceptions.FriendlyFireException";
	String fcePath = "exceptions.FriendlyCityException";
	String tnrePath = "exceptions.TargetNotReachedException";
	String eePath = "exceptions.EmpireException";
	String bicdePath = "exceptions.BuildingInCoolDownException";
	String mrePath = "exceptions.MaxRecruitedException";
	String unitPath = "units.Unit";
	String armyPath = "units.Army";
	String archerPath = "units.Archer";
	String infantryPath = "units.Infantry";
	String cavalryPath = "units.Cavalry";
	
	
	@Test(timeout = 100)
	public void testBuildingInstanceVariableUpgradeCost() throws Exception {
		testInstanceVariableIsPresent(Class.forName(buildingPath), "upgradeCost", true);

		testInstanceVariableIsPresent(Class.forName(economicBuildingPath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(farmPath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(marketPath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(archeryRangePath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath), "upgradeCost", false);
		testInstanceVariableIsPresent(Class.forName(stablePath), "upgradeCost", false);

		testInstanceVariableIsPrivate(Class.forName(buildingPath), "upgradeCost");
		testInstanceVariableOfType(Class.forName(buildingPath), "upgradeCost", int.class);
	}

	@Test(timeout = 100)
	public void testBuildingUpgradeCostSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(buildingPath), "setUpgradeCost", int.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testSetterAbsentInSubclasses("upgradeCost", subClasses);

		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		int randomUpgradeCost = (int) (Math.random() * 1000) + 500;
		testSetterLogic(b, "upgradeCost", randomUpgradeCost, randomUpgradeCost, int.class);
		
	}

	@Test(timeout = 100)
	public void testBuildingUpgradeCostGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(buildingPath), "getUpgradeCost", int.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testGetterAbsentInSubclasses("upgradeCost", subClasses, int.class);
		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		int randomUpgradeCost = (int) (Math.random() * 1000) + 500;
		testGetterLogic(b, "upgradeCost", randomUpgradeCost);
	}

	@Test(timeout = 100)
	public void testBuildingInstanceVariableCoolDown() throws Exception {
		testInstanceVariableIsPresent(Class.forName(buildingPath), "coolDown", true);

		testInstanceVariableIsPresent(Class.forName(economicBuildingPath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(farmPath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(marketPath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(archeryRangePath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath), "coolDown", false);
		testInstanceVariableIsPresent(Class.forName(stablePath), "coolDown", false);

		testInstanceVariableIsPrivate(Class.forName(buildingPath), "coolDown");
		testInstanceVariableOfType(Class.forName(buildingPath), "coolDown", boolean.class);
	}

	@Test(timeout = 100)
	public void testBuildingUpgradeCoolDownSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(buildingPath), "setCoolDown", boolean.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testSetterAbsentInSubclasses("coolDown", subClasses);

		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		testSetterLogic(b, "coolDown", true, true, boolean.class);
		testSetterLogic(b, "coolDown", false, false, boolean.class);
	}

	@Test(timeout = 100)
	public void testBuildingUpgradeCoolDownGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(buildingPath), "isCoolDown", boolean.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testGetterAbsentInSubclasses("coolDown", subClasses, boolean.class);
		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		testGetterLogic(b, "coolDown", true);
		testGetterLogic(b, "coolDown", false);
	}
	@Test(timeout = 100)
	public void testInstanceVariablePlayerFood() throws Exception {
		testInstanceVariableIsPresent(Class.forName(playerPath), "food", true);
		testInstanceVariableIsPrivate(Class.forName(playerPath), "food");
		testInstanceVariableOfType(Class.forName(playerPath), "food", double.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayerFoodGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(playerPath), "getFood", double.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayerFoodGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		double randomDouble = (Math.random() * 50);
		testGetterLogic(myObj, "food", randomDouble);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayerFoodSetter() throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(playerPath), "setFood", double.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayerFoodSetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		double randomDouble = (Math.random() * 50);
		testSetterLogic(myObj, "food", randomDouble,randomDouble, double.class);
	}
	@Test(timeout = 100)
	public void testInstanceVariableCityMilitaryBuildings() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cityPath), "militaryBuildings",
				true);
		testInstanceVariableIsPrivate(Class.forName(cityPath), "militaryBuildings");
		testInstanceVariableOfType(Class.forName(cityPath),
				"militaryBuildings", ArrayList.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityMilitaryBuildingsGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(cityPath),
				"getMilitaryBuildings", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityMilitaryBuildingsSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(cityPath),
				"setMilitaryBuildings", ArrayList.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityMilitaryBuildingsGetterLogic()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		ArrayList<?> value = new ArrayList<Object>();
		testGetterLogic(myObj, "militaryBuildings", value);
	}
	
	@Test(timeout = 100)
	public void testConstructorCityConstructorInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		String[] names = { "name" };
		Object[] values = { name };
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testConstructorMaxLevelExceptionConstructor2() throws Exception {
		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(mlePath), inputs);
	}
	@Test(timeout = 100)
	   	public void testInstanceVariablePlayerNameGetter() throws ClassNotFoundException {
	   		testGetterMethodExistsInClass(Class.forName(playerPath), "getName", String.class, true);
	   	}

	 @Test(timeout = 100)
	   	public void testInstanceVariablePlayerNameGetterLogic() throws Exception {
	   		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
	   		int random = (int) (Math.random() * 50);
	   		String name = "name" + random;
	   		Object myObj = constructor.newInstance(name);
	   		testGetterLogic(myObj, "name", name);
	   	}
	       @Test(timeout = 100)
	      	public void testInstanceVariablePlayerTreasury() throws Exception {
	      		testInstanceVariableIsPresent(Class.forName(playerPath), "treasury", true);
	      		testInstanceVariableIsPrivate(Class.forName(playerPath), "treasury");
	      		testInstanceVariableOfType(Class.forName(playerPath), "treasury", double.class);
	      	}

	          @Test(timeout = 100)
	      	public void testInstanceVariablePlayerTreasuryGetter() throws ClassNotFoundException {
	      		testGetterMethodExistsInClass(Class.forName(playerPath), "getTreasury", double.class, true);
	      	}
	          @Test(timeout = 100)
	         	public void testInstanceVariablePlayerControlledCitiesGetter() throws ClassNotFoundException {
	         		testGetterMethodExistsInClass(Class.forName(playerPath), "getControlledCities", ArrayList.class, true);
	         	}

	         	@Test(timeout = 100)
	         	public void testInstanceVariablePlayerControlledCitiesGetterLogic() throws Exception {
	         		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
	         		int random = (int) (Math.random() * 50);
	         		String name = "name" + random;
	         		Object myObj = constructor.newInstance(name);
	         		ArrayList<?> value = new ArrayList<Object>();
	         		testGetterLogic(myObj, "controlledCities", value);
	         	}
	       
	          
	         	@Test(timeout = 100)
	        	public void testInstanceVariableCityNameSetter()
	        			throws ClassNotFoundException {
	        		testSetterMethodExistsInClass(Class.forName(cityPath), "setName",
	        				String.class, false);
	        	}
	        	
	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityNameGetterLogic() throws Exception {
	        		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
	        				String.class);
	        		int random = (int) (Math.random() * 50);
	        		String name = "name" + random;
	        		Object myObj = constructor.newInstance(name);
	        		testGetterLogic(myObj, "name", name);
	        	}
	        	
	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityEconomicalBuildings() throws Exception {
	        		testInstanceVariableIsPresent(Class.forName(cityPath),
	        				"economicalBuildings", true);
	        		testInstanceVariableIsPrivate(Class.forName(cityPath),
	        				"economicalBuildings");
	        		testInstanceVariableOfType(Class.forName(cityPath),
	        				"economicalBuildings", ArrayList.class);
	        	}
	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityDefendingArmyGetterLogic() throws Exception {
	        		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
	        				String.class);

	        		Constructor<?> armyConstructor = Class.forName(armyPath)
	        				.getConstructor(String.class);

	        		int random = (int) (Math.random() * 50);
	        		String name = "name" + random;
	        		Object myObj = constructor.newInstance(name);
	        		
	        		Object army = armyConstructor.newInstance(name);

	        		testGetterLogic(myObj, "defendingArmy", army);
	        	}
	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityTurnsUnderSiege() throws Exception {
	        		testInstanceVariableIsPresent(Class.forName(cityPath),
	        				"turnsUnderSiege", true);
	        		testInstanceVariableIsPrivate(Class.forName(cityPath),
	        				"turnsUnderSiege");
	        		testInstanceVariableOfType(Class.forName(cityPath),
	        				"turnsUnderSiege", int.class);
	        	}

	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityTurnsUnderSiegeGetter()
	        			throws ClassNotFoundException {
	        		testGetterMethodExistsInClass(Class.forName(cityPath),
	        				"getTurnsUnderSiege", int.class, true);
	        	}

	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityTurnsUnderSiegeSetter()
	        			throws ClassNotFoundException {
	        		testSetterMethodExistsInClass(Class.forName(cityPath),
	        				"setTurnsUnderSiege", int.class, true);
	        	}
	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityUnderSiege() throws Exception {
	        		testInstanceVariableIsPresent(Class.forName(cityPath),
	        				"underSiege", true);
	        		testInstanceVariableIsPrivate(Class.forName(cityPath),
	        				"underSiege");
	        		testInstanceVariableOfType(Class.forName(cityPath),
	        				"underSiege", boolean.class);
	        	}

	        	@Test(timeout = 100)
	        	public void testInstanceVariableCityUnderSiegeGetter()
	        			throws Exception {
	        		testGetterMethodExistsInClass(Class.forName(cityPath),
	        				"isUnderSiege", boolean.class, true);
	        	}


	@Test(timeout = 1000)
	public void testConstructorMaxLevelExceptionConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(mlePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}
	
	
	
	@Test(timeout = 1000)
	public void testConstructorFriendlyFireExceptionConstructor2() throws Exception {
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(ffePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorFriendlyFireExceptionConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(ffePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}
	
	
	
	@Test(timeout = 1000)
	public void testConstructorFriendlyCityExceptionConstructor2() throws Exception {
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(fcePath), inputs);
	}
	
	
	@Test(timeout = 1000)
	public void testConstructorFriendlyCityExceptionConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(fcePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}
	
	
	@Test(timeout = 1000)
	public void testConstructorTargetNotReachedExceptionConstructor2() throws Exception {
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(tnrePath), inputs);
	}
	
	
	
	@Test(timeout = 1000)
	public void testConstructorTargetNotReachedExceptionConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(tnrePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}

	
	@Test(timeout = 100)
	public void testConstructorEmpireException2() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(eePath), inputs);
	}


	

	@Test(timeout = 100)
	public void testConstructorBuildingException2() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(bePath), inputs);
	}

	

	@Test(timeout = 100)
	public void testConstructorArmyException2() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(aePath), inputs);
	}

	

	@Test(timeout = 100)
	public void testConstructorBuildingInCoolDownException2() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(bicdePath), inputs);
	}

	

	@Test(timeout = 1000)
	public void testConstructorBuildingInCoolDownConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(bicdePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}

	

	@Test(timeout = 100)
	public void testConstructorMaxRecruitedException2() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(mrePath), inputs);
	}

	
	@Test(timeout = 1000)
	public void testConstructorMaxRecruitedExceptionConstructor2Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(mrePath).getConstructor(String.class);
		String message = "Error";
		Object myObj = constructor.newInstance(message);
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
		assertEquals(
				"The constructor of the " + myObj.getClass().getSimpleName()
						+ " class should initialize the instance variable \"" + "message" + "\" correctly.",
				message, ((Exception) myObj).getMessage());
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitCurrentSoldierCount() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "currentSoldierCount", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "currentSoldierCount");
		testInstanceVariableIsPresent(Class.forName(archerPath), "currentSoldierCount", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "currentSoldierCount", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "currentSoldierCount", false);
		testInstanceVariableOfType(Class.forName(unitPath), "currentSoldierCount", int.class);
		
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitCurrentSoldierCountGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath),"getCurrentSoldierCount", int.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getCurrentSoldierCount", int.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getCurrentSoldierCount", int.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getCurrentSoldierCount", int.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("currentSoldierCount", subClasses, int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitCurrentSoldierCountGetterLogic()
			throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testGetterLogic(arch, "currentSoldierCount", 0);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitCurrentSoldierCountSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setCurrentSoldierCount", int.class, true);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("currentSoldierCount", subClasses);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitCurrentSoldierCountSetterLogic() throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		int random6 = (int) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testSetterLogic(arch, "currentSoldierCount", random6, random6, int.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitSiegeUpkeep() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "siegeUpkeep", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "siegeUpkeep");
		testInstanceVariableIsPresent(Class.forName(archerPath), "siegeUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "siegeUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "siegeUpkeep", false);
		testInstanceVariableOfType(Class.forName(unitPath), "siegeUpkeep", double.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitSiegeUpkeepGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath), "getSiegeUpkeep", double.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getSiegeUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getSiegeUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getSiegeUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("siegeUpkeep", subClasses, double.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitSiegeUpkeepGetterLogic()
			throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		double random6 = (double) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testGetterLogic(arch, "marchingUpkeep", random6);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitSiegeUpkeepSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setSiegeUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("siegeUpkeep", subClasses);
	}
	@Test(timeout = 100)
	public void testInstanceVariableArmyDistancetoTarget() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "distancetoTarget", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "distancetoTarget");
		testInstanceVariableOfType(Class.forName(armyPath), "distancetoTarget", int.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyDistancetoTargetGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getDistancetoTarget", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableArmyDistancetoTargetGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		testGetterLogic(a, "distancetoTarget", -1);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyDistancetoTargetSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setDistancetoTarget", int.class, true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyDistancetoTargetSetterLogic() throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		random = (int) (Math.random() * 50);
		testSetterLogic(a, "distancetoTarget", random, random, int.class);
	}
	
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyMaxToHoldGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getMaxToHold", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableMaxToHoldGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		testGetterLogic(a, "maxToHold", 10);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyMaxToHoldSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setMaxToHold", int.class, false);
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassCavalry() throws Exception {
		testClassIsSubclass(Class.forName(cavalryPath), Class.forName(unitPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorCavalryConstructor()
			throws Exception {
		Class[] inputs = { int.class, int.class, double.class, double.class, double.class };
		testConstructorExists(Class.forName(cavalryPath), inputs);
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
		Object[] values = { random1, random2, 0, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 100)
	public void testDistanceInstanceVariableTo() throws Exception {
		testInstanceVariableIsPresent(Class.forName(distancePath),
				"to", true);
		
		testInstanceVariableIsPrivate(Class.forName(distancePath),
				"to");
		testInstanceVariableOfType(Class.forName(distancePath),
				"to", String.class);
	}

	@Test(timeout = 100)
	public void testDistanceToSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(distancePath),"setTo", String.class, false);
	}
	
	@Test(timeout = 100)
	public void testDistanceToGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(distancePath),"getTo", String.class, true);
		
		Constructor<?> constructor = Class.forName(distancePath).getConstructor(
				String.class, String.class, int.class);
		String fromCairo = "Cairo";
		String toRome = "Rome";
		int randomDistance = (int) (Math.random() * 450) + 50;
		Object b = constructor.newInstance(fromCairo, toRome, randomDistance);
		testGetterLogic(b, "to", "London");
	}
	@Test(timeout = 100)
	public void testGameInstanceVariableDistances() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath),
				"distances", true);
		
		testInstanceVariableIsPrivate(Class.forName(gamePath),
				"distances");
		testInstanceVariableOfType(Class.forName(gamePath),
				"distances", ArrayList.class);
	}

	@Test(timeout = 100)
	public void testGameDistancesSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath),"setDistances", ArrayList.class, false);
	}
	
	@Test(timeout = 100)
	public void testGameDistancesGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(gamePath),"getDistances", ArrayList.class, true);
		
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		testGetterLogic(b, "distances", new ArrayList<>());
	}
	@Test(timeout = 800)
	public void testGameConstructorWithCitySparta() throws Exception {
		Class[] inputs = {String.class, String.class};
		testConstructorExists(Class.forName(gamePath), inputs);
		testGameConstructorWithCity("Sparta");
		
	}
	

	
	
	// ############################################# Helper Methods  ############################################# //

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
			assertEquals(
					varName + " instance variable in class " + aClass.getName()
							+ " should not be accessed outside that class", 2,
					f.getModifiers());
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

		private void testSetterAbsentInSubclasses(String varName, String[] subclasses)
				throws SecurityException, ClassNotFoundException {
			String methodName = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
			boolean methodIsInSubclasses = false;
			for (String subclass : subclasses) {
				Method[] methods = Class.forName(subclass).getDeclaredMethods();
				methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

			}
			assertFalse("The " + methodName + " method should not be implemented in a subclasses.", methodIsInSubclasses);
		}

		private void testGetterAbsentInSubclasses(String varName, String[] subclasses, Class type)
				throws SecurityException, ClassNotFoundException {
			String methodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
			if (type == boolean.class) {
				methodName = "is" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
			}
			boolean methodIsInSubclasses = false;
			for (String subclass : subclasses) {
				Method[] methods = Class.forName(subclass).getDeclaredMethods();
				methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

			}
			assertFalse("The " + methodName + " method should not be implemented in subclasses.", methodIsInSubclasses);
		}

		private static boolean containsMethodName(Method[] methods, String name) {
			for (Method method : methods) {
				if (method.getName().equals(name))
					return true;
			}
			return false;
		}

		private void testConstructorExists(Class aClass, Class[] inputs) {
			boolean thrown = false;
			try {
				aClass.getConstructor(inputs);
			} catch (NoSuchMethodException e) {
				thrown = true;
			}

			if (inputs.length > 0) {
				String msg = "";
				int i = 0;
				do {
					msg += inputs[i].getSimpleName() + " and ";
					i++;
				} while (i < inputs.length);

				msg = msg.substring(0, msg.length() - 4);

				assertFalse(
						"Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in "
								+ aClass.getSimpleName() + " class.",

						thrown);
			} else
				assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.",

						thrown);

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

		private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

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
			f.set(createdObject, value);

			Character c = name.charAt(0);

			String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

			if (value.getClass().equals(Boolean.class))
				methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

			Method m = createdObject.getClass().getMethod(methodName);
			assertEquals(
					"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
							+ " should return the correct value of variable \"" + name + "\".",
					value, m.invoke(createdObject));

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

		private void testClassIsSubclass(Class subClass, Class superClass) {
			assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".",
					superClass, subClass.getSuperclass());
		}

		private boolean checkArmiesEqual(Object c1n,Object a1, Object a2) throws Exception
		{
			Field fcl = Class.forName(armyPath).getDeclaredField("currentLocation");
			fcl.setAccessible(true);
			Object a1cl = (Object) fcl.get(a1);
			Object a2cl = (Object) fcl.get(a2);
			if(!a1cl.equals(a2cl))
			{
				fail("Army Loacation of city "+c1n+" is incorrect");
			}
			
			Field fu = Class.forName(armyPath).getDeclaredField("units");
			fu.setAccessible(true);
			ArrayList<Object> a1u = (ArrayList<Object>) fu.get(a1);
			ArrayList<Object> a2u = (ArrayList<Object>) fu.get(a2);
			
				assertEquals("The Units size of city "+c1n+" was loaded incorrectly",a1u.size(), a2u.size());
			
			Field fl = Class.forName(unitPath).getDeclaredField("level");
			fl.setAccessible(true);
			Field fI = Class.forName(unitPath).getDeclaredField("idleUpkeep");
			fI.setAccessible(true);
			Field fS = Class.forName(unitPath).getDeclaredField("marchingUpkeep");
			fS.setAccessible(true);
			Field fM = Class.forName(unitPath).getDeclaredField("siegeUpkeep");
			fM.setAccessible(true);
			Field fC = Class.forName(unitPath).getDeclaredField("maxSoldierCount");
			fC.setAccessible(true);
			for(int i = 0 ;i < a1u.size(); i++)
			{
				
					assertEquals("The Units level of city "+c1n+" was loaded incorrectly",fl.get(a1u.get(i)), fl.get(a2u.get(i)));
					assertEquals("The Units class type of city "+c1n+" was loaded incorrectly",a1u.get(i).getClass(), a2u.get(i).getClass());
					assertEquals("The Units Idle Up Keep of city "+c1n+" was loaded incorrectly",fI.get(a1u.get(i)), fI.get(a2u.get(i)));
					assertEquals("The Units Siege Up Keep of city "+c1n+" was loaded incorrectly",fS.get(a1u.get(i)), fS.get(a2u.get(i)));
					assertEquals("The Units Marching up Keep of city "+c1n+" was loaded incorrectly",fM.get(a1u.get(i)), fM.get(a2u.get(i)));
					assertEquals("The Units max soldier count of city "+c1n+" was loaded incorrectly",fC.get(a1u.get(i)), fC.get(a2u.get(i)));
					
				
				
			}
			return true;
		}
		
		
		private boolean checkCitiesEqual(Object c1, Object c2, boolean skipArmy) throws Exception
		{
			Field fn = Class.forName(cityPath).getDeclaredField("name");
			fn.setAccessible(true);
			Object c1n = (Object) fn.get(c1);
			Object c2n = (Object) fn.get(c2);
			if(!c1n.equals(c2n))
			{
				fail("City name was loaded incorrectly");
			}
			
			Field fda = Class.forName(cityPath).getDeclaredField("defendingArmy");
			fda.setAccessible(true);
			Object c1da = (Object) fda.get(c1);
			Object c2da = (Object) fda.get(c2);
			
			if(skipArmy)
				return c1da == c2da && c1da == null;
			
			return checkArmiesEqual(c1n,c1da, c2da);
		}
		
		private boolean checkDistancesEqual(Object d1, Object d2) throws Exception
		{
			Field ff = Class.forName(distancePath).getDeclaredField("from");
			ff.setAccessible(true);
			Object d1f = (Object) ff.get(d1);
			Object d2f = (Object) ff.get(d2);
			if(!d1f.equals(d2f))
			{
				return false;
			}
			Field ft = Class.forName(distancePath).getDeclaredField("from");
			ft.setAccessible(true);
			Object d1t = (Object) ft.get(d1);
			Object d2t = (Object) ft.get(d2);
			if(!d1t.equals(d2t))
			{
				return false;
			}
			Field fd = Class.forName(distancePath).getDeclaredField("distance");
			fd.setAccessible(true);
			Object d1d = (Object) fd.get(d1);
			Object d2d = (Object) fd.get(d2);
			if(!d1d.equals(d2d))
			{
				return false;
			}
			return true;
		}
		
		private Object generateCairoArmy() throws Exception
		{
			Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
			Object cArmy = armyConstructor.newInstance("Cairo");
			ArrayList<Object> cArmyUnits = new ArrayList<Object>();
			Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> infantryConstructor = Class.forName(infantryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> cavalryConstructor = Class.forName(cavalryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Object item = null;
			item = archerConstructor.newInstance(1, 60, 0.4, 0.5, 0.6);
			cArmyUnits.add(item);
			item = archerConstructor.newInstance(2, 60, 0.4, 0.5, 0.6);
			cArmyUnits.add(item);
			item = archerConstructor.newInstance(3,70,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = archerConstructor.newInstance(2, 60, 0.4, 0.5, 0.6);
			cArmyUnits.add(item);
			
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			item = infantryConstructor.newInstance(2,50,0.5,0.6,0.7);
			cArmyUnits.add(item);
			
			item = cavalryConstructor.newInstance(3, 60, 0.7, 0.8, 0.9);
			cArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3, 60, 0.7, 0.8, 0.9);
			cArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3, 60, 0.7, 0.8, 0.9);
			cArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3, 60, 0.7, 0.8, 0.9);
			cArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3, 60, 0.7, 0.8, 0.9);
			cArmyUnits.add(item);
			Field f = Class.forName(armyPath).getDeclaredField("units");
			f.setAccessible(true);
			f.set(cArmy, cArmyUnits);
			return cArmy;
			
		}
		
		private Object generateCairoCity(String playerCity) throws Exception {
			
			Constructor<?> cityConstructor = Class.forName(cityPath).getConstructor(String.class);
			Object cairoCity = cityConstructor.newInstance("Cairo");
			Field fda = Class.forName(cityPath).getDeclaredField("defendingArmy");
			fda.setAccessible(true);
			if(playerCity.equals("Cairo"))
				fda.set(cairoCity, null);
			else
				fda.set(cairoCity, generateCairoArmy());
			return cairoCity;
		}
		
		private Object generateRomeArmy() throws Exception
		{
			Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
			Object rArmy = armyConstructor.newInstance("Rome");
			ArrayList<Object> rArmyUnits = new ArrayList<Object>();
			Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> infantryConstructor = Class.forName(infantryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> cavalryConstructor = Class.forName(cavalryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Object item = null;
			item = archerConstructor.newInstance(2, 60, 0.4, 0.5, 0.6);
			rArmyUnits.add(item);
			item = archerConstructor.newInstance(2, 60, 0.4, 0.5, 0.6);
			rArmyUnits.add(item);
			item = archerConstructor.newInstance(2, 60, 0.4, 0.5, 0.6);
			rArmyUnits.add(item);
			item = archerConstructor.newInstance(1, 60, 0.4, 0.5, 0.6);
			rArmyUnits.add(item);
			
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(3,60,0.6,0.7,0.8);
			rArmyUnits.add(item);
			item = infantryConstructor.newInstance(1, 50, 0.5, 0.6, 0.7);
			rArmyUnits.add(item);
			
			
			item = cavalryConstructor.newInstance(2,40,0.6,0.7,0.75);
			rArmyUnits.add(item);
			item = cavalryConstructor.newInstance(2,40,0.6,0.7,0.75);
			rArmyUnits.add(item);
			item = cavalryConstructor.newInstance(2,40,0.6,0.7,0.75);
			rArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3,60,0.7,0.8,0.9);
			rArmyUnits.add(item);
			
			Field f = Class.forName(armyPath).getDeclaredField("units");
			f.setAccessible(true);
			f.set(rArmy, rArmyUnits);
			return rArmy;
			
		}
		
		private Object generateRomeCity(String playerCity) throws Exception {
			
			Constructor<?> cityConstructor = Class.forName(cityPath).getConstructor(String.class);
			Object romeCity = cityConstructor.newInstance("Rome");
			Field fda = Class.forName(cityPath).getDeclaredField("defendingArmy");
			fda.setAccessible(true);
			if(playerCity.equals("Rome"))
				fda.set(romeCity, null);
			else
				fda.set(romeCity, generateRomeArmy());
			return romeCity;
		}
		
		private Object generateSpartaArmy() throws Exception
		{
			Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
			Object sArmy = armyConstructor.newInstance("Sparta");
			ArrayList<Object> sArmyUnits = new ArrayList<Object>();
			Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> infantryConstructor = Class.forName(infantryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> cavalryConstructor = Class.forName(cavalryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Object item = null;
			item = archerConstructor.newInstance(3, 70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(3,  70, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(1, 60, 0.4, 0.5, 0.6);
			sArmyUnits.add(item);
			item = archerConstructor.newInstance(2,60,0.4,0.5,0.6);
			sArmyUnits.add(item);
			
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(3, 60, 0.6, 0.7, 0.8);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(1, 50, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			item = infantryConstructor.newInstance(2, 50, 0.5, 0.6, 0.7);
			sArmyUnits.add(item);
			
			item = cavalryConstructor.newInstance(1, 40, 0.6, 0.7, 0.75);
			sArmyUnits.add(item);
			item = cavalryConstructor.newInstance(2, 40, 0.6, 0.7, 0.75);
			sArmyUnits.add(item);
			item = cavalryConstructor.newInstance(3,60,0.7,0.8,0.9);
			sArmyUnits.add(item);
			
			
			Field f = Class.forName(armyPath).getDeclaredField("units");
			f.setAccessible(true);
			f.set(sArmy, sArmyUnits);
			return sArmy;
			
		}
		
		private Object generateSpartaCity(String playerCity) throws Exception {
			
			Constructor<?> cityConstructor = Class.forName(cityPath).getConstructor(String.class);
			Object spartaCity = cityConstructor.newInstance("Sparta");
			Field fda = Class.forName(cityPath).getDeclaredField("defendingArmy");
			fda.setAccessible(true);
			if(playerCity.equals("Sparta"))
				fda.set(spartaCity, null);
			else
				fda.set(spartaCity, generateSpartaArmy());
			return spartaCity;
		}
		
		private ArrayList<Object> generateDistances() throws Exception {
			ArrayList<Object> distances = new ArrayList<Object>();
			Constructor<?> distanceConstructor = Class.forName(distancePath).getConstructor(String.class, String.class, int.class);
			distances.add(distanceConstructor.newInstance("Cairo", "Rome", 6));
			distances.add(distanceConstructor.newInstance("Cairo", "Sparta", 5));
			distances.add(distanceConstructor.newInstance("Sparta", "Rome", 9));
			return distances;
		}
		
		private void testGameConstructorWithCity(String city) throws Exception
		{
			Constructor<?> gameConstructor = Class.forName(gamePath).getConstructor(String.class, String.class);
			String name = "player" + ((int) (Math.random() * 10));
			Object game = gameConstructor.newInstance(name, city);
			
			Field fmtc = Class.forName(gamePath).getDeclaredField("maxTurnCount");
			fmtc.setAccessible(true);
			assertEquals("Wrong max turn count on initilaization.", 30, fmtc.get(game));
			Field fctc = Class.forName(gamePath).getDeclaredField("currentTurnCount");
			fctc.setAccessible(true);
			assertEquals("Wrong current turn count on initilaization.", 1, fctc.get(game));
			
			Object romeCity = generateRomeCity(city);
			Object spartaCity = generateSpartaCity(city);
			Object cairoCity = generateCairoCity(city);
			
			
			Object[] allCities = {romeCity, spartaCity, cairoCity};
			
			ArrayList<Object> distances = generateDistances();
			
			Field fac = Class.forName(gamePath).getDeclaredField("availableCities");
			fac.setAccessible(true);
			
			ArrayList<Object> availCities = (ArrayList<Object>) fac.get(game);
			

			for (Object aCity : allCities) {
				boolean loadedCorrectly = false;
				for (Object bCity : availCities) {
					Field fcn = Class.forName(cityPath).getDeclaredField("name");
					fcn.setAccessible(true);
					if(!fcn.get(aCity).equals(fcn.get(bCity)))
					{
						
						continue;
					}
					boolean skipArmy = city.equals(fcn.get(bCity));
					loadedCorrectly = loadedCorrectly || checkCitiesEqual(aCity, bCity, skipArmy);	
				}
				if(!loadedCorrectly)
				{
					fail("Cities and their armies were not loaded correctly.");
				}
			}
			
			Field fd = Class.forName(gamePath).getDeclaredField("distances");
			fd.setAccessible(true);
			
			ArrayList<Object> distancesLoaded = (ArrayList<Object>) fd.get(game);
			
			for (Object distance : distances) {
				boolean loadedCorrectly = false;
				for (Object distance2 : distancesLoaded) {
					loadedCorrectly = loadedCorrectly || checkDistancesEqual(distance, distance2);	
				}
				if(!loadedCorrectly)
				{
					fail("Cities' distances were not loaded correctly.");
				}
			}
			
			Field fp = Class.forName(gamePath).getDeclaredField("player");
			fp.setAccessible(true);
			Object p = fp.get(game);
			Field fpn = Class.forName(playerPath).getDeclaredField("name");
			fpn.setAccessible(true);
			Object n = fpn.get(p);
			assertEquals("Wrong player name on initilaization.", n, name);
			
			Field fpcc = Class.forName(playerPath).getDeclaredField("controlledCities");
			fpcc.setAccessible(true);
			Object cc = ((ArrayList<Object>)fpcc.get(p)).get(0);
			if(city.equals("Cairo")) {
				assertTrue("Initial player city was loaded incorrectly.", checkCitiesEqual(cairoCity, cc, true));
			}
			else if (city.equals("Rome")) {
				assertTrue("Initial player city was loaded incorrectly.", checkCitiesEqual(romeCity, cc, true));
			}
			else {
				assertTrue("Initial player city was loaded incorrectly.", checkCitiesEqual(spartaCity, cc, true));
			}
			
			Field fpca = Class.forName(playerPath).getDeclaredField("controlledArmies");
			fpca.setAccessible(true);
			int sizeOfCA = ((ArrayList<Object>)fpca.get(p)).size();
			assertEquals("Initial player army should not have an initial army.", 0, sizeOfCA);
		}
		

}