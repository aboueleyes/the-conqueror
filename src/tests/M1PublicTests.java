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



@SuppressWarnings({ "rawtypes", "unchecked" })
public class M1PublicTests {

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
	
	// ############################################# Test Methods  ############################################# //
	@Test(timeout = 100)
	public void testClassIsAbstractBuilding() throws Exception {
		testClassIsAbstract(Class.forName(buildingPath));
	}

	@Test(timeout = 100)
	public void testConstructorBuilding() throws Exception {
		Class[] inputs = { int.class, int.class };
		testConstructorExists(Class.forName(buildingPath), inputs);
		
	}

	@Test(timeout = 100)
	public void testBuildingInstanceVariableCost() throws Exception {
		testInstanceVariableIsPresent(Class.forName(buildingPath), "cost", true);

		testInstanceVariableIsPresent(Class.forName(economicBuildingPath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(farmPath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(marketPath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(archeryRangePath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath), "cost", false);
		testInstanceVariableIsPresent(Class.forName(stablePath), "cost", false);

		testInstanceVariableIsPrivate(Class.forName(buildingPath), "cost");
		testInstanceVariableOfType(Class.forName(buildingPath), "cost", int.class);
	}

	@Test(timeout = 100)
	public void testBuildingCostSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(buildingPath), "setCost", int.class, false);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testSetterAbsentInSubclasses("cost", subClasses);
	}

	@Test(timeout = 100)
	public void testBuildingCostGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(buildingPath), "getCost", int.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testGetterAbsentInSubclasses("cost", subClasses, int.class);
		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		int randomCost = (int) (Math.random() * 1500) + 1000;
		testGetterLogic(b, "cost", randomCost);
	}

	@Test(timeout = 100)
	public void testBuildingInstanceVariableLevel() throws Exception {
		testInstanceVariableIsPresent(Class.forName(buildingPath), "level", true);

		testInstanceVariableIsPresent(Class.forName(economicBuildingPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(farmPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(marketPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(archeryRangePath), "level", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(stablePath), "level", false);

		testInstanceVariableIsPrivate(Class.forName(buildingPath), "level");
		testInstanceVariableOfType(Class.forName(buildingPath), "level", int.class);
	}

	@Test(timeout = 100)
	public void testBuildingLevelSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(buildingPath), "setLevel", int.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testSetterAbsentInSubclasses("level", subClasses);

		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		int randomLevel = (int) (Math.random() * 2) + 1;
		testSetterLogic(b, "level", randomLevel, randomLevel, int.class);
		
	}
	@Test(timeout = 100)
	public void testClassIsAbstractEconomicBuilding() throws Exception {
		testClassIsAbstract(Class.forName(economicBuildingPath));
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassEconomicBuilding() throws Exception {
		testClassIsSubclass(Class.forName(economicBuildingPath), Class.forName(buildingPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorEconomicBuilding() throws Exception {
		Class[] inputs = {int.class, int.class};
		testConstructorExists(Class.forName(economicBuildingPath), inputs);
	}
	
	
	@Test(timeout = 100)
	public void testClassIsSubclassFarm() throws Exception {
		testClassIsSubclass(Class.forName(farmPath), Class.forName(economicBuildingPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorFarm() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(farmPath), inputs);
		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = {"cost", "upgradeCost", "level", "coolDown"};
		Object[] varValues = {1000, 500, 1, true};
		testConstructorInitialization(b, varNames, varValues);
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassMarket() throws Exception {
		testClassIsSubclass(Class.forName(marketPath), Class.forName(economicBuildingPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorMarket() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(marketPath), inputs);
		Constructor<?> constructor = Class.forName(marketPath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = {"cost", "upgradeCost", "level", "coolDown"};
		Object[] varValues = {1500, 700, 1, true};
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testBuildingLevelGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(buildingPath), "getLevel", int.class, true);
		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
				barracksPath, stablePath };
		testGetterAbsentInSubclasses("level", subClasses, int.class);
		Constructor<?> constructor = Class.forName(farmPath).getConstructor();
		Object b = constructor.newInstance();
		int randomLevel = (int) (Math.random() * 2) + 1;
		testGetterLogic(b, "level", randomLevel);
	}
	
	@Test(timeout = 100)
	public void testClassIsAbstractMilitaryBuilding() throws Exception {
		testClassIsAbstract(Class.forName(militaryBuildingPath));
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassMilitaryBuilding() throws Exception {
		testClassIsSubclass(Class.forName(militaryBuildingPath), Class.forName(buildingPath));
	}
	
	
	
	@Test(timeout = 100)
	public void testConstructorMilitaryBuilding() throws Exception {
		Class[] inputs = {int.class, int.class, int.class};
		testConstructorExists(Class.forName(militaryBuildingPath), inputs);
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassArcheryRange() throws Exception {
		testClassIsSubclass(Class.forName(archeryRangePath), Class.forName(militaryBuildingPath));
	}
	
	
	
	@Test(timeout = 100)
	public void testConstructorArcheryRange() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(archeryRangePath), inputs);
		Constructor<?> constructor = Class.forName(archeryRangePath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = {"cost", "upgradeCost", "level", "coolDown", "recruitmentCost", "currentRecruit", "maxRecruit"};
		Object[] varValues = {1500, 800, 1, true, 400, 0, 3};
		testConstructorInitialization(b, varNames, varValues);
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassBarracks() throws Exception {
		testClassIsSubclass(Class.forName(barracksPath), Class.forName(militaryBuildingPath));
	}
	
	
	
	@Test(timeout = 100)
	public void testConstructorBarracks() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(barracksPath), inputs);
		Constructor<?> constructor = Class.forName(barracksPath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = {"cost", "upgradeCost", "level", "coolDown", "recruitmentCost", "currentRecruit", "maxRecruit"};
		Object[] varValues = {2000, 1000, 1, true, 500, 0, 3};
		testConstructorInitialization(b, varNames, varValues);
	}
	
	@Test(timeout = 100)
	public void testClassIsSubclassStable() throws Exception {
		testClassIsSubclass(Class.forName(stablePath), Class.forName(militaryBuildingPath));
	}
	
	
	
	@Test(timeout = 100)
	public void testConstructorStable() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(stablePath), inputs);
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		String[] varNames = {"cost", "upgradeCost", "level", "coolDown", "recruitmentCost", "currentRecruit", "maxRecruit"};
		Object[] varValues = {2500, 1500, 1, true, 600, 0, 3};
		testConstructorInitialization(b, varNames, varValues);
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingInstanceVariableRecruitmentCost() throws Exception {
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath),
				"recruitmentCost", true);
		
		testInstanceVariableIsPresent(Class.forName(archeryRangePath),
				"recruitmentCost", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath),
				"recruitmentCost", false);
		testInstanceVariableIsPresent(Class.forName(stablePath),
				"recruitmentCost", false);
		
		testInstanceVariableIsPrivate(Class.forName(militaryBuildingPath),
				"recruitmentCost");
		testInstanceVariableOfType(Class.forName(militaryBuildingPath),
				"recruitmentCost", int.class);
	}

	@Test(timeout = 100)
	public void testMilitaryBuildingRecruitmentCostSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(militaryBuildingPath),"setRecruitmentCost", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testSetterAbsentInSubclasses("recruitmentCost", subClasses);
		
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomRecruitmentCost = (int) (Math.random() * 1500) + 500;
		testSetterLogic(b, "recruitmentCost", randomRecruitmentCost, randomRecruitmentCost, int.class);
		
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingRecruitmentCostGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath),"getRecruitmentCost", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testGetterAbsentInSubclasses("recruitmentCost", subClasses, int.class);
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomRecruitmentCost = (int) (Math.random() * 1500) + 500;
		testGetterLogic(b, "recruitmentCost", randomRecruitmentCost);
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingInstanceVariableCurrentRecruit() throws Exception {
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath),
				"currentRecruit", true);
		
		testInstanceVariableIsPresent(Class.forName(archeryRangePath),
				"currentRecruit", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath),
				"currentRecruit", false);
		testInstanceVariableIsPresent(Class.forName(stablePath),
				"currentRecruit", false);
		
		testInstanceVariableIsPrivate(Class.forName(militaryBuildingPath),
				"currentRecruit");
		testInstanceVariableOfType(Class.forName(militaryBuildingPath),
				"currentRecruit", int.class);
	}

	@Test(timeout = 100)
	public void testMilitaryBuildingCurrentRecruitSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(militaryBuildingPath),"setCurrentRecruit", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testSetterAbsentInSubclasses("currentRecruit", subClasses);
		
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomCurrentRecruit = (int) (Math.random() * 2) + 1;
		testSetterLogic(b, "currentRecruit", randomCurrentRecruit, randomCurrentRecruit, int.class);
		
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingCurrentRecruitGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath),"getCurrentRecruit", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testGetterAbsentInSubclasses("currentRecruit", subClasses, int.class);
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomCurrentRecruit = (int) (Math.random() * 2) + 1;
		testGetterLogic(b, "currentRecruit", randomCurrentRecruit);
	}
	@Test(timeout = 100)
	public void testMilitaryBuildingInstanceVariableMaxRecruit() throws Exception {
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath),
				"maxRecruit", true);
		
		testInstanceVariableIsPresent(Class.forName(archeryRangePath),
				"maxRecruit", false);
		testInstanceVariableIsPresent(Class.forName(barracksPath),
				"maxRecruit", false);
		testInstanceVariableIsPresent(Class.forName(stablePath),
				"maxRecruit", false);
		
		testInstanceVariableIsPrivate(Class.forName(militaryBuildingPath),
				"maxRecruit");
		testInstanceVariableOfType(Class.forName(militaryBuildingPath),
				"maxRecruit", int.class);
	}

	@Test(timeout = 100)
	public void testMilitaryBuildingMaxRecruitSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(militaryBuildingPath),"setMaxRecruit", int.class, false);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testSetterAbsentInSubclasses("maxRecruit", subClasses);
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingMaxRecruitGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath),"getMaxRecruit", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testGetterAbsentInSubclasses("maxRecruit", subClasses, int.class);
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		testGetterLogic(b, "maxRecruit", 3);
	}
	@Test(timeout = 100)
	public void testEnumStatus() throws ClassNotFoundException {
		testIsEnum(Class.forName(statusPath));
	}

    @Test(timeout = 100)
	public void testEnumStatusValues() throws ClassNotFoundException {
		try {
			Enum.valueOf((Class<Enum>) Class.forName(statusPath), "IDLE");
		} catch (IllegalArgumentException e) {
			fail("Status enum can be IDLE");
		}
		
		try {
			Enum.valueOf((Class<Enum>) Class.forName(statusPath), "MARCHING");	
		}catch(IllegalArgumentException e) {
			fail("Status enum can be MARCHING");
		}
		
		try {
			Enum.valueOf((Class<Enum>) Class.forName(statusPath), "BESIEGING");	
		}catch(IllegalArgumentException e) {
			fail("Status enum can be BESIEGING");
		}
    }
    @Test(timeout = 100)
   	public void testConstructorPlayerConstructor() throws ClassNotFoundException {

   		Class[] inputs = { String.class };
   		testConstructorExists(Class.forName(playerPath), inputs);
   	}

       @Test(timeout = 100)
   	public void testConstructorPlayerConstructorInitialization() throws Exception {
   		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
   		int random = (int) (Math.random() * 50);
   		String name = "name" + random;
   		Object myObj = constructor.newInstance(name);
   		String[] names = { "name" };
   		Object[] values = { name };
   		testConstructorInitialization(myObj, names, values);

   	}

       @Test(timeout = 100)
   	public void testInstanceVariablePlayerName() throws Exception {
   		testInstanceVariableIsPresent(Class.forName(playerPath), "name", true);
   		testInstanceVariableIsPrivate(Class.forName(playerPath), "name");
   		testInstanceVariableOfType(Class.forName(playerPath), "name", String.class);
   	}

    

       @Test(timeout = 100)
   	public void testInstanceVariablePlayerNameSetter() throws ClassNotFoundException {
   		testSetterMethodExistsInClass(Class.forName(playerPath), "setName", String.class, false);
   	}

     

       @Test(timeout = 100)
   	public void testInstanceVariablePlayerTreasuryGetterLogic() throws Exception {
   		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
   		int random = (int) (Math.random() * 50);
   		String name = "name" + random;
   		Object myObj = constructor.newInstance(name);
   		double randomDouble = (Math.random() * 50);
   		testGetterLogic(myObj, "treasury", randomDouble);
   	}

       @Test(timeout = 100)
   	public void testInstanceVariablePlayerTreasurySetter() throws ClassNotFoundException {
   		testSetterMethodExistsInClass(Class.forName(playerPath), "setTreasury", double.class, true);
   	}

       @Test(timeout = 100)
   	public void testInstanceVariablePlayerTreasurySetterLogic() throws Exception {
   		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
   		int random = (int) (Math.random() * 50);
   		String name = "name" + random;
   		Object myObj = constructor.newInstance(name);
   		double randomDouble = (Math.random() * 50);
   		testSetterLogic(myObj, "treasury", randomDouble,randomDouble, double.class);
   	}
       @Test(timeout = 100)
   	public void testInstanceVariablePlayerControlledCities() throws Exception {
   		testInstanceVariableIsPresent(Class.forName(playerPath), "controlledCities", true);
   		testInstanceVariableIsPrivate(Class.forName(playerPath), "controlledCities");
   		testInstanceVariableOfType(Class.forName(playerPath), "controlledCities", ArrayList.class);
   	}

   	

   	@Test(timeout = 100)
   	public void testInstanceVariablePlayerControlledCitiesSetter() throws ClassNotFoundException {
   		testSetterMethodExistsInClass(Class.forName(playerPath), "setControlledCities", ArrayList.class, false);
   	}

   	@Test(timeout = 100)
   	public void testInstanceVariablePlayerControlledArmies() throws Exception {
   		testInstanceVariableIsPresent(Class.forName(playerPath), "controlledArmies", true);
   		testInstanceVariableIsPrivate(Class.forName(playerPath), "controlledArmies");
   		testInstanceVariableOfType(Class.forName(playerPath), "controlledArmies", ArrayList.class);
   	}

   	@Test(timeout = 100)
   	public void testInstanceVariablePlayerControlledArmiesGetter() throws ClassNotFoundException {
   		testGetterMethodExistsInClass(Class.forName(playerPath), "getControlledArmies", ArrayList.class, true);
   	}
   	@Test(timeout = 100)
	public void testInstanceVariablePlayerControlledArmiesGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(playerPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		ArrayList<?> value = new ArrayList<Object>();
		testGetterLogic(myObj, "controlledArmies", value);
	}
   	@Test(timeout = 100)
	public void testInstanceVariablePlayerControlledArmiesSetter() throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(playerPath), "setControlledArmies", ArrayList.class, false);
	}
   	@Test(timeout = 100)
	public void testInstanceVariableCityName() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cityPath), "name",
				true);
		testInstanceVariableIsPrivate(Class.forName(cityPath), "name");
		testInstanceVariableOfType(Class.forName(cityPath),
				"name", String.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityNameGetter()
			throws Exception {
		testGetterMethodExistsInClass(Class.forName(cityPath),
				"getName", String.class, true);
	}
	
	
	@Test(timeout = 100)
	public void testInstanceVariableCityEconomicalBuildingsGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(cityPath),
				"getEconomicalBuildings", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityEconomicalBuildingsSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(cityPath),
				"setEconomicBuildings", ArrayList.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityEconomicalBuildingsGetterLogic()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		ArrayList<?> value = new ArrayList<Object>();
		testGetterLogic(myObj, "economicalBuildings", value);
	}
	@Test(timeout = 100)
	public void testInstanceVariableCityDefendingArmy() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cityPath), "defendingArmy",
				true);
		testInstanceVariableIsPrivate(Class.forName(cityPath), "defendingArmy");
		testInstanceVariableOfType(Class.forName(cityPath),
				"defendingArmy", Class.forName(armyPath));
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityDefendingArmyGetter()
			throws Exception {
		testGetterMethodExistsInClass(Class.forName(cityPath),
				"getDefendingArmy", Class.forName(armyPath), true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableCityDefendingArmySetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(cityPath), "setDefendingArmy",
				 Class.forName(armyPath), true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableCityDefendingArmySetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);

		Constructor<?> armyConstructor = Class.forName(armyPath)
				.getConstructor(String.class);

		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		
		Object army = armyConstructor.newInstance(name);

		testSetterLogic(myObj, "defendingArmy", army, army, Class.forName(armyPath));
	}
	
	
	@Test(timeout = 100)
	public void testInstanceVariableCityTurnsUnderSiegeGetterLogic()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);

		testGetterLogic(myObj, "turnsUnderSiege", random);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityTurnsUnderSiegeSetterLogic()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);

		int random = (int) (Math.random() * 50);

		String name = "name" + random;
		Object myObj = constructor.newInstance(name);
		testSetterLogic(myObj, "turnsUnderSiege", random, random, int.class);
	}

	

	@Test(timeout = 100)
	public void testInstanceVariableCityUnderSiegeSetter()
			throws Exception {
		testSetterMethodExistsInClass(Class.forName(cityPath),
				"setUnderSiege", boolean.class, true);
	}


	@Test(timeout = 100)
	public void testInstanceVariableCityUnderSiegeGetterLogic()
			throws Exception {
		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);

		testGetterLogic(myObj, "underSiege", true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCityUnderSiegeSetterLogics()
			throws Exception {

		Constructor<?> constructor = Class.forName(cityPath).getConstructor(
				String.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name);

		testSetterLogic(myObj, "underSiege", true, true, boolean.class);
		testSetterLogic(myObj, "underSiege", false, false, boolean.class);
	}

	@Test(timeout = 100)
	public void testConstructorCityConstructor()
			throws ClassNotFoundException {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(cityPath), inputs);
	}
	@Test(timeout = 1000)
	public void testClassIsSubclassMaxLevelException() throws Exception {
		testClassIsSubclass(Class.forName(mlePath), Class.forName(bePath));
	}

	@Test(timeout = 1000)
	public void testConstructorMaxLevelExceptionConstructor1() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(mlePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorMaxLevelExceptionConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(mlePath).getConstructor();

		Object myObj = constructor.newInstance();
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testClassIsSubclassFriendlyFireException() throws Exception {
		testClassIsSubclass(Class.forName(ffePath), Class.forName(aePath));
	}	
	
	@Test(timeout = 1000)
	public void testConstructorFriendlyFireExceptionConstructor1() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(ffePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorFriendlyFireExceptionConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(ffePath).getConstructor();

		Object myObj = constructor.newInstance();
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testClassIsSubclassFriendlyCityException() throws Exception {
		testClassIsSubclass(Class.forName(fcePath), Class.forName(aePath));
	}	
	
	@Test(timeout = 1000)
	public void testConstructorFriendlyCityExceptionConstructor1() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(fcePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorFriendlyCityExceptionConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(fcePath).getConstructor();

		Object myObj = constructor.newInstance();
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 1000)
	public void testClassIsSubclassTargetNotReachedException() throws Exception {
		testClassIsSubclass(Class.forName(tnrePath), Class.forName(aePath));
	}	
	
	@Test(timeout = 1000)
	public void testConstructorTargetNotReachedExceptionConstructor1() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(tnrePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorTargetNotReachedExceptionConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(tnrePath).getConstructor();

		Object myObj = constructor.newInstance();
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 100)
	public void testClassIsAbstractEmpireException() throws Exception {
		testClassIsAbstract(Class.forName(eePath));
	}

	@Test(timeout = 1000)
	public void testConstructorEmpireException1()
			throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(eePath), inputs);
	}
	@Test(timeout = 100)
	public void testClassIsSubclassEmpireException() throws Exception {
		testClassIsSubclass(Class.forName(eePath), Exception.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractBuildingException() throws Exception {
		testClassIsAbstract(Class.forName(bePath));
	}

	@Test(timeout = 1000)
	public void testConstructorBuildingException1()
			throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(bePath), inputs);
	}
	@Test(timeout = 100)
	public void testClassIsSubclassBuildingException() throws Exception {
		testClassIsSubclass(Class.forName(bePath), Class.forName(eePath));
	}

	@Test(timeout = 100)
	public void testClassIsAbstractArmyException() throws Exception {
		testClassIsAbstract(Class.forName(aePath));
	}

	@Test(timeout = 1000)
	public void testConstructorArmyException1()
			throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(aePath), inputs);
	}
	@Test(timeout = 100)
	public void testClassIsSubclassArmyException() throws Exception {
		testClassIsSubclass(Class.forName(aePath), Class.forName(eePath));
	}

	@Test(timeout = 1000)
	public void testConstructorBuildingInCoolDownException1()
			throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(bicdePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorBuildingInCoolDownConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(bicdePath).getConstructor();

		Object myObj = constructor.newInstance();
		
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);

	}
	@Test(timeout = 100)
	public void testClassIsSubclassBuildingInCoolDownException() throws Exception {
		testClassIsSubclass(Class.forName(bicdePath), Class.forName(bePath));
	}
	
	@Test(timeout = 1000)
	public void testConstructorMaxRecruitedException1()
			throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(mrePath), inputs);
	}
	@Test(timeout = 1000)
	public void testConstructorMaxRecruitedExceptionConstructor1Initialization() throws Exception {
		Constructor<?> constructor = Class.forName(mrePath).getConstructor();

		Object myObj = constructor.newInstance();
		String[] names = {};
		Object[] values = {};
		testConstructorInitialization(myObj, names, values);
	}
	@Test(timeout = 100)
	public void testClassIsSubclassMaxRecruitedException() throws Exception {
		testClassIsSubclass(Class.forName(mrePath), Class.forName(bePath));
	}
	
	@Test(timeout = 100)
	public void testClassIsAbstractUnit() throws Exception {
		testClassIsAbstract(Class.forName(unitPath));
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitLevel() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "level", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "level");
		testInstanceVariableIsPresent(Class.forName(archerPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "level", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "level", false);
		testInstanceVariableOfType(Class.forName(unitPath), "level", int.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitLevelGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath), "getLevel", int.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getLevel", int.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getLevel", int.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getLevel", int.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("level", subClasses, int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitLevelGetterLogic()
			throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		int random6 = (int) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testGetterLogic(arch, "level", random6);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitLevelSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setLevel", int.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("level", subClasses);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitMaxSoldierCount() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "maxSoldierCount", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "maxSoldierCount");
		testInstanceVariableIsPresent(Class.forName(archerPath), "maxSoldierCount", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "maxSoldierCount", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "maxSoldierCount", false);
		testInstanceVariableOfType(Class.forName(unitPath), "maxSoldierCount", int.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitMaxSoldierCountGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath),"getMaxSoldierCount", int.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getMaxSoldierCount", int.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getMaxSoldierCount", int.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getMaxSoldierCount", int.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("maxSoldierCount", subClasses, int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitMaxSoldierCountGetterLogic()
			throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		int random6 = (int) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testGetterLogic(arch, "maxSoldierCount", random6);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitMaxSoldierCountSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setMaxSoldierCount", int.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("maxSoldierCount", subClasses);
	}
	
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitIdleUpkeep() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "idleUpkeep", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "idleUpkeep");
		testInstanceVariableIsPresent(Class.forName(archerPath), "idleUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "idleUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "idleUpkeep", false);
		testInstanceVariableOfType(Class.forName(unitPath), "idleUpkeep", double.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitIdleUpkeepGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath), "getIdleUpkeep", double.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getIdleUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getIdleUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getIdleUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("idleUpkeep", subClasses, double.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitIdleUpkeepGetterLogic()
			throws Exception {
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		double random6 = (double) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		testGetterLogic(arch, "idleUpkeep", random6);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitIdleUpkeepSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setIdleUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("idleUpkeep", subClasses);
	}
	
	
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitMarchingUpkeep() throws Exception {
		testInstanceVariableIsPresent(Class.forName(unitPath), "marchingUpkeep", true);
		testInstanceVariableIsPrivate(Class.forName(unitPath), "marchingUpkeep");
		testInstanceVariableIsPresent(Class.forName(archerPath), "marchingUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(infantryPath), "marchingUpkeep", false);
		testInstanceVariableIsPresent(Class.forName(cavalryPath), "marchingUpkeep", false);
		testInstanceVariableOfType(Class.forName(unitPath), "marchingUpkeep", double.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableUnitMarchingUpkeepGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(unitPath), "getMarchingUpkeep", double.class, true);
		testGetterMethodExistsInClass(Class.forName(archerPath), "getMarchingUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(infantryPath), "getMarchingUpkeep", double.class, false);
		testGetterMethodExistsInClass(Class.forName(cavalryPath), "getMarchingUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testGetterAbsentInSubclasses("marchingUpkeep", subClasses, double.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableUnitMarchingUpkeepGetterLogic()
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
	public void testInstanceVariableUnitMarchingUpkeepSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(unitPath), "setMarchingUpkeep", double.class, false);
		String[] subClasses = {archerPath, infantryPath, cavalryPath};
		testSetterAbsentInSubclasses("marchingUpkeep", subClasses);
	}
	
	
	@Test(timeout = 100)
	public void testConstructorUnitConstructor()
			throws Exception {
		Class[] inputs = { int.class, int.class, double.class, double.class, double.class };
		testConstructorExists(Class.forName(unitPath), inputs);
	}
	
	
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentStatus() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "currentStatus", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "currentStatus");
		testInstanceVariableOfType(Class.forName(armyPath), "currentStatus", Class.forName(statusPath));
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentStatusGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getCurrentStatus", Class.forName(statusPath), true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentStatusGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		Object s = Enum.valueOf((Class<Enum>) Class.forName(statusPath),"IDLE");
		testGetterLogic(a, "currentStatus", s);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentStatusSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setCurrentStatus", Class.forName(statusPath), true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentStatusSetterLogic() throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		Object s = Enum.valueOf((Class<Enum>) Class.forName(statusPath),"IDLE");
		testSetterLogic(a, "currentStatus", s, s, Class.forName(statusPath));
	}
		
	
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyUnits() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "units", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "units");
		testInstanceVariableOfType(Class.forName(armyPath), "units", ArrayList.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyUnitsGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getUnits", ArrayList.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableArmyUnitsGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		ArrayList<?> value = new ArrayList<Object>();
		testGetterLogic(a, "units", value);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyUnitsSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setUnits", ArrayList.class, true);
	}
	
	@Test(timeout = 1000)
	public void testInstanceVariableArmyUnitsSetterLogic() throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		random = (int) (Math.random() * 10);
		ArrayList<Object> values = new ArrayList<Object>();
		for(int i=0;i<random;i++){
		Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
		int random1 = (int) (Math.random() * 4);
		int random2 = (int) (Math.random() * 50);
		double random3 = (double) (Math.random() * 50);
		double random4 = (double) (Math.random() * 50);
		double random5 = (double) (Math.random() * 50);
		Object arch = archerConstructor.newInstance(random1, random2, random3, random4, random5);
		values.add(arch);
		}
		testSetterLogic(a, "units", values, values, ArrayList.class);
	}
	@Test(timeout = 100)
	public void testInstanceVariableArmyTarget() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "target", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "target");
		testInstanceVariableOfType(Class.forName(armyPath), "target", String.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyTargetGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getTarget", String.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableArmyTargetGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		testGetterLogic(a, "target", "");
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyTargetSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setTarget", String.class, true);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyTargetSetterLogic() throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a1 = armyConstructor.newInstance("Location_" + random);
		random = (int) (Math.random() * 50);
		String a2 = "Army_" + random;
		testSetterLogic(a1, "target", a2, a2, String.class);
	}
	
	
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentLocation() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "currentLocation", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "currentLocation");
		testInstanceVariableOfType(Class.forName(armyPath), "currentLocation", String.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentLocationGetter()
			throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(armyPath),"getCurrentLocation", String.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentLocationGetterLogic()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		testGetterLogic(a, "currentLocation", "");
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentLocationSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(armyPath), "setCurrentLocation", String.class, true);
	}
	@Test(timeout = 100)
	public void testInstanceVariableArmyMaxToHold() throws Exception {
		testInstanceVariableIsPresent(Class.forName(armyPath), "maxToHold", true);
		testInstanceVariableIsPrivate(Class.forName(armyPath), "maxToHold");
		testInstanceVariableIsFinal(Class.forName(armyPath), "maxToHold");
		testInstanceVariableOfType(Class.forName(armyPath), "maxToHold", int.class);
	}
	
	@Test(timeout = 100)
	public void testInstanceVariableArmyCurrentLocationSetterLogic() throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		Object a = armyConstructor.newInstance("Location_" + random);
		random = (int) (Math.random() * 50);
		String l = "Location_" + random;
		testSetterLogic(a, "currentLocation", l, l, String.class);
	}
	
	@Test(timeout = 100)
	public void testConstructorArmyConstructor()
			throws Exception {
		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(armyPath), inputs);
	}
	
	@Test(timeout = 100)
	public void testConstructorArmyConstructorInitialization()
			throws Exception {
		Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
		int random = (int) (Math.random() * 50);
		String l = "Location_" + random;
		Object myObj = armyConstructor.newInstance(l);
		String[] names = { "currentStatus", "units", "distancetoTarget", "target", "currentLocation", "maxToHold" };
		Object s = Enum.valueOf((Class<Enum>) Class.forName(statusPath),"IDLE");
		random = (int) (Math.random() * 10);
		ArrayList<Object> units = new ArrayList<Object>();
		random = (int) (Math.random() * 50);
		String a = "";		
		Object[] values = { s, units, -1, a, l, 10 };
		testConstructorInitialization(myObj, names, values);
	}
	
	
	
	@Test(timeout = 100)
	public void testClassIsSubclassArcher() throws Exception {
		testClassIsSubclass(Class.forName(archerPath), Class.forName(unitPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorArcherConstructor()
			throws Exception {
		Class[] inputs = { int.class, int.class, double.class, double.class, double.class };
		testConstructorExists(Class.forName(archerPath), inputs);
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
		Object[] values = { random1, random2, 0, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	
	
	
	@Test(timeout = 100)
	public void testClassIsSubclassInfantry() throws Exception {
		testClassIsSubclass(Class.forName(infantryPath), Class.forName(unitPath));
	}
	
	@Test(timeout = 100)
	public void testConstructorInfantryConstructor()
			throws Exception {
		Class[] inputs = { int.class, int.class, double.class, double.class, double.class };
		testConstructorExists(Class.forName(infantryPath), inputs);
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
		Object[] values = { random1, random2, 0, random3, random4, random5 };
		testConstructorInitialization(myObj, names, values);
	}
	
	@Test(timeout = 100)
	public void testConstructorDistance() throws Exception {
		Class[] inputs = {String.class, String.class, int.class};
		testConstructorExists(Class.forName(distancePath), inputs);
		Constructor<?> constructor = Class.forName(distancePath).getConstructor(String.class, String.class, int.class);
		Object b = constructor.newInstance("Cairo", "Rome", 500);
		String[] varNames = {"from", "to", "distance"};
		Object[] varValues = {"Cairo", "Rome", 500};
		testConstructorInitialization(b, varNames, varValues);

	}
	
	@Test(timeout = 100)
	public void testDistanceInstanceVariableFrom() throws Exception {
		testInstanceVariableIsPresent(Class.forName(distancePath),
				"from", true);
		
		testInstanceVariableIsPrivate(Class.forName(distancePath),
				"from");
		testInstanceVariableOfType(Class.forName(distancePath),
				"from", String.class);
	}

	@Test(timeout = 100)
	public void testDistanceFromSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(distancePath),"setFrom", String.class, false);
	}
	
	@Test(timeout = 100)
	public void testDistanceFromGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(distancePath),"getFrom", String.class, true);
		
		Constructor<?> constructor = Class.forName(distancePath).getConstructor(
				String.class, String.class, int.class);
		String fromCairo = "Cairo";
		String toRome = "Rome";
		int randomDistance = (int) (Math.random() * 450) + 50;
		Object b = constructor.newInstance(fromCairo, toRome, randomDistance);
		testGetterLogic(b, "from", "Alexandria");
	}
	@Test(timeout = 100)
	public void testDistanceInstanceVariableDistance() throws Exception {
		testInstanceVariableIsPresent(Class.forName(distancePath),
				"distance", true);
		
		testInstanceVariableIsPrivate(Class.forName(distancePath),
				"distance");
		testInstanceVariableOfType(Class.forName(distancePath),
				"distance", int.class);
	}

	@Test(timeout = 100)
	public void testDistanceDistanceSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(distancePath),"setDistance", int.class, false);
	}
	
	@Test(timeout = 100)
	public void testDistanceDistanceGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(distancePath),"getDistance", int.class, true);
		
		Constructor<?> constructor = Class.forName(distancePath).getConstructor(
				String.class, String.class, int.class);
		String fromCairo = "Cairo";
		String toRome = "Rome";
		int randomDistance = (int) (Math.random() * 450) + 50;
		Object b = constructor.newInstance(fromCairo, toRome, randomDistance);
		randomDistance = (int) (Math.random() * 450) + 50;
		testGetterLogic(b, "distance", randomDistance);
	}
	@Test(timeout = 100)
	public void testGameInstanceVariablePlayer() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath),
				"player", true);
		
		testInstanceVariableIsPrivate(Class.forName(gamePath),
				"player");
		testInstanceVariableOfType(Class.forName(gamePath),
				"player", Class.forName(playerPath));
	}

	@Test(timeout = 100)
	public void testGamePlayerSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath),"setPlayer", Class.forName(playerPath), true);
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		Constructor<?> constructorP = Class.forName(playerPath).getConstructor(
				String.class);
		Object p = constructorP.newInstance(name);
		testSetterLogic(b, "player", p, p, Class.forName(playerPath));
	}
	
	@Test(timeout = 100)
	public void testGamePlayerGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(gamePath),"getPlayer", Class.forName(playerPath), true);
		
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		Constructor<?> constructorP = Class.forName(playerPath).getConstructor(
				String.class);
		Object p = constructorP.newInstance(name);
		testGetterLogic(b, "player", p);
	}
	
	@Test(timeout = 100)
	public void testGameInstanceVariableAvailableCities() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath),
				"availableCities", true);
		
		testInstanceVariableIsPrivate(Class.forName(gamePath),
				"availableCities");
		testInstanceVariableOfType(Class.forName(gamePath),
				"availableCities", ArrayList.class);
	}

	@Test(timeout = 100)
	public void testGameAvailableCitiesSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath),"setAvailableCities", ArrayList.class, false);
	}
	
	@Test(timeout = 100)
	public void testGameAvailableCitiesGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(gamePath),"getAvailableCities", ArrayList.class, true);
		
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		testGetterLogic(b, "availableCities", new ArrayList<>());
	}
	@Test(timeout = 100)
	public void testGameInstanceVariableMaxTurnCount() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath),
				"maxTurnCount", true);
		
		testInstanceVariableIsPrivate(Class.forName(gamePath),
				"maxTurnCount");
		testInstanceVariableOfType(Class.forName(gamePath),
				"maxTurnCount", int.class);
	}

	@Test(timeout = 100)
	public void testGameMaxTurnCountSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath),"setMaxTurnCount", int.class, false);
	}
	
	@Test(timeout = 100)
	public void testGameMaxTurnCountGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(gamePath),"getMaxTurnCount", int.class, true);
		
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		testGetterLogic(b, "maxTurnCount", 30);
	}
	
	@Test(timeout = 100)
	public void testGameInstanceVariableCurrentTurnCount() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath),
				"currentTurnCount", true);
		
		testInstanceVariableIsPrivate(Class.forName(gamePath),
				"currentTurnCount");
		testInstanceVariableOfType(Class.forName(gamePath),
				"currentTurnCount", int.class);
	}

	@Test(timeout = 100)
	public void testGameCurrentTurnCountSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath),"setCurrentTurnCount", int.class, true);
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		int randomTurnCount = (int) (Math.random()*29) +1;
		testSetterLogic(b, "currentTurnCount", randomTurnCount, randomTurnCount, int.class);
		
	}
	
	@Test(timeout = 100)
	public void testGameCurrentTurnCountGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(gamePath),"getCurrentTurnCount", int.class, true);
		
		Constructor<?> constructor = Class.forName(gamePath).getConstructor(
				String.class, String.class);
		String name = "name" + (int)(Math.random()*10);
		Object b = constructor.newInstance(name, "Cairo");
		testGetterLogic(b, "currentTurnCount", 30);
	}
	@Test(timeout = 1000)
	public void testLoadArmyMethod()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException, NoSuchFieldException {
	
		PrintWriter csvWriter = new PrintWriter("alex_city.csv");
		csvWriter.println("Archer,3");
		csvWriter.println("Infantry,2");
		csvWriter.println("Cavalry,1");
		csvWriter.flush();
		csvWriter.close();
		Constructor<?> gameConstructor = Class.forName(gamePath).getConstructor(String.class, String.class);
		Object createdGame = gameConstructor.newInstance("player", "Cairo");



		Constructor<?> cityConstructor = Class.forName(cityPath).getConstructor(String.class);
		Object alexCity = cityConstructor.newInstance("Alex");

		Field fac = Class.forName(gamePath).getDeclaredField("availableCities");
		fac.setAccessible(true);
		ArrayList<Object> avCities = (ArrayList<Object>)fac.get(createdGame);
		
		avCities.add(alexCity);

		Class[] args = {String.class, String.class};
		Method m = createdGame.getClass().getMethod("loadArmy", args);
		Object[] argsIn = {"Alex", "alex_city.csv"};
		m.invoke(createdGame, argsIn);

		Field fa = Class.forName(cityPath).getDeclaredField("defendingArmy");
		fa.setAccessible(true);
		Object alexArmy = fa.get(alexCity); 

		try {
			
			Constructor<?> armyConstructor = Class.forName(armyPath).getConstructor(String.class);
			Object armyCreated = armyConstructor.newInstance("Alex");
			ArrayList<Object> armyUnits = new ArrayList<Object>();
			Constructor<?> archerConstructor = Class.forName(archerPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> infantryConstructor = Class.forName(infantryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Constructor<?> cavalryConstructor = Class.forName(cavalryPath).getConstructor(int.class, int.class, double.class, double.class, double.class);
			Object item = null;
			item = archerConstructor.newInstance(3, 70, 0.5, 0.6, 0.7);
			armyUnits.add(item);
			item = infantryConstructor.newInstance(2, 50, 0.5, 0.6, 0.7);
			armyUnits.add(item);
			item = cavalryConstructor.newInstance(1, 40, 0.6, 0.7, 0.75);
			armyUnits.add(item);

			Field f = Class.forName(armyPath).getDeclaredField("units");
			f.setAccessible(true);
			f.set(armyCreated, armyUnits);
			
			
			boolean dynamicArmy = checkArmiesEqual("Alex",armyCreated, alexArmy);
			
			assertTrue("Army Units aren't loaded dynamically from a CSV file", dynamicArmy);

			File test = new File("alex_city.csv");
			test.delete();
		} catch (Exception e) {
			File test = new File("alex_city.csv");
			test.delete();
		}

	}

	@Test(timeout = 1000)
	public void testLoadCitiesAndDistancesMethod()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		File test = new File("distances.csv");
		test.delete();
		PrintWriter csvWriter = new PrintWriter("distances.csv");
		csvWriter.println("Cairo,Rome,4");
		csvWriter.println("Cairo,Sparta,3");
		csvWriter.println("Sparta,Rome,6");
		csvWriter.flush();
		csvWriter.close();
		Constructor<?> gameConstructor = Class.forName(gamePath).getConstructor(String.class, String.class);
		Object createdGame = gameConstructor.newInstance("player", "Cairo");
		Class[] args = {};
		Method m = createdGame.getClass().getDeclaredMethod("loadCitiesAndDistances", args);
		m.setAccessible(true);
		Object[] argsIn = {};
		m.invoke(createdGame, argsIn);
		try {
			Constructor<?> distanceConstructor = Class.forName(distancePath).getConstructor(String.class, String.class, int.class);
			ArrayList<Object> dis = new ArrayList();
			dis.add(distanceConstructor.newInstance("Cairo", "Rome", 4));
			dis.add(distanceConstructor.newInstance("Cairo", "Sparta", 3));
			dis.add(distanceConstructor.newInstance("Sparta", "Rome", 6));
			
			Field fd = Class.forName(gamePath).getDeclaredField("distances");
			fd.setAccessible(true);
			
			ArrayList<Object> actualDistances = (ArrayList<Object>) fd.get(createdGame);
			
			boolean dynamicDis = true;
			for(int i = 0; i < dis.size(); i ++)
			{
				dynamicDis = dynamicDis && checkDistancesEqual(dis.get(i), actualDistances.get(i));
			}
			
			assertTrue("Distances aren't loaded dynamically from a CSV file", dynamicDis);

			test = new File("distances.csv");
			test.delete();
		} catch (Exception e) {
			test = new File("distances.csv");
			test.delete();
		}
		csvWriter = new PrintWriter("distances.csv");
		csvWriter.println("Cairo,Rome,6");
		csvWriter.println("Cairo,Sparta,5");
		csvWriter.println("Sparta,Rome,9");
		csvWriter.flush();
		csvWriter.close();

	}


	@Test(timeout = 800)
	public void testGameConstructorWithCityCairo() throws Exception {
		Class[] inputs = {String.class, String.class};
		testConstructorExists(Class.forName(gamePath), inputs);
		testGameConstructorWithCity("Cairo");
		
		
	}
	@Test(timeout = 800)
	public void testGameConstructorWithCityRome() throws Exception {
		Class[] inputs = {String.class, String.class};
		testConstructorExists(Class.forName(gamePath), inputs);
		testGameConstructorWithCity("Rome");
		
		
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
			assertEquals("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " should not be accessed outside that class.", false, f.isAccessible());
		}

		private void testInstanceVariableIsFinal(Class aClass, String varName)
				throws NoSuchFieldException, SecurityException {
			Field f = aClass.getDeclaredField(varName);
			assertEquals("The value of \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " should not be open for changes.", 18, f.getModifiers());

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

		private void testClassIsAbstract(Class aClass) {
			assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.",
					Modifier.isAbstract(aClass.getModifiers()));
		}

		

		private void testIsEnum(Class aClass) {

			assertEquals(aClass.getName() + " should be an Enum", true, aClass.isEnum());

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