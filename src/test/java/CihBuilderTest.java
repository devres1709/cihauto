import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class CihBuilderTest {
    @Test
    public void doBuild() throws IOException, InterruptedException {
        ProjectBuilder.build("buildCih");
    }

    @Test
    public void test(){
        System.out.println(UUID.randomUUID());
    }

}
