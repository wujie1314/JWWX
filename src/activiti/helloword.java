package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class helloword {

	public void deploymentProcessDefinition(){
		ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
		 RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment=repositoryService
				.createDeployment().name("helloword")
				.addClasspathResource("diagrams/hellowword.bpmn")
				.addClasspathResource("diagrams/hellowword.png")
				.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
	}
	
}
