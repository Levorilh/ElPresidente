package company;

import com.company.Country;
import com.company.DifficultyLevel;
import com.company.Faction;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;

public class CountryTest {
    @Test
    public void Should_containAllFactions_When_createdByFault() {
        Country c = new Country("testCountry" , DifficultyLevel.EASY.minimalSatisfaction);
        StringBuilder sb = new StringBuilder();
        for (Faction faction : c.getListFaction()) {
                sb.append(String.format("%s ",faction.getName()));
        }
        assertThat(sb.toString(),  containsString(
                "Capitalistes "+
                        "Communistes "+
                        "Libéraux "+
                        "Religieux "+
                        "Militaristes "+
                        "Ecologistes "+
                        "Nationnalistes "+
                        "Loyalistes "
        ));
    }

    @Test
    public void Should_neverAddUpOver100ForIndustryAndFood_When_modifiedCountry(){
        int BASE_FARMING = 10;
        int BASE_INDUSTRY = 50;
        Country c = new Country("testCountry", null, 0, 0, BASE_INDUSTRY, BASE_FARMING, DifficultyLevel.EASY.minimalSatisfaction);

        c.addFarming(70);
        Assert.assertEquals(c.getFarming(),BASE_FARMING);

        c.addIndustry(60);
        Assert.assertEquals(c.getIndustry(),BASE_INDUSTRY);
    }
}
