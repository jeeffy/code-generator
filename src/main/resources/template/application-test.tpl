package ${packageName};

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "com", excludeFilters = {
    @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Application.class),
    @Filter(type = FilterType.REGEX, pattern = ".*Init")
})
@SpringBootApplication
public class ApplicationTest {
}
