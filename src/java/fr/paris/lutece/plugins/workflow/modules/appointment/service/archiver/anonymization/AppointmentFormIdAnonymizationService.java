package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver.anonymization;

import fr.paris.lutece.plugins.appointment.business.form.Form;

public class AppointmentFormIdAnonymizationService extends AbstractAnonymizationService
{
	public static final String BEAN_NAME = "workflow-appointment.appointmentFormIdAnonymizationService";
	
	public static final String PATTERN = "%f";
	
	@Override
	public String getAnonymisedValue(Form form)
	{
		return String.valueOf(form.getIdForm());
	}

	@Override
	public String getBeanName()
	{
		return BEAN_NAME;
	}

	@Override
	public String getPattern()
	{
		return PATTERN;
	}

}
