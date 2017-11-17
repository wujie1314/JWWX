package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

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
	public void startProcessInstance(){
		String processDefinitionKey = "waitQ";
		ProcessInstance p1=processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("流程实例ID"+p1.getId());//流程实例ID
		System.out.println("流程定义ID"+p1.getProcessDefinitionId());//流程定义ID
	}
	public void completetask() {
		String assignee="w1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if (list!=null && list.size()>0) {
			for (Task task:list) {
				System.out.println("任务ID："+task.getId());
				System.out.println("任务名称："+task.getName());
				System.out.println("任务创建时间："+task.getCreateTime());
				System.out.println("任务的办理人："+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID："+task.getExecutionId());
				System.out.println("流程定义ID："+task.getProcessDefinitionId());
				
			}
			
		}
		if(list!=null && list.size()>0){
		String taskId=list.get(0).getId();
		processEngine.getTaskService()
		              .complete(taskId);
		System.out.println("完成任务：任务ID"+taskId);
		}
		
		
		
	}

}
