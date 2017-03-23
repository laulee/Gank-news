package com.laulee.gank.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by laulee on 17/3/23.
 */

public class CreateUtil {

    /**
     * 通过反射创建指定范型对象
     *
     * @param o
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T createT( Object o, int index ) {
        try {
            return ( (Class<T>) ( (ParameterizedType) ( o.getClass( ).getGenericSuperclass( ) ) )
                    .getActualTypeArguments( )[index] ).newInstance( );
        } catch( Exception e ) {
            e.printStackTrace( );
        }
        return null;
    }

}
