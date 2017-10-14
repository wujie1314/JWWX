package org.jiaowei.common.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.jiaowei.common.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15-4-17.
 */
@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        /*要求开起事务*/
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> Serializable save(T entity) {
        Serializable id = getSession().save(entity);
        getSession().flush();
        return id;
    }

    @Override
    public <T> void batchSave(List<T> entitys) {
        for (T t : entitys) {
            getSession().save(t);
        }
        getSession().flush();
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        getSession().flush();
    }

    @Override
    public <T> void delete(T entity) {
        getSession().delete(entity);
        getSession().flush();
    }

    @Override
    public <T> List<T> loadAll(Class<T> entityClass) {
        return getSession().createCriteria(entityClass).list();
    }


    @Override
    public <T> T get(Class<T> entityName, Serializable id) {
        return (T) getSession().get(entityName, id);
    }

    @Override
    public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        return getSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value)).list();
    }

    @Override
    public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return (T) criteria.uniqueResult();
    }

    @Override
    public <T> void deleteEntityById(Class<T> entityClassName, Serializable id) {
        getSession().delete(get(entityClassName, id));
        getSession().flush();
    }

    @Override
    public <T> void deleteAllEntities(Collection<T> entities) {
        for (T t : entities) {
            getSession().delete(t);
        }
        getSession().flush();
    }

    @Override
    public <T> void updateEntity(T entity) {
        getSession().update(entity);

    }

    @Override
    public <T> List<T> findByMap(Class<T> className, Map<String, Object> map) {
        return createCriteria(className, map).list();
    }

    @Override
    public <T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions) {
        Criteria criteria = getSession().createCriteria(className);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }

    @Override
    public <T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions, String orderPropertyName, boolean isAsc) {
        Assert.hasText(orderPropertyName);
        Criteria criteria = getSession().createCriteria(className);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        if (isAsc && null != orderPropertyName) {
            criteria.addOrder(Order.asc(orderPropertyName));
        } else if (!isAsc && null != orderPropertyName) {
            criteria.addOrder(Order.desc(orderPropertyName));
        }
        return criteria.list();
    }

    @Override
    public <T> List<T>
    findByCriteriaPaging(Class<T> className, Map<String, Object> params,
                                            String orderPropertyName, boolean isAsc, int star, int end) {
        Criteria criteria = createCriteria(className, params);
        if (isAsc && null != orderPropertyName) {
            criteria.addOrder(Order.asc(orderPropertyName));
        } else if (!isAsc && null != orderPropertyName) {
            criteria.addOrder(Order.desc(orderPropertyName));
        }
        return criteria.setFirstResult(star).setMaxResults(end).list();
    }

    @Override
    public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params,
                                            String orderPropertyName1, boolean isAsc1,
                                            String orderPropertyName2, boolean isAsc2, int star, int end) {
        Criteria criteria = createCriteria(className, params);
        if (isAsc1 && null != orderPropertyName1) {
            criteria.addOrder(Order.asc(orderPropertyName1));
        } else if (!isAsc1 && null != orderPropertyName1) {
            criteria.addOrder(Order.desc(orderPropertyName1));
        }
        if (isAsc2 && null != orderPropertyName2) {
            criteria.addOrder(Order.asc(orderPropertyName2));
        } else if (!isAsc2 && null != orderPropertyName2) {
            criteria.addOrder(Order.desc(orderPropertyName2));
        }
        return criteria.setFirstResult(star).setMaxResults(end).list();
    }

    @Override
    public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, Map<String, Object> sortMap, int star, int end) {
        Criteria criteria = createCriteria(className, params);
        Iterator<String> it = sortMap.keySet().iterator();
        while (it.hasNext()) {
            String kay = it.next();
            Boolean val = (Boolean) sortMap.get(kay);
            if (val) {
                criteria.addOrder(Order.asc(kay));
            } else {
                criteria.addOrder(Order.desc(kay));
            }
        }
        return criteria.setFirstResult(star).setMaxResults(end).list();
    }

    @Override
    public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params,
                                            Map<String, Object> likeParams, String orderPropertyName,
                                            boolean isAsc, int star, int end) {
        Criteria criteria = createCriteriaEqAndLike(className, params, likeParams);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderPropertyName));
        } else {
            criteria.addOrder(Order.desc(orderPropertyName));
        }
        return criteria.setFirstResult(star).setMaxResults(end).list();
    }

    @Override
    public <T> Long findByCriteriaPagingTotal(Class<T> className, Map<String, Object> params, Map<String, Object> likeParams) {
        Criteria criteria = createCriteriaEqAndLike(className, params, likeParams);
        criteria.setProjection(Projections.rowCount());
        return Long.valueOf(criteria.uniqueResult().toString());
    }

    @Override
    public <T> Long findByCriteriaPagingTotal(Class<T> className, Map<String, Object> params) {
        Criteria criteria = createCriteria(className, params);
        criteria.setProjection(Projections.rowCount());
        return Long.valueOf(criteria.uniqueResult().toString());
    }


    @Override
    public <T> T findUniqueByCriteria(Class<T> className, Map<String, Object> map) {
        return (T) createCriteria(className, map).uniqueResult();
    }

    @Override
    public <T> List<T> findByKey(Class<T> className, Map<String, Object> map,Map<String,Object>likemap) {
        return  createCriteriaEqAndLike(className, map, likemap).list();
    }

    @Override
    public <T> List<T> findByHql(String hql) {
        return getSession().createQuery(hql).list();
    }

    @Override
    public <T> List<T> findByHql(String hql, List<Object> params) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        return query.list();
    }

    @Override
    public <T> List<T> findByHql(String hql, Map<String, Object> map) {
        Query query = getSession().createQuery(hql);
        String kay = null;
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            kay = it.next();
            query.setParameter(kay, map.get(kay));
        }
        return query.list();
    }

    @Override
    public <T> int ExecHQL(String hql) {
        Query query = getSession().createQuery(hql);
        int r = query.executeUpdate();
        return r;
    }

    @Override
   public Long findTotalBySQL(String sql) {
        Query query = getSession().createQuery(sql);
        try{
            Long total = (Long)query.uniqueResult();
            return total;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> findBySQL(String sql) {
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }



    @Override
    public <T> List<T> findBySQL(String sql, Class cls) {
//        Query query = getSession().createSQLQuery(sql);
//        query.setResultTransformer(Transformers.aliasToBean(cls));
        Query query = getSession().createSQLQuery(sql).addEntity(cls);
//        query.setResultTransformer(Transformers.aliasToBean(cls));
        try {
            List<T> list = query.list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> int ExecSQL(String sql) {
        Query query = getSession().createSQLQuery(sql);
        int r = query.executeUpdate();
        return r;
    }
    
    @Override
    public <T> int ExecSQL(String sql, Map<String, Object> params) {
    	Query query = getSession().createSQLQuery(sql);
    	String kay = null;
        for (Iterator<String> it = params.keySet().iterator(); it.hasNext(); ) {
            kay = it.next();
            query.setParameter(kay, params.get(kay));
//            System.out.println("kay:"+kay+",value:"+params.get(kay));
        }
    	int r = query.executeUpdate();
    	return r;
    }
    
    @Override
    public <T> int ExecSQL(String sql, List<Object> params) {
    	Query query = getSession().createSQLQuery(sql);
    	String kay = null;
    	for (int i = 0 ; i < params.size() ; i++) {
    		query.setParameter( i, params.get(i));
//    		System.out.println("i:"+i+",value:"+params.get(i));
        }
    	int r = query.executeUpdate();
    	return r;
    }

    @Override
    public <T> List<T> findBySQL(String sql, List<Object> params) {
        Query query = getSession().createSQLQuery(sql);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        return query.list();
    }

    @Override
    public <T> List<T> findBySQL(String sql, Map<String, Object> params) {
        Query query = getSession().createSQLQuery(sql);
        String kay = null;
        for (Iterator<String> it = params.keySet().iterator(); it.hasNext(); ) {
            kay = it.next();
            query.setParameter(kay, params.get(kay));
        }
        return query.list();
    }

    public Criteria createCriteria(Class className, Map<String, Object> map) {
        Criteria criteria = getSession().createCriteria(className);
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String kay = it.next();
            criteria.add(Restrictions.eq(kay, map.get(kay)));
        }
        return criteria;
    }

    public Criteria createCriteriaEqAndLike(Class className, Map<String, Object> map, Map<String, Object> likeMap) {
        Criteria criteria = getSession().createCriteria(className);
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String kay = it.next();
            criteria.add(Restrictions.eq(kay, map.get(kay)));
        }

//        Disjunction disjunction = Restrictions.disjunction();
        for (Iterator<String> it = likeMap.keySet().iterator(); it.hasNext(); ) {
            String kay = it.next();
//            disjunction.add(Restrictions.like(kay, "%" + likeMap.get(kay).toString().trim() + "%"));
            criteria.add(Restrictions.like(kay, "%" + likeMap.get(kay).toString().trim() + "%"));
        }
//        criteria.add(disjunction);
        return criteria;
    }

    public Criteria createCriteriaLike(Class className, Map<String, Object> likeMap) {
        Criteria criteria = getSession().createCriteria(className);
//        Disjunction disjunction = Restrictions.disjunction();
        for (Iterator<String> it = likeMap.keySet().iterator(); it.hasNext(); ) {
            String kay = it.next();
//            disjunction.add(Restrictions.like(kay, "%" + likeMap.get(kay).toString().trim() + "%"));
            criteria.add(Restrictions.like(kay, "%" + likeMap.get(kay).toString().trim() + "%"));
        }
//        criteria.add(disjunction);
        return criteria;
    }
}
