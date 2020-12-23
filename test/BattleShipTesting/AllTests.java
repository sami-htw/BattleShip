package BattleShipTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
    {
        BoardTests.class,
        LocalBoardTests.class,
        ViewTests.class,
        InputTests.class,
    }
)
public class AllTests {

}
