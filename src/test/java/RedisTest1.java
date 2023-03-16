import com.eunxxun.Application;
import com.eunxxun.config.RedisRepositoryConfig;
import com.eunxxun.domain.Point;
import com.eunxxun.domain.PointRedisRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest1 {

    @Autowired
    private PointRedisRepository pointRedisRepository;

    @After
    public void levelDown() throws Exception {
        pointRedisRepository.deleteAll();
    }

    @Test
    public void normal_select() {
        String id = "esunny";
        LocalDateTime refreshTime = LocalDateTime.of(2023, 3, 16, 0,0);
        Point point = Point.builder()
                .id(id)
                .amount(1000L)
                .refreshTime(refreshTime)
                .build();

        pointRedisRepository.save(point);

        Point savedPoint = pointRedisRepository.findById(id).get();
        assertThat(savedPoint.getAmount()).isEqualTo(1000L);
        assertThat(savedPoint.getRefreshTime()).isEqualTo(refreshTime);
    }
}
