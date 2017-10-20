package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

public class WaitQ {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	public void deploymentProcessDefinition(){
		 RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment=repositoryService
				.createDeployment().name("用户等待")
				.addClasspathResource("diagrams/waitQ.bpmn")
				.addClasspathResource("diagrams/waitQ.png")
				.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
	}

}
