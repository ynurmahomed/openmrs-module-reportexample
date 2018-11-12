package org.openmrs.module.reportexample.reporting.reports;

import org.apache.poi.util.IOUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.reportexample.reporting.library.datasets.MaleFarmersInHivProgramDataSet;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.indicator.util.IndicatorUtil;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.util.OpenmrsClassLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class MaleFarmersInHivProgramPeriodIndicatorReport extends BaseReportManager {
	
	@Override
	public ReportDefinition constructReportDefinition() {
		PeriodIndicatorReportDefinition rd = new PeriodIndicatorReportDefinition();
		DataSetDefinition dataset = MaleFarmersInHivProgramDataSet.getDataSetDefinition();
		rd.setName(this.getName());
		rd.addDataSetDefinition(dataset, IndicatorUtil.getDefaultParameterMappings());
		return rd;
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		String name = "Male Farmers in HIV Program Excel Template";
		String resourceName = "design.xls";
		ReportDesign design = null;
		
		this.removeReportDesign(name);
		
		ReportDesignResource resource = new ReportDesignResource();
		resource.setName(resourceName);
		resource.setExtension("xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream(resourceName);
		
		try {
			resource.setContents(IOUtils.toByteArray(is));
			design = new ReportDesign();
			design.setName(name);
			design.setReportDefinition(reportDefinition);
			design.setRendererType(ExcelTemplateRenderer.class);
			design.addResource(resource);
			resource.setReportDesign(design);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return Arrays.asList(design);
	}
	
	private void removeReportDesign(String name) {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rdd : rs.getAllReportDesigns(false)) {
			if (name.equals(rdd.getName())) {
				rs.purgeReportDesign(rdd);
			}
		}
		
	}
	
	@Override
	public String getUuid() {
		return "7eabac43-38e2-4fb8-9e6d-9918bd1c0a1a";
	}
	
	@Override
	public String getName() {
		return "Male Farmers in HIV Program";
	}
	
	@Override
	public String getDescription() {
		return "Male Farmer in HIV Program created with reporting API";
	}
	
	@Override
	public String getVersion() {
		return "1.0.0-SNAPSHOT";
	}
}
