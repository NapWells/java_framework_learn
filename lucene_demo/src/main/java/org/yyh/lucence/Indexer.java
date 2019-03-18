package org.yyh.lucence;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.yyh.constant.LuceneConstants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lhy
 * @description:
 * @date: 2019-03-05 11:13
 **/
public class Indexer {
    private IndexWriter indexWriter;
    private static AtomicInteger count = new AtomicInteger(0);

    public Indexer(String indexDirectoryPath) throws IOException{
        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
        IndexWriterConfig config = new IndexWriterConfig(new SmartChineseAnalyzer());
        indexWriter = new IndexWriter(indexDirectory,config );
    }

    public void close() throws IOException {
        indexWriter.close();
    }

    private Document getFileDocument(File file) throws IOException{
        Document document = new Document();
        IndexableField id = new StringField(LuceneConstants.ID,String.valueOf(count.getAndIncrement()),Field.Store.YES);
        IndexableField content = new TextField(LuceneConstants.CONTENTS, FileUtils.readFileToString(file, Charset.forName("GBK")),Field.Store.YES);
        IndexableField filename = new StringField(LuceneConstants.FILE_NAME,file.getName(),Field.Store.YES);
        IndexableField filepath = new StringField(LuceneConstants.FILE_PATH,file.getPath(),Field.Store.YES);
        document.add(id);
        document.add(content);
        document.add(filename);
        document.add(filepath);
        return document;
    }

    public void createIndex(File file) throws IOException {
        if (!file.exists() || !file.getName().endsWith(".txt")){
            return;
        }
        Document document = getFileDocument(file);
        indexWriter.addDocument(document);
    }

    public void createIndex(String filePath) throws IOException{
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("file path is not right, file : " + file.getAbsolutePath());
            return;
        }
        if (file.isDirectory()){
            File[] files = file.listFiles(filename -> !filename.getName().contains("index"));
            if ((files != null ? files.length : 0) == 0){
                System.out.println("director is empty, directory : " + filePath);
                return;
            }
            for (File file1 : files){
                createIndex(file1.getAbsolutePath());
            }
        }
        createIndex(file);
    }





}
