package com.fastcampus.ch3;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception {
        User user = new User("bb", "1234", "Abc", "ss.com", new Date(), "fb", new Date());
        deleteAll();
        int rowCnt = insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        User user = new User("bb", "1234", "Abc", "ss.com", new Date(), "fb", new Date());
        int rowCnt = insertUser(user);
        User user2 = selectUser("bb");

        assertTrue(user.getId().equals("bb"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("aa");

        assertTrue(rowCnt == 0);

        User user = new User("bb", "1234", "Abc", "ss.com", new Date(), "fb", new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId()) == null);
    }

    // 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
    public int updateUser(User user) throws Exception {

        return 0;
    }

    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.setString(1, id);
        int rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }

    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(rs.getDate(7));

            return user;
        }
        return null;
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.executeUpdate();
    }

    // 사용자 정보를 user_info 테이블에 젖아하는 메서드
    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();

//        insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//        values ('asss', '1234', 'smith', 'aa@com', '2021-01-01', 'sss', '2021-01-05');

        String sql = " insert into user_info values (?,?,?,?,?,?,now())";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }

    @Test
    public void transactionTest() throws Exception {
        Connection conn = null;
        try {
            deleteAll();
            conn = ds.getConnection();
            conn.setAutoCommit(false);

//        insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//        values ('asss', '1234', 'smith', 'aa@com', '2021-01-01', 'sss', '2021-01-05');

            String sql = " insert into user_info values (?,?,?,?,?,?,now())";

            PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
            pstmt.setString(1, "asdf");
            pstmt.setString(2, "123");
            pstmt.setString(3, "kbs");
            pstmt.setString(4, "aa@aa.com");
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, "fb");

            int rowCnt = pstmt.executeUpdate();

            pstmt.setString(1, "asdf");
            rowCnt = pstmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
        }

    }

    @Test
    public void main() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null);
    }
}
