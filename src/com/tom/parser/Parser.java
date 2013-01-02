package com.tom.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Parser {
	private Document doc;
	private Element root;
	private ProductVO[] productList;
	
	public Parser(InputStream ins) {
		SAXBuilder saxBuilder = new SAXBuilder();
		
		try {
			doc = saxBuilder.build(ins);
			root = doc.getRootElement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Element> list = new ArrayList<Element>();
		list = root.getChildren();
		
		List<ProductVO> pList = new ArrayList<ProductVO>();
		for (Element var : list) {
			ProductVO vo = new ProductVO();
			vo.setName(var.getChildText("name"));
			vo.setPrice(Integer.parseInt(var.getChildText("price")));
			vo.setType(var.getChildText("type"));
			vo.setPromo(var.getChildText("promo"));
			pList.add(vo);
		}
		productList = new ProductVO[list.size()];
		pList.toArray(productList);
	}

	public ProductVO[] getProductList() {
		return productList;
	}

	public void setProductList(ProductVO[] productList) {
		this.productList = productList;
	}
	
	
}
