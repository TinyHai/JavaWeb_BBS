import bbs.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
@WebAppConfiguration
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        Assert.notNull(userMapper.getAccountByName("tiny"), "getUserByName return null");
        Assert.notNull(userMapper.getUserByEmail("1234@qq.com"), "getUserByEmail return null");
        Assert.notNull(userMapper.getUsers(), "getUsers return null");
        Assert.notEmpty(userMapper.getUsers(), "getUsers return EmptyList");
        Assert.isTrue(userMapper.pwdChanged(3) == 0, "tiny's pwdChanged is not zero");

    }
}
