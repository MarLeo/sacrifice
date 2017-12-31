package dao;

import api.Chien;
import api.ChienDao;
import enums.RaceDeChien;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static enums.RaceDeChien.*;

public class PoiChienDaoTest {

    private static final Logger logger = Logger.getLogger(PoiChienDaoTest.class);

    private static final String file = "src/test/resources/chien-01.xlsx";

    private ChienDao dao;

    @Before
    public void before() {
        dao = new PoiChienDao(file);
    }

    @Test
    public void testFindAllChien() {

        logger.debug("testFindAllchien");

        final int expectedSize = 5;

        List<Chien> chiens = dao.findAllChiens();

        Assert.assertNotNull(chiens);
        Assert.assertEquals(expectedSize, chiens.size());
    }

    @Test
    public void testOrdreDesChiens() {

        logger.debug("testOrdreDesChiens");

        // Arrange
        final String[] expectedNoms = {"Milou", "Pluto", "Lassie", "Volt", "Medor"};

        // Act
        final List<Chien> chiens = dao.findAllChiens();

        // Assert
        for (int i = 0; i < expectedNoms.length; i++) {
            Assert.assertEquals(expectedNoms[i], chiens.get(i).getNom());
        }
    }

    @Test
    public void testTaillesDesChiens() {
        logger.debug("testTaillesDesChiens");

        // Arrange
        final double[] expectedPoids = {12.5, 24, 32.3, 14, 32};

        // Act
        final List<Chien> chiens = dao.findAllChiens();

        // Assert
        for (int i = 0; i < expectedPoids.length; i++) {
            Assert.assertEquals(expectedPoids[i], chiens.get(i).getPoids(), 0.001d);
        }
    }

    @Test
    public void testRacesDesChiens() {
        logger.debug("testRacesDesChiens");

        // Arrange
        final RaceDeChien[] expectedRaces = {CANICHE, GOLDEN, BERGER_ALLEMAND, CANICHE, ROTTWEILER};

        // Act
        final List<Chien> chiens = dao.findAllChiens();

        // Assert
        for (int i = 0; i < expectedRaces.length; i++) {
            Assert.assertEquals(expectedRaces[i], chiens.get(i).getRace());
        }
    }

}
