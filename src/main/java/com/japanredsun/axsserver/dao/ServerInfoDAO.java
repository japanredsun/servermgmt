package com.japanredsun.axsserver.dao;

import com.japanredsun.axsserver.entity.Server;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

import java.util.List;

@Repository
@Transactional
public class ServerInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public ServerInfoDAO(){};

    public List<Server> getAllServers(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server");
        List<Server> list = query.list();
        return list;
    }

    public List<Server> getAvailableServers(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server Where isAvailable = :isAvailable");
        query.setParameter("isAvailable",1);
        List<Server> list = query.list();
        return list;
    }

    public List<Server> getRegisteredServer(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server Where isAvailable = :isAvailable");
        query.setParameter("isAvailable",0);
        List<Server> list = query.list();
        return list;
    }

    public Server getServerById(long id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server Where id = :id");
        query.setLong("id",id);
        Server server = (Server) query.uniqueResult();
        return server;
    }

    public List<Server> getServersByOwner(String owner){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server Where owner = :owner");
        query.setParameter("owner",owner);
        List<Server> list = query.list();
        return list;
    }

    public void saveOrUpdate(Server serverModel){
        long id = serverModel.getId();
        String server = serverModel.getServer();
        String platform = serverModel.getPlatform();
        String sshUserPassword = serverModel.getSshUserPassword();
        String rootPassword = serverModel.getRootPassword();
        int isAvailable = serverModel.getIsAvailable();
        String amsVersion = serverModel.getAmsVersion();
        String note = serverModel.getNote();

        Server newserver = null;
        Boolean isNew = false;
        //Check input server
        if(id > 0){
            newserver = this.getServerById(id);
        }
        if (newserver == null)
        {
            isNew = true;
            newserver = new Server();
            newserver.setServer(server);
            newserver.setPlatform(platform);
            newserver.setSshUserPassword(sshUserPassword);
            newserver.setRootPassword(rootPassword);
            newserver.setIsAvailable(isAvailable);
            newserver.setAmsVersion(amsVersion);
            newserver.setNote(note);
        }
        if(isNew){
            this.sessionFactory.getCurrentSession().persist(newserver);
        }
        this.sessionFactory.getCurrentSession().flush();
    }

    public void updateServer(Server server){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Server set server = :server, platform = :platform, sshUserPassword = :ssh, " +
                                                "rootPassword = :root, amsVersion = :ams, note = :note WHERE id = :id");
        query.setParameter("server",server.getServer());
        query.setParameter("platform", server.getPlatform());
        query.setParameter("ssh",server.getSshUserPassword());
        query.setParameter("root",server.getRootPassword());
        query.setParameter("ams",server.getAmsVersion());
        query.setParameter("note",server.getNote());
        query.setLong("id",server.getId());
        int result = query.executeUpdate();
        System.out.println("Update: " + server.getServer() + " " + result);
    }

    public void deleteServer(long id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Server Where id = :id");
        query.setLong("id",id);
        int result = query.executeUpdate();
        System.out.println("Delete: " + id + " " + result);
    }
    public void register(Server server, String currentUser) throws ParseException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Server set owner = :owner, startDate = :startdate, " +
                "endDate = :enddate, note = :note, isAvailable = :boolean WHERE id = :id");
        query.setParameter("owner",currentUser);
        query.setParameter("startdate",server.getStartDate());
        query.setParameter("enddate", server.getEndDate());
        query.setParameter("note", server.getNote());
        query.setInteger("boolean",0);
        query.setLong("id",server.getId());
        int result = query.executeUpdate();
        System.out.println("Register: " + server.getServer() + " " + result);
    }

    public void unregister(Server server){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Server set owner = :owner , startDate = :start,endDate = :end, " +
                "note = :note, isAvailable = :boolean WHERE id = :id" );
        query.setParameter("owner","");
        query.setParameter("start",null);
        query.setParameter("end",null);
        query.setParameter("note","");
        query.setParameter("boolean",1);
        query.setLong("id",server.getId());
        int result = query.executeUpdate();
        System.out.println("Un-Register: " + server.getServer() + " " + result);
    }

    public void extendEndDate(Server server){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Server set endDate = :end WHERE id = :id");
        query.setParameter("end",server.getEndDate());
        query.setLong("id",server.getId());
        int result = query.executeUpdate();
        System.out.println("Extended: " + server.getServer() + " " + result);
    }
}
