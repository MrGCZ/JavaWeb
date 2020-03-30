package com.gcz;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {

    @Test
    public void test1()  {

        // 1.创建一个SaxReader输入流，去读取 xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();

        Document document = null;
        try {
            //在Junit测试中，相对路径是从模块名开始算
            document = saxReader.read("src/books.xml");

            //2.通过Document对象获取根元素
            Element rootElement = document.getRootElement();

            System.out.println(rootElement);
            //3.element() elements()都是通过标签名返回子元素
            List<Element> books= rootElement.elements("book");

            //4.遍历，处理每个book标签，转化为book类
            for(Element book : books){
                //把标签对象转换为标签字符串
                //System.out.println(book.asXML());

                //方法一：先获取name标签，再获取其内容
                Element nameElement = book.element("name");
                String nameText = nameElement.getText();

                //方法二：直接获取指定标签名的文本的内容
                String priceText = book.elementText("price");
                String authorText = book.elementText("author");

                String snValue = book.attributeValue("sn");

                System.out.println(new Book(snValue,nameText,Double.parseDouble(priceText),authorText));
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }



    }

}
