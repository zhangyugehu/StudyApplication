package com.thssh.sqlorm;

import com.thssh.sqlorm.execption.SqlOrmExecption;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 接口不关心数据表的创建，交由{@link AbsBaseDao} 抽象类处理
 * Created by zhangyugehu on 2017/5/10.
 */

public interface IBaseDao <T>{
    public static class QueryBuilder<T>{
        public String groupBy;
        public String having;
        public String orderBy;
        public String[] bys;
        public IBaseDao<T> dao;
        public T entity;

        public QueryBuilder groupBy(String groupBy){
            this.groupBy = groupBy;
            return this;
        }
        public QueryBuilder having(String having){
            this.having = having;
            return this;
        }
        public QueryBuilder orderBy(String orderBy){
            this.orderBy = orderBy;
            return this;
        }
        public QueryBuilder bys(String... bys){
            this.bys = bys;
            return this;
        }

        public QueryBuilder entity(T entity){
            this.entity = entity;
            return this;
        }

        public List<T> query() throws SqlOrmExecption {
            return dao.query(entity, this);
        }

        public QueryBuilder dao(IBaseDao<T> dao) {
            this.dao = dao;
            return this;
        }
    }

    long insert(T entity) throws SqlOrmExecption;

    int delete(T where) throws SqlOrmExecption;

    int delete(T entity, String... bys) throws SqlOrmExecption;

    int clear() throws SqlOrmExecption;

    void drop() throws SqlOrmExecption;

    int update(T entity, T where) throws SqlOrmExecption;

    int update(T entity, String... bys) throws SqlOrmExecption;

    /**
     * * 查询数据
     * @param entity 不可为空需要其提供class信息
     * @param builder
     * @return
     * @throws SqlOrmExecption
     */
    List<T> query(@NotNull T entity, QueryBuilder builder) throws SqlOrmExecption;
}
