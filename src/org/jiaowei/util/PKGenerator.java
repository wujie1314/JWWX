package org.jiaowei.util;

/**
 * 主键生成器。该类使用单例模式。
 */
public class PKGenerator
{
  /**
   * 存储主机IP左移48位后的值。
   */
  private long key;
  /**
   * 静态不变单例变量。
   */
  private static final PKGenerator instance = new PKGenerator();

  /**
   * 获得当前主机IP的后3位（如192.168.0.112中的112），左移48位后存入变量lastTransIP中
   * 。
   * @roseuid 3EF6A8F0023D
   */
  private PKGenerator()
  {
//    try
//    {
//      InetAddress ia = InetAddress.getLocalHost();
//      String strIP = ia.getHostAddress();
//      int lastPoint = strIP.lastIndexOf(".");
//      int longip = Integer.parseInt(strIP.substring(lastPoint + 1));
      makeKey(0);
//    }
//    catch (UnknownHostException e)
//    {
//      System.out.println("No Host");
//    }
  }

  /**
   * 生成key
   * @param seed
   */
  public synchronized void makeKey(int seed) {
    long lastTransIP = ((long)seed) << 48;
    long longTime = System.currentTimeMillis();
    key = longTime | lastTransIP;
  }


  /**
   * @return com.adtech.util.PKGenerator
   * @roseuid 3EF6A8F00251
   */
  public static PKGenerator getInstance()
  {
    return instance;
  }

  /**
   * 返回唯一的键。获得当前时间的毫秒数位与lastTransIP的值。
   * @return long
   * @roseuid 3EF6A8F00265
   */
  public synchronized long getKey()
  {
    return ++key;
  }

  public static long generateKey()
  {
    return instance.getKey();
  }
}
