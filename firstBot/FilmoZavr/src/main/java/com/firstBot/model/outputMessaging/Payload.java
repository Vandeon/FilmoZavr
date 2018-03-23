package com.firstBot.model.outputMessaging;

import java.util.List;

import com.firstBot.model.other.TemplateType;

public class Payload {

	private TemplateType template_type;
	
	private List<Element> elements;
	
	public Payload() {}

	public Payload(TemplateType template_type, List<Element> elements) {
		super();
		this.template_type = template_type;
		this.elements = elements;
	}

	public TemplateType getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(TemplateType template_type) {
		this.template_type = template_type;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public String toString() {
		return "Payload [template_type=" + template_type + ", elements=" + elements + "]";
	}
	
}
