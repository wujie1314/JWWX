package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class PeopleServic {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	public void deploymentProcessDefinition() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.name("�˹�����")
				.addClasspathResource("diagrams/PeopleS.bpmn")
				.addClasspathResource("diagrams/PeopleS.png")
				.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

	}

	public void startProcessInstance() {
		String processDefinitionKey = "PeopleS";
		ProcessInstance p1 = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("����ʵ��ID" + p1.getId());// ����ʵ��ID
		System.out.println("���̶���ID" + p1.getProcessDefinitionId());// ���̶���ID
	}

	public void completetask(String assignee) {
		List<Task> list = processEngine.getTaskService().createTaskQuery()
				.taskAssignee(assignee).list();
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				System.out.println("����ID��" + task.getId());
				System.out.println("�������ƣ�" + task.getName());
				System.out.println("���񴴽�ʱ�䣺" + task.getCreateTime());
				System.out.println("����İ����ˣ�" + task.getAssignee());
				System.out.println("����ʵ��ID��" + task.getProcessInstanceId());
				System.out.println("ִ�ж���ID��" + task.getExecutionId());
				System.out.println("���̶���ID��" + task.getProcessDefinitionId());
			}
		}
		if (list != null) {
			String taskId = list.get(0).getId();
			processEngine.getTaskService().complete(taskId);
			System.out.println("�����������ID" + taskId);
		}

	}
}
