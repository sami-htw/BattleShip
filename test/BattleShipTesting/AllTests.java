package BattleShipTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
    {
        BoardTests.class,
        LocalBoardTests.class,
        ViewTests.class
    }
)
public class AllTests {

}
