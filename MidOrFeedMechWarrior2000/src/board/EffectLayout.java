package board;

import java.util.ArrayList;
import java.util.List;
import visualeffects.LaserBlast;

public class EffectLayout {

  LaserBlast laserBlastHero;

  public EffectLayout() {
    laserBlastHero = new LaserBlast();
  }

  public void createEffects() {
    laserBlastHero = new LaserBlast();
  }

  public LaserBlast getLaserBlastHero() {
    return laserBlastHero;
  }

  public void setLaserBlastHero(LaserBlast laserBlastHero) {
    this.laserBlastHero = laserBlastHero;
  }

  public List<LaserBlast> getListOfLaserBlasts() {
    List<LaserBlast> listOfLaserBlasts = new ArrayList<>();
    listOfLaserBlasts.add(laserBlastHero);
    return listOfLaserBlasts;
  }
}
