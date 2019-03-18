package org.yyh.lucence;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.yyh.constant.LuceneConstants;
import org.yyh.model.ItemModel;
import org.yyh.model.ResultModel;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhy
 * @description:
 * @date: 2019-03-06 11:42
 **/
public class Searcher {
    private IndexSearcher indexSearcher;

    public Searcher(String path) throws IOException {
        Directory d = FSDirectory.open(Paths.get(path));
        // 通过indexSearcher去检索索引目录
        IndexReader indexReader = DirectoryReader.open(d);
        indexSearcher = new IndexSearcher(indexReader);
    }

    private ResultModel search(Query query) throws IOException {
        TopDocs topDocs = indexSearcher.search(query,LuceneConstants.MAX_SEARCH);

        ResultModel resultModel = new ResultModel();
        resultModel.setMaxScore(topDocs.getMaxScore());
        resultModel.setTotalHits(topDocs.totalHits);

        List<ItemModel> resultModelList = new ArrayList<>();
        resultModel.setItemModels(resultModelList);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs){
            ItemModel itemModel = new ItemModel();
            itemModel.setScore(scoreDoc.score);
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            itemModel.setId(Integer.valueOf(document.get(LuceneConstants.ID)));
            itemModel.setFilename(document.get(LuceneConstants.FILE_NAME));
            itemModel.setFilepath(document.get(LuceneConstants.FILE_PATH));
            itemModel.setContent(document.get(LuceneConstants.CONTENTS));
            resultModelList.add(itemModel);
        }
        return resultModel;
    }


    public ResultModel byId(Integer id) throws IOException {
        if (id == null ){
            return null;
        }
        Query query = new TermQuery(new Term(LuceneConstants.ID,String.valueOf(id)));
        return search(query);
    }

    public ResultModel byFileName(String name) throws IOException {
        if ("".equals(name) || null == name){
            return null;
        }
        Query query = new TermQuery(new Term(LuceneConstants.FILE_NAME,name));
        return search(query);
    }

    public ResultModel bywWord(String word) throws IOException, ParseException {
        if ("".equals(word) || null == word){
            return null;
        }
        QueryParser queryParser = new QueryParser(LuceneConstants.CONTENTS,new SmartChineseAnalyzer());
        Query query = queryParser.parse(word);
        return search(query);
    }


}
