package fr.paris.lutece.plugins.workflow.modules.appointment.service.archiver.anonymization;

import fr.paris.lutece.plugins.appointment.business.form.Form;

public interface IAnonymizationService
{

	String getAnonymisedValue(Form form);
	
	String getBeanName();
	
	String getPattern();

}
