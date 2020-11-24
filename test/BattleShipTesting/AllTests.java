package BattleShipTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            BattleShipTesting.BoardTests.class,
            BattleShipTesting.LocalBoardTests.class,
            BattleShipTesting.ViewTests.class
        }
)
public class AllTests {
}
