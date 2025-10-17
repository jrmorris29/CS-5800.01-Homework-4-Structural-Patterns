import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import bridge.BridgePatternTest;
import decorator.DecoratorPatternTest;

@Suite
@SelectClasses({
        BridgePatternTest.class,
        DecoratorPatternTest.class
})
public class CompleteUnitTest {
}
