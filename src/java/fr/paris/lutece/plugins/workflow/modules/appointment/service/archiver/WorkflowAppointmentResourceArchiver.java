package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver;

import javax.inject.Inject;
import javax.inject.Named;

import fr.paris.lutece.plugins.workflow.modules.archive.ArchivalType;
import fr.paris.lutece.plugins.workflow.modules.archive.IResourceArchiver;
import fr.paris.lutece.plugins.workflow.modules.archive.service.IArchiveProcessingService;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflow;

public class WorkflowAppointmentResourceArchiver implements IResourceArchiver {
	
	public static final String BEAN_NAME = "workflow-appointment.workflowAppointmentResourceArchiver";
	
	@Inject
    @Named( WorkflowAppointmentAnonymizeArchiveProcessingService.BEAN_NAME )
    private IArchiveProcessingService _anonymizeArchiveProcessingService;

	@Override
	public void archiveResource(ArchivalType archivalType, ResourceWorkflow resourceWorkflow) {
		switch( archivalType )
        {
        	case DELETE:
        		break;
	        case ANONYMIZE:
	            _anonymizeArchiveProcessingService.archiveResource( resourceWorkflow );
	            break;
	        default:
	            break;
        }
	}

	@Override
	public String getBeanName()
	{
		return BEAN_NAME;
	}

}
