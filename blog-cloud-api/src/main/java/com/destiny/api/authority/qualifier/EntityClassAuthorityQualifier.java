package com.destiny.api.authority.qualifier;

/**
 * @Date 2020/2/20 13:51
 * @Version 1.0
 **/
public class EntityClassAuthorityQualifier implements AuthorityQualifier {

    private Class entityClass;

    @Override
    public boolean qualifier(Object target) {
        if (entityClass == null){
            return true;
        }
       return  target instanceof Class && entityClass.equals(target);
    }
}
