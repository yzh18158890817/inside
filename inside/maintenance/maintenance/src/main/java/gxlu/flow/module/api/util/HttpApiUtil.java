package gxlu.flow.module.api.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class HttpApiUtil {

	/**GET
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static String interfaceUtil(String path) {
    	 String result = "";
         BufferedReader in = null;
         try {
             String urlNameString =path;
             URL realUrl = new URL(urlNameString);
             // 打开和URL之间的连接
             URLConnection connection = realUrl.openConnection();
             // 设置通用的请求属性
             connection.setRequestProperty("accept", "*/*");
             connection.setRequestProperty("X-Custom-Header" , "true");
             connection.setRequestProperty("connection", "Keep-Alive");
             connection.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             // 建立实际的连接
             connection.connect();
             // 获取所有响应头字段
             Map<String, List<String>> map = connection.getHeaderFields();
             // 遍历所有的响应头字段
             for (String key : map.keySet()) {
                 System.out.println(key + "--->" + map.get(key));
             }
             // 定义 BufferedReader输入流来读取URL的响应
             in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
             String line;
             while ((line = in.readLine()) != null) {
                 result += line;
             }
         } catch (Exception e) {
             System.out.println("发送GET请求出现异常！" + e);
             e.printStackTrace();
         }
         // 使用finally块来关闭输入流
         finally {
             try {
                 if (in != null) {
                     in.close();
                 }
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }
         return result;
     }
    
    /**
     * POSt
     * @param xmlInfo
     * @param URL
     * @return
     */
    public static String doHttpPost(String xmlInfo, String URL) {
        System.out.println("发起的数据:" + xmlInfo);
        InputStream instr = null;
        java.io.ByteArrayOutputStream out = null;
        try {
        	 byte[] xmlData = xmlInfo.getBytes("UTF-8");
        	 
             
            URL url = new URL(URL);
            URLConnection urlCon = url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setUseCaches(false);
            urlCon.setRequestProperty("X-Custom-Header" , "true");
            urlCon.setRequestProperty("content-Type", "application/json");
            urlCon.setRequestProperty("charset", "utf-8");
            urlCon.setRequestProperty("Content-length",
                    String.valueOf(xmlData.length));
            System.out.println(String.valueOf(xmlData.length));
            DataOutputStream printout = new DataOutputStream(
                    urlCon.getOutputStream());
            printout.write(xmlData);
            printout.flush();
            printout.close();
            instr = urlCon.getInputStream();
            byte[] bis = IOUtils.toByteArray(instr);
            String ResponseString = new String(bis, "UTF-8");
            if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                System.out.println("返回空");
            }
            System.out.println("返回数据为:" + ResponseString);
            return ResponseString;

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            try {
                //out.close();
                instr.close();

            } catch (Exception ex) {
                return "0";
            }
        }
    }
    
   }
    
    	        

