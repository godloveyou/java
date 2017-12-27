package cn.nilaile.ssm.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验表单重复提交的自定义注解 Token
 * 关联拦截器是:cn.nilaile.ssm.interceptor.TokenInterceptor
 *  * 在需要生成token的controller上增加@Token(save=true)，
 * 而在需要检查重复提交的controller上添加@Token(remove=true)就可以了。
 * @author david
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	boolean save() default false;
	boolean remove() default false;
}
