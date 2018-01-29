package cn.nilaile.ssm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 个人工具
 *部署本地的war包到服务器,同时允许执行远程命令
 * @author david
 */
public class DeployToServer {

	  //数据服务器的ip地址  
    private static String dataServerIp = "113.209.26.17";  
    //数据服务器的用户名  
    private static String dataServerUsername = "root";  
    //数据服务器的密码  
    private static String dataServerPassword = "%100.RuiRui";  
    //数据服务器的目的文件夹  
    private static String dataServerDestDir = "/usr/local/apache-tomcat-7.0.67/webapps";  
    
    private static int port=41036;
    
	private final static Logger log = LoggerFactory.getLogger(DeployToServer.class);
	
	public static void main(String[] args) {
		boolean uploadRes = uploadToServer();
		
	}


	/**
	 * 本地文件上传到远程服务器
	 * @return
	 */
	private static boolean uploadToServer() {
		  //文件scp到数据服务器  
	    Connection conn = new Connection(dataServerIp,port);  
	    log.info("开始scp文件");  
	    try {  
	        conn.connect();  
	        boolean isAuthenticated = conn.authenticateWithPassword(dataServerUsername, dataServerPassword);  
	        
	        if (isAuthenticated == false) {
	        	   throw new IOException("Authentication failed.文件scp到数据服务器时发生异常");  
	        }
	         
	        
	        SCPClient client = new SCPClient(conn);  
	        client.put("F:/david/git/ssm/ssm/target/blogforjava.war", dataServerDestDir); //本地文件scp到远程目录  
	       // client.get(dataServerDestDir + "00审计.zip", localDir);//远程的文件scp到本地目录  
	        
//	    	Session sess = conn.openSession();
//			sess.execCommand("/usr/local/apache-tomcat-7.0.67/bin/shutdown.sh"+";"+"rm -rf /usr/local/apache-tomcat-7.0.67/webapps/blogforjava");
//			sess.close();
			
	        conn.close();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	        log.error("文件scp到数据服务器时发生异常");  
	        return false;
	    }  
	    log.info("scp文件结束");  
	    
		return true;
	}
}
