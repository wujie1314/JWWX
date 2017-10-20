package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

public class SeatW {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	public void deploymentProcessDefinition(){
		 RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment=repositoryService
				.createDeployment().name("座席等待")
				.addClasspathResource("diagrams/SeatW.bpmn")
				.addClasspathResource("diagrams/SeatW.png")
				.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
	}

}
