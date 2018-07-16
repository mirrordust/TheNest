package mapper;

import com.mirrordust.telcodata.entity.Holder;
import com.mirrordust.telcodata.mapper.HolderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan("com.mirrordust.telcodata.mapper")
public class HolderMapperTest {

    @Autowired
    private HolderMapper holderMapper;

    @Test
    public void testQuery() {
        List<Holder> holders = holderMapper.getAll();
        System.out.println(holders.toString());
    }

    @Test
    public void testQueryByImei() {
        Holder holder = holderMapper.findByImei("861374033830412");
        System.out.println(holder);
    }
}
