package org.openmrs.module.reportexample.reporting.definitions;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.*;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.reporting.evaluation.parameter.Mapped.map;
import static org.openmrs.module.reporting.evaluation.parameter.Mapped.noMappings;
import static org.openmrs.module.reporting.indicator.CohortIndicator.newFractionIndicator;

public class ExampleReportDefinition extends BaseReportManager {
	
	private static final String HIV_PROGRAM_UUID = "ac3c2784-d6bd-11e8-847d-0242ac110003";
	
	private static final String OCCUPATION_CONCEPT_UUID = "824f3aaf-08f4-4164-94b0-2c4fe496c6a5";
	
	private static final String FARMER_CONCEPT_UUID = "529c645e-0497-42c2-9df1-afa467d4499f";
	
	@Override
	public ReportDefinition constructReportDefinition() {
		
		CodedObsCohortDefinition farmersCohort = getFarmersCohortDefinition();
		InProgramCohortDefinition inHivProgramCohort = getInHivProgramCohortDefinition();
		
		CohortDefinition maleCohort = getMaleCohort();
		CohortDefinitionDimension genderDimension = new CohortDefinitionDimension();
		genderDimension.addCohortDefinition("Male-Cohort", noMappings(maleCohort));
		
		Mapped<CodedObsCohortDefinition> numerator = noMappings(farmersCohort);
		Mapped<InProgramCohortDefinition> denominator = map(inHivProgramCohort, "onDate --> ${endDate}");
		
		CohortIndicator farmersInHivProgramIndicator = newFractionIndicator("Farmers in HIV Program Indicator 2", numerator,
		    denominator, null);
		
		farmersInHivProgramIndicator.addParameter(new Parameter("startDate", "startDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("endDate", "endDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("location", "location", Location.class));
		
		PeriodIndicatorReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setupDataSetDefinition();
		report.setName("HIV Program Report 2");
		report.addDimension("Gender-dimension", genderDimension);
		report.addIndicator(farmersInHivProgramIndicator, "Gender-dimension=Male-Cohort");
		
		return report;
	}
	
	private CodedObsCohortDefinition getFarmersCohortDefinition() {
		Concept occupationConcept = Context.getConceptService().getConceptByUuid(OCCUPATION_CONCEPT_UUID);
		Concept farmer = Context.getConceptService().getConceptByUuid(FARMER_CONCEPT_UUID);
		CodedObsCohortDefinition farmersCohort = new CodedObsCohortDefinition();
		farmersCohort.setTimeModifier(BaseObsCohortDefinition.TimeModifier.LAST);
		farmersCohort.setQuestion(occupationConcept);
		farmersCohort.setOperator(SetComparator.IN);
		farmersCohort.addValue(farmer);
		return farmersCohort;
	}
	
	private InProgramCohortDefinition getInHivProgramCohortDefinition() {
		Program hivProgram = Context.getProgramWorkflowService().getProgramByUuid(HIV_PROGRAM_UUID);
		InProgramCohortDefinition inHivProgramCohort = new InProgramCohortDefinition();
		inHivProgramCohort.setPrograms(Collections.singletonList(hivProgram));
		// TODO maybe missing parameter
		return inHivProgramCohort;
	}
	
	public CohortDefinition getFemaleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("FemaleCohort");
		cohort.setFemaleIncluded(true);
		return cohort;
	}
	
	public CohortDefinition getMaleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("MaleCohort");
		cohort.setMaleIncluded(true);
		return cohort;
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		return null;
	}
	
	@Override
	public String getUuid() {
		return "284d8aad-4497-4def-9c33-2fe4a41ce674";
	}
	
	@Override
	public String getName() {
		return "HIV Program Report from module";
	}
	
	@Override
	public String getDescription() {
		return "HIV Program Report created with reporting API";
	}
	
	@Override
	public String getVersion() {
		return "1.0.0";
	}
}
