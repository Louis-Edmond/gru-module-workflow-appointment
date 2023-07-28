package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver;

import java.util.List;

import fr.paris.lutece.plugins.appointment.business.appointment.Appointment;
import fr.paris.lutece.plugins.appointment.business.form.Form;
import fr.paris.lutece.plugins.appointment.business.slot.Slot;
import fr.paris.lutece.plugins.appointment.business.user.User;
import fr.paris.lutece.plugins.appointment.business.user.UserHome;
import fr.paris.lutece.plugins.appointment.service.AppointmentService;
import fr.paris.lutece.plugins.appointment.service.FormService;
import fr.paris.lutece.plugins.appointment.service.SlotService;
import fr.paris.lutece.plugins.appointment.service.UserService;
import fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver.anonymization.IAnonymizationService;
import fr.paris.lutece.plugins.workflow.modules.archive.service.AbstractArchiveProcessingService;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflow;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class WorkflowAppointmentAnonymizeArchiveProcessingService extends AbstractArchiveProcessingService
{
	
	public static final String BEAN_NAME = "workflow-appointment.workflowAppointmentAnonymizeArchiveProcessingService";

	@Override
	public void archiveResource(ResourceWorkflow resourceWorkflow) {
		int nIdAppointment = resourceWorkflow.getIdResource( );
		Appointment appointment = AppointmentService.findAppointmentById( nIdAppointment );
		if (appointment != null)
		{
			 List<Slot> slotList = SlotService.findListSlotByIdAppointment(appointment.getIdAppointment());
			 Form form = (slotList != null && !slotList.isEmpty()) ? FormService.findFormLightByPrimaryKey(slotList.get(0).getIdForm()) : null;
			 User user = UserService.findUserById( appointment.getIdUser( ) );
			 if (user != null && form != null && form.isAnonymizable())
			 {
				 IAnonymizationService anonymizationService = getAnonymizationServiceByPattern(form.getAnonymizationPattern());
				 if (anonymizationService != null)
				 {
					 user.setFirstName(anonymizationService.getAnonymisedValue(form));
					 user.setLastName(anonymizationService.getAnonymisedValue(form));
					 user.setEmail(anonymizationService.getAnonymisedValue(form));
					 UserHome.update(user);
				 }
			 }
			 
		}
	}
	
	private IAnonymizationService getAnonymizationServiceByPattern(String pattern)
	{
		List<IAnonymizationService> anonymizationServiceList = SpringContextService.getBeansOfType(IAnonymizationService.class);
		for(IAnonymizationService anonymizationService : anonymizationServiceList)
		{
			if (pattern.equals(anonymizationService.getPattern()))
			{
				return anonymizationService;
			}
		}
		return null;
	}

}
