# TheConqueror
A single player turn-based empire building game. A player
initially chooses one historical city to start his empire with. The goal is to conquer the whole
world by taking control over every other city under a certain amount of turns. In order to
achieve this goal, the player have the option of building various types of building in any city he has control over and also build armies in order to conquer other cities.

## Contents 
1. OOP concepts (Inheritance - Polymorphism - Abstraction - Encapsulation ).
2. Exception Handling.
3. GUI 

## Structure 
```bash
src/
├── buildings
│   ├── ArcheryRange.java
│   ├── Barracks.java
│   ├── Building.java
│   ├── EconomicBuilding.java
│   ├── Market.java
│   └── ..............
├── controllers
│   └── Controller.java
├── engine
│   ├── City.java
│   ├── Distance.java
│   ├── Game.java
│   ├── Player.java
│   └── ............
├── exceptions
│   ├── ArmyException.java
│   ├── BuildingException.java
│   ├── BuildingInCoolDownException.java
│   ├── FriendlyCityException.java
│   ├── FriendlyFireException.java
│   └── .......................
├── units
│   ├── Archer.java
│   ├── Army.java
│   ├── Cavalry.java
│   ├── Infantry.java
│   ├── Status.java
│   └── ................
├── utlis
│   └── ReadingCSVFile.java
└── views
    ├── button
    │   ├── ArmyButton.java
    │   ├── CityButton.java
    │   ├── StyledButton.java
    │   └── UnitButton.java
    ├── MyInputVerifier.java
    ├── panel
    │   ├── ArmyPanel.java
    │   ├── BuildingPanel.java
    │   ├── CardsPanel.java
    │   ├── MilitaryBuildingPanel.java
    │   ├── PlayerPanel.java
    │   └── .....................
    ├── RXCardLayout.java
    └── view
        ├── BattleView.java
        ├── CityView.java
        ├── EndGameView.java
        ├── StartView.java
        └── ..............

```

## Authors 
1. [Shimaa Ahmed](https://github.com/ShimaaBetah)
2. [Ahmed Shaawray](https://github.com/shaarawy29)
3. [Ibrahim Abou Elenein](https://github.com/aboueleyes)

