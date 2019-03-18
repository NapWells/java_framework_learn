package src;

import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;
import org.yyh.lucence.Indexer;
import org.yyh.lucence.Searcher;
import org.yyh.model.ResultModel;

import java.io.IOException;

/**
 * @author: lhy
 * @description:
 * @date: 2019-03-06 14:51
 **/
public class LuceneTest {
    private static final String indexPath = "C:\\Users\\42515\\Desktop\\lucene-test\\index";
    private static final String dataPath = "C:\\Users\\42515\\Desktop\\lucene-test";


    @Test
    public void testCreateIndex() throws IOException {
        Indexer indexer = new Indexer(indexPath);
        indexer.createIndex(dataPath);
        indexer.close();
    }

    @Test
    public void testSearch() throws IOException, ParseException {
        Searcher searcher = new Searcher(indexPath);
        ResultModel resultModel = searcher.bywWord("Java");
        System.out.println(JSONObject.toJSONString(resultModel));
    }
}
