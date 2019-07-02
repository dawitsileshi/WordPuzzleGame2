package com.example.wordpuzzlegame;

import android.content.Context;

import com.example.wordpuzzlegame.model.DataSource;

//import static com.example.wordpuzzlegame.model.ItemTables.AMHARIC_EXAMPLE;
//import static com.example.wordpuzzlegame.model.ItemTables.AMHARIC_WORD;
//import static com.example.wordpuzzlegame.model.ItemTables.ENGLISH_EXAMPLE;
//import static com.example.wordpuzzlegame.model.ItemTables.ENGLISH_WORD;
//import static com.example.wordpuzzlegame.model.ItemTables.ID_ENGLISH;
//import static com.example.wordpuzzlegame.model.ItemTables.IMAGE;
//import static com.example.wordpuzzlegame.model.ItemTables.RELATIONSHIP_ID;
//import static com.example.wordpuzzlegame.model.ItemTables.TIGIRIGNA_EXAMPLE;
//import static com.example.wordpuzzlegame.model.ItemTables.TIGIRIGNA_WORD;

public class Words {

    private long id;
    private String word;
    private int word_code;
    private long lang_id;

    private Context context;
    private DataSource dataSource;

    public Words(long id, String word, int word_code, long lang_id) {
        this.id = id;
        this.word = word;
        this.word_code = word_code;
        this.lang_id = lang_id;
    }

    public Words(Context context) {
        this.dataSource = new DataSource(context);
    }

//    private long insertData(ArrayList<Words> words) {
//
//        for (int i = 0; i < words.size(); i++) {
//            Words word = words.get(i);
//            long lang_id = word.getLang_id();
//
//
//        }
//
//        return dataSource.createItem(contentValues, )
//
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWord_code() {
        return word_code;
    }

    public void setWord_code(int word_code) {
        this.word_code = word_code;
    }

    public long getLang_id() {
        return lang_id;
    }

    public void setLang_id(long lang_id) {
        this.lang_id = lang_id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //    private String englishWord;
//    private String englishSent;
//    private String amharicWord;
//    private String amharicSent;
//    private String tigrignaWord;
//    private String tigrignaSent;
//    private byte[] image;
//
//    private DataSource dataSource;
//    private Context context;
//
//    public Words(Context context) {
//
//        this.context = context;
//        dataSource = new DataSource(context);
//
//    }
//
//    public Words() {
//    }
//
//    public Words(long id, String englishWord, String englishSent, String amharicWord, String amharicSent, String tigrignaWord, String tigrignaSent, byte[] image) {
//        this.id = id;
//        this.englishWord = englishWord;
//        this.englishSent = englishSent;
//        this.amharicWord = amharicWord;
//        this.amharicSent = amharicSent;
//        this.tigrignaWord = tigrignaWord;
//        this.tigrignaSent = tigrignaSent;
//        this.image = image;
//    }
//
//    public Words(long id, String englishWord, String englishSent, byte[] image) {
//        this.id = id;
//        this.englishWord = englishWord;
//        this.englishSent = englishSent;
//        this.image = image;
//    }
//
//    public Words(String amharicWord, String amharicSent, byte[] image) {
//        this.amharicWord = amharicWord;
//        this.amharicSent = amharicSent;
//        this.image = image;
//    }
//
//    public Words(long id, String amharicWord, String amharicSent) {
//        this.id = id;
//        this.amharicWord = amharicWord;
//        this.amharicSent = amharicSent;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getEnglishWord() {
//        return englishWord;
//    }
//
//    public void setEnglishWord(String englishWord) {
//        this.englishWord = englishWord;
//    }
//
//    public String getEnglishSent() {
//        return englishSent;
//    }
//
//    public void setEnglishSent(String englishSent) {
//        this.englishSent = englishSent;
//    }
//
//    public String getAmharicWord() {
//        return amharicWord;
//    }
//
//    public void setAmharicWord(String amharicWord) {
//        this.amharicWord = amharicWord;
//    }
//
//    public String getAmharicSent() {
//        return amharicSent;
//    }
//
//    public void setAmharicSent(String amharicSent) {
//        this.amharicSent = amharicSent;
//    }
//
//    public String getTigrignaWord() {
//        return tigrignaWord;
//    }
//
//    public void setTigrignaWord(String tigrignaWord) {
//        this.tigrignaWord = tigrignaWord;
//    }
//
//    public String getTigrignaSent() {
//        return tigrignaSent;
//    }
//
//    public void setTigrignaSent(String tigrignaSent) {
//        this.tigrignaSent = tigrignaSent;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public DataSource getDataSource() {
//        return dataSource;
//    }
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public ArrayList<Words> wordArrayList(String lang) {
//
//        Cursor cursor = dataSource.getAllElements(lang);
//
//        ArrayList<Words> words = new ArrayList<>();
//
//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()) {
//
//            long id = cursor.getLong(0);
//            String word = cursor.getString(1);
//            String word_definition = cursor.getString(2);
//            String word_example = cursor.getString(2);
//
//            Words word1 = new Words(id, word, word_example);
//            words.add(word1);
//            cursor.moveToNext();
//        }
//
//        cursor.close();
//
//        return words;
//
//    }
//
//    public void saveWords(ArrayList<Words> words) {
//
//        ArrayList<Long> ids = new ArrayList<>();
//        ArrayList<String> tableNames = new ArrayList<>();
//        tableNames.add(ItemTables.ENGLISH_WORD);
//        tableNames.add(ItemTables.ENGLISH_EXAMPLE);
//        tableNames.add(ItemTables.TABLE_ENGLISH);
//        tableNames.add(ItemTables.AMHARIC_WORD);
//        tableNames.add(ItemTables.AMHARIC_EXAMPLE);
//        tableNames.add(ItemTables.TABLE_AMHARIC);
//        tableNames.add(ItemTables.TIGIRIGNA_WORD);
//        tableNames.add(ItemTables.TIGIRIGNA_EXAMPLE);
//        tableNames.add(ItemTables.TABLE_TIGIRIGNA);
//
//        int tableIndex = 0;
//        for (int i = 0; i < words.size(); i++) {
//
//            Words word = words.get(i);
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(tableNames.get(tableIndex), word.getEnglishWord());
//            tableIndex++;
//            contentValues.put(tableNames.get(tableIndex), word.getEnglishSent());
//            tableIndex++;
//
//            long id = dataSource.createItem(contentValues, tableNames.get(tableIndex));
//            Log.i(tableNames.get(tableIndex), String.valueOf(id));
//            ids.add(id);
//            tableIndex++;
//
//
//        }
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID_ENGLISH, ids.get(0));
//        contentValues.put(ItemTables.ID_AMHARIC, ids.get(1));
//        contentValues.put(ItemTables.ID_TIGIRIGNA, ids.get(2));
//        long id = dataSource.createItem(contentValues, ItemTables.TABLE_RELATIONSHIP);
//        Log.i(ItemTables.TABLE_RELATIONSHIP, String.valueOf(id));
//
//    }
//
//    public ArrayList<Words> allListed() {
//
//        Cursor relationship = dataSource.getAll();
//        Cursor englishWords = dataSource.getAllElements("ENGLISH");
//        Cursor amharicWords = dataSource.getAllElements("AMHARIC");
//        Cursor tigrignaWords = dataSource.getAllElements("TIGRIGNA");
//
//        ArrayList<Words> words = new ArrayList<>();
//
//        relationship.moveToFirst();
//        englishWords.moveToFirst();
//        amharicWords.moveToFirst();
//        tigrignaWords.moveToFirst();
//
//        while(!relationship.isAfterLast()) {
//
//            long id = relationship.getLong(relationship.getColumnIndex(RELATIONSHIP_ID));
//            String englishWord = englishWords.getString(englishWords.getColumnIndex(ENGLISH_WORD));
//            String englishSent = englishWords.getString(englishWords.getColumnIndex(ENGLISH_EXAMPLE));
//            String amharicWord = englishWords.getString(amharicWords.getColumnIndex(AMHARIC_WORD));
//            String amharicSent = englishWords.getString(amharicWords.getColumnIndex(AMHARIC_EXAMPLE));
//            String tigrignaWord = englishWords.getString(tigrignaWords.getColumnIndex(TIGIRIGNA_WORD));
//            String tigrignaSent = englishWords.getString(tigrignaWords.getColumnIndex(TIGIRIGNA_EXAMPLE));
//            byte[] image = relationship.getBlob(relationship.getColumnIndex(IMAGE));
//            Words aWord = new Words(id, englishWord, englishSent, amharicWord, amharicSent, tigrignaWord, tigrignaSent, image);
//            words.add(aWord);
//            relationship.moveToNext();
//            englishWords.moveToNext();
//            amharicWords.moveToNext();
//            tigrignaWords.moveToNext();
//
//        }
//
//        relationship.close();
//        englishWords.close();
//        amharicWords.close();
//        tigrignaWords.close();
//
//        return words;
//
//    }
}
//