package org.openmrs.module.reportexample.reporting.library.cohorts;

import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.InProgramCohortDefinition;

import java.util.Collections;

public class InHivProgramCohort {
	
	private static final String HIV_PROGRAM_UUID = "12275e58-7d35-46d8-964a-37c0e5bb4a40";
	
	public static CohortDefinition getCohortDefinition() {
		Program hivProgram = Context.getProgramWorkflowService().getProgramByUuid(HIV_PROGRAM_UUID);
		InProgramCohortDefinition inHivProgramCohort = new InProgramCohortDefinition();
		inHivProgramCohort.setPrograms(Collections.singletonList(hivProgram));
		return inHivProgramCohort;
	}
}
