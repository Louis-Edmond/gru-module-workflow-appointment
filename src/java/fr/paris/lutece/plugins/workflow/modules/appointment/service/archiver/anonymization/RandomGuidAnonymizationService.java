package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver.anonymization;

import java.util.UUID;

import fr.paris.lutece.plugins.appointment.business.form.Form;

public class RandomGuidAnonymizationService extends AbstractAnonymizationService
{
	public static final String BEAN_NAME = "workflow-appointment.randomGuidAnonymizationService";
	
	public static final String PATTERN = "%g";

	@Override
	public String getAnonymisedValue(Form form)
	{
		return UUID.randomUUID( ).toString( );
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
