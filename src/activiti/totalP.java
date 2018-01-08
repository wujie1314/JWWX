package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class totalP {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	public void deploymentProcessDefinition() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.name("�������")
				.addClasspathResource("diagrams/totalP.bpmn")
				.addClasspathResource("diagrams/totalP.png")
				.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

	}
	public void startProcessInstance() {
		String processDefinitionKey = "totalP";
		ProcessInstance p1 = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("����ʵ��ID" + p1.getId());// ����ʵ��ID
		System.out.println("���̶���ID" + p1.getProcessDefinitionId());// ���̶���ID
	}
	public void completetaskU1(){
		String assignee="U1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if(list!=null && list.size()>0){
			String taskId=list.get(0).getId();
			processEngine.getTaskService().setVariable(taskId,"state",1);
			processEngine.getTaskService()
			              .complete(taskId);
			System.out.println("�����������ID"+taskId);
			}
		
	}
	public void completetaskU1end(){
		String assignee="U1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if(list!=null && list.size()>0){
			String taskId=list.get(0).getId();
			processEngine.getTaskService().setVariable(taskId,"state",0);
			processEngine.getTaskService()
			              .complete(taskId);
			System.out.println("�����������ID"+taskId);
			}
		
	}
	public void completetaskS1(){
		String assignee="S1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if(list!=null && list.size()>0){
			String taskId=list.get(0).getId();
			processEngine.getTaskService().setVariable(taskId,"state",1);
			processEngine.getTaskService()
			              .complete(taskId);
			System.out.println("�����������ID"+taskId);
			}
		
	}
	public void completetaskS1end(){
		String assignee="S1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if(list!=null && list.size()>0){
			String taskId=list.get(0).getId();
			processEngine.getTaskService().setVariable(taskId,"state",0);
			processEngine.getTaskService()
			              .complete(taskId);
			System.out.println("�����������ID"+taskId);
			}
		
	}
	public void completetaskP1(){
		String assignee="P1";
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		if(list!=null && list.size()>0){
			String taskId=list.get(0).getId();
			processEngine.getTaskService()
			              .complete(taskId);
			System.out.println("�����������ID"+taskId);
			}
		
	}
}
