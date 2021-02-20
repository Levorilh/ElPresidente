package company;

import com.company.Country;
import com.company.DifficultyLevel;
import com.company.Faction;
import com.company.ListFaction;
import org.junit.Assert;
import org.junit.Test;

public class FactionTest {

    @Test
    public void Should_ReturnCorrectBribeAmount_When_Brided(){
        int BASE_PARTISANT = 8;
        Faction fac = new Faction("testFaction" , 10 , BASE_PARTISANT);

        Assert.assertEquals(fac.getBribePrice() , BASE_PARTISANT*Faction.BRICE_COST_PER_PARTISANT);
    }

    @Test
    public void Should_UpdateSatisfaction_When_Brided(){
        int BASE_SATISFACTION = 10;
        Faction fac = new Faction("testFaction" , BASE_SATISFACTION , 8);

        Assert.assertEquals(fac.bribe().getSatisfaction(), (int)(BASE_SATISFACTION *Faction.BRIBE_UPGRADE));
    }

    @Test
    public void Should_LowerLoyalistsSatisfaction_When_AnyIsBrided() {
        Country c = new Country("countryTest" , DifficultyLevel.EASY.minimalSatisfaction);

        ListFaction lf = c.getListFaction();
        c.addTreasury(1000);
        c.bribe(lf.get(0));

        Assert.assertEquals(lf.get(lf.size() - 1).getSatisfaction() , 100-15*15/10);
    }


}
