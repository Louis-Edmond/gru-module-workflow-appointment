package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver.anonymization;

import java.util.concurrent.ThreadLocalRandom;

import fr.paris.lutece.plugins.appointment.business.form.Form;

public class RandomNumberAnonymizationService extends AbstractAnonymizationService
{
	public static final String BEAN_NAME = "workflow-appointment.randomNumberAnonymizationService";
	
	public static final String PATTERN = "%n";

	@Override
	public String getAnonymisedValue(Form form)
	{
		return String.valueOf( ThreadLocalRandom.current( ).nextLong( ) );
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
