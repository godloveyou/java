#优雅的SSM架构(Spring+SpringMVC+Mybatis）
- Maven
- Spring（IOC DI AOP 声明式事务处理）
- SpringMVC（支持Restful风格）
- Hibernate Validate（参数校验）
- Mybatis（最少配置方案）
- Quartz时间调度
- Redis缓存（ProtoStuff序列化）
- [Redis Sentinel主从高可用方案](http://wosyingjun.iteye.com/blog/2289593)
- [Redis Cluster集群高可用方案](http://wosyingjun.iteye.com/blog/2289220)
- [Druid（数据源配置 sql防注入 sql性能监控)](http://wosyingjun.iteye.com/blog/2306139)
- 统一的异常处理
- JSP JSTL JavaScript

### Maven项目运行方式##
1.RUN AS-->RUN Configuration-->
2.Goals-->输入tomcat:run
3.点击run


maven自动化部署到远程服务器方法
http://xxgblog.com/2015/10/23/wagon-maven-plugin/#

scp://用户名:密码@ip/usr/local/apache-tomcat-7.0.67/webapps
package wagon:upload-single wagon:sshexec

##Mybatis配置运行方式##
https://www.cnblogs.com/gosky/p/5518748.html
命令： mybatis-generator:generate  -e  加了“-e ”是为了让该插件输出详细信息，这样可以定位问题

###Mybaits笔记###
- Mybatis调用存储过程
    <select id="bugWithProcedure" statementType="CALLABLE">
        call execute_buy(
          #{userId,jdbcType=BIGINT,mode=IN},
          #{goodsId,jdbcType=BIGINT,mode=IN},
          #{title,jdbcType=VARCHAR,mode=IN},
          #{result,jdbcType=INTEGER,mode=OUT}
        )
    </select>
 

 ##使用插件
 编辑器   https://pandao.github.io/editor.md/
 Editor.md 是国人开发的开源在线Markdown编辑器，单纯基于前端JavaScript，无需后台代码加持，适用于任何语言（仅在上传图片功能时需要一点后台代码与之配合，其余都交给Editor.md吧），因为是中国人开发的，对中文支持得相当到位
 
 https://www.hexcode.cn/article/show/editormd