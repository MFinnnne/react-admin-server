package com.df.mapper;

import com.df.pojo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * The type User mapper test.
 *
 * @author MFine
 * @version 1.0
 * @date 2020 /10/12 0:00
 */
public class UserMapperTest {

    private static UserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UserMapper.class, builder.openSession(true));
    }

    @Test
    public void testFindOneByName() throws FileNotFoundException {
        User admin = mapper.findOneByName("admin");
        System.out.println(admin);
    }
}
