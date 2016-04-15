package com.linkit.garsi.manager.vo;

import com.linkit.garsi.common.document.vo.Document;

/**
 * 公司相关资料
 * 
 * @author wang.sheng
 * 
 */
public class CompanyData
{
	private Company company;
	private Document[] documents;

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public Document[] getDocuments()
	{
		return documents;
	}

	public void setDocuments(Document[] documents)
	{
		this.documents = documents;
	}

}
