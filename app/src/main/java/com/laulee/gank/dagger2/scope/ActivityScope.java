package com.laulee.gank.dagger2.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by laulee on 17/4/10.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME )
public @interface ActivityScope {}
