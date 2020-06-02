package experiencesystem;

import java.util.List;
import units.Mech;
import units.Unit;

public class LevelUpManager {

  public void grantXpToMechs(Unit unitTargeted, List<Mech> listOfMechs) {
    if (unitTargeted.getUnitType().contains("creep")) {
      if (unitTargeted.getUnitType().contains("Enemy")) {
        for (int i = 0; i < listOfMechs.size(); i++) {
          if (listOfMechs.get(i).getUnitType().contains("Hero") &&
                  listOfMechs.get(i).isAlive() &&
                  listOfMechs.get(i).getLevel() < 6 &&
                  listOfMechs.get(i).calculateDistanceBetweenUnits(unitTargeted) < 216) {
            listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + unitTargeted.getXpBounty().get(unitTargeted.getLevel()));
            checkLevelUpMech(listOfMechs.get(i));
          }
        }
      } else {
        for (int i = 0; i < listOfMechs.size(); i++) {
          if (listOfMechs.get(i).getUnitType().contains("Enemy") &&
                  listOfMechs.get(i).isAlive() &&
                  listOfMechs.get(i).getLevel() < 6 &&
                  listOfMechs.get(i).calculateDistanceBetweenUnits(unitTargeted) < 216) {
            listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + unitTargeted.getXpBounty().get(unitTargeted.getLevel()));
            checkLevelUpMech(listOfMechs.get(i));
          }
        }
      }
    }

    if (unitTargeted.getUnitType().equals("mechEnemy")) {
      for (int i = 0; i < listOfMechs.size(); i++) {
        if (listOfMechs.get(i).getUnitType().contains("Hero") &&
                listOfMechs.get(i).isAlive()) {
          listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + unitTargeted.getXpBounty().get(unitTargeted.getLevel()));
          checkLevelUpMech(listOfMechs.get(i));
        }
      }
    } else if (unitTargeted.getUnitType().equals("mechHero")) {
      for (int i = 0; i < listOfMechs.size(); i++) {
        if (listOfMechs.get(i).getUnitType().contains("mechEnemy") &&
                listOfMechs.get(i).isAlive()) {
          listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + unitTargeted.getXpBounty().get(unitTargeted.getLevel()));
          checkLevelUpMech(listOfMechs.get(i));
        }
      }
    }
  }

  private void checkLevelUpMech(Mech mech) {
    if (mech.getLevel() < 6) {
      int nextLevel = mech.getLevel() + 1;
      int xpLimitNextLevel = mech.getXpNeededForLevelUp().get(nextLevel);
      if (mech.getExperiencePoints() >= xpLimitNextLevel && mech.getLevel() < 6) {
        mech.setLevel(mech.getLevel() + 1);
        mech.levelUpMech();
      }
    }
  }
}
