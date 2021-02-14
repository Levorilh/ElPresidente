package company;

import com.company.Country;
import com.company.DifficultyLevel;
import com.company.Faction;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

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
                        "Nationalistes "+
                        "Loyalistes "
        ));

    }
}
