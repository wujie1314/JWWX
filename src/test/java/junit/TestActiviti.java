package test.java.junit; 

import org.activiti.engine.ProcessEngine;  
import org.activiti.engine.ProcessEngineConfiguration;  

  
public class TestActiviti {  
    /**ʹ�ô��봴����������Ҫ��23�ű�*/  
    public void createTable(){  
        //��������ProcessEngine�������в������벻���������  
        ProcessEngineConfiguration processEngineConfiguration =  
                ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        //�������ݿ������  
        processEngineConfiguration.setJdbcDriver("oracle.jdbc.driver.OracleDriver");  
        processEngineConfiguration.setJdbcUrl("jdbc:oracle:thin:@superc102.vicp.cc:1522:jwwx");  
        processEngineConfiguration.setJdbcUsername("jwwx");  
        processEngineConfiguration.setJdbcPassword("All4Icode");  
        //��������  
        //1.��ɾ�����ٴ�����processEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP="create-drop"  
        //2.�����Զ���������Ҫ����ڣ�processEngineConfiguration.DB_SCHEMA_UPDATE_FALSE="false"  
        //3.�������ڣ����Զ�������processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE="true"  
       // processEngineConfiguration.setDatabaseSchema(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);  
        processEngineConfiguration.setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //��ȡ�������ĺ��Ķ���ProcessEngine����  
        ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();  
        System.out.println("processEngine:"+processEngine+"Create Success!!");  
    }  
    /**ʹ�ô��봴����������Ҫ��23�ű�*/  
  
    public void createTable_2(){
    	ProcessEngineConfiguration processEngineConfiguration =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-cfg.xml");
    	 ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();  
         System.out.println("processEngine:"+processEngine+"Create Success!!");  
    }
}  
