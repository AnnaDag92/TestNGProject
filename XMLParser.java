import org.testng.annotations.DataProvider;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class XMLParser {

    public static Object[][] getTestData(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList testNodes = doc.getElementsByTagName("test");
            int testDataCount = testNodes.getLength();
            Object[][] testData = new Object[testDataCount][2];

            for (int i = 0; i < testDataCount; i++) {
                Node testNode = testNodes.item(i);
                if (testNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element testElement = (Element) testNode;
                    String username = testElement.getElementsByTagName("parameter")
                            .item(0)
                            .getAttributes()
                            .getNamedItem("value")
                            .getTextContent();
                    String password = testElement.getElementsByTagName("parameter")
                            .item(1)
                            .getAttributes()
                            .getNamedItem("value")
                            .getTextContent();
                    testData[i][0] = username;
                    testData[i][1] = password;
                }
            }

            return testData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return getTestData("C:/Users/AnnaD/Desktop/LoginData.xml");
    }
}
