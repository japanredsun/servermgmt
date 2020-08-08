package com.japanredsun.axsserver.dao;

import com.japanredsun.axsserver.entity.User;
import com.japanredsun.axsserver.entity.UserRole;
import com.japanredsun.axsserver.model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public UserInfoDAO(){};

    public UserInfo findUserInfo(String userName){
        String sql = "SELECT new " + UserInfo.class.getName() + "(u.username, u.password)"
                + "from " + User.class.getName() + " u WHERE u.username = :username ";
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("username",userName);

        return (UserInfo) query.uniqueResult();
    }

    public List<String> getUserRoles(String userName){
        String sql = "Select r.userRole " + "from " + UserRole.class.getName() + " r WHERE r.user.username = :username ";

        Session session  = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
        List<String> roles = query.list();

        return roles;
    }

    public User findUser(String username){
        Session session  = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("username",username));
        return (User) crit.uniqueResult();
    }

     public void save(UserInfo userInfo){
        String username = userInfo.getUserName();
        String password = userInfo.getPassword();

        User user = null;
        boolean isNew = false;
        if(username != null){
            user = this.findUser(username);
        }
        if(user == null){
            isNew = true;
            user = new User();
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(true);

        if(isNew){
            this.sessionFactory.getCurrentSession().persist(user);
        }

        // else
         this.sessionFactory.getCurrentSession().flush();
     }

     public void initRole(UserInfo userInfo){
        String username = userInfo.getUserName();

        User user = null;
        if(username != null){
            user = this.findUser(username);
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setUserRole("USER");
            //userRole.setRoleId(String.valueOf(user.getUsername().toLowerCase()));

            this.sessionFactory.getCurrentSession().persist(userRole);
        }

     }

}
